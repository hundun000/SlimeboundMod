package slimeboundclassic.cards;



import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import slimeboundclassic.SlimeboundMod;
import slimeboundclassic.actions.SlimeSpawnAction;

public class SplitBronze extends AbstractSlimeboundCard {
    public static final String ID = "SlimeboundClassic:SplitBronze";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/splitbronze.png";

    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings;

    private static final int COST = 0;
    private static final int BLOCK = 5;
    private static final int UPGRADE_BONUS = 3;

    public SplitBronze() {
        super(ID, NAME, slimeboundclassic.SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);
        tags.add(SlimeboundMod.STUDY_AUTOMATON);
        tags.add(SlimeboundMod.STUDY);

        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 1;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        int bonus = 0;
        if (upgraded) bonus = this.magicNumber;
        AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimeboundclassic.orbs.BronzeSlime(), false, true,bonus,bonus));

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    public AbstractCard makeCopy() {
        return new SplitBronze();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();


        }
    }
}


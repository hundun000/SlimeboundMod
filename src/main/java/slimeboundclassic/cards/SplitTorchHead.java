package slimeboundclassic.cards;



import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import slimeboundclassic.SlimeboundMod;
import slimeboundclassic.actions.SlimeSpawnAction;


public class SplitTorchHead extends AbstractSlimeboundCard {
    public static final String ID = "SlimeboundClassic:SplitTorchHead";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/splittorchhead.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 0;
    private static final int BLOCK = 5;
    private static final int UPGRADE_BONUS = 3;
    private static final CardStrings cardStrings;

    public SplitTorchHead() {
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);
        tags.add(SlimeboundMod.STUDY_COLLECTOR);
        tags.add(SlimeboundMod.STUDY);
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;


    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int bonus = 0;
        if (upgraded) bonus = this.magicNumber;
        AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimeboundclassic.orbs.TorchHeadSlime(), false, true,bonus,0));

    }

    public AbstractCard makeCopy() {
        return new SplitTorchHead();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();


        }
    }
}


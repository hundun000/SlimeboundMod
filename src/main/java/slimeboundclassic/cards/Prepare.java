package slimeboundclassic.cards;



import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import slimeboundclassic.SlimeboundMod;
import slimeboundclassic.patches.AbstractCardEnum;
import slimeboundclassic.powers.EnergizedSlimeboundPower;


public class Prepare extends AbstractSlimeboundCard {
    public static final String ID = "SlimeboundClassic:Prepare";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/prepare.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings;

    private static final int COST = 2;
    private static final int BLOCK = 5;
    private static final int UPGRADE_BONUS = 3;

    public Prepare() {
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);

        this.baseBlock = 8;
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EnergizedSlimeboundPower(p, p, this.magicNumber), this.magicNumber));
      // if (upgraded) AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RetainCardsOneTurnPower(p,2),2));

    }

    public AbstractCard makeCopy() {
        return new Prepare();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(3);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
}



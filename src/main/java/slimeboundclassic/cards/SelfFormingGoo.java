package slimeboundclassic.cards;



import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import slimeboundclassic.SlimeboundMod;
import slimeboundclassic.patches.AbstractCardEnum;
import slimeboundclassic.powers.SelfFormingGooPower;


public class SelfFormingGoo extends AbstractSlimeboundCard {
    public static final String ID = "SlimeboundClassic:SelfFormingGoo";

    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/straygoop.png";
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;

    public SelfFormingGoo() {
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        this.slimed = this.baseSlimed = 2;
        upgradeSlimed(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SelfFormingGooPower(p, p, 1), 1));

    }

    public AbstractCard makeCopy() {
        return new SelfFormingGoo();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(0);


        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
}


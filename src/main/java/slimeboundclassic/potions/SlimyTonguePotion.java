package slimeboundclassic.potions;


import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import slimeboundclassic.powers.AcidTonguePowerUpgraded;


public class SlimyTonguePotion extends CustomPotion {
    public static final String POTION_ID = "SlimeboundClassic:SlimyTonguePotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;


    public SlimyTonguePotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.SNECKO, PotionColor.WEAK);
        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1]);
        this.isThrown = false;
        this.targetRequired = false;
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
       AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AcidTonguePowerUpgraded(AbstractDungeon.player, AbstractDungeon.player, this.potency), this.potency));

    }


    public CustomPotion makeCopy() {
        return new SlimyTonguePotion();
    }

    public int getPotency(int ascensionLevel) {
        return 2;
    }
}



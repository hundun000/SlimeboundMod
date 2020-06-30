package slimeboundclassic.actions;

import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import slimeboundclassic.SlimeboundMod;
import slimeboundclassic.orbs.*;
import slimeboundclassic.patches.SlimeboundEnum;
import slimeboundclassic.powers.DuplicatedFormNoHealPower;


public class SlimeSpawnAction extends AbstractGameAction {
    private AbstractOrb orbType;
    private boolean SelfDamage = true;
    private boolean upgraded = false;
    private boolean random = false;
    private int currentAmount;
    private int upgradedamount;
    private int count;

    private int bonusUniqueFocus;
    private int bonusSecondary;

    public SlimeSpawnAction(AbstractOrb newOrbType, boolean upgraded, boolean SelfDamage, int bonusUniqueFocus, int bonusSecondary) {

        this(newOrbType,upgraded,SelfDamage);

        this.bonusUniqueFocus = bonusUniqueFocus;
        this.bonusSecondary = bonusSecondary;

    }


    public SlimeSpawnAction(AbstractOrb newOrbType, boolean upgraded, boolean SelfDamage) {

        this.duration = Settings.ACTION_DUR_FAST;
        this.random = random;

        if (newOrbType != null) {
            this.orbType = newOrbType;
            SpawnedSlime s = (SpawnedSlime) newOrbType;

            this.upgradedamount = s.upgradedInitialBoost;
        }

        this.upgraded = upgraded;
        this.SelfDamage = SelfDamage;
        this.currentAmount = 3;


        this.count = count;


    }


    public void update() {
        //SlimeboundMod.logger.info("Starting slime spawn action");

        if (AbstractDungeon.player.maxOrbs > 0) {

            int currentHealth = AbstractDungeon.player.currentHealth;

            if (TempHPField.tempHp.get(AbstractDungeon.player) != null)
            currentHealth += TempHPField.tempHp.get(AbstractDungeon.player);

            /*
            int maxFortitudes = 0;
            if (AbstractDungeon.player.hasPower("FirmFortitudePower"))
                maxFortitudes = AbstractDungeon.player.getPower("FirmFortitudePower").amount;
            if (AbstractDungeon.player.hasPower("Buffer"))
                maxFortitudes = maxFortitudes + AbstractDungeon.player.getPower("Buffer").amount;


            int usedFortitudes = 0;
            */


            if (SelfDamage) {

                if (currentAmount >= currentHealth) {
                    AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 1.0F, "Need... health...", true));
                    this.isDone = true;
                    return;
                }
                if (currentAmount > 0) {
                    //SlimeboundMod.logger.info("Losing HP" + this.currentAmount);


                    if (AbstractDungeon.player.chosenClass == SlimeboundEnum.SLIMEBOUND) {
                        SlimeboundMod.disabledStrikeVFX = true;
                    }
                    //SlimeboundMod.logger.info("No buffer, proceeding");
                    AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, currentAmount, DamageInfo.DamageType.HP_LOSS));


                    //AbstractDungeon.player.damageFlash = true;
                    //AbstractDungeon.player.damageFlashFrames = 4;

                    //SlimeboundMod.logger.info("Reducing max HP");
                    int MaxHPActuallyLost = 3;
                    if (AbstractDungeon.player.maxHealth <= 3) {
                        MaxHPActuallyLost = AbstractDungeon.player.maxHealth - 1;
                    }

                    if (MaxHPActuallyLost > 0)
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DuplicatedFormNoHealPower(AbstractDungeon.player, AbstractDungeon.player, MaxHPActuallyLost), MaxHPActuallyLost));

                }

                }
                // AbstractDungeon.effectsQueue.add(new SlimeDripsEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 0));

                //SlimeboundMod.logger.info("Channeling slime orb");
                if (this.random || this.orbType == null) {

                    //OLD RANDOM, NOW UNUSED, CLEAN UP LATER

                } else {

                    if (this.bonusUniqueFocus > 0){
                        ((SpawnedSlime)this.orbType).applyUniqueFocus(bonusUniqueFocus);
                    }
                    if (this.bonusSecondary > 0){
                        ((SpawnedSlime)this.orbType).applySecondaryBonus(bonusSecondary);
                    }

                    AbstractDungeon.player.channelOrb(this.orbType);
                }


                if (this.upgraded) {
                    SlimeboundMod.bumpnextlime = true;

                    AbstractDungeon.actionManager.addToTop(new SlimeBuffUpgraded(this.upgradedamount, SlimeboundMod.mostRecentSlime));
                }
                tickDuration();


                this.isDone = true;


            }
            this.isDone = true;
        }

}




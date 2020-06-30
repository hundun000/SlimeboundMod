package slimeboundclassic.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import slimeboundclassic.actions.SlimeSpawnAction;
import slimeboundclassic.characters.SlimeboundCharacter;
import slimeboundclassic.orbs.ShieldSlime;

public class MaxSlimesRelic extends CustomRelic {
    public static final String ID = "SlimeboundClassic:MaxSlimesRelic";
    public static final String IMG_PATH = "relics/slimeplushie.png";
    public static final String IMG_PATH_LARGE = "relics/slimeplushieLarge.png";
    public static final String OUTLINE_IMG_PATH = "relics/slimeplushieOutline.png";
    private static final int HP_PER_CARD = 1;

    public MaxSlimesRelic() {
        super(ID, new Texture(slimeboundclassic.SlimeboundMod.getResourcePath(IMG_PATH)), new Texture(slimeboundclassic.SlimeboundMod.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.UNCOMMON, LandingSound.FLAT);
        this.largeImg = ImageMaster.loadImage(slimeboundclassic.SlimeboundMod.getResourcePath(IMG_PATH_LARGE));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStartPreDraw() {
        this.flash();
        com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction(1));
        AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new ShieldSlime(), false, true));
    }

    public boolean canSpawn() {
        return AbstractDungeon.player instanceof SlimeboundCharacter;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MaxSlimesRelic();
    }

}
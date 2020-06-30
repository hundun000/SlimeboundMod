package slimeboundclassic.ui;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.vfx.campfire.CampfireTokeEffect;
import slimeboundclassic.SlimeboundMod;


public class ScrapBonfireOption extends AbstractCampfireOption
{
    private static final RelicStrings relicStrings;


    public static final String[] DESCRIPTIONS;


    //private ArrayList<String> idleMessages;
    public ScrapBonfireOption(boolean active) {
        //this.idleMessages = new ArrayList();
        //this.idleMessages.add("~Treasure!");
        //this.idleMessages.add("~Feed ~me!");
        //this.idleMessages.add("~Give ~shiny!");
        //this.idleMessages.add("~More ~scrap!");
        this.label = "Scrap";
        //this.description = "Remove a card from your deck. Increase the Scrap Ooze's damage by 2.";
        this.usable = active;
        if (active) {
            this.description = DESCRIPTIONS[1];
            this.img = ImageMaster.loadImage("SlimeboundClassicImages/ui/scrapcampfire.png");

        } else {
            this.description = DESCRIPTIONS[2];
            this.img = ImageMaster.loadImage("SlimeboundClassicImages/ui/scrapcampfiredisabled.png");
        }
    }

    @Override
    public void useOption() {
        if (this.usable) {
            SlimeboundMod.scrapping = true;
            AbstractDungeon.effectList.add(new CampfireTokeEffect());


        }
    }
    static {
        relicStrings = CardCrawlGame.languagePack.getRelicStrings("SlimeboundClassic:ScrapOozeRelic");
        DESCRIPTIONS = relicStrings.DESCRIPTIONS;

    }
}

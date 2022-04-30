package theGartic.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import theGartic.vfx.InariGiftShowCardAndAddToDrawPileEffect;

import java.util.ArrayList;
import java.util.Iterator;

/*
    If you are reading this code and thinking "hey, this looks familiar"
    CONGRATS! Yes, it is, I used the DiscoverAction.class file as a starting point.
    Levender
 */
public class InariGiftAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private AbstractCard.CardType cardType = null;

    public InariGiftAction(int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
    }

    public void update() {
        ArrayList generatedCards;
        generatedCards = this.generateColorlessCardChoices();

        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], this.cardType != null);
            this.tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    AbstractCard disCard2 = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
                        disCard.upgrade();
                        disCard2.upgrade();
                    }
                    if (!disCard.exhaust){
                        setCardToHaveExhaust(disCard);
                    }
                    if (!disCard2.exhaust){
                        setCardToHaveExhaust(disCard2);
                    }


                    disCard.current_x = -1000.0F * Settings.xScale;
                    disCard2.current_x = -1000.0F * Settings.xScale + AbstractCard.IMG_HEIGHT_S;
                    if (this.amount == 1) {
                        AbstractDungeon.effectList.add(new InariGiftShowCardAndAddToDrawPileEffect(disCard, false, false));
                        disCard2 = null;
                    } else {
                        AbstractDungeon.effectList.add(new InariGiftShowCardAndAddToDrawPileEffect(disCard, false, false));
                        AbstractDungeon.effectList.add(new InariGiftShowCardAndAddToDrawPileEffect(disCard2, false, false));
                    }

                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                this.retrieveCard = true;
            }

            this.tickDuration();
        }
    }

    private ArrayList<AbstractCard> generateColorlessCardChoices() {
        ArrayList cardChoices = new ArrayList();

        while(cardChoices.size() != 3) {
            boolean dupe = false;
            AbstractCard tmp = AbstractDungeon.returnTrulyRandomColorlessCardInCombat();


            Iterator var4 = cardChoices.iterator();

            while(var4.hasNext()) {
                AbstractCard c = (AbstractCard)var4.next();
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }

            if (!dupe) {
                AbstractCard temp = tmp.makeCopy();
                if (!temp.exhaust){
                    setCardToHaveExhaust(temp);
                }
                cardChoices.add(temp);
            }
        }

        return cardChoices;
    }

    private static void setCardToHaveExhaust(AbstractCard the_card) {
        if (the_card.type != AbstractCard.CardType.POWER){
            the_card.exhaust = true;

            String upper_cased_exhaust = GameDictionary.EXHAUST.NAMES[0].
                    substring(0, 1).toUpperCase() +
                    GameDictionary.EXHAUST.NAMES[0].substring(1);

            if (Settings.language == Settings.GameLanguage.ZHS)
                upper_cased_exhaust = "" + upper_cased_exhaust;

            the_card.rawDescription += " NL " +
                    upper_cased_exhaust + ".";

            the_card.initializeDescription();
        }
    }
}

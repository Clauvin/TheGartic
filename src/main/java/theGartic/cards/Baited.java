package theGartic.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.cards.fish.AbstractFishCard;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class Baited extends AbstractEasyCard {
    public final static String ID = makeID("Baited");

    public Baited() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 16;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        makeInHand(AbstractFishCard.returnRandomFish());
        if (m.getIntentBaseDmg() > -1) {
            for (int i = 0; i < magicNumber; i++) {
                makeInHand(AbstractFishCard.returnRandomFish());
            }
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}
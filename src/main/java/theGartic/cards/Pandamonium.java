package theGartic.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theGartic.powers.PandamoniumPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.*;

public class Pandamonium extends AbstractEasyCard {
    public final static String ID = makeID(Pandamonium.class.getSimpleName());
    public final static int MAGIC = 10;
    public final static int UPGRADE_MAGIC = 5;
    private final static int COST = 3;

    public Pandamonium() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PandamoniumPower(1, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}
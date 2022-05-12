package theGartic.cards.InariModal;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import theGartic.cards.AngelicGlide;
import theGartic.cards.EasyModalChoiceCard;
import theGartic.powers.InariDashPower;

import static theGartic.GarticMod.makeID;
import static theGartic.util.Wiz.atb;

public class InariDash extends EasyModalChoiceCard {

    public static final String ID = makeID(InariDash.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    public InariDash(){
        super(NAME, DESCRIPTION, () -> {
            AbstractPlayer p = AbstractDungeon.player;
            atb(new ApplyPowerAction(p, p, new InariDashPower(1), 1));
        });

        baseMagicNumber = magicNumber;

        if (baseMagicNumber == 1) {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
        } else {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[1];
        }
        initializeDescription();
    }

    public InariDash(int magicNumber) {
        super(NAME, DESCRIPTION, () -> {
            AbstractPlayer p = AbstractDungeon.player;
            atb(new ApplyPowerAction(p, p, new InariDashPower(magicNumber), 1));
        });

        baseMagicNumber = magicNumber;

        if (baseMagicNumber == 1) {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
        } else {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[1];
        }
        initializeDescription();
    }
}

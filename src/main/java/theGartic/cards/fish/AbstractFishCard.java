package theGartic.cards.fish;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theGartic.GarticMod;
import theGartic.cards.AbstractEasyCard;
import basemod.BaseMod;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

public abstract class AbstractFishCard extends AbstractEasyCard {
    public AbstractFishCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }
    public AbstractFishCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
    }

    public static LinkedHashMap<AbstractFishCard, Integer> weightedFishList;

    public static AbstractCard returnRandomFish() {
        if (weightedFishList == null) {
            weightedFishList = new LinkedHashMap<>();
            weightedFishList.put(new Guppy(),       40);
            weightedFishList.put(new Piranha(),     21);
            weightedFishList.put(new Shark(),       10);
            weightedFishList.put(new Clownfish(),   10);
            weightedFishList.put(new Octopus(),     8);
            weightedFishList.put(new Orca(),        2);
            weightedFishList.put(new Hammerhead(),  2);
            weightedFishList.put(new Qwilfish(),    2);
            weightedFishList.put(new Siren(),       2);
            weightedFishList.put(new Boot(),        2);
            weightedFishList.put(new SeaMonster(),  1);
        }

        int fishRoll = AbstractDungeon.cardRandomRng.random( 1,
                weightedFishList.keySet().stream()
                        .mapToInt(f -> weightedFishList.get(f))
                        .sum()
        );

        for (AbstractFishCard fishy: weightedFishList.keySet()) {
            fishRoll -= weightedFishList.get(fishy);
            if (fishRoll <= 0)
                return fishy.makeCopy();
        }

        return new Madness();
    }
    
    @Override
    public List<String> getCardDescriptors() {
        List<String> tags = new ArrayList<>();
        tags.add(BaseMod.getKeywordTitle("garticmod:fish"));
        return tags;
    }
}

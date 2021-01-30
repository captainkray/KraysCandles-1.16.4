package com.captainkray.krayscandles.ritual;

import com.captainkray.krayscandles.init.InitItems;
import com.captainkray.krayscandles.init.InitSounds;
import net.minecraft.util.SoundEvents;

public class RitualStructureTypes {

    public static final RitualStructure ESSENCE = new RitualStructure("Essence", SoundEvents.BLOCK_CONDUIT_ACTIVATE);
    public static final RitualStructure RUNE = new RitualStructure("Rune", InitSounds.RUNE_RITUAL);
    public static final RitualStructure CANDLE = new RitualStructure("Candle", SoundEvents.BLOCK_CONDUIT_ACTIVATE);
    public static final RitualStructure WAND = new RitualStructure("Wand", InitSounds.WAND_RITUAL);
    public static final RitualStructure WRAITH = new RitualStructure("Wraith", InitSounds.WRAITH_RITUAL);

    public static void init() {

        ESSENCE.addRitualBlock(new RitualBlockCandle(InitItems.CANDLE_WAX_BEE.get()));
        ESSENCE.addSymmetricalRitualBlock(new RitualBlockCandleSoy(1, 0, 1));

        RUNE.addRitualBlock(new RitualBlock(InitItems.STATUE.get()));
        RUNE.addSymmetricalRitualBlock(new RitualBlockCandleSoy(2, 0, 2));
        RUNE.addSymmetricalRitualBlock(new RitualBlockAltar(2, 0, 0));

        CANDLE.addRitualBlock(new RitualBlockCandleSoy());
        CANDLE.addSymmetricalRitualBlock(new RitualBlockAltar(3, 0, 0));
        CANDLE.addSymmetricalRitualBlock(new RitualBlockAltar(2, 0, 2));
        CANDLE.addSymmetricalRitualBlock(new RitualBlockCandleSoy(3, 0, 1));
        CANDLE.addSymmetricalRitualBlock(new RitualBlockCandleSoy(1, 0, 3));

        WAND.addRitualBlock(new RitualBlockAltar());
        WAND.addSymmetricalRitualBlock(new RitualBlockAltar(2, 0, 0));
        WAND.addSymmetricalRitualBlock(new RitualBlockCandleSoy(1, 0, 1));

        WRAITH.addRitualBlock(new RitualBlockCandle(InitItems.CANDLE_CURSED.get()));
        WRAITH.addRitualBlock(new RitualBlockCandleSoy(0, 0, -2));
        WRAITH.addRitualBlock(new RitualBlockCandleSoy( 0, 0, 2));
        WRAITH.addRitualBlock(new RitualBlockCandleSoy(2, 0, -1));
        WRAITH.addRitualBlock(new RitualBlockCandleSoy(-2, 0, -1));
        WRAITH.addRitualBlock(new RitualBlockCandleSoy(2, 0, 1));
        WRAITH.addRitualBlock(new RitualBlockCandleSoy(-2, 0, 1));
    }
}

package com.captainkray.krayscandles.item;

import com.captainkray.krayscandles.init.InitSounds;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvents;

public class ItemMusicDiscChunk extends MusicDiscItem {
    public ItemMusicDiscChunk() {
        super(1, InitSounds.MUSIC_DISC_CHUNK.get(), new Item.Properties().maxStackSize(1));

    }

}

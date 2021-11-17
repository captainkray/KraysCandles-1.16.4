package com.tm.krayscandles.integration.jei;

import com.tm.krayscandles.main.KCReference;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class JeiIntegrationPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid () {
        return new ResourceLocation(KCReference.MOD_ID, "main");
    }

    @Override
    public void registerGuiHandlers (IGuiHandlerRegistration registration) {

    }
}

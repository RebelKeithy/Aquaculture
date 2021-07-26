/*package com.teammetallurgy.aquaculture.integration.jei;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.integration.jei.recipes.FilletKnifeRecipeMaker;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

@JeiPlugin
public class JEIIntegration implements IModPlugin {

    @Override
    @Nonnull
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Aquaculture.MOD_ID, "jei_support");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(FilletKnifeRecipeMaker.createFilletKnifeRecipes(), VanillaRecipeCategoryUid.CRAFTING);
    }
}*/
/*package com.teammetallurgy.aquaculture.client;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.handlers.Config;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.DefaultGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class GuiFactory extends DefaultGuiFactory {

    public GuiFactory() {
        super(Aquaculture.MOD_ID, GuiConfig.getAbridgedConfigPath(Config.config.toString()));
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new GuiConfig(parentScreen, getConfigElements(), Aquaculture.MOD_ID, false, false, title);
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<>();

        List<IConfigElement> basic = new ConfigElement(Config.config.getCategory(Config.categoryBasic)).getChildElements();
        List<IConfigElement> fishRarity = new ConfigElement(Config.config.getCategory(Config.categoryFishRarity)).getChildElements();
        List<IConfigElement> junkRarity = new ConfigElement(Config.config.getCategory(Config.categoryJunkRarity)).getChildElements();

        list.add(new DummyConfigElement.DummyCategoryElement("Basic options", String.valueOf(new ResourceLocation(Aquaculture.MOD_ID, "config.category.basic")), basic));
        list.add(new DummyConfigElement.DummyCategoryElement("Fish rarity", String.valueOf(new ResourceLocation(Aquaculture.MOD_ID, "config.category.fishRarity")), fishRarity));
        list.add(new DummyConfigElement.DummyCategoryElement("Junk rarity", String.valueOf(new ResourceLocation(Aquaculture.MOD_ID, "config.category.junkRarity")), junkRarity));

        return list;
    }
}*/
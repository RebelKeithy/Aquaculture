package com.teammetallurgy.aquaculture;

import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.block.WormFarmBlock;
import com.teammetallurgy.aquaculture.client.ClientHandler;
import com.teammetallurgy.aquaculture.init.AquaEntities;
import com.teammetallurgy.aquaculture.init.AquaItems;
import com.teammetallurgy.aquaculture.init.FishRegistry;
import com.teammetallurgy.aquaculture.item.crafting.FishFilletRecipe;
import com.teammetallurgy.aquaculture.loot.BiomeTagCheck;
import com.teammetallurgy.aquaculture.loot.FishReadFromJson;
import com.teammetallurgy.aquaculture.loot.FishWeightHandler;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import cpw.mods.modlauncher.Environment;
import cpw.mods.modlauncher.Launcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod(value = Aquaculture.MOD_ID)
public class Aquaculture {
    public static Aquaculture instance;
    public static final boolean IS_DEV = Launcher.INSTANCE.environment().getProperty(Environment.Keys.VERSION.get()).filter(v -> v.equals("MOD_DEV")).isPresent();
    public final static String MOD_ID = "aquaculture";
    public static final Logger LOG = LogManager.getLogger(MOD_ID);
    public static final CreativeModeTab GROUP = new CreativeModeTab(Aquaculture.MOD_ID) {
        @Override
        @Nonnull
        public ItemStack makeIcon() {
            return new ItemStack(AquaItems.IRON_FISHING_ROD);
        }
    };
    public static final LootItemConditionType BIOME_TAG_CHECK = LootItemConditions.register(new ResourceLocation(MOD_ID, "biome_tag_check").toString(), new BiomeTagCheck.BiomeTagCheckSerializer());

    public Aquaculture() {
        instance = this;
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::setupCommon);
        modBus.addListener(this::setupClient);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AquaConfig.spec);
        AquacultureAPI.Tags.init();
        FishFilletRecipe.IRECIPE_SERIALIZERS_DEFERRED.register(modBus);
    }

    private void setupCommon(FMLCommonSetupEvent event) {
        FishWeightHandler.registerFishData();
        event.enqueueWork(AquaEntities::setSpawnPlacement);
        event.enqueueWork(WormFarmBlock::addCompostables());
        if (AquaConfig.BASIC_OPTIONS.aqFishToBreedCats.get()) {
            FishRegistry.addCatBreeding();
        }
        if (AquaConfig.BASIC_OPTIONS.enableFishSpawning.get()) {
            FishReadFromJson.read();
        }
    }

    private void setupClient(FMLClientSetupEvent event) {
        ClientHandler.setupClient();
    }
}
package com.teammetallurgy.aquaculture;

import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.block.WormFarmBlock;
import com.teammetallurgy.aquaculture.client.ClientHandler;
import com.teammetallurgy.aquaculture.entity.AquaFishEntity;
import com.teammetallurgy.aquaculture.init.*;
import com.teammetallurgy.aquaculture.item.crafting.FishFilletRecipe;
import com.teammetallurgy.aquaculture.loot.AquaBiomeModifiers;
import com.teammetallurgy.aquaculture.loot.FishWeightHandler;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import cpw.mods.modlauncher.Environment;
import cpw.mods.modlauncher.Launcher;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
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
            return new ItemStack(AquaItems.IRON_FISHING_ROD.get());
        }
    };
    public static LootItemConditionType BIOME_TAG_CHECK;

    public Aquaculture() {
        instance = this;
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::setupCommon);
        modBus.addListener(this::setupClient);
        this.registerDeferredRegistries(modBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AquaConfig.spec);
        AquacultureAPI.Tags.init();

        AquaBiomeModifiers.BIOME_MODIFIER_SERIALIZERS_DEFERRED.register("mob_spawn", AquaBiomeModifiers.MobSpawnBiomeModifier::makeCodec);
        AquaBiomeModifiers.BIOME_MODIFIER_SERIALIZERS_DEFERRED.register("fish_spawn", AquaBiomeModifiers.FishSpawnBiomeModifier::makeCodec);
    }

    private void setupCommon(FMLCommonSetupEvent event) {
        event.enqueueWork(FishWeightHandler::registerFishData);
        event.enqueueWork(AquaEntities::setSpawnPlacement);
        event.enqueueWork(WormFarmBlock::addCompostables);
        event.enqueueWork(AquaRecipes::registerBrewingRecipes);
        event.enqueueWork(() -> {
            for (RegistryObject<EntityType<AquaFishEntity>> entityType : FishRegistry.fishEntities) {
                SpawnPlacements.register(entityType.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AquaFishEntity::canSpawnHere);
            }
        });
        if (AquaConfig.BASIC_OPTIONS.aqFishToBreedCats.get()) {
            event.enqueueWork(FishRegistry::addCatBreeding);
        }
    }

    private void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(ClientHandler::setupClient);
    }

    public void registerDeferredRegistries(IEventBus modBus) {
        AquaBlocks.BLOCK_DEFERRED.register(modBus);
        AquaItems.ITEM_DEFERRED.register(modBus);
        AquaBlockEntities.BLOCK_ENTITY_DEFERRED.register(modBus);
        AquaEntities.ENTITY_DEFERRED.register(modBus);
        AquaSounds.SOUND_EVENT_DEFERRED.register(modBus);
        AquaGuis.MENU_DEFERRED.register(modBus);
        FishFilletRecipe.IRECIPE_SERIALIZERS_DEFERRED.register(modBus);
        AquaBiomeModifiers.BIOME_MODIFIER_SERIALIZERS_DEFERRED.register(modBus);
    }
}
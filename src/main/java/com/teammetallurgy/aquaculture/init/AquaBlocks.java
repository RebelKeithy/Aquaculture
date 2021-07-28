package com.teammetallurgy.aquaculture.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.block.FarmlandMoistBlock;
import com.teammetallurgy.aquaculture.block.NeptunesBountyBlock;
import com.teammetallurgy.aquaculture.block.TackleBoxBlock;
import com.teammetallurgy.aquaculture.block.WormFarmBlock;
import com.teammetallurgy.aquaculture.block.tileentity.NeptunesBountyTileEntity;
import com.teammetallurgy.aquaculture.block.tileentity.TackleBoxTileEntity;
import com.teammetallurgy.aquaculture.item.BlockItemWithoutLevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Aquaculture.MOD_ID)
public class AquaBlocks {
    public static List<Block> BLOCKS = Lists.newArrayList();
    public static final Block FARMLAND = register(new FarmlandMoistBlock(), "farmland", null);
    public static final Block NEPTUNIUM_BLOCK = register(new Block(Block.Properties.of(Material.METAL, MaterialColor.COLOR_CYAN).strength(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL)), "neptunium_block");
    public static final Block NEPTUNES_BOUNTY = registerWithRenderer(new NeptunesBountyBlock(), "neptunes_bounty", new Item.Properties());
    public static final Block TACKLE_BOX = registerWithRenderer(new TackleBoxBlock(), "tackle_box", new Item.Properties());
    public static final Block WORM_FARM = register(new WormFarmBlock(), "worm_farm");

    /**
     * Same as {@link AquaBlocks#register(Block, String, Item.Properties)}, but have group set by default
     */
    public static Block register(@Nonnull Block block, @Nonnull String name) {
        return register(block, name, new Item.Properties());
    }

    /**
     * Registers an block with a basic BlockItem
     *
     * @param block The block to be registered
     * @param name  The name to register the block with
     * @return The Block that was registered
     */
    public static Block register(@Nonnull Block block, @Nonnull String name, @Nullable Item.Properties properties) {
        registerBaseBlock(block, name);
        AquaItems.register(new BlockItem(block, properties == null ? new Item.Properties() : properties.tab(Aquaculture.GROUP)), name);
        return block;
    }

    /**
     * Registers an block with a BlockItemWithoutLevelRenderer
     *
     * @param block The block to be registered
     * @param name  The name to register the block with
     * @return The Block that was registered
     */
    public static Block registerWithRenderer(@Nonnull Block block, @Nonnull String name, @Nullable Item.Properties properties) {
        registerBaseBlock(block, name);
        AquaItems.register(new BlockItemWithoutLevelRenderer(block, properties == null ? new Item.Properties() : properties.tab(Aquaculture.GROUP)), name);
        return block;
    }

    public static Block registerBaseBlock(@Nonnull Block block, @Nonnull String name) {
        block.setRegistryName(new ResourceLocation(Aquaculture.MOD_ID, name));
        BLOCKS.add(block);
        return block;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : BLOCKS) {
            event.getRegistry().register(block);
        }
    }

    @Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    @ObjectHolder(Aquaculture.MOD_ID)
    public static class AquaTileEntities {
        public static List<BlockEntityType<?>> TILE_ENTITIES = Lists.newArrayList();
        public static final BlockEntityType<NeptunesBountyTileEntity> NEPTUNES_BOUNTY = register("neptunes_bounty", BlockEntityType.Builder.of(NeptunesBountyTileEntity::new, AquaBlocks.NEPTUNES_BOUNTY));
        public static final BlockEntityType<TackleBoxTileEntity> TACKLE_BOX = register("tackle_box", BlockEntityType.Builder.of(TackleBoxTileEntity::new, AquaBlocks.TACKLE_BOX));

        public static <T extends BlockEntity> BlockEntityType<T> register(@Nonnull String name, @Nonnull BlockEntityType.Builder<T> builder) {
            BlockEntityType<T> tileEntityType = builder.build(null);
            tileEntityType.setRegistryName(new ResourceLocation(Aquaculture.MOD_ID, name));
            TILE_ENTITIES.add(tileEntityType);
            return tileEntityType;
        }

        @SubscribeEvent
        public static void registerTileEntity(RegistryEvent.Register<BlockEntityType<?>> event) {
            for (BlockEntityType<?> tileEntity : TILE_ENTITIES) {
                event.getRegistry().register(tileEntity);
            }
        }
    }
}
package com.teammetallurgy.aquaculture.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.block.FarmlandMoistBlock;
import com.teammetallurgy.aquaculture.block.TackleBoxBlock;
import com.teammetallurgy.aquaculture.block.tileentity.TackleBoxTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import java.util.List;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Aquaculture.MOD_ID)
public class AquaBlocks {
    public static List<Block> BLOCKS = Lists.newArrayList();
    public static final Block TACKLE_BOX = register(new TackleBoxBlock(), "tackle_box", new Item.Properties().maxStackSize(1));
    public static final Block FARMLAND = register(new FarmlandMoistBlock(), "farmland", new Item.Properties());

    /**
     * Registers an block
     *
     * @param block The block to be registered
     * @param name  The name to register the block with
     * @return The Block that was registered
     */
    public static Block register(@Nonnull Block block, @Nonnull String name, Item.Properties properties) {
        block.setRegistryName(new ResourceLocation(Aquaculture.MOD_ID, name));
        BLOCKS.add(block);
        AquaItems.register(new BlockItem(block, properties), name);
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
        public static List<TileEntityType> TILE_ENTITIES = Lists.newArrayList();
        public static final TileEntityType<TackleBoxTileEntity> TACKLE_BOX = register("tackle_box", TileEntityType.Builder.create(TackleBoxTileEntity::new, AquaBlocks.TACKLE_BOX));

        public static <T extends TileEntity> TileEntityType<T> register(@Nonnull String name, @Nonnull TileEntityType.Builder<T> builder) {
            TileEntityType<T> tileEntityType = builder.build(null);
            tileEntityType.setRegistryName(new ResourceLocation(Aquaculture.MOD_ID, name));
            TILE_ENTITIES.add(tileEntityType);
            return tileEntityType;
        }

        @SubscribeEvent
        public static void registerTileEntity(RegistryEvent.Register<TileEntityType<?>> event) {
            for (TileEntityType tileEntity : TILE_ENTITIES) {
                event.getRegistry().register(tileEntity);
            }
        }
    }
}
package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.block.FarmlandMoistBlock;
import com.teammetallurgy.aquaculture.block.NeptunesBountyBlock;
import com.teammetallurgy.aquaculture.block.TackleBoxBlock;
import com.teammetallurgy.aquaculture.block.WormFarmBlock;
import com.teammetallurgy.aquaculture.item.BlockItemWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class AquaBlocks {
    public static final DeferredRegister<Block> BLOCK_DEFERRED = DeferredRegister.create(Block.class, Aquaculture.MOD_ID);
    public static final RegistryObject<Block> FARMLAND = register(FarmlandMoistBlock::new, "farmland", null);
    public static final RegistryObject<Block> NEPTUNIUM_BLOCK = register(() -> new Block(Block.Properties.of(Material.METAL, MaterialColor.COLOR_CYAN).strength(5.0F, 6.0F).sound(SoundType.METAL)), "neptunium_block");
    public static final RegistryObject<Block> NEPTUNES_BOUNTY = registerWithRenderer(NeptunesBountyBlock::new, "neptunes_bounty", new Item.Properties());
    public static final RegistryObject<Block> TACKLE_BOX = registerWithRenderer(TackleBoxBlock::new, "tackle_box", new Item.Properties());
    public static final RegistryObject<Block> WORM_FARM = register(WormFarmBlock::new, "worm_farm");

    /**
     * Same as {@link AquaBlocks#register(Supplier, String, Item.Properties)}, but have group set by default
     */
    public static RegistryObject<Block> register(Supplier<Block> supplier, @Nonnull String name) {
        return register(supplier, name, new Item.Properties());
    }

    /**
     * Registers a block with a basic BlockItem
     *
     * @param supplier The block to register
     * @param name     The name to register the block with
     * @return The Block that was registered
     */
    public static RegistryObject<Block> register(Supplier<Block> supplier, @Nonnull String name, @Nullable Item.Properties properties) {
        RegistryObject<Block> block = BLOCK_DEFERRED.register(name, supplier);
        AquaItems.register(() -> new BlockItem(block.get(), properties == null ? new Item.Properties() : properties.tab(Aquaculture.GROUP)), name);
        return block;
    }

    /**
     * Registers a block with a BlockItemWithoutLevelRenderer
     *
     * @param supplier The block to register
     * @param name     The name to register the block with
     * @return The Block that was registered
     */
    public static RegistryObject<Block> registerWithRenderer(Supplier<Block> supplier, @Nonnull String name, @Nullable Item.Properties properties) {
        RegistryObject<Block> block = BLOCK_DEFERRED.register(name, supplier);
        AquaItems.register(() -> new BlockItemWithoutLevelRenderer(block.get(), properties == null ? new Item.Properties() : properties.tab(Aquaculture.GROUP)), name);
        return block;
    }
}
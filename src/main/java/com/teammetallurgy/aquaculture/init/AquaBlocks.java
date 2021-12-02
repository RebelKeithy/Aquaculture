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
    public static final RegistryObject<Block> FARMLAND = register(new FarmlandMoistBlock(), "farmland", null);
    public static final RegistryObject<Block> NEPTUNIUM_BLOCK = register(new Block(Block.Properties.of(Material.METAL, MaterialColor.COLOR_CYAN).strength(5.0F, 6.0F).sound(SoundType.METAL)), "neptunium_block");
    public static final RegistryObject<Block> NEPTUNES_BOUNTY = registerWithRenderer(new NeptunesBountyBlock(), "neptunes_bounty", new Item.Properties());
    public static final RegistryObject<Block> TACKLE_BOX = registerWithRenderer(new TackleBoxBlock(), "tackle_box", new Item.Properties());
    public static final RegistryObject<Block> WORM_FARM = register(new WormFarmBlock(), "worm_farm");

    /**
     * Same as {@link AquaBlocks#register(Block, String, Item.Properties)}, but have group set by default
     */
    public static RegistryObject<Block> register(@Nonnull Block block, @Nonnull String name) {
        return register(block, name, new Item.Properties());
    }

    /**
     * Registers a block with a basic BlockItem
     *
     * @param block The block to register
     * @param name        The name to register the block with
     * @return The Block that was registered
     */
    public static RegistryObject<Block> register(@Nonnull Block block, @Nonnull String name, @Nullable Item.Properties properties) {
        AquaItems.register(() -> new BlockItem(block, properties == null ? new Item.Properties() : properties.tab(Aquaculture.GROUP)), name);
        return registerBaseBlock(() -> block, name);
    }

    /**
     * Registers a block with a BlockItemWithoutLevelRenderer
     *
     * @param block The block to register
     * @param name        The name to register the block with
     * @return The Block that was registered
     */
    public static RegistryObject<Block> registerWithRenderer(@Nonnull Block block, @Nonnull String name, @Nullable Item.Properties properties) {
        AquaItems.register(() -> new BlockItemWithoutLevelRenderer(block, properties == null ? new Item.Properties() : properties.tab(Aquaculture.GROUP)), name);
        return registerBaseBlock(() -> block, name);
    }

    public static RegistryObject<Block> registerBaseBlock(@Nonnull Supplier<Block> initializer, @Nonnull String name) {
        return BLOCK_DEFERRED.register(name, initializer);
    }
}
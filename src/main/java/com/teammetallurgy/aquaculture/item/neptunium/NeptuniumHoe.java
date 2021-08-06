package com.teammetallurgy.aquaculture.item.neptunium;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.init.AquaBlocks;
import com.teammetallurgy.aquaculture.init.AquaItems;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID)
public class NeptuniumHoe extends HoeItem {

    public NeptuniumHoe(Tier tier, int damage, float speed) {
        super(tier, damage, speed, new Item.Properties().tab(Aquaculture.GROUP));
    }

    @SubscribeEvent
    public static void blockToolInteract(BlockEvent.BlockToolInteractEvent event) {
        System.out.println("Interact");
        if (event.getToolType() == ToolType.HOE && event.getHeldItemStack().getItem() == AquaItems.NEPTUNIUM_HOE) {
            System.out.println("blockToo");
            event.setFinalState(AquaBlocks.FARMLAND.defaultBlockState());
        }
    }
}
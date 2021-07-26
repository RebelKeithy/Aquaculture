package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.world.item.Item;

public class SimpleItem extends Item {

    public SimpleItem() {
        super(new Item.Properties().tab(Aquaculture.GROUP));
    }

    public SimpleItem(Item.Properties properties) {
        super(properties.tab(Aquaculture.GROUP));
    }
}
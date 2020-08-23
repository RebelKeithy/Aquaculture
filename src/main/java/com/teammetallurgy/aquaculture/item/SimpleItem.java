package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.item.Item;

public class SimpleItem extends Item {

    public SimpleItem() {
        super(new Item.Properties().group(Aquaculture.GROUP));
    }

    public SimpleItem(Item.Properties properties) {
        super(properties.group(Aquaculture.GROUP));
    }
}
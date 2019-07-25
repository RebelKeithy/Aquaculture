package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.fishing.Hook;
import net.minecraft.item.Item;

public class HookItem extends Item {
    private Hook hook;

    public HookItem(Hook hook) {
        super(new Item.Properties().group(Aquaculture.GROUP).maxStackSize(16));
        this.hook = hook;
    }

    public Hook getHookType() {
        return hook;
    }
}
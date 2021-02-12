package com.teammetallurgy.aquaculture.integration.crafttweaker;

import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.aquaculture.FishData")
public class CTFishData {

    @ZenCodeType.Method
    public void add(IItemStack fishStack, double min, double max, int filletAmount) {
        AquacultureAPI.FISH_DATA.add(fishStack.getDefinition(), min, max, filletAmount);
    }
}
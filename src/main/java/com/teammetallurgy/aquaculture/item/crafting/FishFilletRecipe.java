package com.teammetallurgy.aquaculture.item.crafting;

import com.google.common.collect.Lists;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.init.AquaItems;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FishFilletRecipe extends SpecialRecipe {
    private static final SpecialRecipeSerializer<FishFilletRecipe> FISH_FILLET_SERIALIZER = IRecipeSerializer.register(Aquaculture.MOD_ID + ":crafting_special_fish_fillet", new SpecialRecipeSerializer<>(FishFilletRecipe::new));

    private FishFilletRecipe(ResourceLocation location) {
        super(location);
    }

    @Override
    @Nonnull
    public ResourceLocation getId() {
        return new ResourceLocation(Aquaculture.MOD_ID, "fish_fillet");
    }

    @Override
    public boolean matches(@Nonnull CraftingInventory craftingInventory, @Nonnull World world) {
        ItemStack stack = ItemStack.EMPTY;
        List<ItemStack> list = Lists.newArrayList();

        for (int i = 0; i < craftingInventory.getSizeInventory(); ++i) {
            ItemStack slotStack = craftingInventory.getStackInSlot(i);
            if (!slotStack.isEmpty()) {
                if (AquacultureAPI.FISH_DATA.hasFilletAmount(slotStack.getItem())) {
                    if (!stack.isEmpty()) {
                        return false;
                    }
                    stack = slotStack;
                } else {
                    if (!(slotStack.getItem().isIn(AquacultureAPI.Tags.FILLET_KNIFE) && slotStack.isDamageable())) {
                        return false;
                    }
                    list.add(slotStack);
                }
            }
        }
        return !stack.isEmpty() && !list.isEmpty();
    }

    @Override
    @Nonnull
    public ItemStack getCraftingResult(@Nonnull CraftingInventory craftingInventory) {
        List<Item> list = Lists.newArrayList();
        ItemStack stack = ItemStack.EMPTY;

        for (int i = 0; i < craftingInventory.getSizeInventory(); ++i) {
            ItemStack stackSlot = craftingInventory.getStackInSlot(i);
            if (!stackSlot.isEmpty()) {
                Item item = stackSlot.getItem();
                if (AquacultureAPI.FISH_DATA.hasFilletAmount(item.getItem())) {
                    if (!stack.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                    stack = stackSlot.copy();
                } else {
                    if (!(item.isIn(AquacultureAPI.Tags.FILLET_KNIFE))) {
                        return ItemStack.EMPTY;
                    }
                    list.add(item);
                }
            }
        }
        if (!stack.isEmpty() && !list.isEmpty()) {
            return new ItemStack(AquaItems.FISH_FILLET, AquacultureAPI.FISH_DATA.getFilletAmount(stack.getItem()));
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    @Nonnull
    public NonNullList<ItemStack> getRemainingItems(CraftingInventory craftingInventory) {
        NonNullList<ItemStack> list = NonNullList.withSize(craftingInventory.getSizeInventory(), ItemStack.EMPTY);
        for (int i = 0; i < list.size(); ++i) {
            ItemStack stack = craftingInventory.getStackInSlot(i);
            if (stack.getItem().isIn(AquacultureAPI.Tags.FILLET_KNIFE)) {
                ItemStack knife = stack.copy();
                if (knife.attemptDamageItem(1, new Random(), null)) {
                    knife.shrink(1);
                }
                list.set(i, knife);
            }
        }
        return list;
    }

    @Override
    @Nonnull
    public IRecipeSerializer<?> getSerializer() {
        return FISH_FILLET_SERIALIZER;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @SubscribeEvent
    public static void registerRecipe(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        //event.getRegistry().register(FISH_FILLET_SERIALIZER);
    }
}
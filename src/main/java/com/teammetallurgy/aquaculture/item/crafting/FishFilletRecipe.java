package com.teammetallurgy.aquaculture.item.crafting;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.api.fish.FishData;
import com.teammetallurgy.aquaculture.init.AquaItems;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class FishFilletRecipe extends CustomRecipe {
    public static final DeferredRegister<RecipeSerializer<?>> IRECIPE_SERIALIZERS_DEFERRED = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Aquaculture.MOD_ID);
    private static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> FISH_FILLET_SERIALIZER = registerRecipeSerializer("crafting_special_fish_fillet", new SimpleCraftingRecipeSerializer<>(FishFilletRecipe::new));

    private FishFilletRecipe(CraftingBookCategory craftingBookCategory) {
        super(craftingBookCategory);
    }

    @Override
    public boolean matches(@Nonnull CraftingContainer craftingInventory, @Nonnull Level world) {
        ItemStack stack = ItemStack.EMPTY;
        List<ItemStack> list = new ArrayList<>();

        for (int i = 0; i < craftingInventory.getContainerSize(); ++i) {
            ItemStack slotStack = craftingInventory.getItem(i);
            if (!slotStack.isEmpty()) {
                if (AquacultureAPI.FISH_DATA.hasFilletAmount(slotStack.getItem())) {
                    if (!stack.isEmpty()) {
                        return false;
                    }
                    stack = slotStack;
                } else {
                    if (!(slotStack.is(AquacultureAPI.Tags.FILLET_KNIFE) && (slotStack.isDamageableItem() || isKnifeNeptunium(slotStack.getItem())) && slotStack.getItem() instanceof TieredItem)) {
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
    public ItemStack assemble(@Nonnull CraftingContainer craftingInventory, RegistryAccess registryAccess) {
        ItemStack fish = ItemStack.EMPTY;
        Item knife = null;

        for (int i = 0; i < craftingInventory.getContainerSize(); ++i) {
            ItemStack stackSlot = craftingInventory.getItem(i);
            if (!stackSlot.isEmpty()) {
                Item item = stackSlot.getItem();
                if (AquacultureAPI.FISH_DATA.hasFilletAmount(item)) {
                    if (!fish.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                    fish = stackSlot.copy();
                } else {
                    if (!(stackSlot.is(AquacultureAPI.Tags.FILLET_KNIFE))) {
                        return ItemStack.EMPTY;
                    }
                    knife = item;
                }
            }
        }
        if (!fish.isEmpty() && knife != null) {
            int filletAmount = AquacultureAPI.FISH_DATA.getFilletAmount(fish.getItem());
            if (AquaConfig.BASIC_OPTIONS.randomWeight.get() && fish.getTag() != null && fish.getTag().contains("fishWeight")) {
                filletAmount = FishData.getFilletAmountFromWeight(fish.getTag().getDouble("fishWeight"));
            }
            if (isKnifeNeptunium(knife)) {
                filletAmount += filletAmount * (25.0F / 100.0F);
            }
            return new ItemStack(AquaItems.FISH_FILLET.get(), filletAmount);
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    @Nonnull
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer craftingInventory) {
        NonNullList<ItemStack> list = NonNullList.withSize(craftingInventory.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < list.size(); ++i) {
            ItemStack stack = craftingInventory.getItem(i);
            if (stack.is(AquacultureAPI.Tags.FILLET_KNIFE)) {
                ItemStack knife = stack.copy();
                if (!isKnifeNeptunium(knife.getItem())) {
                    if (knife.hurt(1, RandomSource.create(), null)) {
                        knife.shrink(1);
                    }
                }
                list.set(i, knife);
            }
        }
        return list;
    }

    public static boolean isKnifeNeptunium(@Nonnull Item knife) {
        return knife instanceof TieredItem && ((TieredItem) knife).getTier() == AquacultureAPI.MATS.NEPTUNIUM;
    }

    @Override
    @Nonnull
    public RecipeSerializer<?> getSerializer() {
        return FISH_FILLET_SERIALIZER.get();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    public static DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> registerRecipeSerializer(String name, RecipeSerializer<?> serializer) {
        return IRECIPE_SERIALIZERS_DEFERRED.register(name, () -> serializer);
    }
}
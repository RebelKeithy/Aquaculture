package rebelkeithy.mods.aquaculture.items;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rebelkeithy.mods.aquaculture.loot.WeightedLootSet;
import rebelkeithy.mods.aquaculture.items.meta.MetaItem;
import rebelkeithy.mods.aquaculture.items.meta.SubItem;

import java.util.Random;

public class ItemBox extends SubItem {
    public Random rand = new Random();
    public WeightedLootSet loot;

    public ItemBox(MetaItem par1) {
        super(par1);
        loot = new WeightedLootSet();
        loot.addLoot(Blocks.stone, 5, 1, 1);
        loot.addLoot(Blocks.dirt, 5, 1, 1);
        loot.addLoot(Blocks.cobblestone, 5);
        loot.addLoot(new ItemStack(Blocks.planks, 1, 0), 1);
        loot.addLoot(new ItemStack(Blocks.planks, 1, 1), 1);
        loot.addLoot(new ItemStack(Blocks.planks, 1, 2), 1);
        loot.addLoot(new ItemStack(Blocks.planks, 1, 3), 1);
        loot.addLoot(new ItemStack(Blocks.sapling, 1, 0), 1);
        loot.addLoot(new ItemStack(Blocks.sapling, 1, 1), 1);
        loot.addLoot(new ItemStack(Blocks.sapling, 1, 2), 1);
        loot.addLoot(new ItemStack(Blocks.sapling, 1, 3), 1);
        loot.addLoot(new ItemStack(Blocks.log, 1, 0), 1);
        loot.addLoot(new ItemStack(Blocks.log, 1, 1), 1);
        loot.addLoot(new ItemStack(Blocks.log, 1, 2), 1);
        loot.addLoot(new ItemStack(Blocks.log, 1, 3), 1);
        loot.addLoot(Blocks.gravel, 5);
        loot.addLoot(new ItemStack(Items.coal, 1, 0), 3);
        loot.addLoot(new ItemStack(Items.coal, 1, 1), 3);
        loot.addLoot(Items.wheat_seeds, 3);
        loot.addLoot(Items.stick, 5);
        loot.addLoot(Items.bowl, 3);
        loot.addLoot(Items.leather_helmet, 2);
        loot.addLoot(Items.leather_chestplate, 2);
        loot.addLoot(Items.leather_leggings, 2);
        loot.addLoot(Items.leather_boots, 2);
        loot.addLoot(Items.flint, 4);
        loot.addLoot(Items.clay_ball, 4);
        loot.addLoot(Items.bucket, 1);
        loot.addLoot(Items.leather, 4);
        loot.addLoot(Items.slime_ball, 1);
        loot.addLoot(Blocks.reeds, 1);
        loot.addLoot(Items.bone, 5);
        loot.addLoot(Items.rotten_flesh, 5);
        loot.addLoot(Items.glass_bottle, 1);
        loot.addLoot(Items.carrot, 1);
        loot.addLoot(Items.potato, 1);
        loot.addLoot(Blocks.vine, 1);
        loot.addLoot(Blocks.tallgrass, 3);
        loot.addLoot(Items.string, 3);
        loot.addLoot(Items.feather, 4);
        loot.addLoot(Items.apple, 1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par2World.isRemote)
            return par1ItemStack;

        ItemStack randomLoot = loot.getRandomLoot();

        EntityItem entityitem = new EntityItem(par3EntityPlayer.worldObj, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, randomLoot);
        par2World.spawnEntityInWorld(entityitem);

        --par1ItemStack.stackSize;
        return par1ItemStack;
    }
}

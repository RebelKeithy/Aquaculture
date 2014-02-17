package rebelkeithy.mods.aquaculture.items;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import rebelkeithy.mods.aquaculture.BiomeType;
import rebelkeithy.mods.aquaculture.FishLoot;
import rebelkeithy.mods.aquaculture.LocalizationHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFish extends Item {
	public List<Fish> fish;

	public class Fish {
		public String name;
		public int filletAmount;
		public int minWeight;
		public int maxWeight;
		public Icon icon;

		public Fish(String name, int amount, int min, int max) {
			this.name = name;
			filletAmount = amount;
			maxWeight = max;
			minWeight = min;
		}
	}

	public ItemFish(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);

		fish = new ArrayList<Fish>();
	}

	public void addFish(String name, int filletAmount, int minWeight, int maxWeight, BiomeType biome, int rarity) {
		addFish(name, filletAmount, minWeight, maxWeight, new BiomeType[]{biome}, rarity);
	}

	public void addFish(String name, int filletAmount, int minWeight, int maxWeight, BiomeType[] biomes, int rarity) {
		fish.add(new Fish(name, filletAmount, minWeight, maxWeight));

		for(BiomeType biome : biomes) {
			FishLoot.instance().addFish(this.getItemStackFish(name), biome, rarity);
		}

		LanguageRegistry.addName(new ItemStack(itemID, 1, fish.size() - 1), LocalizationHelper.localize("item.Fish." + name.replace(" ", "_") + ".name"));
	}

	public void addFilletRecipes() {
		for(int i = 0; i < fish.size(); i++) {
			Fish f = fish.get(i);
			if(f.filletAmount != 0) {
				GameRegistry.addShapelessRecipe(AquacultureItems.fishFillet.getItemStack(f.filletAmount), new ItemStack(itemID, 1, i));
			}
		}
	}

	public String getItemDisplayName(ItemStack par1ItemStack) {
		if(par1ItemStack.hasTagCompound()) {
			if(par1ItemStack.getTagCompound().hasKey("Prefix")) {
				return par1ItemStack.getTagCompound().getString("Prefix") + " " + super.getItemDisplayName(par1ItemStack);
			}
		}
		return super.getItemDisplayName(par1ItemStack);
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(par1ItemStack.hasTagCompound()) {
			if(par1ItemStack.getTagCompound().hasKey("Weight")) {
				float weight = par1ItemStack.getTagCompound().getFloat("Weight");

				DecimalFormat df = new DecimalFormat("#,###.##");
				BigDecimal bd = new BigDecimal(weight);
				bd = bd.round(new MathContext(3));
				if(bd.doubleValue() > 999)
					par3List.add("Weight: " + df.format((int) bd.doubleValue()) + "lb");
				else
					par3List.add("Weight: " + bd + "lb");
			}
		}
	}

	public void assignRandomWeight(ItemStack stack, int heavyLineLvl) {
		if(stack == null)
			return;

		Fish f = fish.get(stack.getItemDamage());

		if(f.maxWeight == 1 && f.minWeight == 1)
			return;

		Random rand = new Random();

		float min = f.minWeight;

		min += (f.maxWeight - min) * (0.1 * heavyLineLvl);

		float weight = rand.nextFloat() * ((f.maxWeight * 1.1f) - min) + min;

		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound("tag"));
		}

		stack.getTagCompound().setFloat("Weight", weight);

		if(weight <= f.maxWeight / 10.0) {
			stack.getTagCompound().setString("Prefix", "Juvenile");
		}

		if(weight > f.maxWeight) {
			stack.getTagCompound().setString("Prefix", "Massive");
		}

	}

	public ItemStack getItemStackFish(String name) {
		for(int i = 0; i < fish.size(); i++) {
			if(fish.get(i).name.equals(name)) {
				return new ItemStack(itemID, 1, i);
			}
		}

		return null;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Gets an icon index based on an item's damage value
	 */
	@Override
	public Icon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, fish.size());
		return fish.get(j).icon;
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have different names based on their damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, fish.size());

		return super.getUnlocalizedName() + "." + fish.get(i).name;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	 */
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int j = 0; j < fish.size(); ++j) {
			par3List.add(new ItemStack(par1, 1, j));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		for(Fish f : fish) {
			f.icon = par1IconRegister.registerIcon("Aquaculture:" + f.name);
		}
	}
}

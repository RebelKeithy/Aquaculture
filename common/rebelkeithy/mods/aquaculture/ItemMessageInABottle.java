package rebelkeithy.mods.aquaculture;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class ItemMessageInABottle extends Item
{
	Random rand = new Random();
	
    public ItemMessageInABottle(int par1)
    {
        super(par1);
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(par2World.isRemote)
    		return par1ItemStack;
    	
        par2World.playSoundAtEntity(par3EntityPlayer, "random.glass", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        
        int messageRoll = rand.nextInt(29) + 1;
        
        String message = "";
        switch(messageRoll)
        {
	        case 1: message = "How I mine for fish?"; break;
	        case 2: message = "Doctor said I should start bottling up my feelings."; break;
	        case 3: message = "Help I'm trapped in a Message in a Bottle making factory!"; break;
	        case 4: message = "My house was griefed, send supplies!"; break;
	        case 5: message = "Hey! Over here! I think I see you!"; break;
	        case 6: message = "Haha, now YOU'RE littering!"; break;
	        case 7: message = "Property of Creeper-Cola."; break;
	        case 8: message = "Recycle for 5 Brass."; break;
	        case 9: message = "Warning: Suffocation Hazard, keep out of reach of children."; break;
	        case 10: message = "If you get this, this is you from the future, do NOT eat the porkchop."; break;
	        case 11: message = "Admit one, Davy Jones' Locker."; break;
	        case 12: message = "9.17 N 19.89 E"; break;
	        case 13: message = "Neptunite is REAL, I tell you! I wasn't making it up!"; break;
	        case 14: message = "Enlarge your Fishing Rod for only 19.95 Gold! Guaranteed!"; break;
	        case 15: message = "What the hell is Fishallurgy? Some sort of reaction to fish?"; break;
	        case 16: message = "WHALE BURGERS! 2x Bread + Whale Steak! SPREAD THE WORD!"; break;
	        case 17: message = "Ever since I came ashore things haven't been the same, because the moment I stepped onto the shore I totally lost the game.."; break;
	        case 18: message = "Press Alt-F4 for free Diamonds!"; break;
	        case 19: message = "Oh I've got a lovely bunch of coconuts.."; break;
	        case 20: message = "I joined a tribe of cannibals to meat new people."; break;
	        case 21: message = "So much room for ACTIVITIES!"; break;
	        case 22: message = "Dear Shadowclaimer, add Unobtainium Hyper Diamonds that are invulnerable with instant mining speed tools and armor. Sincerely, Everyone."; break;
	        case 23: message = "Dear Shadowclaimer, so when's 1.X coming out? Sincerely, Everyone."; break;
	        case 24: message = "Dear fans, hi! Sincerely, Shadowclaimer."; break;
	        case 25: message = "Shadowclaimer <3's Nhira"; break;
	        case 26: message = "Null Pointer Exception: Aquaculture.MessageInABottle (String) cannot be null."; break;
	        case 27: message = "I grind up diamond armor every morning and eat it for breakfast. - mDiyo"; break;
	        case 28: message = "-The message magically fades from your hand.-"; break;
	        case 29: message = "Look up at me, now back at your inventory, now back at me, this message is now DIAMONDS! Nah just kidding, its still paper."; break;
	        default: message = "ERROR! Fish Escaping!"; break;
        }
        
        par3EntityPlayer.addChatMessage(message);
        --par1ItemStack.stackSize;
        return par1ItemStack;
    }
}
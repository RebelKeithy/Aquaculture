package rebelkeithy.mods.aquaculture.events;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

/**
 * @author Freyja
 */
public class FishingCatchEvent extends FishingEvent {

    public ItemStack fishCaught;

    public FishingCatchEvent(ItemStack stack, EntityLiving entityLiving, ItemStack fishCaught) {
        super(stack, entityLiving);
        this.fishCaught = fishCaught;
    }
}

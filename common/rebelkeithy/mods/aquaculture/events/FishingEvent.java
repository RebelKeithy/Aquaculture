package rebelkeithy.mods.aquaculture.events;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.Event;

/**
 * @author Freyja
 */
public class FishingEvent extends Event {

    public final ItemStack stack;
    public final EntityLiving entity;

    public FishingEvent(ItemStack stack, EntityLiving entityLiving) {
        this.stack = stack;
        this.entity = entityLiving;
    }
}

package rebelkeithy.mods.aquaculture.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;

/**
 * @author Freyja
 */
public class FishingCastEvent extends FishingEvent {

    public Vec3 velocity;

    public FishingCastEvent(Vec3 velocity, EntityPlayer player, ItemStack stack) {
        super(stack, player);
        this.velocity = velocity;
    }
}

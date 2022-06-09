package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public final class AquaSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT_DEFERRED = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Aquaculture.MOD_ID);
    public static final RegistryObject<SoundEvent> TACKLE_BOX_OPEN = registerSound("tackle_box_open");
    public static final RegistryObject<SoundEvent> TACKLE_BOX_CLOSE = registerSound("tackle_box_close");
    public static final RegistryObject<SoundEvent> WORM_FARM_EMPTY = registerSound("worm_farm_empty");
    public static final RegistryObject<SoundEvent> FISH_MOUNT_REMOVED = registerSound("fish_mount_removed");
    public static final RegistryObject<SoundEvent> FISH_MOUNT_BROKEN = registerSound("fish_mount_broken");
    public static final RegistryObject<SoundEvent> FISH_MOUNT_PLACED = registerSound("fish_mount_placed");
    public static final RegistryObject<SoundEvent> FISH_MOUNT_ADD_ITEM = registerSound("fish_mount_add_item");
    public static final RegistryObject<SoundEvent> BOBBER_BAIT_BREAK = registerSound("bobber_bait");
    public static final RegistryObject<SoundEvent> BOBBER_LAND_IN_LAVA = registerSound("bobber_land_lava");
    public static final RegistryObject<SoundEvent> JELLYFISH_FLOP = registerSound("jellyfish_flop");
    public static final RegistryObject<SoundEvent> FISH_FLOP = registerSound("fish_flop");
    public static final RegistryObject<SoundEvent> FISH_AMBIENT = registerSound("fish_ambient");
    public static final RegistryObject<SoundEvent> FISH_DEATH = registerSound("fish_death");
    public static final RegistryObject<SoundEvent> FISH_HURT = registerSound("fish_hurt");
    public static final RegistryObject<SoundEvent> JELLYFISH_COLLIDE = registerSound("jellyfish_collide");
    public static final RegistryObject<SoundEvent> BOTTLE_OPEN = registerSound("bottle_open");
    public static final RegistryObject<SoundEvent> BOBBER_NOTE = registerSound("bobber_note_catch");

    private static RegistryObject<SoundEvent> registerSound(String name) {
        ResourceLocation resourceLocation = new ResourceLocation(Aquaculture.MOD_ID, name);
        return SOUND_EVENT_DEFERRED.register(name, () -> new SoundEvent(resourceLocation));
    }
}
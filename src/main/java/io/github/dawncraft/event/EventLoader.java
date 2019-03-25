package io.github.dawncraft.event;

import io.github.dawncraft.capability.CapabilityEvent;
import io.github.dawncraft.enchantment.EnchantmentEvent;
import io.github.dawncraft.potion.PotionEvent;
import io.github.dawncraft.world.WorldEventHandler;
import net.minecraftforge.common.MinecraftForge;

/**
 * Register some common events.
 *
 * @author QingChenW
 */
public class EventLoader
{
    public static void initEvents()
    {
        registerEvent(new EventHandler());
        registerEvent(new EnchantmentEvent());
        registerEvent(new PotionEvent());
        registerEvent(new CapabilityEvent());
        registerEvent(new WorldEventHandler());
    }

    private static void registerEvent(Object target)
    {
        MinecraftForge.EVENT_BUS.register(target);
    }
}

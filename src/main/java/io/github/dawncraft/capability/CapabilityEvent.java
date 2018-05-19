package io.github.dawncraft.capability;

import io.github.dawncraft.network.MessageWindowSkills;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import io.github.dawncraft.container.ISkillInventory;
import io.github.dawncraft.entity.AttributesLoader;
import io.github.dawncraft.network.MessageSpellCooldown;
import io.github.dawncraft.network.NetworkLoader;
import io.github.dawncraft.skill.Skill;
import io.github.dawncraft.skill.SkillStack;
import io.github.dawncraft.stats.AchievementLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 *
 *
 * @author QingChenW
 */
public class CapabilityEvent
{
    public CapabilityEvent(FMLInitializationEvent event) {}

    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent.Entity event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            player.getAttributeMap().registerAttribute(AttributesLoader.maxMana);
            event.addCapability(IPlayer.domain, new CapabilityPlayer.Provider(player));
        }
    }
    
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if (!event.world.isRemote && event.entity instanceof EntityPlayer)
        {
            EntityPlayerMP player = (EntityPlayerMP) event.entity;
            player.triggerAchievement(AchievementLoader.basic);
            if (player.hasCapability(CapabilityLoader.player, null))
            {
                IPlayer playerCap = player.getCapability(CapabilityLoader.player, null);
                IStorage<IPlayer> storage = CapabilityLoader.player.getStorage();
                
                ISkillInventory inventory = playerCap.getInventory();
                List<SkillStack> list = new ArrayList<SkillStack>();
                for(int i = 0; i < inventory.getSizeInventory(); i++)
                    list.add(inventory.getStackInSlot(i));
                NetworkLoader.instance.sendTo(new MessageWindowSkills(0, list), player);
                for(Entry<Skill, Integer> entry : playerCap.getCooldownTracker().cooldowns.entrySet())
                {
                    NetworkLoader.instance.sendTo(new MessageSpellCooldown(entry.getKey(), entry.getValue()), player);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event)
    {
        Capability<IPlayer> capability = CapabilityLoader.player;
        IStorage<IPlayer> storage = capability.getStorage();

        if (event.original.hasCapability(capability, null) && event.entityPlayer.hasCapability(capability, null))
        {
            IPlayer player = event.original.getCapability(capability, null);
            NBTTagCompound nbt = (NBTTagCompound) storage.writeNBT(capability, player, null);
            if(event.wasDeath)
            {
                float mana = player.getMaxMana();
                nbt.setFloat("ManaF", mana);
                nbt.setShort("Mana", (short) Math.ceil(mana));
            }
            storage.readNBT(capability, event.entityPlayer.getCapability(capability, null), null, nbt);
        }
    }
}

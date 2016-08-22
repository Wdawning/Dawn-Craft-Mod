package com.github.wdawning.dawncraft.network;

import com.github.wdawning.dawncraft.extend.ExtendedPlayer;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageMagic implements IMessage{
	
    public MessageMagic(){}
    
	//private NBTTagCompound data;
	public int mana;
	
	public MessageMagic(int amount)
	{
		this.mana = amount;
	}

    @Override
    public void fromBytes(ByteBuf buf) {
    	//mana = ByteBufUtils.readVarInt(buf, 20);
    	mana = buf.readInt();
    	//mana = buf.getInt(0);
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
    	//ByteBufUtils.writeVarInt(buf, mana, 20);
    	buf.writeInt(mana);
    	//buf.setInt(0, mana)
    }

    public static class MessageHandler implements IMessageHandler<MessageMagic, IMessage>
    {
        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(final MessageMagic message, MessageContext ctx)
        {
            if (ctx.side == Side.CLIENT)
            {
    			final EntityPlayer player = Minecraft.getMinecraft().thePlayer;
                
                Minecraft.getMinecraft().addScheduledTask(new Runnable()
                {
                    @Override
                    public void run()
                    {
            			int mana = message.mana;
                    	ExtendedPlayer.get(player).setMana(mana);
                    }
                });
            }
            return null;
        }
    }
}
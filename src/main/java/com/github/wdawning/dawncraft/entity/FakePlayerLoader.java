package com.github.wdawning.dawncraft.entity;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;

import net.minecraftforge.common.util.FakePlayerFactory;

import java.lang.ref.WeakReference;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

public class FakePlayerLoader
{
    private static GameProfile gameProfile;
    private static WeakReference<EntityPlayerMP> fakePlayer;
    
    public FakePlayerLoader()
    {
        gameProfile = new GameProfile(UUID.fromString("D5X8H6R2-DF56-D56G-A56D-6A5FW4EF43T8"), "[Dawncraft]");
        fakePlayer = new WeakReference<EntityPlayerMP>(null);
    }
    
    public static WeakReference<EntityPlayerMP> getFakePlayer(WorldServer server)
    {
        if (fakePlayer.get() == null)
        {
            fakePlayer = new WeakReference<EntityPlayerMP>(FakePlayerFactory.get(server, gameProfile));
        }
        else
        {
            fakePlayer.get().worldObj = server;
        }
        return fakePlayer;
    }
}

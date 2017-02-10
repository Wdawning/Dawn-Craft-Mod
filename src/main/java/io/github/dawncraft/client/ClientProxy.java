package io.github.dawncraft.client;

import io.github.dawncraft.client.renderer.entity.EntityRenderLoader;
import io.github.dawncraft.client.renderer.tileentity.TileEntityRenderLoader;
import io.github.dawncraft.common.CommonProxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * The client proxy of Dawncraft Mod.
 * 
 * @author QingChenW
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        new ItemRenderLoader(event);
        new EntityRenderLoader(event);
        new TileEntityRenderLoader(event);
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        new KeyLoader(event);
        new ClientEventLoader(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        
        super.postInit(event);
    }
}
package com.github.wdawning.dawncraft.item;

import com.github.wdawning.dawncraft.creativetab.CreativeTabsLoader;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemFlan extends Item
{
    public ItemFlan(int maxDamage)
    {
        this.maxStackSize = 1;
        this.setFull3D();
        this.setMaxDamage(maxDamage);
        this.setCreativeTab(CreativeTabsLoader.tabFlans);
    }
	
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(1, attacker);
        return true;
    }
    
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
    {
        if ((double)blockIn.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(2, playerIn);
        }

        return true;
    }
}

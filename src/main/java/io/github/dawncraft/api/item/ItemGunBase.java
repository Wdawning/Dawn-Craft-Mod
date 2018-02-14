package io.github.dawncraft.api.item;

import io.github.dawncraft.Dawncraft;
import io.github.dawncraft.entity.projectile.EntityBullet;
import io.github.dawncraft.item.ItemLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGunBase extends ItemGun
{
    private float damage;
    
    public ItemGunBase(int maxDamage, float damage)
    {
        super(maxDamage);
        this.setDamage(damage);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        if (playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItem(ItemLoader.gunBullet))
        {
            if (!playerIn.capabilities.isCreativeMode)
            {
                itemStackIn.damageItem(1, playerIn);
                playerIn.inventory.consumeInventoryItem(ItemLoader.gunBullet);
            }
            
            worldIn.playSoundAtEntity(playerIn, this.getShootSound(), 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            
            if (!worldIn.isRemote)
            {
                EntityBullet entity = new EntityBullet(worldIn, playerIn, 3.0F);
                entity.setDamage(this.getDamage());
                worldIn.spawnEntityInWorld(entity);
            }
        }
        
        return itemStackIn;
    }
    
    public float getDamage()
    {
        return this.damage;
    }

    public ItemGunBase setDamage(float damage)
    {
        this.damage = damage;
        return this;
    }

    private String getShootSound()
    {
        return Dawncraft.MODID + ":" + "random.gun";
    }
}

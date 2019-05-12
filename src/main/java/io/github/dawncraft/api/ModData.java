package io.github.dawncraft.api;

import io.github.dawncraft.Dawncraft;
import io.github.dawncraft.skill.Skill;
import io.github.dawncraft.talent.Talent;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import net.minecraftforge.fml.common.registry.PersistentRegistryManager;

/**
 * The registry is out of date.
 * <br>The new version of Forge has RegistryBuilder to use.<br/>
 * But now, we can only use it...
 *
 * @author QingChenW
 */
public class ModData
{
    public static final ResourceLocation Enchantments = new ResourceLocation("minecraft:enchantments");
    public static final ResourceLocation Skills = new ResourceLocation(Dawncraft.MODID + ":" + "skills");
    public static final ResourceLocation Talents = new ResourceLocation(Dawncraft.MODID + ":" + "talents");
    private static final int MIN_ENCHANTMENT_ID = 0;
    private static final int MAX_ENCHANTMENT_ID = Short.MAX_VALUE - 1; // Short - serialized as a short in ItemStack NBTs.
    private static final int MIN_SKILL_ID = 0;
    private static final int MAX_SKILL_ID = 31999;
    private static final int MIN_TALENT_ID = 0;
    private static final int MAX_TALENT_ID = 4095;
    
    private static final ModData main = new ModData();
    
    private final FMLControlledNamespacedRegistry<Enchantment> iEnchantmentRegistry = PersistentRegistryManager.createRegistry(Enchantments, Enchantment.class, null, MAX_ENCHANTMENT_ID, MIN_ENCHANTMENT_ID, false);
    private final FMLControlledNamespacedRegistry<Skill> iSkillRegistry = PersistentRegistryManager.createRegistry(Skills, Skill.class, null, MAX_SKILL_ID, MIN_SKILL_ID, true);
    private final FMLControlledNamespacedRegistry<Talent> iTalentRegistry = PersistentRegistryManager.createRegistry(Talents, Talent.class, null, MAX_TALENT_ID, MIN_TALENT_ID, true);
    
    void registerSkill(Skill skill, String name)
    {
        this.iSkillRegistry.register(-1, this.addPrefix(name), skill);
    }

    /**
     * Copy from {@link net.minecraftforge.fml.common.registry.GameData#addPrefix(String)}
     */
    public ResourceLocation addPrefix(String name)
    {
        int index = name.lastIndexOf(':');
        String oldPrefix = index == -1 ? "" : name.substring(0, index);
        name = index == -1 ? name : name.substring(index + 1);
        String prefix;
        ModContainer mc = Loader.instance().activeModContainer();
        
        if (mc != null)
        {
            prefix = mc.getModId().toLowerCase();
        }
        else // no mod container, assume minecraft
        {
            prefix = "minecraft";
        }
        
        if (!oldPrefix.equals(prefix) && oldPrefix.length() > 0)
        {
            FMLLog.bigWarning("Dangerous alternative prefix %s for name %s, invalid registry invocation/invalid name?", prefix, name);
            prefix = oldPrefix;
        }
        
        return new ResourceLocation(prefix, name);
    }
    
    public static ModData getMain()
    {
        return main;
    }
    
    public static FMLControlledNamespacedRegistry<Enchantment> getEnchantmentRegistry()
    {
        return getMain().iEnchantmentRegistry;
    }

    public static FMLControlledNamespacedRegistry<Skill> getSkillRegistry()
    {
        return getMain().iSkillRegistry;
    }

    public static FMLControlledNamespacedRegistry<Talent> getTalentRegistry()
    {
        return getMain().iTalentRegistry;
    }

    public static Enchantment getEnchantmentByID(int id)
    {
        return getEnchantmentRegistry().getObjectById(id);
    }
    
    public static int getEnchantmentID(Enchantment enchantment)
    {
        return getEnchantmentRegistry().getIDForObject(enchantment);
    }
    
    public static Enchantment getEnchantmentByLocation(String location)
    {
        return getEnchantmentRegistry().getObject(new ResourceLocation(location));
    }

    static Skill findSkill(String modId, String name)
    {
        return getMain().iSkillRegistry.getObject(new ResourceLocation(modId, name));
    }
}

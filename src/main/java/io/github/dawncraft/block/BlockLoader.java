package io.github.dawncraft.block;

import io.github.dawncraft.common.CreativeTabsLoader;
import io.github.dawncraft.fluid.FluidLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Register Blocks in the class.
 * 
 * @author QingChenW
 */
public class BlockLoader
{
    // Energy
    public static Block fluidPetroleum = new BlockFluidClassic(FluidLoader.fluidPetroleum, Material.water).setUnlocalizedName("fluidPetroleum");
    
    public static Block electricCable = new BlockElectricCable().setUnlocalizedName("electricityCable").setCreativeTab(CreativeTabsLoader.tabEnergy);
    
    public static Block energyGeneratorHeat = new BlockEnergyGenerator(BlockEnergyGenerator.EnergyGeneratorType.HEAT).setUnlocalizedName("heatGenerator").setCreativeTab(CreativeTabsLoader.tabEnergy);
    public static Block energyGeneratorFluid = new BlockEnergyGenerator(BlockEnergyGenerator.EnergyGeneratorType.FLUID).setUnlocalizedName("fluidGenerator").setCreativeTab(CreativeTabsLoader.tabEnergy);
    public static Block energyGeneratorSolar = new BlockEnergyGenerator(BlockEnergyGenerator.EnergyGeneratorType.SOLAR).setUnlocalizedName("solarGenerator").setCreativeTab(CreativeTabsLoader.tabEnergy);
    public static Block energyGeneratorWind = new BlockEnergyGenerator(BlockEnergyGenerator.EnergyGeneratorType.WIND).setUnlocalizedName("windGenerator").setCreativeTab(CreativeTabsLoader.tabEnergy);
    public static Block energyGeneratorNuclear = new BlockEnergyGenerator(BlockEnergyGenerator.EnergyGeneratorType.NUCLEAR).setUnlocalizedName("nuclearGenerator").setCreativeTab(CreativeTabsLoader.tabEnergy);
    public static Block energyGeneratorMagic = new BlockEnergyGenerator(BlockEnergyGenerator.EnergyGeneratorType.MAGIC).setUnlocalizedName("magicGenerator").setCreativeTab(CreativeTabsLoader.tabEnergy);
    
    // Magnet
    public static Block magnetOre = new BlockOre(MapColor.stoneColor).setUnlocalizedName("magnetOre").setCreativeTab(CreativeTabsLoader.tabMagnetic);
    public static Block magnetBlock = new Block(Material.iron).setUnlocalizedName("magnetBlock").setCreativeTab(CreativeTabsLoader.tabMagnetic)
            .setHardness(5.0f).setResistance(10.0f).setStepSound(Block.soundTypePiston);
    // public static Block magnetDoor = new BlockMagnetDoor();
    
    // Machine
    public static Block machineFurnace = new BlockMachineFurnace().setUnlocalizedName("machineEleFurnace").setCreativeTab(CreativeTabsLoader.tabMachine);
    
    // Computer
    public static Block simpleComputer = new BlockComputerCase(BlockComputerCase.ComputerCaseType.SIMPLE).setUnlocalizedName("simpleComputer").setCreativeTab(CreativeTabsLoader.tabComputer);
    public static Block proComputer = new BlockComputerCase(BlockComputerCase.ComputerCaseType.PRO).setUnlocalizedName("proComputer").setCreativeTab(CreativeTabsLoader.tabComputer);
    public static Block superComputer = new BlockComputerCase(BlockComputerCase.ComputerCaseType.SUPER).setUnlocalizedName("superComputer").setCreativeTab(CreativeTabsLoader.tabComputer);
    
    // Materials
    
    // Furniture
    public static Block superChest = new BlockFurnitureSuperChest().setUnlocalizedName("superChest").setCreativeTab(CreativeTabsLoader.tabFurniture);
    // Food
    
    // Magic
    public static Block magicOre = new BlockOre(MapColor.stoneColor).setUnlocalizedName("magicOre").setCreativeTab(CreativeTabsLoader.tabMagic);
    
    // Flans
    
    // ColourEgg
    public static Block dawnPortal = new BlockDawnPortal().setUnlocalizedName("dawnPortal");
    
    public BlockLoader(FMLPreInitializationEvent event)
    {
        // Energy
        register(fluidPetroleum, "fluid_petroleum");
        
        register(electricCable, "electric_cable");
        
        register(energyGeneratorHeat, "heat_generator");
        register(energyGeneratorFluid, "fluid_generator");
        register(energyGeneratorSolar, "solar_generator");
        register(energyGeneratorWind, "wind_generator");
        register(energyGeneratorNuclear, "nuclear_generator");
        register(energyGeneratorMagic, "magic_generator");
        // Magnet
        register(magnetOre, "magnet_ore");
        magnetBlock.setHarvestLevel("ItemPickaxe", 1);
        register(magnetBlock, "magnet_block");
        
        // Machine
        register(machineFurnace, "machine_furnace");
        
        // Computer
        register(simpleComputer, "simple_computer");
        register(proComputer, "pro_computer");
        register(superComputer, "super_computer");

        // Materials
        
        // Furniture
        register(superChest, "super_chest");
        
        // Food
        
        // Magic
        register(magicOre, "magic_ore");
        
        // Flans
        
        // ColourEgg
        registerWithItem(dawnPortal, null, "dawn_portal");
    }
    
    /**
     * Register a block with a name-id.
     * 
     * @param block The block to register
     * @param name The block's name-id
     */
    private static void register(Block block, String name)
    {
        GameRegistry.registerBlock(block, name);
    }
    
    /**
     * Register a block with a name-id and a item-block.
     * 
     * @param block The block to register
     * @param itemclass The item-block's class
     * @param name The block's name-id
     */
    private static void registerWithItem(Block block, Class<? extends ItemBlock> itemclass, String name)
    {
        GameRegistry.registerBlock(block, itemclass, name);
    }
}
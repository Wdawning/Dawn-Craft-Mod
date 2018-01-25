package io.github.dawncraft.client.renderer.block;

import io.github.dawncraft.block.BlockLoader;
import io.github.dawncraft.block.BlockMagnetDoor;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameData;

/**
 * Register custom blocks' model.(Include FieldBlock)
 * <br>这是啥Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register()</br>
 *
 * @author QingChenW
 */
public class BlockRenderLoader
{
    public BlockRenderLoader(FMLPreInitializationEvent event)
    {
        registerRender((BlockFluidBase) BlockLoader.fluidPetroleum);

        //registerRender((BlockContainer) BlockLoader.superChest);
        //registerRender((BlockContainer) BlockLoader.skull);
        
        registerStateMapper(BlockLoader.magnetDoor, new StateMap.Builder().ignore(BlockMagnetDoor.POWERED).build());
    }
    
    /**
     * Register a fluid's inventory model and it's model.
     *
     * @param blockFluid Fluid block to register
     */
    public static void registerRender(BlockFluidBase blockFluid)
    {
        Item itemFluid = Item.getItemFromBlock(blockFluid);
        final String name = GameData.getBlockRegistry().getNameForObject(blockFluid).toString();
        ModelLoader.setCustomMeshDefinition(itemFluid, new ItemMeshDefinition()
        {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation(name, "fluid");
            }
        });
        registerStateMapper(blockFluid, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation(name, "fluid");
            }
        });
    }

    /**
     * Register a BlockStateMapper.
     *
     * @param block block to register
     * @param mapper block's state mapper
     */
    private static void registerStateMapper(Block block, IStateMapper mapper)
    {
        ModelLoader.setCustomStateMapper(block, mapper);
    }
    
    /**
     * Register BuiltIn blocks.
     * Remove to {@link ClientEventLoader.BakeEventHandler.onModelBake()}
     */
    //private static void registerBuiltIn(Block... blocks){}
}

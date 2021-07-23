package xufly.threebodysophon.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import xufly.threebodysophon.entity.TileEntityLowDimensionalExpansionInstrument;

import javax.annotation.Nullable;

/**
 * @author XuFly
 */

public class BlockLowDimensionalExpansionInstrument extends Block
{
    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    public BlockLowDimensionalExpansionInstrument()
    {
        super(AbstractBlock.Properties.of(Material.METAL).harvestTool(ToolType.PICKAXE));
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new TileEntityLowDimensionalExpansionInstrument();
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {

        return ActionResultType.SUCCESS;
    }

    /*@Override
    public BlockState getStateForPlacement(BlockItemUseContext blockItemUseContext)
    {
        return this.defaultBlockState().setValue(FACING, blockItemUseContext.getHorizontalDirection().getOpposite());
    }*/
}

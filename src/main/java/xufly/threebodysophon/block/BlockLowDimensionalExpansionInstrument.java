package xufly.threebodysophon.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import xufly.threebodysophon.tileentity.TileEntityLowDimensionalExpansionInstrument;

public class BlockLowDimensionalExpansionInstrument extends Block
{
    public static final EnumProperty<Direction> FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BlockLowDimensionalExpansionInstrument()
    {
        super(AbstractBlock.Properties.create(Material.IRON).harvestTool(ToolType.PICKAXE));
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new TileEntityLowDimensionalExpansionInstrument();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext blockItemUseContext)
    {
        return this.getDefaultState().with(FACING, blockItemUseContext.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
        super.fillStateContainer(builder);
    }
}

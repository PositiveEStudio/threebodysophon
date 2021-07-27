package xufly.threebodysophon.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.state.EnumProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import xufly.threebodysophon.tileentity.TileEntityLowDimensionalExpansionInstrument;

import javax.annotation.Nullable;

public class BlockLowDimensionalExpansionInstrument extends Block
{
    public static final EnumProperty<Direction> FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BlockLowDimensionalExpansionInstrument()
    {
        super(AbstractBlock.Properties.create(Material.IRON).harvestTool(ToolType.PICKAXE));
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
}

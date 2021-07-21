package xufly.threebodysophon.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;

/**
 * @author XuFly
 */

public class BlockLowDimensionalExpansionInstrument extends Block
{
    public BlockLowDimensionalExpansionInstrument()
    {
        super(AbstractBlock.Properties.of(Material.METAL));
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }
}

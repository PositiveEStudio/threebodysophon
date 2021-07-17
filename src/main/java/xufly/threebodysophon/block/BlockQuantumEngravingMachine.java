package xufly.threebodysophon.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

/**
 * @author XuFly
 */

public class BlockQuantumEngravingMachine extends Block
{
    public BlockQuantumEngravingMachine()
    {
        super(Properties.of(Material.METAL, MaterialColor.METAL).harvestLevel(4));
    }
}

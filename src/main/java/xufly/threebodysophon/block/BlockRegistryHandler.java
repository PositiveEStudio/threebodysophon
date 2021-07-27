package xufly.threebodysophon.block;

import net.minecraft.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xufly.threebodysophon.ThreebodySophon;

public class BlockRegistryHandler
{
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, ThreebodySophon.MODID);
    public static final Block LOW_DIMENSIONAL_EXPANSION_INSTRUMENT = register("low_dimensional_expansion_instrument", new BlockLowDimensionalExpansionInstrument());

    private static Block register(String name, Block block) {
        BLOCK_REGISTER.register(name, () -> block);
        return block;
    }
}

package xufly.threebodysophon.block;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xufly.threebodysophon.ThreebodySophon;

/**
 * @author XuFly
 */

@Mod.EventBusSubscriber
public class BlockRegistryHandler
{
    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, ThreebodySophon.MODID);
    public static final RegistryObject<Block> LOW_DIMENSIONAL_EXPANSION_INSTRUMENT = BLOCK_REGISTER.register("low_dimensional_expansion_instrument", BlockLowDimensionalExpansionInstrument::new);
}
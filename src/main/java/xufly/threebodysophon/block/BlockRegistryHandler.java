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
    public static final DeferredRegister<Block> BLOCKs = DeferredRegister.create(ForgeRegistries.BLOCKS, ThreebodySophon.MODID);
    public static RegistryObject<Block> QUANTUM_ENGRAVING_MACHINE = BLOCKs.register("quantum_engraving_machine", BlockQuantumEngravingMachine::new);
}

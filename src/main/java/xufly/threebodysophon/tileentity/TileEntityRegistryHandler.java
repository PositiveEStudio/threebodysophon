package xufly.threebodysophon.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xufly.threebodysophon.ThreebodySophon;
import xufly.threebodysophon.block.BlockRegistryHandler;

@Mod.EventBusSubscriber
public class TileEntityRegistryHandler
{
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ThreebodySophon.MODID);
    public static final RegistryObject<TileEntityType<TileEntityLowDimensionalExpansionInstrument>> TILE_ENTITY_LOW_DIMENSIONAL_EXPANSION_INSTRUMENT = TILE_ENTITY_REGISTER.register("tile_entity_low_dimensional_expansion_instrument", () -> TileEntityType.Builder.create(TileEntityLowDimensionalExpansionInstrument::new, BlockRegistryHandler.LOW_DIMENSIONAL_EXPANSION_INSTRUMENT.get()).build(null));
}

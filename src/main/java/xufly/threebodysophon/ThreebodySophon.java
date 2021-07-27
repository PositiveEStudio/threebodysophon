package xufly.threebodysophon;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xufly.threebodysophon.block.BlockRegistryHandler;
import xufly.threebodysophon.entity.EntityRegistryHandler;
import xufly.threebodysophon.item.ItemRegistryHandler;
import xufly.threebodysophon.tileentity.TileEntityRegistryHandler;

@Mod("threebodysophon")
public class ThreebodySophon
{
    public static final String MODID = "threebodysophon";

    private static final Logger LOGGER = LogManager.getLogger();

    public ThreebodySophon()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);

        ItemRegistryHandler.ITEM_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockRegistryHandler.BLOCK_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityRegistryHandler.ENTITY_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        TileEntityRegistryHandler.TILE_ENTITY_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {

    }
}
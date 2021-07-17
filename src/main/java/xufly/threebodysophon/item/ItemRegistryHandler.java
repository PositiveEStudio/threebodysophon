package xufly.threebodysophon.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xufly.threebodysophon.ThreebodySophon;

/**
 * @author XuFly
 */

@Mod.EventBusSubscriber
public class ItemRegistryHandler
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ThreebodySophon.MODID);
    public static RegistryObject<Item> PROTON = ITEMS.register("proton", ItemProton::new);
    public static RegistryObject<Item> SOPHON = ITEMS.register("sophon", ItemSophon::new);
    public static RegistryObject<Item> SOPHON_CONTROLLER = ITEMS.register("sophon_controller", ItemSophonController::new);
}

package xufly.threebodysophon.item;

import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xufly.threebodysophon.ThreebodySophon;

public class ItemRegistryHandler
{
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, ThreebodySophon.MODID);
    public static final Item PROTON = register("proton", new ItemProton());
    public static final Item SOPHON_CONTROLLER = register("sophon_controller", new ItemSophonController());

    private static Item register(String name, Item item)
    {
        ITEM_REGISTER.register(name, () -> item);
        return item;
    }
}
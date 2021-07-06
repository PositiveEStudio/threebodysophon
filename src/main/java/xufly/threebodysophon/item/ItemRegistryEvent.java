package xufly.threebodysophon.item;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;
import xufly.threebodysophon.ThreebodySophon;

@Mod.EventBusSubscriber
public class ItemRegistryEvent {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ThreebodySophon.MODID);
    public static RegistryObject<Item> PROTON = ITEMS.register("proton", () -> {
        return new ItemProton();
    });

}
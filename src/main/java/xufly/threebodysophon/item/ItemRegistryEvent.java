package xufly.threebodysophon.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;


@Mod.EventBusSubscriber
public class ItemRegistryEvent
{
    public static final Item Proton = new ItemProton();

}

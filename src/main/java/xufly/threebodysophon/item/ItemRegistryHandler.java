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
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, ThreebodySophon.MODID);
    public static final RegistryObject<Item> PROTON = ITEM_REGISTER.register("proton", ItemProton::new);
    public static final RegistryObject<Item> MATERIAL_SINGULARITY_17 = ITEM_REGISTER.register("material_singularity_17", ItemMaterialSingularity17::new);
    public static final RegistryObject<Item> CONTROL_CORE = ITEM_REGISTER.register("control_core", ItemControlCore::new);
    public static final RegistryObject<Item> ACCELERATOR_COMPONENT = ITEM_REGISTER.register("acceleration_component", ItemAccelerationComponent::new);
    public static final RegistryObject<Item> BASIC_ACCELERATOR = ITEM_REGISTER.register("basic_accelerator", ItemBasicAccelerator::new);
}

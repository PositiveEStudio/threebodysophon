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
    public static RegistryObject<Item> MATERIAL_SINGULARITY_17 = ITEMS.register("material_singularity_17", ItemMaterialSingularity17::new);
    public static RegistryObject<Item> BASIC_ACCELERATOR = ITEMS.register("basic_accelerator", ItemMaterialSingularity17::new);
    public static RegistryObject<Item> ACCELERATION_COMPONENT = ITEMS.register("acceleration_component", ItemAccelerationComponent::new);
    public static RegistryObject<Item> CONTROL_CORE = ITEMS.register("control_core", ItemControlCore::new);
    public static RegistryObject<Item> ENGRAVING_COMPONENT = ITEMS.register("engraving_component", ItemEngravingComponent::new);
    public static RegistryObject<Item> COMPUTING_COMPONENT = ITEMS.register("computing_component", ItemComputingComponent::new);
}
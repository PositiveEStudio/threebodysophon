package xufly.threebodysophon.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xufly.threebodysophon.ThreebodySophon;
import xufly.threebodysophon.block.BlockRegistryHandler;
import xufly.threebodysophon.creative.ModGroup;

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
    public static final RegistryObject<Item> STEEL_GEAR = ITEM_REGISTER.register("steel_gear", ItemSteelGear::new);
    public static final RegistryObject<Item> EXPAND_COMPONENT = ITEM_REGISTER.register("expand_component", ItemExpandComponent::new);
    public static final RegistryObject<Item> SOPHON = ITEM_REGISTER.register("sophon", ItemSophon::new);

    public static final RegistryObject<BlockItem> LOW_DIMENSIONAL_EXPANSION_INSTRUMENT = ITEM_REGISTER.register("low_dimensional_expansion_instrument", () -> new BlockItem(BlockRegistryHandler.LOW_DIMENSIONAL_EXPANSION_INSTRUMENT.get(), new Item.Properties().group(ModGroup.itemGroup)));
}

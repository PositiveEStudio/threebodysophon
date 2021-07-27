package xufly.threebodysophon.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xufly.threebodysophon.ThreebodySophon;
import xufly.threebodysophon.block.BlockRegistryHandler;
import xufly.threebodysophon.creative.ModGroup;

public class ItemRegistryHandler
{
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, ThreebodySophon.MODID);
    public static final Item PROTON = register("proton", new ItemProton());
    public static final Item MATERIAL_SINGULARITY_17 = register("material_singularity_17", new ItemMaterialSingularity17());
    public static final Item CONTROL_CORE = register("control_core", new ItemControlCore());
    public static final Item ACCELERATOR_COMPONENT = register("acceleration_component", new ItemAccelerationComponent());
    public static final Item BASIC_ACCELERATOR = register("basic_accelerator", new ItemBasicAccelerator());
    public static final Item STEEL_GEAR = register("steel_gear", new ItemSteelGear());
    public static final Item EXPAND_COMPONENT = register("expand_component", new ItemExpandComponent());
    public static final Item SOPHON_CONTROLLER = register("sophon_controller", new ItemSophonController());

    public static final Item LOW_DIMENSIONAL_EXPANSION_INSTRUMENT = register("low_dimensional_expansion_instrument", new BlockItem(BlockRegistryHandler.LOW_DIMENSIONAL_EXPANSION_INSTRUMENT, new Item.Properties().group(ModGroup.itemGroup)));

    private static Item register(String name, Item item)
    {
        ITEM_REGISTER.register(name, () -> item);
        return item;
    }
}
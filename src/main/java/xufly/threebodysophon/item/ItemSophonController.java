package xufly.threebodysophon.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import xufly.threebodysophon.creative.ModGroup;

public class ItemSophonController extends Item
{
    public ItemSophonController()
    {
        super(new Properties().group(ModGroup.itemGroup));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        ItemStack stack = context.getItem();

        return ActionResultType.SUCCESS;
    }
}
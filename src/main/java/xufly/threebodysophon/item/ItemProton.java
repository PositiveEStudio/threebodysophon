package xufly.threebodysophon.item;

import net.minecraft.item.Item;
import xufly.threebodysophon.creative.ModGroup;

public class ItemProton extends Item
{
    public ItemProton()
    {
        super(new Properties().group(ModGroup.itemGroup).maxStackSize(1));
    }
}

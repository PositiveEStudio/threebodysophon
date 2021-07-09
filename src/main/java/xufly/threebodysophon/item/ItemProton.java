package xufly.threebodysophon.item;

import net.minecraft.item.Item;
import xufly.threebodysophon.creative.ModGroup;

/**
 * @author XuFly
 */

public class ItemProton extends Item
{
    public static final String name = "proton";

    public ItemProton()
    {
        super(new Properties().tab(ModGroup.itemGroup).stacksTo(1));
    }
}

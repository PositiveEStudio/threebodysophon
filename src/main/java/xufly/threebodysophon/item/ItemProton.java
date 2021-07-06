package xufly.threebodysophon.item;

import net.minecraft.item.Item;
import xufly.threebodysophon.ThreebodySophon;

public class ItemProton extends Item
{
    public ItemProton()
    {
        super(new Properties().stacksTo(1));
        this.setRegistryName(ThreebodySophon.MODID,"proton");
    }
}

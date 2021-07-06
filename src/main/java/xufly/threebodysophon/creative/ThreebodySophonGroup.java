package xufly.threebodysophon.creative;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import xufly.threebodysophon.item.ItemRegistryEvent;

/**
 * @author XuFly
 */

public class ThreebodySophonGroup extends ItemGroup
{
    public ThreebodySophonGroup()
    {
        super("threebody_sophon_group");
    }

    @Override
    public ItemStack makeIcon()
    {
        return new ItemStack(ItemRegistryEvent.PROTON.get());
    }
}

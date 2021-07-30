package xufly.threebodysophon.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xufly.threebodysophon.client.gui.screen.ScreenLoader;
import xufly.threebodysophon.creative.ModGroup;

public class ItemSophonController extends Item
{
    public ItemSophonController()
    {
        super(new Properties().group(ModGroup.itemGroup).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (worldIn.isRemote && stack.getOrCreateTag().hasUniqueId("BindSophon"))
        {
            ScreenLoader.sophonControllerScreen(stack.getOrCreateTag().getUniqueId("BindSophon"));
        }
        return ActionResult.func_233538_a_(stack, worldIn.isRemote);
    }
}
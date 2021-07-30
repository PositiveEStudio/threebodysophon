package xufly.threebodysophon.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
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
        if (worldIn.isRemote)
        {
            if (stack.getOrCreateTag().hasUniqueId("BindSophon"))
            {
                ScreenLoader.sophonControllerScreen(stack.getOrCreateTag().getUniqueId("BindSophon"));
            }
            else
            {
                playerIn.sendMessage(new TranslationTextComponent("message.sophon_controller.hint"), playerIn.getUniqueID());
            }
        }
        return ActionResult.func_233538_a_(stack, worldIn.isRemote);
    }
}
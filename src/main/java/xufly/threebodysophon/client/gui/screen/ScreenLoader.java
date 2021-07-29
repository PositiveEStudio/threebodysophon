package xufly.threebodysophon.client.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;

public class ScreenLoader
{
    public static void sophonControllerScreen(PlayerEntity playerIn)
    {
        if (playerIn.isSneaking())
        {
            Minecraft.getInstance().displayGuiScreen(new ScreenSophonControllerBinding());
        }
        else
        {
            Minecraft.getInstance().displayGuiScreen(new ScreenSophonControllerUsing());
        }
    }
}

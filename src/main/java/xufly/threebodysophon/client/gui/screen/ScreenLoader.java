package xufly.threebodysophon.client.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;

public class ScreenLoader
{
    public static void sophonControllerScreen(PlayerEntity playerin)
    {
        if (playerin.isSneaking())
        {
            Minecraft.getInstance().displayGuiScreen(new ScreenSophonControllerUsing());
        }
        else
        {
            Minecraft.getInstance().displayGuiScreen(new ScreenSophonControllerBinding());
        }
    }
}

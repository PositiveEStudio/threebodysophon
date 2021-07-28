package xufly.threebodysophon.client.gui.screen;

import net.minecraft.client.Minecraft;

public class ScreenLoader
{
    public static void sophonControllerScreen() {
        Minecraft.getInstance().displayGuiScreen(new SophonControllerScreen());
    }
}

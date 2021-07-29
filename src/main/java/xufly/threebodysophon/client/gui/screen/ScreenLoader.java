package xufly.threebodysophon.client.gui.screen;

import net.minecraft.client.Minecraft;

import java.util.UUID;

public class ScreenLoader
{
    public static void sophonControllerScreen(UUID sophonId) {
        Minecraft.getInstance().displayGuiScreen(new SophonControllerScreen(sophonId));
    }
}

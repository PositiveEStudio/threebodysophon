package xufly.threebodysophon.client.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import xufly.threebodysophon.client.gui.SophonGui;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class EventHandler
{
    public static void renderGameOverlayEvent(final RenderGameOverlayEvent event)
    {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL && Minecraft.getInstance().player != null)
        {
            new SophonGui(event.getMatrixStack(), Minecraft.getInstance().player).render();
        }
    }
}

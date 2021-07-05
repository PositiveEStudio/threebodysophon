package xufly.threebodysophon.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author XuFly
 */

@Mod.EventBusSubscriber
public class EventHandler
{
    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent event)
    {
        Entity entity = event.getEntity();
        if (entity instanceof PlayerEntity)
        {
            String message = "消灭人类暴政，世界属于三体！";
            ITextComponent text = new StringTextComponent(message);
            entity.sendMessage(text, entity.getUUID());
        }
    }
}

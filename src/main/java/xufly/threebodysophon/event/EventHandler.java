package xufly.threebodysophon.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author XuFly
 */

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class EventHandler
{
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        //Test
        //"消灭人类暴政，世界属于三体！"
        /*ITextComponent component = new StringTextComponent("\u6d88\u706d\u4eba\u7c7b\u66b4\u653f\uff0c\u4e16\u754c\u5c5e\u4e8e\u4e09\u4f53\uff01");
        if (event.getPlayer() instanceof ServerPlayerEntity)
        {
            event.getPlayer().sendMessage(component,event.getPlayer().getUniqueID());
        }*/
    }
}
package xufly.threebodysophon.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author XuFly
 */

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class EventHandler
{
    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent event)
    {
        Entity entity = event.getEntity();
        if (entity instanceof PlayerEntity)
        {
            //String message = "消灭人类暴政，世界属于三体！";
            String message = "\u6d88\u706d\u4eba\u7c7b\u66b4\u653f\uff0c\u4e16\u754c\u5c5e\u4e8e\u4e09\u4f53\uff01";
            ITextComponent text = new StringTextComponent(message);
            entity.sendMessage(text, entity.getUUID());
        }
    }
}

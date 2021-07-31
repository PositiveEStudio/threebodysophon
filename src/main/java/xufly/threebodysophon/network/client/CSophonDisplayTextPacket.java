package xufly.threebodysophon.network.client;

import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;
import xufly.threebodysophon.entity.EntitySophon;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CSophonDisplayTextPacket
{
    private final UUID sophonId;
    private final String text;

    public CSophonDisplayTextPacket(PacketBuffer buffer)
    {
        this.sophonId = buffer.readUniqueId();
        this.text = buffer.readString(32767);
    }

    public CSophonDisplayTextPacket(UUID sophonId, String text)
    {
        this.sophonId = sophonId;
        this.text = text;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeUniqueId(this.sophonId);
        buffer.writeString(this.text);
    }

    public void consumer(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() -> {
            try
            {
                EntitySophon sophon = null;
                for (Entity entity : ((ServerWorld) Objects.requireNonNull(context.get().getSender()).world).getEntities().collect(Collectors.toList()))
                {
                    if (entity instanceof EntitySophon && entity.getUniqueID().equals(this.sophonId))
                    {
                        sophon = (EntitySophon) entity;
                    }
                }
                sophon.setDisplayText(this.text);
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        });
        context.get().setPacketHandled(true);
    }
}

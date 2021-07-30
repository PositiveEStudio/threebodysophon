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

public class CSophonExpansionPacket
{
    private final UUID sophonId;
    private final boolean expansion;

    public CSophonExpansionPacket(PacketBuffer buffer)
    {
        this.sophonId = buffer.readUniqueId();
        this.expansion = buffer.readBoolean();
    }

    public CSophonExpansionPacket(UUID sophonId, boolean expansion)
    {
        this.sophonId = sophonId;
        this.expansion = expansion;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeUniqueId(this.sophonId);
        buffer.writeBoolean(this.expansion);
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
                sophon.setLowDimensionalExpansion(this.expansion);
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        });
        context.get().setPacketHandled(true);
    }
}

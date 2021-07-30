package xufly.threebodysophon.network.client;

import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;
import xufly.threebodysophon.entity.EntitySophon;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CSophonPositionPacket
{
    private final UUID sophonId;
    private final boolean usePos; // true:绝对坐标，false:玩家面前
    private final double posX;
    private final double posY;
    private final double posZ;
    private final UUID trackingPlayer;

    public CSophonPositionPacket(PacketBuffer buffer)
    {
        this.sophonId = buffer.readUniqueId();
        this.usePos = buffer.readBoolean();
        this.posX = buffer.readDouble();
        this.posY = buffer.readDouble();
        this.posZ = buffer.readDouble();
        this.trackingPlayer = buffer.readUniqueId();
    }

    public CSophonPositionPacket(UUID sophonId, boolean usePos, double x, double y, double z, UUID trackingPlayer)
    {
        this.sophonId = sophonId;
        this.usePos = usePos;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        if (this.usePos) // 防止出现空指针
        {
            this.trackingPlayer = UUID.randomUUID();
        }
        else
        {
            this.trackingPlayer = trackingPlayer;
        }
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeUniqueId(this.sophonId);
        buffer.writeBoolean(this.usePos);
        buffer.writeDouble(this.posX);
        buffer.writeDouble(this.posY);
        buffer.writeDouble(this.posZ);
        buffer.writeUniqueId(this.trackingPlayer);
    }

    public void consumer(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() -> {
            try
            {
                EntitySophon sophon = null;
                World world = Objects.requireNonNull(context.get().getSender()).world;
                for (Entity entity : ((ServerWorld) world).getEntities().collect(Collectors.toList()))
                {
                    if (entity instanceof EntitySophon && entity.getUniqueID().equals(this.sophonId))
                    {
                        sophon = (EntitySophon) entity;
                    }
                }

                if (usePos)
                {
                    sophon.setPositionByController(this.posX, this.posY, this.posZ);
                }
                else
                {
                    sophon.setTrackingPlayer(this.trackingPlayer);
                }
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        });
        context.get().setPacketHandled(true);
    }
}

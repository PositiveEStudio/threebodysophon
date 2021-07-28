package xufly.threebodysophon.network.client;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import xufly.threebodysophon.ThreebodySophon;
import xufly.threebodysophon.entity.EntitySophon;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

public class CSophonPositionPacket
{
    private final int sophonId;
    private final boolean pos; // true:绝对坐标，false:玩家面前
    private final double posX;
    private final double posY;
    private final double posZ;
    private final UUID trackingPlayer;

    public CSophonPositionPacket(PacketBuffer buffer)
    {
        this.sophonId = buffer.readInt();
        this.pos = buffer.readBoolean();
        this.posX = buffer.readDouble();
        this.posY = buffer.readDouble();
        this.posZ = buffer.readDouble();
        this.trackingPlayer = buffer.readUniqueId();
    }

    public CSophonPositionPacket(int sophonId, boolean pos, double x, double y, double z, UUID trackingPlayer)
    {
        this.sophonId = sophonId;
        this.pos = pos;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.trackingPlayer = trackingPlayer;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(this.sophonId);
        buffer.writeBoolean(this.pos);
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
                World world = Objects.requireNonNull(context.get().getSender()).world;
                if (world.getEntityByID(this.sophonId) instanceof EntitySophon)
                {
                    EntitySophon sophon = (EntitySophon) world.getEntityByID(this.sophonId);
                    if (pos)
                    {
                        if (world.isAreaLoaded(new BlockPos(this.posX, this.posY, this.posZ), 0))
                        {
                            sophon.setPosition(this.posX, this.posY, this.posZ);
                        }
                    }
                    else
                    {
                        sophon.setTrackingPlayer(this.trackingPlayer);
                    }
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

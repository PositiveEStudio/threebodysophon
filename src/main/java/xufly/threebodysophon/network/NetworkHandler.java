package xufly.threebodysophon.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import xufly.threebodysophon.ThreebodySophon;
import xufly.threebodysophon.network.client.CSophonDisplayTextPacket;
import xufly.threebodysophon.network.client.CSophonExpansionPacket;
import xufly.threebodysophon.network.client.CSophonPositionPacket;

public class NetworkHandler
{
    public static final String VERSION = "1.0";
    public static final SimpleChannel SOPHON_POSITION = NetworkRegistry.newSimpleChannel(new ResourceLocation(ThreebodySophon.MODID, "sophon_position"), () -> VERSION, (v) -> v.equals(VERSION), (v) -> v.equals(VERSION));
    public static final SimpleChannel SOPHON_DISPLAY_TEXT = NetworkRegistry.newSimpleChannel(new ResourceLocation(ThreebodySophon.MODID, "sophon_display_text"), () -> VERSION, (v) -> v.equals(VERSION), (v) -> v.equals(VERSION));
    public static final SimpleChannel SOPHON_TEXT_ON_PLAYER_EYE = NetworkRegistry.newSimpleChannel(new ResourceLocation(ThreebodySophon.MODID, "sophon_text_on_player_eye"), () -> VERSION, (v) -> v.equals(VERSION), (v) -> v.equals(VERSION));
    public static final SimpleChannel SOPHON_EXPANSION = NetworkRegistry.newSimpleChannel(new ResourceLocation(ThreebodySophon.MODID, "sophon_expansion"), () -> VERSION, (v) -> v.equals(VERSION), (v) -> v.equals(VERSION));

    private static int id = 0;

    public static void register()
    {
        SOPHON_POSITION.messageBuilder(CSophonPositionPacket.class, getId()).encoder(CSophonPositionPacket::encode).decoder(CSophonPositionPacket::new).consumer(CSophonPositionPacket::consumer).add();
        SOPHON_DISPLAY_TEXT.messageBuilder(CSophonDisplayTextPacket.class, getId()).encoder(CSophonDisplayTextPacket::encode).decoder(CSophonDisplayTextPacket::new).consumer(CSophonDisplayTextPacket::consumer).add();
        SOPHON_EXPANSION.messageBuilder(CSophonExpansionPacket.class, getId()).encoder(CSophonExpansionPacket::encode).decoder(CSophonExpansionPacket::new).consumer(CSophonExpansionPacket::consumer).add();
    }

    private static int getId()
    {
        return id++;
    }
}

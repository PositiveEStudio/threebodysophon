package xufly.threebodysophon.client.renderer.entity;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xufly.threebodysophon.entity.EntityRegistryHandler;

public class EntityRendererManager
{
    public static void register()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistryHandler.SOPHON, RendererSophon::new);
    }
}

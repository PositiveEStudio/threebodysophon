package xufly.threebodysophon.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xufly.threebodysophon.ThreebodySophon;

public class EntityRegistryHandler
{
    public static final DeferredRegister<EntityType<?>> ENTITY_REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, ThreebodySophon.MODID);
    public static final EntityType<EntitySophon> SOPHON = register("sophon", EntityType.Builder.create(EntitySophon::new, EntityClassification.MISC).size(2.0F, 2.0F).trackingRange(20).immuneToFire().build("sophon"));

    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> type)
    {
        ENTITY_REGISTER.register(name, () -> type);
        return type;
    }
}
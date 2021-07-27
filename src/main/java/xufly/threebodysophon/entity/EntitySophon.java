package xufly.threebodysophon.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xufly.threebodysophon.item.ItemRegistryHandler;

public class EntitySophon extends Entity
{
    private static final DataParameter<Boolean> LOW_DIMENSIONAL_EXPANSION = EntityDataManager.createKey(EntitySophon.class, DataSerializers.BOOLEAN);
    private static final DataParameter<String> DISPLAY_TEXT = EntityDataManager.createKey(EntitySophon.class, DataSerializers.STRING);
    private static final DataParameter<String> TEXT_ON_PLAYER_EYE = EntityDataManager.createKey(EntitySophon.class, DataSerializers.STRING);

    public EntitySophon(EntityType<?> entityTypeIn, World worldIn)
    {
        super(entityTypeIn, worldIn);
    }

    public EntitySophon(World world, double x, double y, double z, boolean lowDimensionalExpansion)
    {
        this(EntityRegistryHandler.SOPHON, world);
        this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
        this.dataManager.set(LOW_DIMENSIONAL_EXPANSION, lowDimensionalExpansion);
    }

    @Override
    protected void registerData()
    {
        this.dataManager.register(LOW_DIMENSIONAL_EXPANSION, false);
        this.dataManager.register(DISPLAY_TEXT, "");
        this.dataManager.register(TEXT_ON_PLAYER_EYE, "");
    }

    public boolean getLowDimensionalExpansion()
    {
        return this.dataManager.get(LOW_DIMENSIONAL_EXPANSION);
    }

    public void setLowDimensionalExpansion(boolean lowDimensionalExpansion)
    {
        this.dataManager.set(LOW_DIMENSIONAL_EXPANSION, lowDimensionalExpansion);
    }

    @Override
    public ActionResultType processInitialInteract(PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
        if (stack.getItem() == ItemRegistryHandler.SOPHON_CONTROLLER)
        {
            stack.getOrCreateTag().putInt("BindSophon", this.getEntityId());
            return ActionResultType.func_233537_a_(this.world.isRemote);
        }
        return super.processInitialInteract(player, hand);
    }

    @Override
    protected void readAdditional(CompoundNBT compound)
    {
        if (compound.contains("expansion"))
        {
            this.dataManager.set(LOW_DIMENSIONAL_EXPANSION, compound.getBoolean("expansion"));
        }
        if (compound.contains("text"))
        {
            this.dataManager.set(DISPLAY_TEXT, compound.getString("text"));
        }
        if (compound.contains("TextOnPlayerEye"))
        {
            this.dataManager.set(TEXT_ON_PLAYER_EYE, compound.getString("TextOnPlayerEye"));
        }
    }

    @Override
    protected void writeAdditional(CompoundNBT compound)
    {
        compound.putBoolean("expansion", this.dataManager.get(LOW_DIMENSIONAL_EXPANSION));
        compound.putString("text", this.dataManager.get(DISPLAY_TEXT));
        compound.putString("TextOnPlayerEye", this.dataManager.get(TEXT_ON_PLAYER_EYE));
    }

    @Override
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

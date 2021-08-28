package xufly.threebodysophon.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xufly.threebodysophon.item.ItemRegistryHandler;

import java.util.UUID;

public class EntitySophon extends Entity
{
    private static final DataParameter<Boolean> LOW_DIMENSIONAL_EXPANSION = EntityDataManager.createKey(EntitySophon.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> BOUND = EntityDataManager.createKey(EntitySophon.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> TRACK_PLAYER = EntityDataManager.createKey(EntitySophon.class, DataSerializers.BOOLEAN);
    private static final DataParameter<String> DISPLAY_TEXT = EntityDataManager.createKey(EntitySophon.class, DataSerializers.STRING);
    private static final DataParameter<String> TEXT_ON_PLAYER_EYE = EntityDataManager.createKey(EntitySophon.class, DataSerializers.STRING);
    private static final DataParameter<String> TRACKING_PLAYER = EntityDataManager.createKey(EntitySophon.class, DataSerializers.STRING);

    public EntitySophon(EntityType<?> entityTypeIn, World worldIn)
    {
        super(entityTypeIn, worldIn);
    }

    public EntitySophon(World world, double x, double y, double z)
    {
        this(EntityRegistryHandler.SOPHON, world);
        this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
    }

    @Override
    protected void registerData()
    {
        this.dataManager.register(LOW_DIMENSIONAL_EXPANSION, true);
        this.dataManager.register(BOUND, false);
        this.dataManager.register(TRACK_PLAYER, false);
        this.dataManager.register(DISPLAY_TEXT, "");
        this.dataManager.register(TEXT_ON_PLAYER_EYE, "");
        this.dataManager.register(TRACKING_PLAYER, "");
    }

    public boolean getLowDimensionalExpansion()
    {
        return this.dataManager.get(LOW_DIMENSIONAL_EXPANSION);
    }

    public void setLowDimensionalExpansion(boolean lowDimensionalExpansion)
    {
        if (!this.world.isRemote)
        {
            this.dataManager.set(LOW_DIMENSIONAL_EXPANSION, lowDimensionalExpansion);
            this.setInvisible(!lowDimensionalExpansion);
        }
    }

    public void setTrackingPlayer(UUID trackingPlayer)
    {
        if (!this.world.isRemote)
        {
            this.dataManager.set(TRACKING_PLAYER, trackingPlayer.toString());
            this.dataManager.set(TRACK_PLAYER, true);
        }
    }

    public void setPositionByController(double x, double y, double z)
    {
        if (this.world.isAreaLoaded(new BlockPos(x, y, z), 0) && !this.world.isRemote)
        {
            this.setPosition(x, y, z);
            this.dataManager.set(TRACK_PLAYER, false);
        }
    }

    public ITextComponent getDisplayText()
    {
        return new StringTextComponent(this.dataManager.get(DISPLAY_TEXT));
    }

    public void setDisplayText(String displayText)
    {
        if (!this.world.isRemote)
        {
            this.dataManager.set(DISPLAY_TEXT, displayText);
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.dataManager.get(TRACK_PLAYER))
        {
            try
            {
                PlayerEntity player = this.world.getPlayerByUuid(UUID.fromString(this.dataManager.get(TRACKING_PLAYER)));
                Vector3d vector3d = player.getLookVec();
                this.setPosition(player.getPosX() + vector3d.x * 3.5D, player.getPosYEye() + vector3d.y * 3.5D - 1, player.getPosZ() + vector3d.z * 3.5D);
            }
            catch (IllegalArgumentException | NullPointerException ignored)
            {
            }
        }
    }

    @Override
    public ActionResultType processInitialInteract(PlayerEntity player, Hand hand) // 玩家拿着控制器右键时
    {
        if (this.getLowDimensionalExpansion() && !this.dataManager.get(BOUND) && !this.world.isRemote)
        {
            ItemStack stack = player.getHeldItem(hand);
            if (stack.getItem() == ItemRegistryHandler.SOPHON_CONTROLLER && !stack.getOrCreateTag().hasUniqueId("BindSophon"))
            {
                stack.getOrCreateTag().putUniqueId("BindSophon", this.getUniqueID()); // 绑定智子
                this.dataManager.set(BOUND, true);
                return ActionResultType.CONSUME;
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return this.isAlive() && !this.dataManager.get(BOUND);
    }

    @Override
    protected float getEyeHeight(Pose poseIn, EntitySize sizeIn)
    {
        return 1.0F;
    }

    @Override
    protected void readAdditional(CompoundNBT compound)
    {
        if (compound.contains("expansion"))
        {
            this.setLowDimensionalExpansion(compound.getBoolean("expansion"));
        }
        if (compound.contains("bound"))
        {
            this.dataManager.set(BOUND, compound.getBoolean("bound"));
        }
        if (compound.contains("TrackPlayer"))
        {
            this.dataManager.set(TRACK_PLAYER, compound.getBoolean("TrackPlayer"));
        }
        if (compound.contains("text"))
        {
            this.dataManager.set(DISPLAY_TEXT, compound.getString("text"));
        }
        if (compound.contains("TextOnPlayerEye"))
        {
            this.dataManager.set(TEXT_ON_PLAYER_EYE, compound.getString("TextOnPlayerEye"));
        }
        if (compound.contains("TrackingPlayer"))
        {
            this.dataManager.set(TRACKING_PLAYER, compound.getString("TrackingPlayer"));
        }
    }

    @Override
    protected void writeAdditional(CompoundNBT compound)
    {
        compound.putBoolean("expansion", this.getLowDimensionalExpansion());
        compound.putBoolean("bound", this.dataManager.get(BOUND));
        compound.putBoolean("TrackPlayer", this.dataManager.get(TRACK_PLAYER));
        compound.putString("text", this.dataManager.get(DISPLAY_TEXT));
        compound.putString("TextOnPlayerEye", this.dataManager.get(TEXT_ON_PLAYER_EYE));
        compound.putString("TrackingPlayer", this.dataManager.get(TRACKING_PLAYER));
    }

    @Override
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

package com.captainkray.krayscandles.entity;

import com.captainkray.krayscandles.init.InitEntityTypes;
import com.captainkray.krayscandles.init.InitParticles;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityWraith extends MonsterEntity {

    private static final DataParameter<String> PLAYER_NAME = EntityDataManager.createKey(EntityWraith.class, DataSerializers.STRING);
    private static final DataParameter<Integer> TYPE_ID = EntityDataManager.createKey(EntityWraith.class, DataSerializers.VARINT);

    public EntityWraith(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    public EntityWraith(World world, String playerName, WraithType wraithType, int x, int y, int z) {
        super(InitEntityTypes.WRAITH.get(), world);
        setLocationAndAngles(x, y, z, 0, 0);

        dataManager.set(PLAYER_NAME, playerName);
        dataManager.set(TYPE_ID, wraithType.getID());
    }

    public WraithType getWraithType() {
        return WraithType.fromID(dataManager.get(TYPE_ID));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 60D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public void livingTick() {

        if (!onGround && getMotion().y < 0.0D) {
            setMotion(getMotion().mul(1.0D, 0.6D, 1.0D));
        }

        if (world.isRemote) {
            world.addParticle(getWraithType().getParticle(), getPosXRandom(0.5D), getPosYRandom(), getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.LARGE_SMOKE, getPosXRandom(0.5D), getPosYRandom(), getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
        }

        super.livingTick();
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(dataManager.get(PLAYER_NAME) + "'s Wraith of " + getWraithType().getName());
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(PLAYER_NAME, "");
        dataManager.register(TYPE_ID, 0);
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        dataManager.set(PLAYER_NAME, nbt.getString("player_name"));
        dataManager.set(TYPE_ID, nbt.getInt("type_id"));
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        nbt.putString("player_name", dataManager.get(PLAYER_NAME));
        nbt.putInt("type_id", dataManager.get(TYPE_ID));
    }

    @Override
    public boolean getAlwaysRenderNameTagForRender() {
        return true;
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_HUSK_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_HUSK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_HUSK_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {}

    @Override
    public float getBrightness() {
        return 1.0F;
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 40;
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public enum WraithType {

        FIRE (0, "Fire", InitParticles.SOUL_FLAME_FIRE.get()),
        WATER (0, "Water", InitParticles.SOUL_FLAME_WATER.get()),
        AIR (0, "Air", InitParticles.SOUL_FLAME_AIR.get()),
        EXPLOSION (0, "Combustion", InitParticles.SOUL_FLAME_EXPLOSION.get()),
        MAGIC (0, "Magic", InitParticles.SOUL_FLAME_MAGIC.get()),
        MOB (0, "Monsters", InitParticles.SOUL_FLAME_MOB.get());

        private final int id;
        private final String name;
        private final BasicParticleType particle;

        WraithType(int id, String name, BasicParticleType particle) {
            this.id = id;
            this.name = name;
            this.particle = particle;
        }

        public static WraithType fromID(int id) {

            WraithType returnType = WraithType.FIRE;

            for (WraithType type : WraithType.values()) {

                if (type.getID() == id) {
                    returnType = type;
                }
            }

            return returnType;
        }

        public int getID () {
            return id;
        }

        public String getName() {
            return name;
        }

        public BasicParticleType getParticle() {
            return particle;
        }
    }
}

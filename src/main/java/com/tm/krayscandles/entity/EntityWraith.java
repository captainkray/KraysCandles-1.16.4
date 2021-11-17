package com.tm.krayscandles.entity;

import com.tm.krayscandles.init.InitParticles;
import com.tm.krayscandles.init.InitSounds;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class EntityWraith extends MonsterEntity {

    private static final DataParameter<String> PLAYER_NAME = EntityDataManager.createKey(EntityWraith.class, DataSerializers.STRING);

    public EntityWraith(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    public EntityWraith(EntityType<? extends MonsterEntity> type, World world, String playerName, double x, double y, double z) {
        super(type, world);
        setLocationAndAngles(x, y, z, 0, 0);
        dataManager.set(PLAYER_NAME, playerName);
    }

    public abstract WraithType getWraithType();

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
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

        if (dataManager.get(PLAYER_NAME).isEmpty()) {
            return new StringTextComponent("Wraith of " + getWraithType().getName());
        }

        return new StringTextComponent(dataManager.get(PLAYER_NAME) + "'s Wraith of " + getWraithType().getName());
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(PLAYER_NAME, "");
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        dataManager.set(PLAYER_NAME, nbt.getString("player_name"));
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        nbt.putString("player_name", dataManager.get(PLAYER_NAME));
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
    public boolean isImmuneToExplosions() {
        return true;
    }

    @Override
    public boolean isImmuneToFire() {
        return true;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return InitSounds.WRAITH_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return InitSounds.WRAITH_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return InitSounds.WRAITH_DEATH.get();
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

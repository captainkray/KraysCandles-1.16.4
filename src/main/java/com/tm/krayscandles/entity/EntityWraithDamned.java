package com.tm.krayscandles.entity;

import com.tm.krayscandles.init.InitEntityTypes;
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
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityWraithDamned extends MonsterEntity {

    private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    private int currentColorID = 0;

    public EntityWraithDamned(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    public EntityWraithDamned(World world, BlockPos pos) {
        super(InitEntityTypes.WRAITH_DAMNED.get(), world);
        setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 200D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 6);
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

            if (ticksExisted % 3 == 0) {
                world.addParticle(InitParticles.SOUL_FLAME_AIR.get(), getPosXRandom(0.5D), getPosYRandom(), getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
                world.addParticle(InitParticles.SOUL_FLAME_EXPLOSION.get(), getPosXRandom(0.5D), getPosYRandom(), getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
                world.addParticle(InitParticles.SOUL_FLAME_MAGIC.get(), getPosXRandom(0.5D), getPosYRandom(), getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
                world.addParticle(InitParticles.SOUL_FLAME_FIRE.get(), getPosXRandom(0.5D), getPosYRandom(), getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
                world.addParticle(InitParticles.SOUL_FLAME_MOB.get(), getPosXRandom(0.5D), getPosYRandom(), getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
                world.addParticle(InitParticles.SOUL_FLAME_WATER.get(), getPosXRandom(0.5D), getPosYRandom(), getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
            }

            world.addParticle(ParticleTypes.LARGE_SMOKE, getPosXRandom(0.5D), getPosYRandom(), getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
        }

        if (ticksExisted % 20 == 0) {

            currentColorID++;

            if (currentColorID == BossInfo.Color.values().length) {
                currentColorID = 0;
            }

            bossInfo.setColor(BossInfo.Color.values()[currentColorID]);
        }

        super.livingTick();
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Wraith of the Damned");
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
        return InitSounds.WRAITH_DAMNED_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return InitSounds.WRAITH_DAMNED_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return InitSounds.WRAITH_DAMNED_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {}

    @Override
    public float getBrightness() {
        return 1.0F;
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 100;
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

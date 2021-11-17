package com.tm.krayscandles.entity;

import com.tm.krayscandles.init.InitEntityTypes;
import com.tm.krayscandles.init.InitSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityVampire extends MonsterEntity {


    public EntityVampire(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }


    public EntityVampire(World world, int x, int y, int z) {
        super(InitEntityTypes.VAMPIRE.get(), world);
        setLocationAndAngles(x, y, z, 0, 0);
    }
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D)
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
        super.livingTick();
        if (!onGround && getMotion().y < 0.0D) {
            setMotion(getMotion().mul(1.0D, 1.0D, 1.0D));
        }

        if (world.isRemote) {
            world.addParticle(ParticleTypes.LARGE_SMOKE, getPosXRandom(0.5D), getPosYRandom(), getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
        }

        if (!this.world.isRemote) {
        if (world.isDaytime()){
            if(getDisplayName().getString().equalsIgnoreCase("The Count")){
                return;
            }
            else {
                playSound(InitSounds.VAMPIRE_VANISH.get(), 1,1);

                addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 20, 4));
                BatEntity vamp = new BatEntity(EntityType.BAT, world);
                vamp.setPosition(getPosX(), getPosY(), getPosZ());
                world.addEntity(vamp);
                remove();
            }
        }
        else {
            addPotionEffect(new EffectInstance(Effects.SPEED, 20));
        }
    }
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {

    }

    @Override
    public boolean getAlwaysRenderNameTagForRender() {
        return false;
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return true;
    }

    @Override
    public boolean isImmuneToExplosions() {
        return false;
    }

    @Override
    public boolean isImmuneToFire() {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return InitSounds.VAMPIRE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return InitSounds.VAMPIRE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return InitSounds.VAMPIRE_DEATH.get();
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
}


package com.captainkray.krayscandles.entity;



import com.captainkray.krayscandles.init.InitEntityTypes;
        import com.captainkray.krayscandles.init.InitSounds;
        import net.minecraft.block.BlockState;
        import net.minecraft.entity.EntityType;
        import net.minecraft.entity.MobEntity;
        import net.minecraft.entity.ai.attributes.AttributeModifierMap;
        import net.minecraft.entity.ai.attributes.Attributes;
        import net.minecraft.entity.ai.goal.*;
        import net.minecraft.entity.monster.MonsterEntity;
        import net.minecraft.entity.monster.SpiderEntity;
        import net.minecraft.entity.player.PlayerEntity;
        import net.minecraft.nbt.CompoundNBT;
        import net.minecraft.network.IPacket;
        import net.minecraft.potion.EffectInstance;
        import net.minecraft.potion.Effects;
        import net.minecraft.util.DamageSource;
        import net.minecraft.util.SoundEvent;
        import net.minecraft.util.math.BlockPos;
        import net.minecraft.world.World;
        import net.minecraftforge.fml.network.NetworkHooks;

public class EntityLeprechaun extends MonsterEntity {


    public EntityLeprechaun(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }


    public EntityLeprechaun(World world, int x, int y, int z) {
        super(InitEntityTypes.LEPRECHAUN.get(), world);
        setLocationAndAngles(x, y, z, 0, 0);
    }
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 10D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SpiderEntity.class, true));
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (!onGround && getMotion().y < 0.0D) {
            setMotion(getMotion().mul(1.0D, 1.0D, 1.0D));
        }


        if (!this.world.isRemote) {
            if (world.isNightTime()) {
                addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 20));
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
        return InitSounds.LEPRECHAUN_COINS.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return InitSounds.LEPRECHAUN_LAUGH.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return InitSounds.LEPRECHAUN_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {}

    @Override
    public float getBrightness() {
        return 1.0F;
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 20;
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


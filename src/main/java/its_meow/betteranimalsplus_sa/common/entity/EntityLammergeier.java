package its_meow.betteranimalsplus_sa.common.entity;

import com.fuzhudada.error.entity.projectiles.EntityFeather;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.packet.ParticleAnimation.PointShrink;
import com.fuzhudada.error.util.ModSoundHandler;
import its_meow.betteranimalsplus_sa.common.entity.ai.LammerMoveHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class EntityLammergeier extends EntityMob {
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.YELLOW, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
    protected static final DataParameter<Byte> FLYING = EntityDataManager.createKey(EntityLammergeier.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> shootTimer = EntityDataManager.createKey(EntityLammergeier.class, DataSerializers.VARINT);
    private static final DataParameter<BlockPos> shootPos = EntityDataManager.createKey(EntityLammergeier.class, DataSerializers.BLOCK_POS);
    private static final DataParameter<BlockPos> findPos = EntityDataManager.createKey(EntityLammergeier.class, DataSerializers.BLOCK_POS);
    private static final DataParameter<Integer> statue = EntityDataManager.createKey(EntityLammergeier.class, DataSerializers.VARINT);

    public int ticksForLand = 0;
    public double lastMotionY = 0;
    public BlockPos spawnPoint;

    public EntityLammergeier(World worldIn) {
        super(worldIn);
        this.setSize(2F, 2F);
        this.experienceValue=0;
        this.moveHelper = new LammerMoveHelper(this);
    }

    @Nonnull
    @Override
    protected PathNavigate createNavigator(@Nonnull World worldIn) {
        PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
        pathnavigateflying.setCanOpenDoors(false);
        pathnavigateflying.setCanFloat(true);
        pathnavigateflying.setCanEnterDoors(true);
        return pathnavigateflying;
    }

    @Override
    protected void initEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.tasks.addTask(3, new AIMeleeAttack(this));
        this.tasks.addTask(7, new AILookAround(this));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(FLYING, (byte) 0);
        this.dataManager.register(shootTimer, 0);
        this.dataManager.register(statue, 0);
        this.dataManager.register(shootPos, new BlockPos(0,0,0));
        this.dataManager.register(findPos, new BlockPos(0,0,0));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(128.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(3.0D);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(getStatue()==2 && source.getTrueSource() != null && getPassengers().size()>0){
            source.getTrueSource().dismountRidingEntity();
            setStatue(4);
            world.playSound(null, posX, posY, posZ, ModSoundHandler.BIRD , SoundCategory.PLAYERS, 10F, getRNG().nextFloat() * 0.1F + 0.9F);
        }

        if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityLivingBase && getRNG().nextDouble()<0.5){
            setAttackTarget((EntityLivingBase) source.getTrueSource());
        }

        return super.attackEntityFrom(source, amount);
    }

    @Override
    public void onUpdate() {
        int s=getStatue();
        if(s==1){
            int t=getShootTimer();
            Vec3d vec3d=new Vec3d(posX-getShootPos().getX(),0,posZ-getShootPos().getZ()).normalize();

            motionX=-1.5*vec3d.z;
            motionY=0.1;
            motionZ=1.5*vec3d.x;

            spawnTrickParticle();

            if(getAttackTarget() != null && t%5==0){
                EntityLivingBase target=getAttackTarget();
                Vec3d motion = new Vec3d(target.posX-posX, target.posY+target.getEyeHeight()-posY, target.posZ-posZ).normalize();

                EntityFeather g = new EntityFeather(world,this,50);
                g.setPosition(posX+motion.x*0.3,posY+motion.y*0.3,posZ+motion.z*0.3);
                g.motionX = motion.x;
                g.motionY = motion.y;
                g.motionZ = motion.z;
                g.setNoGravity(true);
                if (!world.isRemote) {
                    world.spawnEntity(g);
                }
            }

            if(t==1){
                world.playSound(null, posX, posY, posZ, ModSoundHandler.BIRD , SoundCategory.PLAYERS, 10F, getRNG().nextFloat() * 0.1F + 0.9F);
                ticksForLand=200;

                if(hasBlockOnTarget()){
                    findLandingPosition();
                    setStatue(3);
                }else{
                    setStatue(2);
                }
            }

            setShootTimer(t-1);
        }

        if(s==0){
            if(getAttackTarget() != null && !hasBlockOnTarget()){
                setStatue(2);
            }else if(ticksForLand<=0 && getAttackTarget() != null){
                setStatue(4);

                world.playSound(null, posX, posY, posZ, ModSoundHandler.BIRD , SoundCategory.PLAYERS, 10F, getRNG().nextFloat() * 0.1F + 0.9F);
            }

            if(ticksForLand>0) ticksForLand--;
        }

        if(s==3){
            BlockPos pos=getFindPos();
            getMoveHelper().setMoveTo(pos.getX(),pos.getY(),pos.getZ(),1);

            spawnTrickParticle();

            if(onGround){
                getMoveHelper().action=EntityMoveHelper.Action.WAIT;
                setStatue(0);
            }
        }

        if(s==4){
            BlockPos pos=getShootPos().add(0,18,0);
            double d=getDistance(pos.getX(),pos.getY(),pos.getZ());
            motionX=(pos.getX()-posX)/d;
            motionY=(pos.getY()-posY)/d;
            motionZ=(pos.getZ()-posZ)/d;

            spawnTrickParticle();

            if(d<1){
                setStatue(1);
                setShootTimer(240);

                Vec3d vec3d=new Vec3d(posX-pos.getX(),0,posZ-pos.getZ()).normalize();
                motionX=-1.5*vec3d.z;
                motionZ=1.5*vec3d.x;
            }
        }

        if(motionX != 0 || motionZ != 0){
            rotationYaw = -((float) MathHelper.atan2(motionX, motionZ)) * (180F / (float) Math.PI);
            renderYawOffset = rotationYaw;
        }

        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
        this.lastMotionY = this.motionY;

        if(ticksExisted==2){
            setShootPos(spawnPoint.getX(),spawnPoint.getY(),spawnPoint.getZ());
        }

        super.onUpdate();
    }

    private void spawnTrickParticle(){
        List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->getDistance(e)<128);
        for (EntityPlayerMP player : P) {
            PacketHandler.INSTANCE.sendTo(new PointShrink("FLAME",posX,posY,posZ,2,2,2,-0.1,5,true) ,player);
            PacketHandler.INSTANCE.sendTo(new PointShrink("TOTEM",posX,posY,posZ,2,2,2,-0.1,5,true) ,player);
        }
    }

    @Override
    public void setPosition(double x , double y , double z) {
        spawnPoint=new BlockPos(x,y,z);

        super.setPosition(x , y , z);
    }

    public void setShootTimer(int T){
        this.dataManager.set(shootTimer,T);
    }

    public int getShootTimer(){
        return this.dataManager.get(shootTimer);
    }

    public void setStatue(int T){
        this.dataManager.set(statue,T);
    }

    public int getStatue(){
        return this.dataManager.get(statue);
    }

    public void setShootPos(double x,double y,double z){
        this.dataManager.set(shootPos,new BlockPos(x,y,z));
    }

    public BlockPos  getShootPos(){
        return this.dataManager.get(shootPos);
    }

    public void setFindPos(double x,double y,double z){
        this.dataManager.set(findPos,new BlockPos(x,y,z));
    }

    public BlockPos getFindPos(){
        return this.dataManager.get(findPos);
    }

    public void addTrackingPlayer(@Nonnull EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void removeTrackingPlayer(@Nonnull EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        return distance < 128 * 128;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_PARROT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PARROT_DEATH;
    }

    @Override
    protected float getSoundPitch() {
        return 0.4F; // Lower pitch
    }

    @Override
    public boolean isOnLadder() {
        return false;
    }

    private boolean hasBlockOnTarget(){
        EntityLivingBase target=getAttackTarget();
        if(target != null){
            double y=target.posY;
            for(double y1=y;y1<256;y1++){
                if(!world.getBlockState(new BlockPos(target.posX,y1,target.posZ)).getBlock().equals(Blocks.AIR) && !world.getBlockState(new BlockPos(target.posX,y1,target.posZ)).getBlock().equals(Blocks.BARRIER)) return true;
            }
        }

        return false;
    }

    private void findLandingPosition() {
        Random random = getRNG();
        float x,z;

        if(getAttackTarget() != null){
            x = ((int) getAttackTarget().posX) + random.nextInt(16) - 8F + 0.5F;
            z = ((int) getAttackTarget().posZ) + random.nextInt(16) - 8F + 0.5F;
        }else{
            x = ((int) posX) + random.nextInt(16) - 8F + 0.5F;
            z = ((int) posZ) + random.nextInt(16) - 8F + 0.5F;
        }

        float y = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();

        setFindPos(x,y,z);
    }

    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    protected void collideWithEntity(@Nonnull Entity entityIn) {
        if (!this.getFlying()) {
            super.collideWithEntity(entityIn);
        }
    }

    @Override
    protected void collideWithNearbyEntities() {
        if (!this.getFlying()) {
            super.collideWithNearbyEntities();
        }
    }

    @Override
    public void fall(float distance, float damageMultiplier) {}

    @Override
    protected void updateFallState(double y, boolean onGroundIn, @Nonnull IBlockState state, @Nonnull BlockPos pos) {
        if (!this.getFlying()) {
            super.updateFallState(y, onGroundIn, state, pos);
        }
    }

    /**
     * Return whether this entity should NOT trigger a pressure plate or a tripwire.
     */
    @Override
    public boolean doesEntityNotTriggerPressurePlate() {
        return !this.getFlying();
    }

    public boolean getFlying() {
        return (this.dataManager.get(FLYING) & 1) != 0;
    }

    public void setFlying(boolean isFlying) {
        byte b0 = this.dataManager.get(FLYING);

        if (isFlying) {
            this.dataManager.set(FLYING, (byte) (b0 | 1));
        } else {
            this.dataManager.set(FLYING, (byte) (b0 & -2));
        }
        this.setNoGravity(isFlying);
    }

    public BlockPos fromPolarCoordinates(PolarVector3D polar) {
        double r = polar.getR();
        double lat = polar.getThetaY() / 360;
        double lon = polar.getThetaX() / 360;
        double x = r * Math.sin(lat) * Math.cos(lon);
        double y = r * Math.sin(lat) * Math.sin(lon);
        double z = r * Math.cos(lat);
        return new BlockPos(x, y, z);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setByte("LammerFlying", this.dataManager.get(FLYING));
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(@Nonnull NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.dataManager.set(FLYING, compound.getByte("LammerFlying"));
    }

    @Override
    public void updatePassenger(@Nonnull Entity passenger) {
        if (this.isPassenger(passenger)) {
            passenger.setPosition(this.posX + this.motionX, this.posY - passenger.height - 0.05 + this.motionY, this.posZ + this.motionZ);
            this.motionY += Math.abs(passenger.motionY);
            if (passenger instanceof EntityLivingBase && (this.getAttackTarget() == null || this.getAttackTarget() != passenger)) {
                this.setAttackTarget((EntityLivingBase) passenger);
            }
            if (this.world.isRemote) {
                this.applyOrientationToEntity(passenger);
            }
        }
    }

    public void onDeath(@Nonnull DamageSource cause)
    {
        super.onDeath(cause);
        if(cause.getTrueSource() instanceof EntityPlayer){
            EntityPlayer player=(EntityPlayer) cause.getTrueSource();
            if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.GOLDEN_FEATHER)))
            {
                player.dropItem(new ItemStack(ModItems.GOLDEN_FEATHER), false);
            }
        }else{
            EntityItem entityItem=new EntityItem(this.world);
            entityItem.setItem(new ItemStack(ModItems.GOLDEN_FEATHER));
            entityItem.setPosition(this.posX,this.posY,this.posZ);
            if(! this.world.isRemote){
                this.world.spawnEntity(entityItem);
            }
        }
    }

    static class AIMeleeAttack extends EntityAIAttackMelee {

        protected World world;
        protected EntityLammergeier attacker;
        /**
         * An amount of decrementing ticks that allows the entity to attack once the
         * tick reaches 0.
         */
        protected int attackTick;
        protected double liftY = 0;

        public AIMeleeAttack(EntityLammergeier lam) {
            super(lam, 0.5D, false);
            this.attacker = lam;
            this.world = lam.getEntityWorld();
            this.setMutexBits(2);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

            if (entitylivingbase == null || attacker.getStatue() != 2) {
                return false;
            } else return entitylivingbase.isEntityAlive();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        @Override
        public boolean shouldContinueExecuting() {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

            if (entitylivingbase == null || attacker.getStatue() != 2) {
                return false;
            } else if (!entitylivingbase.isEntityAlive()) {
                return false;
            } else {
                return !(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer) entitylivingbase).isSpectator() && !((EntityPlayer) entitylivingbase).isCreative();
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting() {

        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by
         * another one
         */
        @Override
        public void resetTask() {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

            if (entitylivingbase instanceof EntityPlayer && (((EntityPlayer) entitylivingbase).isSpectator() || ((EntityPlayer) entitylivingbase).isCreative())) {
                this.attacker.setAttackTarget(null);
            }
        }

        @Override
        public void updateTask() {
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

            double targetX = entitylivingbase.posX;
            double targetY = entitylivingbase.posY;
            double targetZ = entitylivingbase.posZ;

            double distanceToTarget = this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ);

            // Reduce time till attack
            this.attackTick--;

            double reachToTarget = this.getAttackReachSqr(entitylivingbase);

            // If the entity can reach its target and it's time to attack, reset the timer
            // and attack if the entity is not grabbed
            if (distanceToTarget <= reachToTarget && this.attackTick <= 0) {
                this.attackTick = 20;
                if (!entitylivingbase.isRidingOrBeingRiddenBy(this.attacker)) {
                    this.attacker.attackEntityAsMob(entitylivingbase);
                }
            }

            // If the entity is not grabbing a target, set it to move to its target

            if (!entitylivingbase.isRiding()) {
                if(attacker.hasBlockOnTarget() && attacker.onGround){
                    attacker.getMoveHelper().action=EntityMoveHelper.Action.WAIT;
                    attacker.setStatue(0);
                }else{
                    this.attacker.getMoveHelper().setMoveTo(targetX, targetY, targetZ, 1.0D);
                }
            } else { // If the entity is grabbing a target, set it to move upwards
                this.attacker.getMoveHelper().setMoveTo(targetX, this.liftY + 15, targetZ, 5.0D);
            }

            // If the entity is in range and entity is not grabbing a target and the
            // target's height is less than 3 blocks
            if (distanceToTarget <= reachToTarget && !entitylivingbase.isRiding() && entitylivingbase.height <= 3 && this.attackTick == 20) {
                // Move the entity upwards to avoid being stuck in the ground
                this.attacker.setLocationAndAngles(this.attacker.posX, this.attacker.posY + entitylivingbase.height + 2, this.attacker.posZ, this.attacker.rotationYaw, this.attacker.rotationPitch);
                // Grab the target
                entitylivingbase.startRiding(this.attacker, true);
                entitylivingbase.sendMessage(new TextComponentTranslation("bird.grab"));
                // Set liftY so entity can continue moving up from the spot
                this.liftY = entitylivingbase.posY;
                // Move upwards
                this.attacker.getMoveHelper().setMoveTo(targetX, this.liftY + 15, targetZ, 5.0D);
            }
        }
    }

    static class AILookAround extends EntityAIBase {
        private final EntityLammergeier parentEntity;

        public AILookAround(EntityLammergeier lam) {
            this.parentEntity = lam;
            this.setMutexBits(2);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            return this.parentEntity.getFlying();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask() {
            if (this.parentEntity.getAttackTarget() == null) {
                this.parentEntity.rotationYaw = -((float) MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ)) * (180F / (float) Math.PI);
                this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
            } else {
                EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();

                if (entitylivingbase.getDistanceSq(this.parentEntity) < 4096.0D) {
                    double d1 = entitylivingbase.posX - this.parentEntity.posX;
                    double d2 = entitylivingbase.posZ - this.parentEntity.posZ;
                    this.parentEntity.rotationYaw = -((float) MathHelper.atan2(d1, d2)) * (180F / (float) Math.PI);
                    this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
                }
            }
        }
    }

    public static class PolarVector3D {

        private double thetaX;
        private double thetaY;
        private double r;

        public PolarVector3D(double thetaxIn, double thetayIn, double rIn) {
            this.thetaX = thetaxIn;
            this.thetaY = thetayIn;
            this.r = rIn;
        }

        public double getThetaX() {
            return this.thetaX;
        }

        public void setThetaX(double thetaX) {
            this.thetaX = thetaX;
        }

        public double getThetaY() {
            return this.thetaY;
        }

        public void setThetaY(double thetaY) {
            this.thetaY = thetaY;
        }

        public double getR() {
            return this.r;
        }

        public void setR(double r) {
            this.r = r;
        }

    }
}

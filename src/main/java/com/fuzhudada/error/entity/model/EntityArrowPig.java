package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.packet.MessageParticle;
import com.fuzhudada.error.packet.PacketHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.List;

public class EntityArrowPig extends EntityCreature {
    private static final DataParameter<String> attackerName = EntityDataManager.createKey(EntityArrowPig.class, DataSerializers.STRING);
    private static final DataParameter<Integer> timer = EntityDataManager.createKey(EntityArrowPig.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> state = EntityDataManager.createKey(EntityArrowPig.class, DataSerializers.VARINT);
    public final EntityLivingBase attacker;
    public float damage;
    public HashSet<Entity> set;

    public EntityArrowPig(World worldIn)
    {
        super(worldIn);
        this.attacker=null;
        this.damage=20;
        experienceValue=0;
        setSize(1,0.8F);
        setAlwaysRenderNameTag(false);
        this.set=new HashSet<>();
    }

    public EntityArrowPig(World worldIn,EntityLivingBase attacker,float damage){
        super(worldIn);
        this.attacker=attacker;
        this.damage=damage;
        experienceValue=0;
        setSize(1,1);
        setAttackerName(attacker.getName());
        setAlwaysRenderNameTag(false);
        this.set=new HashSet<>();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }

    protected void entityInit()
    {
        this.dataManager.register(attackerName, "");
        this.dataManager.register(timer,0);
        this.dataManager.register(state,0);
        super.entityInit();
    }

    public void setAttackerName(String attackerName)
    {
        this.dataManager.set(EntityArrowPig.attackerName ,attackerName);
    }

    public String getAttackerName()
    {
        return this.dataManager.get(attackerName);
    }

    public void setTimer(int T){
        this.dataManager.set(timer,T);
    }

    public int getTimer(){
        return this.dataManager.get(timer);
    }

    public void setState(int T){
        this.dataManager.set(state,T);
    }

    public int getState(){
        return this.dataManager.get(state);
    }

    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_PIG_HURT;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source , float amount) {
        if(getState()==0) return false;
        return super.attackEntityFrom(source , amount);
    }

    public void onUpdate()
    {
        if(isEntityAlive()){
            int s=getState();
            if(attacker != null && (s==0 || s==2)){
                double d=s==0?3:1;
                List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class,
                        e -> e.getDistance(this)<d
                                && (getAttackerName().isEmpty() || !e.getName().equals(getAttackerName()))
                                && !e.equals(this)
                                && e.isEntityAlive()
                                && !(e instanceof EntityArrowPig)
                                && !(e instanceof EntityArmorStand));
                if(G.size()>0){
                    for(EntityLivingBase e:G){
                        if(s == 0 || !set.contains(e)){
                            e.attackEntityFrom(DamageSource.causeThrownDamage(this,attacker),s==0?damage:20);

                            if(s==2){
                                set.add(e);
                            }
                        }

                        if(s==0){
                            if(this.posX*this.posX+this.posZ*this.posZ>0){
                                e.knockBack(attacker,0.9f,-this.motionX,-this.motionZ);
                            }
                        }else{
                            if((this.posX - e.posX)*(this.posX - e.posX)+(this.posZ - e.posZ)*(this.posZ - e.posZ) != 0) {
                                e.knockBack(attacker, 0.6f, this.posX - e.posX, this.posZ - e.posZ);
                            }
                        }
                    }
                }
            }

            if(getState()==0 && onGround){
                setState(1);
                setTimer(20);
            }

            int t=getTimer();
            s=getState();
            Vec3d F=getLookVec();

            if(t>0){
                setTimer(t-1);

                if(t==1 && s==1){
                    setState(2);
                    setTimer(30);
                }

                if(s==2){
                    motionX=0.4*F.x;
                    motionZ=0.4*F.z;
                    List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->getDistance(e)<64);
                    for (EntityPlayerMP player : P) {
                        PacketHandler.INSTANCE.sendTo(new MessageParticle(posX,posY,posZ,getLookVec(),5) ,player);
                    }

                    if(t==1){
                        attackEntityFrom(DamageSource.GENERIC,1000);
                    }
                }
            }

            BlockPos pos=new BlockPos(posX+F.x,posY,posZ+F.z);
            IBlockState blockState=world.getBlockState(pos);
            AxisAlignedBB axisAlignedBB=blockState.getBlock().getCollisionBoundingBox(blockState,world,pos);
            if((axisAlignedBB != null && axisAlignedBB.maxY>0.51) || ticksExisted>=200){
                attackEntityFrom(DamageSource.GENERIC,1000);
            }
        }

        super.onUpdate();
    }

    public void onDeath(@Nonnull DamageSource cause)
    {
        super.onDeath(cause);
        world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_PIG_DEATH, SoundCategory.PLAYERS, 3.0F, getRNG().nextFloat() * 0.4F + 1.2F);
        EntityItem entityItem=new EntityItem(this.world);
        entityItem.setItem(new ItemStack(Items.PORKCHOP));
        entityItem.setPosition(this.posX,this.posY,this.posZ);
        if(! this.world.isRemote){
            this.world.spawnEntity(entityItem);
        }
    }
}

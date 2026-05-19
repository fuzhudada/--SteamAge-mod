package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.item.shield.NPCmodShield;
import com.fuzhudada.error.item.shield.ItemShieldBase;
import com.fuzhudada.error.item.sword.ISteamWeapon;
import com.fuzhudada.error.item.sword.ItemShuangZiJian;
import com.fuzhudada.error.packet.MessageParticle;
import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.particle.ModParticleHandler;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class EntityBomb extends EntityLiving {
    private final EntityLivingBase Igniter;
    private static final DataParameter<Integer> type = EntityDataManager.createKey(EntityBomb.class, DataSerializers.VARINT);
    private final float damage;

    public EntityBomb(World worldIn) {
        super(worldIn);
        this.Igniter=null;
        this.damage=185;
    }

    public EntityBomb(World worldIn,EntityLivingBase igniter,float damage) {
        super(worldIn);
        this.Igniter=igniter;
        this.damage=damage;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(type, 0);
    }

    public void setType(int T){
        this.dataManager.set(type,T);
    }

    public int getType(){
        return this.dataManager.get(type);
    }

    public void onUpdate()
    {
        if(this.ticksExisted >= 60){
            List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class,
                    e -> getDistanceSq(e)<64
                            && e.isEntityAlive()
                            && !(e instanceof EntityArmorStand)
                            && (!(Igniter instanceof EntityPlayer) || e.equals(Igniter) || !(e instanceof EntityPlayer)));
            EntityLivingBase xnj=null;
            if(getType()==3){
                for (EntityLivingBase e : G) {
                    if(e instanceof EntityLightningCowHorn){
                        xnj=e;
                        break;
                    }
                }
            }

            if(xnj==null){
                for(EntityLivingBase e:G){
                    ItemStack shield = e.isHandActive() ? e.getActiveItemStack() : ItemStack.EMPTY;
                    if(getType() != 3 && !shield.isEmpty() && IsShield(shield)){
                        if(shield.getItemDamage() < shield.getMaxDamage() - damage) {
                            shield.setItemDamage(shield.getItemDamage() + (int)damage);
                        }else{
                            shield.shrink(1);
                        }

                        if(shield.getItem() == ModItems.ZHEN_QI_DUN && e instanceof EntityPlayer){
                            absorbSteam((EntityPlayer) e);
                        }

                    }else if(!(ModEnchantmentInit.HasCiTiao(e,ModEnchantmentInit.ZHA_DAN_DA_SHI) && e.equals(Igniter))){
                        if(getType()==3){
                            e.attackEntityFrom(ModDamageSource.causeElectricalDamage(Igniter), damage);
                        }else{
                            e.attackEntityFrom(ModDamageSource.causeFireBulletDamage(this,Igniter), damage);
                        }
                    }

                    if(getType() != 3 && (this.posX - e.posX)*(this.posX - e.posX)+(this.posZ - e.posZ)*(this.posZ - e.posZ) != 0) {
                        e.knockBack(Igniter, 0.8f, this.posX - e.posX, this.posZ - e.posZ);
                    }

                    if(getType()==1){
                        e.setFire(5);
                    }

                    if(getType()==2){
                        e.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,100,1));
                        e.setRevengeTarget(null);
                    }

                    if(getType()==3){
                        e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,20,10,false,false));
                        spawnLightning(world,this,e,0);
                    }
                }
            }else{
                xnj.attackEntityFrom(ModDamageSource.causeElectricalDamage(Igniter),damage);
                spawnLightning(this.world,this,xnj,-0.8125);
            }
            this.spawnExplosionParticle();

            if(world.isRemote){
                if(getType()==1){
                    for(int i=0;i<100;i++) {
                        this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.625, this.posZ, Math.random()*0.6-0.3, Math.random()*0.6-0.3, Math.random()*0.6-0.3,1);
                    }
                }

                if(getType()==2){
                    for(int i=0;i<100;i++) {
                        this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.625, this.posZ, Math.random()*0.6-0.3, Math.random()*0.6-0.3, Math.random()*0.6-0.3,1);
                    }
                }
            }

            if(!this.isDead){
                if(getType()==3){
                    this.world.playSound(null, this.posX, this.posY, this.posZ, ModSoundHandler.ELECTRICITY , SoundCategory.BLOCKS, 4F, this.getRNG().nextFloat() * 0.1F + 0.9F);
                }else{
                    this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4F, this.getRNG().nextFloat() * 0.1F + 0.9F);
                }
            }

            this.setDead();
        }else{
            if(world.isRemote){
                if(getType()==3){
                    this.world.spawnParticle(ModParticleHandler.GLOW ,this.posX,this.posY+0.625,this.posZ,Math.random()-0.5,0.5*Math.random(),Math.random()-0.5);
                }else{
                    this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,this.posX,this.posY+0.625,this.posZ,0,0.4,0);
                }
            }
        }

        super.onUpdate();
    }

    @Override
    public float getEyeHeight() {
        return 0.5F;
    }

    protected boolean IsShield(ItemStack Item) {
        return (Item.getItem() == Items.SHIELD || Item.getItem() instanceof ItemShieldBase || Arrays.asList(NPCmodShield.NPCShields).contains(Item.getUnlocalizedName()) || Item.getItem() instanceof ItemShuangZiJian);
    }

    private void absorbSteam(EntityPlayer playerIn){
        int s=100;
        for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
            ItemStack stack = playerIn.inventory.getStackInSlot(i);
            if(stack.getItem().equals(ModItems.KONG_GUAN_ZI)){
                playerIn.inventory.setInventorySlotContents(i,new ItemStack(ModItems.ZHEN_QI_GUAN_ZI,1,100-s));
                break;
            }

            if (stack.getItem() instanceof ISteamWeapon) {
                float k1=ISteamWeapon.getSteam(stack);
                float k2=((ISteamWeapon)stack.getItem()).maxItemSteam();
                if(s<=k2-k1){
                    ISteamWeapon.setSteam(stack,k1+s);
                    break;
                }else{
                    ISteamWeapon.setSteam(stack,k2);
                    s-=(k2-k1);
                }
            }
        }
    }

    private void spawnLightning(World world , EntityLivingBase e1 , EntityLivingBase e2,double dx){
        List<EntityPlayerMP> P = world.getEntities(EntityPlayerMP.class, e->e.getDistanceSq(e1)<4096);
        for (EntityPlayerMP player : P) {
            PacketHandler.INSTANCE.sendTo(new MessageParticle(e1.posX,e1.posY+e1.getEyeHeight(),e1.posZ,new Vec3d(e2.posX+dx,e2.posY+e2.getEyeHeight(),e2.posZ),8),player);
        }
    }
}

package com.fuzhudada.error.entity.model;

import com.fuzhudada.error.ModDamageSource;
import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.item.shield.NPCmodShield;
import com.fuzhudada.error.item.shield.ItemShieldBase;
import com.fuzhudada.error.item.sword.ISteamWeapon;
import com.fuzhudada.error.item.sword.ItemShuangZiJian;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class EntityAntiTankLandmine extends EntityCreature {
    private final EntityLivingBase Igniter;
    private static final int damage=300;

    public EntityAntiTankLandmine(World worldIn) {
        super(worldIn);
        this.Igniter=null;
        this.setSize(this.width, this.height * 0.2F);
    }

    public EntityAntiTankLandmine(World worldIn,EntityLivingBase igniter) {
        super(worldIn);
        this.Igniter=igniter;
        this.setSize(this.width, this.height * 0.2F);
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(source.getTrueSource() instanceof EntityTank && !source.isProjectile()){
            source.getTrueSource().attackEntityFrom(ModDamageSource.causeRpgDamage(this,Igniter), damage);
            explode();
        }

        return super.attackEntityFrom(source,amount);
    }

    public void onUpdate()
    {
        if(this.ticksExisted >= 600){
            this.setDead();
        }else{
            this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,this.posX,this.posY+0.625,this.posZ,0,0.4,0);
        }

        super.onUpdate();
    }

    protected boolean IsShield(ItemStack Item) {
        return (Item.getItem() == Items.SHIELD || Item.getItem() instanceof ItemShieldBase || Arrays.asList(NPCmodShield.NPCShields).contains(Item.getUnlocalizedName()) || Item.getItem() instanceof ItemShuangZiJian);
    }

    private void absorbSteam(EntityPlayer playerIn){
        int s=100;
        for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
            ItemStack stack = playerIn.inventory.getStackInSlot(i);
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

    private void explode(){
        List<EntityLivingBase> G = world.getEntities(EntityLivingBase.class,
                e -> e.getDistance(this)<3 && e.isEntityAlive() && !(e instanceof EntityTank) && !(e instanceof EntityArmorStand));
        for(EntityLivingBase e:G){

            ItemStack shield = e.isHandActive() ? e.getActiveItemStack() : ItemStack.EMPTY;
            if(!shield.isEmpty() && IsShield(shield)){
                if(shield.getItemDamage() < shield.getMaxDamage() - damage) {
                    shield.setItemDamage(shield.getItemDamage() + damage);
                }else{
                    shield.shrink(1);
                }

                if(shield.getItem() == ModItems.ZHEN_QI_DUN && Igniter instanceof EntityPlayer){
                    absorbSteam((EntityPlayer) Igniter);
                }

            }else if(!(ModEnchantmentInit.HasCiTiao(e,ModEnchantmentInit.ZHA_DAN_DA_SHI) && e.equals(Igniter))){
                e.attackEntityFrom(ModDamageSource.causeRpgDamage(this,Igniter), damage);
            }

            if((this.posX - e.posX)*(this.posX - e.posX)+(this.posZ - e.posZ)*(this.posZ - e.posZ) != 0) {
                e.knockBack(Igniter, 0.8f, this.posX - e.posX, this.posZ - e.posZ);
            }
        }
        this.spawnExplosionParticle();
        this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4F, this.getRNG().nextFloat() * 0.1F + 0.9F);

        this.setDead();
    }
}

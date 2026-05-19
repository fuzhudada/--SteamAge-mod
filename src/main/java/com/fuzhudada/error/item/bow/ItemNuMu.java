package com.fuzhudada.error.item.bow;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.entity.projectiles.EntityHuangJinJian;
import com.fuzhudada.error.entity.projectiles.EntityNuMu;
import com.fuzhudada.error.item.Item.ItemArrowBag;
import com.fuzhudada.error.item.ModItems;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Objects;

public class ItemNuMu extends com.somebody.idlframewok.item.ItemBase implements IBow{
    public ItemNuMu(String name)
    {
        super(name);
        this.maxStackSize = 1;
        this.setMaxDamage(384);
        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                {
                    return 0.0F;
                }
                else
                {
                    return entityIn.getActiveItemStack().getItem() != ModItems.NU_MU ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
    }

    private ItemStack findAmmo(EntityPlayer player)
    {
        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isArrow(itemstack))
                {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }

    protected boolean isArrow(ItemStack stack)
    {
        if(stack.getItem() instanceof ItemArrowBase){
            byte b=((ItemArrowBase)(stack.getItem())).type;
            return b==0 || b==4;
        }else if(stack.getItem() instanceof ItemArrowBag){
            if(stack.getItemDamage()<stack.getMaxDamage() && stack.hasTagCompound() && stack.getTagCompound().hasKey("arrow")){
                byte b=stack.getTagCompound().getByte("arrow");
                return b==0 || b==4;
            }
        }
        return false;
    }

    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     */
    public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer && com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(stack) == 0)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(entityplayer);

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag)
            {
                if (itemstack.isEmpty())
                {
                    itemstack = new ItemStack(ModItems.JIAN2);
                }

                float f=70*(float)i/20;
                if(f>70) f=70;

                    boolean flag1 = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack)>0;

                    if (!worldIn.isRemote)
                    {
                        byte b=-1;
                        if(itemstack.getItem() instanceof ItemArrowBag){
                            b=itemstack.getTagCompound().getByte("arrow");
                        }

                        if(itemstack.getItem() == ModItems.HUANG_JIN_JIAN || b==4){
                            EntityHuangJinJian g = new EntityHuangJinJian(worldIn);

                            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                            g.DAMAGE=f+1.875F*j;
                            g.ATTACKER=entityplayer;
                            if(f>=70 && entityplayer.isPotionActive(ModPotions.CEN_DIAN) && !entityplayer.isPotionActive(MobEffects.POISON)) g.addTag("numu");
                            g.Fire=EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0;
                            g.setNoGravity(true);
                            Vec3d F = entityplayer.getLookVec().normalize();
                            g.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, 1.5F, 1.0F);
                            g.setPosition(entityplayer.posX + 1.5 * F.x, entityplayer.posY+entityplayer.getEyeHeight()+1.5*F.y, entityplayer.posZ + 1.5 * F.z);

                            addHuangJinJianEffect(itemstack,g,stack,entityplayer,worldIn,i);

                            worldIn.spawnEntity(g);
                        }else{
                            EntityNuMu NM = new EntityNuMu(worldIn);

                            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                            if (j > 0)
                            {
                                f+=j*1.875F;
                            }

                            NM.huoshi = EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0;

                            stack.damageItem(1, entityplayer);

                            NM.DAMAGE=f;
                            NM.ATTACKER=entityplayer;
                            NM.setIsCritical(f>=70 && entityplayer.isPotionActive(ModPotions.CEN_DIAN) && !entityplayer.isPotionActive(MobEffects.POISON));
                            if(b>-1){
                                NM.setType(b);
                            }else if(itemstack.getItem() instanceof ItemArrowBase){
                                NM.setType(((ItemArrowBase)itemstack.getItem()).getType());
                            }
                            NM.setNoGravity(true);
                            Vec3d F = entityplayer.getLookVec().normalize();
                            NM.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, 1.5F, 1.0F);
                            NM.setPosition(entityplayer.posX + 1.5 * F.x, entityplayer.posY+entityplayer.getEyeHeight()+1.5*F.y, entityplayer.posZ + 1.5 * F.z);

                            worldIn.spawnEntity(NM);
                        }
                    }

                    worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                if (!flag1 && !entityplayer.capabilities.isCreativeMode && !(ModEnchantmentInit.HasCiTiao(entityplayer,ModEnchantmentInit.QIN_JIA) && Math.random()<0.5))
                    {
                        if(itemstack.getItem() instanceof ItemArrowBag){
                            itemstack.setItemDamage(itemstack.getItemDamage()+1);
                        }else{
                            itemstack.shrink(1);

                            if (itemstack.isEmpty())
                            {
                                entityplayer.inventory.deleteStack(itemstack);
                            }
                        }
                    }

                    entityplayer.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
            }
        }
    }

    public void addHuangJinJianEffect(ItemStack arrow,EntityHuangJinJian entityHuangJinJian,ItemStack bow,EntityPlayer player,World world,int i){}

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(@Nonnull ItemStack stack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Nonnull
    public EnumAction getItemUseAction(@Nonnull ItemStack stack)
    {
        return EnumAction.BOW;
    }

    /**
     * Called when the equipped item is right clicked.
     */
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = !this.findAmmo(playerIn).isEmpty();

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;

        if (!playerIn.capabilities.isCreativeMode && !flag)
        {
            return new ActionResult(EnumActionResult.FAIL, itemstack);
        }
        else
        {
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(EnumActionResult.SUCCESS , itemstack);
        }
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }

    @Override
    public float getDamage() {
        return 70;
    }
}

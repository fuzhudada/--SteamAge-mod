package com.fuzhudada.error.item.bow;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.entity.projectiles.EntityHuangJinJian;
import com.fuzhudada.error.item.Item.ItemArrowBag;
import com.fuzhudada.error.item.ModItems;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class ItemBowBase extends com.somebody.idlframewok.item.ItemBase implements IBow{
    public int damage;
    public int shootSpeed;
    public Item[] canUse;

    public ItemBowBase(String name, int damage,Item[] canUse) {
        this(name,damage,1,canUse);
    }

    public ItemBowBase(String name, int damage,int shootSpeed,Item[] canUse) {
        super(name);
        this.damage=damage;
        this.shootSpeed=shootSpeed;
        this.canUse=canUse;
        this.maxStackSize = 1;
        this.setMaxDamage(384);
        this.addPropertyOverride(new ResourceLocation("pull"), (stack,worldIn,entityIn) -> {
            if (entityIn == null)
            {
                return 0.0F;
            }
            else
            {
                return entityIn.getActiveItemStack().getItem() != this ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
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

    protected ItemStack findAmmo(EntityPlayer player)
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

    protected boolean isArrow(ItemStack arrow){
        if(arrow.getItem() instanceof ItemArrowBag){
            if(arrow.getItemDamage()<arrow.getMaxDamage() && arrow.hasTagCompound() && arrow.getTagCompound().hasKey("arrow")){
                byte b=arrow.getTagCompound().getByte("arrow");
                for(Item item:canUse){
                    if(item instanceof ItemArrowBase && ((ItemArrowBase) item).type == b){
                        return true;
                    }
                }
            }
        }else{
            for(Item item:canUse){
                if(arrow.getItem()==item || (arrow.getItem() instanceof ItemArrowBase && item instanceof ItemArrowBase && ((ItemArrowBase)arrow.getItem()).type == ((ItemArrowBase)item).type)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean onShoot(ItemStack bow,ItemStack arrow_itemStack,EntityPlayer player,World world,int i){
        return true;
    }

    public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(entityplayer);

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i=i*shootSpeed;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag)
            {
                if (itemstack.isEmpty())
                {
                    itemstack = new ItemStack(ModItems.JIAN2);
                }

                float f = getArrowVelocity(i);

                if ((double)f >= 0.1D && onShoot(stack,itemstack,entityplayer,worldIn,i))
                {
                    boolean flag1 = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack)>0;

                    if (!worldIn.isRemote)
                    {
                        byte b=-1;
                        if(itemstack.getItem() instanceof ItemArrowBag){
                            b=itemstack.getTagCompound().getByte("arrow");
                        }

                        ItemArrowBase itemarrow = (ItemArrowBase)(itemstack.getItem() instanceof ItemArrowBase ? itemstack.getItem() : ModItems.JIAN2);
                        if(b==4 || itemarrow.getType()==4){
                            EntityHuangJinJian g = new EntityHuangJinJian(worldIn);

                            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                            g.DAMAGE=(this.damage*3.75F+1.875F*j)*f;
                            g.ATTACKER=entityplayer;
                            g.Fire=EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0;
                            g.setNoGravity(true);
                            Vec3d F = entityplayer.getLookVec().normalize();
                            g.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, 1.5F, 1.0F);
                            g.setPosition(entityplayer.posX + 1.5 * F.x, entityplayer.posY+entityplayer.getEyeHeight()+1.5*F.y, entityplayer.posZ + 1.5 * F.z);

                            addHuangJinJianEffect(itemarrow,g,stack,entityplayer,worldIn,i);

                            worldIn.spawnEntity(g);
                        }else {
                            EntityTippedArrow entityarrow = (EntityTippedArrow) itemarrow.createArrow(worldIn, itemstack, entityplayer);

                            addArrowEffect(itemarrow, entityarrow, stack, entityplayer, worldIn, i, b);

                            if (com.fuzhudada.error.enchantments.ModEnchantmentInit.XIN_SHOU_JIAO_CHENG.getEnchantmentLevel(stack) > 0) {
                                entityarrow.setDamage(1);
                            } else {
                                entityarrow.setDamage(damage);
                            }
                            entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                            if (f == 1.0F) {
                                entityarrow.setIsCritical(true);
                            }

                            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                            if (j > 0) {
                                entityarrow.setDamage(entityarrow.getDamage() + (double) j * 0.5D + 0.5D);
                            }

                            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                            if (k > 0) {
                                entityarrow.setKnockbackStrength(k);
                            }

                            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                                entityarrow.setFire(100);
                            }

                            int l = EnchantmentHelper.getEnchantmentLevel(ModEnchantmentInit.ZHI_CAN_DA_JI, stack);

                            if (l > 0) {
                                entityarrow.addEffect(new PotionEffect(ModPotions.ZHI_TI_PO_HUAI, 20, l));
                            }

                            entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;

                            worldIn.spawnEntity(entityarrow);
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
    }

    public static float getArrowVelocity(int charge)
    {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f > 1.0F)
        {
            f = 1.0F;
        }

        return f;
    }

    public void addArrowEffect(ItemArrowBase arrow,EntityTippedArrow entityTippedArrow,ItemStack bow,EntityPlayer player,World world,int i,byte b){
        byte c=b>-1?b:arrow.getType();

        switch (c){
            case 1:
                entityTippedArrow.setFire(100);
                break;

            case 2:
                entityTippedArrow.addEffect(new PotionEffect(ModPotions.ZHI_TI_PO_HUAI,60,1));
                break;

            case 3:
                entityTippedArrow.addEffect(new PotionEffect(ModPotions.SU_FU,60,0));
                break;

            case 5:
                entityTippedArrow.addTag("lightning");
                break;
        }
    }

    public void addHuangJinJianEffect(ItemArrowBase arrow,EntityHuangJinJian entityHuangJinJian,ItemStack bow,EntityPlayer player,World world,int i){}

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

        if ((!playerIn.capabilities.isCreativeMode && !flag) || com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(itemstack)>0)
        {
            return new ActionResult(EnumActionResult.FAIL, itemstack);
        }
        else
        {
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
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
        return damage*3.75F+1;
    }
}

package com.fuzhudada.error.item.bow;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.entity.model.EntityArrowPig;
import com.fuzhudada.error.item.Item.ItemArrowBag;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ItemPigHammer extends ItemBowBase{
    public ItemPigHammer(String name , int damage , Item[] canUse) {
        super(name , damage , canUse);
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

                        EntityArrowPig entityarrow = new EntityArrowPig(worldIn, entityplayer, Math.min(damage,damage*i/20F));

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                        if (j > 0) {
                            entityarrow.damage+=1.875*j;
                        }

                        Vec3d F = entityplayer.getLookVec().normalize();
                        entityarrow.setPosition(entityplayer.posX + 1.5 * F.x, entityplayer.posY+entityplayer.getEyeHeight()+1.5*F.y, entityplayer.posZ + 1.5 * F.z);
                        entityarrow.motionX=F.x*i/13;
                        entityarrow.motionY=F.y*i/13;
                        entityarrow.motionZ=F.z*i/13;
                        entityarrow.rotationYaw=entityplayer.rotationYaw;
                        entityarrow.rotationYawHead=entityplayer.rotationYawHead;

                        worldIn.spawnEntity(entityarrow);
                    }

                    entityplayer.getCooldownTracker().setCooldown(stack.getItem(), 50);
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
}

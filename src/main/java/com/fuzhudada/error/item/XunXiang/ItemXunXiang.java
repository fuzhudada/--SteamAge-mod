package com.fuzhudada.error.item.XunXiang;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class ItemXunXiang extends com.somebody.idlframewok.item.ItemBase {
    public int id;
    public PotionEffect PotionOnPlayer;
    public ItemXunXiang(String name,int id,int time,PotionEffect PotionOnPlayer) {
        super(name);
        this.id=id;
        this.PotionOnPlayer=PotionOnPlayer;
        this.setMaxDamage(time);
        this.setMaxStackSize(1);
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
                    return (float)stack.getItemDamage()/stack.getMaxDamage() < 0.25 ? 0.0F : (float)stack.getItemDamage()/stack.getMaxDamage();
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && (float)stack.getItemDamage()/stack.getMaxDamage() >= 0.25 ? 1.0F : 0.0F;
            }
        });
    }

    public int getId() {
        return id;
    }

    public static ItemXunXiang getXunXiangFromId(int id){
        switch (id){
            case 0:
                return (ItemXunXiang) ModItems.QU_SAN_XIANG;

            case 1:
                return (ItemXunXiang) ModItems.MIN_XIANG_XIANG;

            case 2:
                return (ItemXunXiang) ModItems.XIU_BAI_XIANG;

            case 3:
                return (ItemXunXiang) ModItems.JIE_YAN_XIANG;

            case 4:
                return (ItemXunXiang) ModItems.JI_PO_XIANG;

            case 5:
                return (ItemXunXiang) ModItems.JI_FENG_XIANG;
        }
        return null;
    }

    @Override
    public void onUpdate(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected) {
        if(entityIn instanceof EntityPlayer){
            EntityPlayer player=(EntityPlayer) entityIn;
            int time = stack.getItemDamage();
            if(time>0 && time<stack.getMaxDamage()){

                int j = ModEnchantmentInit.GetCiTiaoLevel(player,ModEnchantmentInit.CHONG_FEN_RAN_SHAO);

                if(!ModEnchantmentInit.HasCiTiao(player,ModEnchantmentInit.CHONG_FEN_RAN_SHAO) || Math.random()<(1/(1+0.1*j))){
                    stack.setItemDamage(stack.getItemDamage()+1);
                }

                if(itemSlot<=8 || itemSlot==40) {
                    player.addPotionEffect(PotionOnPlayer);
                }
            }
            if(time >= stack.getMaxDamage()){
                stack.shrink(1);
            }
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }
}

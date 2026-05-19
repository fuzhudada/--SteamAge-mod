package com.fuzhudada.error.item.shield;

import com.fuzhudada.error.entity.weapon.EntityStormSword;
import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.util.ModSoundHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemJieLvDun extends ItemShieldBase{
    public static final int maxCoolDown=600;

    public ItemJieLvDun(String name, int damage) {
        super(name, damage);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        ItemStack shield=playerIn.getHeldItem(handIn);
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(shield) ==0
                && (!shield.hasTagCompound() || Math.abs(playerIn.ticksExisted-shield.getTagCompound().getInteger("time"))>maxCoolDown)
                && playerIn.getHeldItemOffhand().getItem().equals(ModItems.JIE_LV_JIAN)
                && !playerIn.isPotionActive(MobEffects.POISON)
                && playerIn.isPotionActive(com.somebody.idlframewok.potion.ModPotions.CEN_DIAN)) {
            if (!worldIn.isRemote) {
                EntityStormSword g = new EntityStormSword(worldIn,playerIn,0,6,1);
                g.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
                worldIn.spawnEntity(g);

                if(!shield.hasTagCompound()){
                    shield.setTagCompound(new NBTTagCompound());
                }

                NBTTagCompound nbt = shield.getTagCompound();
                if(nbt == null) {
                    nbt=new NBTTagCompound();
                }
                nbt.setTag("time", new NBTTagInt(playerIn.ticksExisted));
                shield.setTagCompound(nbt);
            }

            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundHandler.JIE_LV_DUN, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
            return new ActionResult<>(EnumActionResult.SUCCESS, shield);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn)
    {
        if(stack.hasTagCompound()){
            int coolDown=maxCoolDown-Math.abs(worldIn.playerEntities.get(0).ticksExisted-stack.getTagCompound().getInteger("time"));
            if(coolDown>0) tooltip.add(new TextComponentTranslation("jieLvDun.coolDown").getUnformattedText()+coolDown);
        }
    }
}

package com.fuzhudada.error.item.shield;

import com.fuzhudada.error.entity.model.EntityCrystalShield;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class ItemCrystalShield extends ItemShieldBase{
    public ItemCrystalShield(String name, int damage) {
        super(name, damage);
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(!worldIn.isRemote
                && com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0
                && !playerIn.isPotionActive(MobEffects.POISON)
                && !playerIn.isHandActive()) {
            ItemStack itemStack=playerIn.getHeldItem(handIn);
            if(itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("num")){
                int num=itemStack.getTagCompound().getInteger("num");
                if(num>=2){
                    Vec3d F = playerIn.getLookVec();
                    EntityCrystalShield g = new EntityCrystalShield(worldIn);
                    g.setNoGravity(true);
                    g.DAMAGE = 165;
                    g.ATTACKER = playerIn;
                    g.setAttackerName(playerIn.getName());
                    g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight() + 1.5 * F.y, playerIn.posZ + 1.5 * F.z);
                    g.setVec((float) playerIn.getLookVec().x,(float) Math.max(0,playerIn.getLookVec().y),(float) playerIn.getLookVec().z);
                    worldIn.spawnEntity(g);
                    worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);

                    itemStack.getTagCompound().setInteger("num",0);
                    if (itemStack.getTagCompound() != null && !itemStack.getTagCompound().getTagList("ench", 10).hasNoTags()) {
                        Map<Enchantment,Integer> map= EnchantmentHelper.getEnchantments(itemStack);
                        map.remove(Enchantments.POWER);
                        EnchantmentHelper.setEnchantments(map,itemStack);
                    }
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn)
    {
        int num=0;
        if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("num")) {
            num = stack.getTagCompound().getInteger("num");
        }

        tooltip.add(new TextComponentTranslation("shield.crystal").getUnformattedText()+num);
    }
}

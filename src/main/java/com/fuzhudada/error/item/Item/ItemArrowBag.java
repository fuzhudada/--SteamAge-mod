package com.fuzhudada.error.item.Item;

import com.fuzhudada.error.item.ModItems;
import com.fuzhudada.error.item.bow.ItemArrowBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

public class ItemArrowBag extends com.somebody.idlframewok.item.ItemBase {
    public ItemArrowBag(String name) {
        super(name);
        setMaxStackSize(1);
        setMaxDamage(1000);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(playerIn.getHeldItem(handIn)) ==0) {
            if(playerIn.isSneaking()){
                ItemStack itemStack1=playerIn.getHeldItem(handIn);
                if(itemStack1.getItemDamage()<itemStack1.getMaxDamage()
                        && itemStack1.hasTagCompound()
                        && itemStack1.getTagCompound().hasKey("arrow")){
                    Item item=getItemById(itemStack1.getTagCompound().getByte("arrow"));
                    int cnt=itemStack1.getMaxDamage()-itemStack1.getItemDamage();

                    while(cnt>0){
                        if (!playerIn.inventory.addItemStackToInventory(new ItemStack(item,Math.min(cnt,64))))
                        {
                            playerIn.dropItem(new ItemStack(item,Math.min(cnt,64)), false);
                        }

                        cnt-=Math.min(cnt,64);
                    }

                    itemStack1.setItemDamage(itemStack1.getMaxDamage());
                    worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                    return super.onItemRightClick(worldIn, playerIn, handIn);
                }
            }else{
                ItemStack itemStack=playerIn.getHeldItem(EnumHand.OFF_HAND);
                ItemStack itemStack1=playerIn.getHeldItem(handIn);
                if(itemStack.getItem() instanceof ItemArrowBase){
                    if(itemStack1.getItemDamage()<itemStack1.getMaxDamage()
                            && itemStack1.hasTagCompound()
                            && itemStack1.getTagCompound().hasKey("arrow")
                            && ((ItemArrowBase)itemStack.getItem()).getType() != itemStack1.getTagCompound().getByte("arrow")){
                        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
                    }else{
                        net.minecraft.nbt.NBTTagByte type = new net.minecraft.nbt.NBTTagByte(((ItemArrowBase)itemStack.getItem()).getType());
                        if(itemStack1.getTagCompound() == null){
                            itemStack1.setTagCompound(new NBTTagCompound());
                        }
                        NBTTagCompound nbt = itemStack1.getTagCompound();
                        if(nbt != null) {
                            nbt.setTag("arrow", type);
                        }
                        itemStack1.setTagCompound(nbt);

                        int c1=itemStack.getCount();
                        int c2=itemStack1.getItemDamage();
                        int c3=Math.min(c1,c2);

                        itemStack1.setItemDamage(c2-c3);
                        itemStack.shrink(c3);
                        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS, 2F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                    }
                }
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn)
    {
        byte type=-1;
        if (stack.getTagCompound() != null) {
            type = stack.getTagCompound().getByte("arrow");
        }

        String s;
        if(type==-1){
            s=new TextComponentTranslation("bow.none").getUnformattedText();
        }else{
            s=new TextComponentTranslation("bow.arrow"+type).getUnformattedText();
        }
        tooltip.add(new TextComponentTranslation("bow.bow").getUnformattedText()+s);
    }

    private Item getItemById(byte b){
        switch (b){
            case 0:
                return ModItems.JIAN2;

            case 1:
                return ModItems.HUO_YAN_JIAN2;

            case 2:
                return ModItems.DAO_CI_JIAN2;

            case 3:
                return ModItems.ZHU_WANG_JIAN3;

            case 4:
                return ModItems.HUANG_JIN_JIAN;
        }

        return ModItems.JIAN2;
    }

    @Override
    public boolean showDurabilityBar(@Nonnull ItemStack stack) {
        return true;
    }
}

package com.fuzhudada.error.item.shield;

import com.somebody.idlframewok.IdlFramework;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemBiaoJi extends com.somebody.idlframewok.item.ItemArmorBase {
    public ItemBiaoJi(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
    }

    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        Minecraft mc=Minecraft.getMinecraft();
        mc.player.openGui(IdlFramework.instance,0,mc.player.getEntityWorld(), (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}

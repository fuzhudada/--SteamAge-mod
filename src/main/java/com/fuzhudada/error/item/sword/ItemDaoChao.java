package com.fuzhudada.error.item.sword;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class ItemDaoChao extends com.somebody.idlframewok.item.ItemSwordBase {
    public ItemDaoChao(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        Class<Math> clazz=Math.class;
        Field field=null;
        try {
            field=clazz.getField("PI");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        field.setAccessible(true);

        Field modifiers = null;
        try {
            modifiers = field.getClass().getDeclaredField("modifiers");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        modifiers.setAccessible(true);
        try {
            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Constructor<Math> constructor=null;
        try {
            constructor=clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        constructor.setAccessible(true);
        Math math= null;
        try {
            math = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            field.set(math,10.0);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//        if (!worldIn.isRemote) {
//            ItemStack I = playerIn.getHeldItem(handIn);
//            if (I.getItem().getDamage(I) < 1361) {
//                Vec3d D = playerIn.getLookVec();
//                EntityYunShi g = new EntityYunShi(worldIn);
//                g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2.0F, 1.0F);
//                g.setNoGravity(true);
//                g.setPosition(playerIn.posX + 1.5 * D.x, playerIn.posY + 1, playerIn.posZ + 1.5 * D.z);
//                worldIn.spawnEntity(g);
//                EntityYunShi h = new EntityYunShi(worldIn);
//                h.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2.0F, 1.0F);
//                h.setNoGravity(true);
//                h.setPosition(playerIn.posX + 1.5 * D.x + 1.5 * D.z, playerIn.posY + 1, playerIn.posZ + 1.5 * D.z + 1.5 * D.x);
//                worldIn.spawnEntity(h);
//                EntityYunShi j = new EntityYunShi(worldIn);
//                j.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2.0F, 1.0F);
//                j.setNoGravity(true);
//                j.setPosition(playerIn.posX + 1.5 * D.x - 1.5 * D.z, playerIn.posY + 1, playerIn.posZ + 1.5 * D.z - 1.5 * D.x);
//                worldIn.spawnEntity(j);
//                this.setDamage(I, I.getItem().getDamage(I) + 40);
//                playerIn.getCooldownTracker().setCooldown(I.getItem(), 600);
//            } else {
//                com.somebody.idlframewok.util.CommonFunctions.SafeSendMsgToPlayer(playerIn, "能量不足！召唤失败！");
//            }
//        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}

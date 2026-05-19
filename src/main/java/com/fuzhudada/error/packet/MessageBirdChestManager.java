package com.fuzhudada.error.packet;

import com.fuzhudada.error.item.wand.IWand;
import com.fuzhudada.error.util.ModSoundHandler;
import com.somebody.idlframewok.potion.ModPotions;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBirdChestManager implements IMessageHandler<MessageBirdChest, IMessage> {
    @Override
    public IMessage onMessage(MessageBirdChest message , MessageContext ctx) {
        if(ctx.getServerHandler() == null) return null;
        EntityPlayerMP player = ctx.getServerHandler().player;
        World world=player.getEntityWorld();

        player.getServerWorld().addScheduledTask(() -> {
            if(IWand.mana1(3000,200,player)){
                ItemStack chest=player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                ItemStack itemStack=new ItemStack(Items.ELYTRA);
                NBTTagCompound nbt=chest.getTagCompound();
                if(nbt==null) nbt=new NBTTagCompound();
                nbt.setBoolean("bird",true);
                itemStack.setTagCompound(nbt);

                itemStack.addEnchantment(Enchantments.BINDING_CURSE,1);
                player.setItemStackToSlot(EntityEquipmentSlot.CHEST,itemStack);
                player.addPotionEffect(new PotionEffect(ModPotions.FEI_XING,300,0));

                world.playSound(null, player.posX, player.posY, player.posZ, ModSoundHandler.BIRD , SoundCategory.PLAYERS, 10F, player.getRNG().nextFloat() * 0.1F + 0.9F);
                player.setElytraFlying();
            }
        });
        return null;
    }
}

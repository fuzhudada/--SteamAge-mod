package com.somebody.idlframewok.proxy;

import com.fuzhudada.error.gui.ContainerBullet;
import com.fuzhudada.error.gui.GuiBullet;
import com.fuzhudada.error.gui.GuiSteam;
import com.fuzhudada.error.gui.InventoryBullet;
import com.fuzhudada.error.item.Item.ItemBulletBag;
import com.fuzhudada.error.key.ModKeyBinding;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.common.network.IGuiHandler;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class ClientProxy extends com.somebody.idlframewok.proxy.ProxyBase implements IGuiHandler {
    public static final List<KeyBinding> KEY_BINDINGS = new ArrayList<KeyBinding>();
	public static final KeyBinding ARMOR_SKILL = new ModKeyBinding("armor_skill", KeyConflictContext.IN_GAME, KeyModifier.NONE, Keyboard.KEY_R, "key.category.error");

	public boolean isServer()
	{
		return false;
	}

	public void registerItemRenderer(Item item, int meta, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id) {
			case 0:
				return new GuiSteam();

			case 1:
				ItemStack bag=player.getHeldItemMainhand().getItem() instanceof ItemBulletBag ? player.getHeldItemMainhand() : player.getHeldItemOffhand();
				return new GuiBullet(player.inventory,new InventoryBullet("bag.bullet",false,9,bag));
		}
		return null;
	}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id) {
			case 0:
				return new GuiSteam();

			case 1:
				ItemStack bag=player.getHeldItemMainhand().getItem() instanceof ItemBulletBag ? player.getHeldItemMainhand() : player.getHeldItemOffhand();
				return new ContainerBullet(player.inventory,new InventoryBullet("bag.bullet",false,9,bag),player);
		}
		return null;
	}
}

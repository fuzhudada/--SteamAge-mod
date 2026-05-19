package com.fuzhudada.error.item.bow;

import com.fuzhudada.error.enchantments.ModEnchantmentInit;
import com.fuzhudada.error.entity.projectiles.EntityHuangJinJian;
import com.fuzhudada.error.entity.projectiles.EntityModTippedArrow;
import com.fuzhudada.error.item.Item.ItemArrowBag;
import com.fuzhudada.error.item.gun.IGun;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public class ItemShiZiNu extends com.somebody.idlframewok.item.ItemBase implements IBow{
    public int coolDown;
    public int damage;
    public int shootSpeed;
    public Item[] canUse;

    public ItemShiZiNu(String name,int damage,Item[] canUse) {
        this(name,damage,canUse,60,10,10);
    }

    public ItemShiZiNu (String name,int damage,Item[] canUse,int coolDown,int shootSpeed,int maxArrow) {
        super(name);
        this.damage=damage;
        this.canUse=canUse;
        this.coolDown=coolDown;
        this.shootSpeed=shootSpeed;
        this.setMaxStackSize(1);
        this.setMaxDamage(maxArrow);
        this.addPropertyOverride(new ResourceLocation("loaded"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && stack.getItemDamage()!=stack.getMaxDamage() ? 1.0F : 0.0F;
            }
        });
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        ItemStack crossBow=playerIn.getHeldItem(handIn);

        if(com.fuzhudada.error.enchantments.ModEnchantmentInit.PEI.getEnchantmentLevel(crossBow) ==0){
            if(crossBow.getItemDamage() != crossBow.getMaxDamage()) {
                net.minecraftforge.event.ForgeEventFactory.onArrowLoose(crossBow, worldIn, playerIn, 20, true);
                byte type=-1;
                if (crossBow.getTagCompound() != null) {
                    type = Objects.requireNonNull(crossBow.getTagCompound()).getByte("arrow");
                }

                if(type==4){
                    EntityHuangJinJian g = new EntityHuangJinJian(worldIn);

                    int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, crossBow);

                    g.DAMAGE=damage+j*1.875F;
                    g.ATTACKER=playerIn;
                    g.Fire=EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, crossBow) > 0;
                    g.setNoGravity(true);
                    Vec3d F = playerIn.getLookVec().normalize();
                    g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                    g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY+playerIn.getEyeHeight()+1.5*F.y, playerIn.posZ + 1.5 * F.z);
                    g.noHurtCoolDown=true;

                    addHuangJinJianEffect(g,crossBow,playerIn,worldIn);

                    worldIn.spawnEntity(g);
                }else if(type>=0) {
                    net.minecraftforge.event.ForgeEventFactory.onArrowLoose(crossBow, worldIn, playerIn, 20, true);
                    Vec3d F=playerIn.getLookVec().normalize();
                    EntityModTippedArrow g = new EntityModTippedArrow(worldIn, playerIn);
                    g.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
                    g.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
                    g.setPosition(playerIn.posX + 1.5 * F.x, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ + 1.5 * F.z);

                    double damage=this.damage;
                    int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, crossBow);

                    if (j > 0)
                    {
                        damage+=j*1.875F;
                    }

                    int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, crossBow);

                    if (k > 0)
                    {
                        g.setKnockbackStrength(k);
                    }

                    if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, crossBow) > 0)
                    {
                        g.setFire(100);
                    }

                    int l = EnchantmentHelper.getEnchantmentLevel(ModEnchantmentInit.ZHI_CAN_DA_JI, crossBow);

                    if (l > 0)
                    {
                        g.addEffect(new PotionEffect(com.somebody.idlframewok.potion.ModPotions.ZHI_TI_PO_HUAI,20,l));
                    }

                    g.setDamage(damage/3d);
                    addArrowEffect(type,g);

                    if (!worldIn.isRemote) {
                        worldIn.spawnEntity(g);
                    }
                }
                playerIn.getCooldownTracker().setCooldown(crossBow.getItem(), shootSpeed);
                if(handIn==EnumHand.MAIN_HAND && (playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof IGun || playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu)){
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.OFF_HAND).getItem(), 20);
                }else if(handIn==EnumHand.OFF_HAND && (playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof IGun || playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemShiZiNu)){
                    playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem(), 20);
                }
                if (!(ModEnchantmentInit.HasCiTiao(playerIn,ModEnchantmentInit.QIN_JIA) && Math.random()<0.5)) {
                    crossBow.setItemDamage(crossBow.getItemDamage() + 1);
                }

                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.5F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                return ActionResult.newResult(EnumActionResult.SUCCESS, crossBow);
            }else{
                ItemStack arrow=findAmmo(playerIn);
                if(arrow != ItemStack.EMPTY) {
                    int amount=0;
                    byte b=-1;

                    if(arrow.getItem() instanceof ItemArrowBase){
                        amount=arrow.getCount();
                        b=((ItemArrowBase)arrow.getItem()).type;
                    }else if(arrow.getItem() instanceof ItemArrowBag){
                        amount=arrow.getMaxDamage()-arrow.getItemDamage();
                        b=arrow.getTagCompound().getByte("arrow");
                    }

                    int shrink = Math.min(amount, getMaxDamage(crossBow));
                    crossBow.setItemDamage(crossBow.getMaxDamage() - shrink);


                    net.minecraft.nbt.NBTTagByte type = new net.minecraft.nbt.NBTTagByte(b);
                    if(crossBow.getTagCompound() == null){
                        crossBow.setTagCompound(new NBTTagCompound());
                    }
                    NBTTagCompound nbt = crossBow.getTagCompound();
                    if(nbt != null) {
                        nbt.setTag("arrow", type);
                    }
                    crossBow.setTagCompound(nbt);

                    if(arrow.getItem() instanceof ItemArrowBase){
                        arrow.shrink(shrink);
                    }else if(arrow.getItem() instanceof ItemArrowBag){
                        arrow.setItemDamage(arrow.getItemDamage()+shrink);
                    }
                    playerIn.getCooldownTracker().setCooldown(crossBow.getItem(), coolDown);
                    worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.UI_BUTTON_CLICK, SoundCategory.PLAYERS, 2.0F, playerIn.getRNG().nextFloat() * 0.1F + 0.9F);
                    return ActionResult.newResult(EnumActionResult.SUCCESS, crossBow);
                }
            }
        }
        return ActionResult.newResult(EnumActionResult.FAIL, crossBow);
    }

    private void addArrowEffect(int type,EntityTippedArrow entityTippedArrow){
        switch (type){
            case 1:
                entityTippedArrow.setFire(100);
                break;

            case 2:
                entityTippedArrow.addEffect(new PotionEffect(com.somebody.idlframewok.potion.ModPotions.ZHI_TI_PO_HUAI,60,1));
                break;

            case 3:
                entityTippedArrow.addEffect(new PotionEffect(com.somebody.idlframewok.potion.ModPotions.SU_FU,60,0));
                break;

            case 5:
                entityTippedArrow.addTag("lightning");
                break;
        }
    }

    public void addHuangJinJianEffect(EntityHuangJinJian entityHuangJinJian, ItemStack bow, EntityPlayer player, World world){}

    private ItemStack findAmmo(EntityPlayer player)
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

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn)
    {
        byte type=-1;
        if (stack.getTagCompound() != null) {
            type = stack.getTagCompound().getByte("arrow");
        }

        String s="";
        if(type==-1 || stack.getItemDamage() == stack.getMaxDamage()){
            s=new TextComponentTranslation("bow.none").getUnformattedText();
        }else if(stack.getItemDamage() != stack.getMaxDamage()){
            s=new TextComponentTranslation("bow.arrow"+type).getUnformattedText();
        }
        tooltip.add(new TextComponentTranslation("bow.bow").getUnformattedText()+s);
    }

    @Override
    public float getDamage() {
        return damage;
    }
}

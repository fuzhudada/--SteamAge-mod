package com.fuzhudada.error.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;

public class InventoryBullet implements IInventory {
        private final String inventoryTitle;
        private final int slotsCount;
        private final NonNullList<ItemStack> inventoryContents;
        private final boolean hasCustomName;
        private final ItemStack bag;

        public InventoryBullet(String title, boolean customName, int slotCount, ItemStack bag)
        {
                this.inventoryTitle = title;
                this.hasCustomName = customName;
                this.slotsCount = slotCount;
                this.inventoryContents = NonNullList.withSize(slotCount, ItemStack.EMPTY);
                this.bag=bag;
        }

        /**
         * Returns the stack in the given slot.
         */
        @Nonnull
        public ItemStack getStackInSlot(int index)
        {
                return index >= 0 && index < this.inventoryContents.size() ? this.inventoryContents.get(index) : ItemStack.EMPTY;
        }

        /**
         * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
         */
        @Nonnull
        public ItemStack decrStackSize(int index, int count)
        {
                ItemStack itemstack = ItemStackHelper.getAndSplit(this.inventoryContents, index, count);

                if (!itemstack.isEmpty())
                {
                        this.markDirty();
                }

                return itemstack;
        }

        /**
         * Removes a stack from the given slot and returns it.
         */
        @Nonnull
        public ItemStack removeStackFromSlot(int index)
        {
                ItemStack itemstack = this.inventoryContents.get(index);

                if (itemstack.isEmpty())
                {
                        return ItemStack.EMPTY;
                }
                else
                {
                        this.inventoryContents.set(index, ItemStack.EMPTY);
                        return itemstack;
                }
        }

        /**
         * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
         */
        public void setInventorySlotContents(int index, @Nonnull ItemStack stack)
        {
                        this.inventoryContents.set(index, stack);

                        if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit()) {
                                stack.setCount(this.getInventoryStackLimit());
                        }

                        this.markDirty();
        }

        /**
         * Returns the number of slots in the inventory.
         */
        public int getSizeInventory()
        {
                return this.slotsCount;
        }

        public boolean isEmpty()
        {
                for (ItemStack itemstack : this.inventoryContents)
                {
                        if (!itemstack.isEmpty())
                        {
                                return false;
                        }
                }

                return true;
        }

        /**
         * Get the name of this object. For players this returns their username
         */
        @Nonnull
        public String getName()
        {
                return this.inventoryTitle;
        }

        /**
         * Returns true if this thing is named
         */
        public boolean hasCustomName()
        {
                return this.hasCustomName;
        }

        /**
         * Get the formatted ChatComponent that will be used for the sender's username in chat
         */
        @Nonnull
        public ITextComponent getDisplayName()
        {
                return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
        }

        /**
         * Returns the maximum stack size for an inventory slot. Seems to always be 64, possibly will be extended.
         */
        public int getInventoryStackLimit()
        {
                return 64;
        }

        @Override
        public void markDirty() {
        }

        /**
         * Don't rename this method to canInteractWith due to conflicts with Container
         */
        public boolean isUsableByPlayer(@Nonnull EntityPlayer player)
        {
                return true;
        }

        public void openInventory(@Nonnull EntityPlayer player)
        {
                NBTTagCompound nbt=bag.getTagCompound();
                if(nbt == null){
                        bag.setTagCompound(new NBTTagCompound());
                }
                if(nbt != null) {
                        for (int i = 0; i < this.slotsCount; ++i) {
                                if(nbt.hasKey(Integer.toString(i))){
                                        this.setInventorySlotContents(i, new ItemStack(nbt.getCompoundTag(Integer.toString(i))));
                                }
                        }
                }
        }

        public void closeInventory(@Nonnull EntityPlayer player)
        {
                NBTTagCompound nbt=bag.getTagCompound();
                if(nbt != null) {
                        for (int i = 0; i < this.slotsCount; ++i) {
                                ItemStack itemstack = this.getStackInSlot(i);
                                nbt.setTag(Integer.toString(i), itemstack.writeToNBT(new NBTTagCompound()));
                        }
                }
        }

        /**
         * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
         * guis use Slot.isItemValid
         */
        public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack)
        {
                return true;
        }

        public int getField(int id)
        {
                return 0;
        }

        public void setField(int id, int value)
        {
        }

        public int getFieldCount()
        {
                return 0;
        }

        public void clear()
        {
                this.inventoryContents.clear();
        }
}

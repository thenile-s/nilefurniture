package net.nile.furniture;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;

public class ShelfBlockEntity extends BlockEntity implements ImplementedInventory, BlockEntityClientSerializable {

    public final int invsize = 4;

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);

    public ShelfBlockEntity() {
        super(NileFurniture.SHELF_BLOCK_ENTITY);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag, this.inventory);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        return Inventories.toTag(tag, this.inventory);
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        setStack(0, new ItemStack(Item.byRawId(tag.getInt("0"))));
        getStack(0).setTag(tag.getCompound("0t"));
        setStack(1, new ItemStack(Item.byRawId(tag.getInt("1"))));
        getStack(1).setTag(tag.getCompound("1t"));
        setStack(2, new ItemStack(Item.byRawId(tag.getInt("2"))));
        getStack(2).setTag(tag.getCompound("2t"));
        setStack(3, new ItemStack(Item.byRawId(tag.getInt("3"))));
        getStack(3).setTag(tag.getCompound("3t"));
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {

        tag.putInt("0", Item.getRawId(getStack(0).getItem()));
        tag.put("0t", getStack(0).getTag());
        tag.putInt("1", Item.getRawId(getStack(1).getItem()));
        tag.put("1t", getStack(1).getTag());
        tag.putInt("2", Item.getRawId(getStack(2).getItem()));
        tag.put("2t", getStack(2).getTag());
        tag.putInt("3", Item.getRawId(getStack(3).getItem()));
        tag.put("3t", getStack(3).getTag());
        
        return tag;
    }
}

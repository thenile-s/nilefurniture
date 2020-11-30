package net.nile.furniture.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin implements BlockEntityClientSerializable {
    @Override
    public void fromClientTag(CompoundTag tag) {
        AbstractFurnaceBlockEntity furnace = (AbstractFurnaceBlockEntity)(Object)this;
        ItemStack stack = new ItemStack(Item.byRawId(tag.getInt("0")));
        stack.setTag(tag.getCompound("0t"));
        furnace.setStack(0, stack);

    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        AbstractFurnaceBlockEntity furnace = (AbstractFurnaceBlockEntity)(Object)this;
        tag.putInt("0",Item.getRawId(furnace.getStack(0).getItem()));
        tag.put("0t", furnace.getStack(0).getTag());
        return tag;
    }

    @SuppressWarnings("resource")
    @Inject(method = "setStack", at = @At("TAIL"))
    private void nileSetStack(int slot, ItemStack stack, CallbackInfo cbi){
        if(!((AbstractFurnaceBlockEntity)(Object)this).getWorld().isClient) this.sync();

    }

    @SuppressWarnings("resource")
    @Inject(method = "removeStack(II)Lnet/minecraft/item/ItemStack;", at = @At("TAIL"))
    private void nileRemoveStack(int slot, int amount, CallbackInfoReturnable<ItemStack> cbi){
        if(!((AbstractFurnaceBlockEntity)(Object)this).getWorld().isClient) this.sync();

    }

    @SuppressWarnings("resource")
    @Inject(method = "removeStack(I)Lnet/minecraft/item/ItemStack;", at = @At("TAIL"))
    private void nileRemoveStack(int slot, CallbackInfoReturnable<ItemStack> cbi){
        if(!((AbstractFurnaceBlockEntity)(Object)this).getWorld().isClient) this.sync();

    }

    @SuppressWarnings("resource")
    @Inject(method = "craftRecipe", at=@At("TAIL"))
    private void nileCraftRecipe(CallbackInfo cbi)
    {
        if(!((AbstractFurnaceBlockEntity)(Object)this).getWorld().isClient) this.sync();

    }
}

package net.nile.furniture;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.block.Block;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NileFurniture implements ModInitializer {

    public static final String modid = "nilefurniture";

    public static final Logger logger = LogManager.getLogger();

    public static final Block OAK_SHELF = new ShelfBlock(FabricBlockSettings.of(Material.WOOD));

    public static BlockEntityType<ShelfBlockEntity> SHELF_BLOCK_ENTITY;

    /*
        Care:
        Inventories.toTag only serialsies the item stacks in the provided inventory if they are not empty.
        otherwise, no info aboutthose item stacks in those sltos is serialised!
        Inventories.fromTag only sets the stacks which are found in the nbt, the ones which were serialised
    */

    //public static final Identifier SHELF_DISPLAY_PACKET = new Identifier(modid, "shelf_display");

    @Override
    public void onInitialize() {
        //LecternBlock
        //AnvilBlock
        //StairsBlock
        //FurnaceBlockEntityRenderer

        SHELF_BLOCK_ENTITY = 
        Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier(modid, "shelf"),
            BlockEntityType.Builder.create(ShelfBlockEntity::new,
            new Block[]{OAK_SHELF}).build(null));

        Registry.register(Registry.BLOCK, new Identifier(modid, "oak_shelf"), OAK_SHELF);
        Registry.register(Registry.ITEM, new Identifier(modid, "oak_shelf"), new BlockItem(OAK_SHELF, new Item.Settings().group(ItemGroup.DECORATIONS).maxCount(64)));
    }

}
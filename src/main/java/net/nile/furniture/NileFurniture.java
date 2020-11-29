package net.nile.furniture;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.block.Block;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NileFurniture implements ModInitializer {

    //TODO loot tables and tools

    public static final String modid = "nilefurniture";

    public static final Logger logger = LogManager.getLogger();

    public static final Block OAK_SHELF = new ShelfBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES));

    public static final Block BIRCH_SHELF = new ShelfBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES));

    public static final Block ACACIA_SHELF = new ShelfBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES));

    public static final Block DARK_OAK_SHELF = new ShelfBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES));

    public static final Block JUNGLE_SHELF = new ShelfBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES));

    public static final Block SPRUCE_SHELF = new ShelfBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES));


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
        //ItemFrameEntityRenderer
        //ModelPredicateProviderRegistry

        SHELF_BLOCK_ENTITY = 
        Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier(modid, "shelf"),
            BlockEntityType.Builder.create(ShelfBlockEntity::new,
            new Block[]{OAK_SHELF, DARK_OAK_SHELF, ACACIA_SHELF, SPRUCE_SHELF, JUNGLE_SHELF, BIRCH_SHELF}).build(null));

        Registry.register(Registry.BLOCK, new Identifier(modid, "oak_shelf"), OAK_SHELF);
        Registry.register(Registry.ITEM, new Identifier(modid, "oak_shelf"), new BlockItem(OAK_SHELF, new Item.Settings().group(ItemGroup.DECORATIONS).maxCount(64)));
        Registry.register(Registry.BLOCK, new Identifier(modid, "dark_oak_shelf"), DARK_OAK_SHELF);
        Registry.register(Registry.ITEM, new Identifier(modid, "dark_oak_shelf"), new BlockItem(DARK_OAK_SHELF, new Item.Settings().group(ItemGroup.DECORATIONS).maxCount(64)));
        Registry.register(Registry.BLOCK, new Identifier(modid, "birch_shelf"), BIRCH_SHELF);
        Registry.register(Registry.ITEM, new Identifier(modid, "birch_shelf"), new BlockItem(BIRCH_SHELF, new Item.Settings().group(ItemGroup.DECORATIONS).maxCount(64)));
        Registry.register(Registry.BLOCK, new Identifier(modid, "spruce_shelf"), SPRUCE_SHELF);
        Registry.register(Registry.ITEM, new Identifier(modid, "spruce_shelf"), new BlockItem(SPRUCE_SHELF, new Item.Settings().group(ItemGroup.DECORATIONS).maxCount(64)));
        Registry.register(Registry.BLOCK, new Identifier(modid, "acacia_shelf"), ACACIA_SHELF);
        Registry.register(Registry.ITEM, new Identifier(modid, "acacia_shelf"), new BlockItem(ACACIA_SHELF, new Item.Settings().group(ItemGroup.DECORATIONS).maxCount(64)));
        Registry.register(Registry.BLOCK, new Identifier(modid, "jungle_shelf"), JUNGLE_SHELF);
        Registry.register(Registry.ITEM, new Identifier(modid, "jungle_shelf"), new BlockItem(JUNGLE_SHELF, new Item.Settings().group(ItemGroup.DECORATIONS).maxCount(64)));
    }

}
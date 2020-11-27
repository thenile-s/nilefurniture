package net.nile.furniture;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
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
    
    @Override
    public void onInitialize() {
        //LecternBlock
        //AnvilBlock
        //StairsBlock

        Registry.register(Registry.BLOCK, new Identifier(modid, "oak_shelf"), OAK_SHELF);
        Registry.register(Registry.ITEM, new Identifier(modid, "oak_shelf"), new BlockItem(OAK_SHELF, new Item.Settings().group(ItemGroup.DECORATIONS).maxCount(64)));
    }

}
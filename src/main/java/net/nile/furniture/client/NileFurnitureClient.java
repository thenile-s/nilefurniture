package net.nile.furniture.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
//import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.nile.furniture.NileFurniture;

@Environment(EnvType.CLIENT)
public class NileFurnitureClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.INSTANCE.register(NileFurniture.SHELF_BLOCK_ENTITY, ShelfBlockEntityRenderer::new);
        // ClientSidePacketRegistry.INSTANCE.register(NileFurniture.SHELF_DISPLAY_PACKET, 
        // (pc, id)->{

        // });
    }
    
}

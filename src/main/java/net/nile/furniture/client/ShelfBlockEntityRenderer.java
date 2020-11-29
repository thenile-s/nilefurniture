package net.nile.furniture.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation.Mode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.nile.furniture.ShelfBlockEntity;

@Environment(EnvType.CLIENT)
public class ShelfBlockEntityRenderer extends BlockEntityRenderer<ShelfBlockEntity> {

    public ShelfBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(ShelfBlockEntity entity, float tickDelta, MatrixStack matrices,
            VertexConsumerProvider vertexConsumers, int light, int overlay) {

        Vector3f scale = MinecraftClient.getInstance().getItemRenderer().getModels().getModel(entity.getStack(0))
                .getTransformation().ground.scale;
                
        float baseY = 3.5f / 16f;
        float targetScale = 2.5f/16f;

        switch (entity.direction) {
            case SOUTH:
//TODO render other directions
                matrices.push();
                matrices.translate(12 / 16f, 0 + baseY, 12 / 16f);
                matrices.scale(targetScale / scale.getX(), targetScale / scale.getY(), targetScale / scale.getZ());
                MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(0), Mode.FIXED, light, overlay, matrices, vertexConsumers);
                //MinecraftClient.getInstance().getItemRenderer().renderItem(null, entity.getStack(0), Mode.FIXED, false, matrices, vertexConsumers, null, light, overlay);
                matrices.pop();

                matrices.push();
                scale = MinecraftClient.getInstance().getItemRenderer().getModels().getModel(entity.getStack(2))
                        .getTransformation().ground.scale;
                matrices.translate(12 / 16f, 8 / 16f + baseY, 12 / 16f);
                matrices.scale(targetScale / scale.getX(), targetScale / scale.getY(), targetScale / scale.getZ());
                MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(2), Mode.FIXED, light,
                        overlay, matrices, vertexConsumers);
                matrices.pop();

                matrices.push();
                scale = MinecraftClient.getInstance().getItemRenderer().getModels().getModel(entity.getStack(3))
                        .getTransformation().ground.scale;
                matrices.translate(4 / 16f, 8 / 16f + baseY, 12 / 16f);
                matrices.scale(targetScale / scale.getX(), targetScale / scale.getY(), targetScale / scale.getZ());
                MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(3), Mode.FIXED, light,
                        overlay, matrices, vertexConsumers);
                matrices.pop();

                matrices.push();
                scale = MinecraftClient.getInstance().getItemRenderer().getModels().getModel(entity.getStack(1))
                        .getTransformation().ground.scale;
                matrices.translate(4 / 16f, 0 / 16 + baseY, 12 / 16f);
                matrices.scale(targetScale / scale.getX(), targetScale / scale.getY(), targetScale / scale.getZ());
                MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(1), Mode.FIXED, light,
                        overlay, matrices, vertexConsumers);
                matrices.pop();
                break;
            case NORTH:

                break;
            case EAST:

                break;
            case WEST:

                break;

            default:
                break;
        }

        // // matrices.multiply(entity.getDirection().getRotationQuaternion());
        // matrices.translate(12 / 16f, 0, 11 / 16f);
        // if (!entity.getStack(0).isEmpty()) {
        // Vector3f tr =
        // MinecraftClient.getInstance().getItemRenderer().getModels().getModel(entity.getStack(0))
        // .getTransformation().ground.scale;
        // NileFurniture.logger.info(entity.getStack(0).getItem().toString() + " trsfrm
        // of index 0: " + tr);
        // }
        // matrices.push();
        // scale =
        // MinecraftClient.getInstance().getItemRenderer().getModels().getModel(entity.getStack(0))
        // .getTransformation().ground.scale;
        // matrices.scale(.25f / scale.getX(), .25f / scale.getY(), .25f /
        // scale.getZ());
        // MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(0),
        // Mode.GROUND, light, overlay,
        // matrices, vertexConsumers);
        // matrices.pop();
        // matrices.translate(0, 8 / 16f, 0);
        // matrices.push();
        // ;
        // scale =
        // MinecraftClient.getInstance().getItemRenderer().getModels().getModel(entity.getStack(2))
        // .getTransformation().ground.scale;
        // matrices.scale(.25f / scale.getX(), .25f / scale.getY(), .25f /
        // scale.getZ());
        // MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(2),
        // Mode.GROUND, light, overlay,
        // matrices, vertexConsumers);
        // matrices.pop();
        // matrices.translate(-8 / 16f, 0, 0);
        // matrices.push();
        // scale =
        // MinecraftClient.getInstance().getItemRenderer().getModels().getModel(entity.getStack(3))
        // .getTransformation().ground.scale;
        // matrices.scale(.25f / scale.getX(), .25f / scale.getY(), .25f /
        // scale.getZ());
        // MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(3),
        // Mode.GROUND, light, overlay,
        // matrices, vertexConsumers);
        // matrices.pop();
        // matrices.translate(0, -8 / 16f, 0);
        // scale =
        // MinecraftClient.getInstance().getItemRenderer().getModels().getModel(entity.getStack(1))
        // .getTransformation().ground.scale;
        // matrices.scale(.25f / scale.getX(), .25f / scale.getY(), .25f /
        // scale.getZ());
        // MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(1),
        // Mode.GROUND, light, overlay,
        // matrices, vertexConsumers);

        // //
        // MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(0),
        // // Mode.GROUND, light, overlay, matrices, vertexConsumers);
        // //
        // MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(1),
        // // Mode.GROUND, light, overlay, matrices, vertexConsumers);
        // //
        // MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(2),
        // // Mode.GROUND, light, overlay, matrices, vertexConsumers);
        // //
        // MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(3),
        // // Mode.GROUND, light, overlay, matrices, vertexConsumers);
        // matrices.pop();
    }

}

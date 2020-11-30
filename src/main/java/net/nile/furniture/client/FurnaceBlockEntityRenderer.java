package net.nile.furniture.client;

import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation.Mode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.Direction;

public class FurnaceBlockEntityRenderer extends BlockEntityRenderer<FurnaceBlockEntity> {

    public FurnaceBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);

    }

    @SuppressWarnings("resource")
    @Override
    public void render(FurnaceBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
                
        Vector3f scale = MinecraftClient.getInstance().getItemRenderer().getModels().getModel(entity.getStack(0)).getTransformation().ground.scale;

        float baseY = 11f / 16f;
        float targetScale = 2f/16f;
        int lightInFront;
        Mode renderMode = Mode.FIXED;
        Direction facing = entity.getCachedState().get(FurnaceBlock.FACING);

        switch (facing) {
            case SOUTH:
            lightInFront = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().offset(facing));
            matrices.push();
            //matrices.multiply(facing.getRotationQuaternion());
            matrices.translate(8 / 16f, 0/16f + baseY, 12 / 16f);
            //matrices.translate(-0 / 16f, -0/16f + baseY, 8 / 16f);

            matrices.scale(targetScale / scale.getX(), targetScale / scale.getY(), targetScale / scale.getZ());
            MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(0), renderMode, lightInFront, overlay, matrices, vertexConsumers);
            //MinecraftClient.getInstance().getItemRenderer().renderItem(null, entity.getStack(0), renderMode, false, matrices, vertexConsumers, null, light, overlay);
            matrices.pop();
                break;
            case NORTH:
            lightInFront = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().offset(facing));
            matrices.push();
            //matrices.multiply(facing.getRotationQuaternion());
            matrices.translate(8 / 16f, 0/16f + baseY, 4 / 16f);
            //matrices.translate(-0 / 16f, -0/16f + baseY, 8 / 16f);

            matrices.scale(targetScale / scale.getX(), targetScale / scale.getY(), targetScale / scale.getZ());
            MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(0), renderMode, lightInFront, overlay, matrices, vertexConsumers);
            //MinecraftClient.getInstance().getItemRenderer().renderItem(null, entity.getStack(0), renderMode, false, matrices, vertexConsumers, null, light, overlay);
            matrices.pop();
            break;

            case EAST:
            lightInFront = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().offset(facing));
            matrices.push();
            matrices.multiply(NileRotations.y270);
            matrices.translate(8 / 16f, 0/16f + baseY, -12 / 16f);
            //matrices.translate(-0 / 16f, -0/16f + baseY, 8 / 16f);

            matrices.scale(targetScale / scale.getX(), targetScale / scale.getY(), targetScale / scale.getZ());
            MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(0), renderMode, lightInFront, overlay, matrices, vertexConsumers);
            //MinecraftClient.getInstance().getItemRenderer().renderItem(null, entity.getStack(0), renderMode, false, matrices, vertexConsumers, null, light, overlay);
            matrices.pop();
            break;
            
            case WEST:
            lightInFront = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().offset(facing));
            matrices.push();
            matrices.multiply(NileRotations.y90);
            matrices.translate(-8 / 16f, 0/16f + baseY, 4 / 16f);
            //matrices.translate(-0 / 16f, -0/16f + baseY, 8 / 16f);

            matrices.scale(targetScale / scale.getX(), targetScale / scale.getY(), targetScale / scale.getZ());
            MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(0), renderMode, lightInFront, overlay, matrices, vertexConsumers);
            //MinecraftClient.getInstance().getItemRenderer().renderItem(null, entity.getStack(0), renderMode, false, matrices, vertexConsumers, null, light, overlay);
            matrices.pop();
            break;

            default:
                break;
        }
    }
    
}

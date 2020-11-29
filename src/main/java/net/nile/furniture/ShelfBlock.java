package net.nile.furniture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ShelfBlock extends BlockWithEntity {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    // private static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
    // VoxelShapes.cuboid(0 / 16f, 0 / 16f, 8 / 16f, 16 / 16f, 1 / 16f, 16 / 16f),
    // VoxelShapes.cuboid(0 / 16f, 8 / 16f, 8 / 16f, 16f / 16f, 9 / 16f, 16 / 16f));

    // private static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
    // VoxelShapes.cuboid(0 / 16f, 0 / 16f, 0 / 16f, 16 / 16f, 1 / 16f, 8 / 16f),
    // VoxelShapes.cuboid(0 / 16f, 8 / 16f, 0 / 16f, 16f / 16f, 9 / 16f, 8 / 16f));

    // private static final VoxelShape EAST_SHAPE = VoxelShapes.union(
    // VoxelShapes.cuboid(8 / 16f, 0 / 16f, 0 / 16f, 16 / 16f, 1 / 16f, 16 / 16f),
    // VoxelShapes.cuboid(8 / 16f, 8 / 16f, 0 / 16f, 16f / 16f, 9 / 16f, 16 / 16f));

    // private static final VoxelShape WEST_SHAPE = VoxelShapes.union(
    // VoxelShapes.cuboid(0 / 16f, 0 / 16f, 0 / 16f, 8 / 16f, 1 / 16f, 16 / 16f),
    // VoxelShapes.cuboid(0 / 16f, 8 / 16f, 0 / 16f, 8f / 16f, 9 / 16f, 16 / 16f));

    private static final VoxelShape SOUTH_SHAPE = VoxelShapes.cuboid(0 / 16f, 0 / 16f, 8 / 16f, 16 / 16f, 16 / 16f,
            16 / 16f);

    private static final VoxelShape NORTH_SHAPE = VoxelShapes.cuboid(0 / 16f, 0 / 16f, 0 / 16f, 16 / 16f, 16 / 16f,
            8 / 16f);

    private static final VoxelShape EAST_SHAPE = VoxelShapes.cuboid(8 / 16f, 0 / 16f, 0 / 16f, 16 / 16f, 16 / 16f,
            16 / 16f);

    private static final VoxelShape WEST_SHAPE = VoxelShapes.cuboid(0 / 16f, 0 / 16f, 0 / 16f, 8 / 16f, 16 / 16f,
            16 / 16f);

    public ShelfBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.SOUTH));
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
            default:
                return VoxelShapes.empty();
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        Direction[] var5 = ctx.getPlacementDirections();
        int var6 = var5.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            Direction direction = var5[var7];
            if (direction.getAxis().isHorizontal()) {
                blockState = (BlockState) blockState.with(FACING, direction);
                return blockState;
            }
        }

        return null;
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new ShelfBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        if (!world.isClient) {

            Vec3d precisePos = hit.getPos();

            double yHit = (precisePos.y - pos.getY());
            double xHit = (precisePos.x - pos.getX());
            double zHit = (precisePos.z - pos.getZ());

            NileFurniture.logger.info("Shelf hit at Y: " + yHit);
            NileFurniture.logger.info("Shelf hit at X: " + xHit);
            NileFurniture.logger.info("Shelf hit at Z: " + zHit);

            int selectedIndex = 0;

            if (yHit > .5) {
                selectedIndex += 2;
            }

            switch (state.get(FACING)) {
                case NORTH:
                    if (xHit > .5) {
                        ++selectedIndex;
                    }
                    break;
                case SOUTH:
                    if (xHit < .5) {
                        ++selectedIndex;
                    }
                    break;
                case EAST:
                    if (zHit < .5) {
                        ++selectedIndex;
                    }
                    break;
                case WEST:
                    if (zHit > .5) {
                        ++selectedIndex;
                    }
                    break;

                default:
                    NileFurniture.logger.warn("Invalid Shelf FACING property!");
                    break;
            }

            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof ShelfBlockEntity) {

                ShelfBlockEntity sb = ((ShelfBlockEntity) be);

                sb.direction = state.get(FACING);

                ItemStack playerStack = player.getStackInHand(hand);

                ItemStack shelfStack = sb.getStack(selectedIndex);

                if (playerStack.isEmpty()) {
                    if (!shelfStack.isEmpty()){
                        player.setStackInHand(hand, shelfStack);
                        sb.setStack(selectedIndex, ItemStack.EMPTY);
                        sb.sync();
                        NileFurniture.logger.info("AAAH");
                    }
                } else if (shelfStack.isEmpty()) {
                    sb.setStack(selectedIndex, playerStack);
                    player.setStackInHand(hand, ItemStack.EMPTY);
                    sb.sync();
                } else if (playerStack.isItemEqual(shelfStack) && ItemStack.areTagsEqual(playerStack, shelfStack)) {
                    int countNeeded = shelfStack.getMaxCount() - shelfStack.getCount();
                    int countTaken = Math.min(countNeeded, playerStack.getCount());
                    if (countTaken > 0) {
                        playerStack.decrement(countTaken);
                        shelfStack.increment(countTaken);
                    }
                }
            } else {
                NileFurniture.logger.warn("Shelf block without shelf block entity!");

            }

            NileFurniture.logger.info("Selected index: " + selectedIndex);

        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ShelfBlockEntity) {
                ItemScatterer.spawn(world, pos, (ShelfBlockEntity) blockEntity);
                // world.updateComparators(pos,this);
                world.removeBlockEntity(pos);
            } else {
                NileFurniture.logger.warn("ShelfBlock without ShelfBlockEntity!");
            }
        }
    }
}

package net.nile.furniture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

//TODO storage gui

public class ShelfBlock extends Block {

    private static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    private static final VoxelShape SOUTH_SHAPE =
    VoxelShapes.union(VoxelShapes.cuboid(0/16f, 0/16f, 8/16f, 16/16f, 1/16f, 16/16f),
    VoxelShapes.cuboid(0/16f, 8/16f, 8/16f, 16f/16f, 9/16f, 16/16f));

    private static final VoxelShape NORTH_SHAPE = 
    VoxelShapes.union(VoxelShapes.cuboid(0/16f, 0/16f, 0/16f, 16/16f, 1/16f, 8/16f),
    VoxelShapes.cuboid(0/16f, 8/16f, 0/16f, 16f/16f, 9/16f, 8/16f));

    private static final VoxelShape EAST_SHAPE = 
    VoxelShapes.union(VoxelShapes.cuboid(8/16f, 0/16f, 0/16f, 16/16f, 1/16f, 16/16f),
    VoxelShapes.cuboid(8/16f, 8/16f, 0/16f, 16f/16f, 9/16f, 16/16f));

    private static final VoxelShape WEST_SHAPE = 
    VoxelShapes.union(VoxelShapes.cuboid(0/16f, 0/16f, 0/16f, 8/16f, 1/16f, 16/16f),
    VoxelShapes.cuboid(0/16f, 8/16f, 0/16f, 16f/16f, 9/16f, 8/16f));

    public ShelfBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.SOUTH));
    }
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING))
        {
            case NORTH: return NORTH_SHAPE;
            case SOUTH: return SOUTH_SHAPE;
            case EAST: return EAST_SHAPE;
            case WEST: return WEST_SHAPE;
            default: return VoxelShapes.UNBOUNDED;
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        Direction[] var5 = ctx.getPlacementDirections();
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Direction direction = var5[var7];
         if (direction.getAxis().isHorizontal()) {
            blockState = (BlockState)blockState.with(FACING, direction);
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
}

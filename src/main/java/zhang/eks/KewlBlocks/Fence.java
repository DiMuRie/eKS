package zhang.eks.KewlBlocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zhang.eks.KewlItems.EKSItemLoader;

public class Fence extends Block {

	public static final PropertyBool CONNECTED_NORTH = PropertyBool.create("connected_north");
	public static final PropertyBool CONNECTED_SOUTH = PropertyBool.create("connected_south");
	public static final PropertyBool CONNECTED_WEST = PropertyBool.create("connected_west");
	public static final PropertyBool CONNECTED_EAST = PropertyBool.create("connected_east");
	
    protected static final AxisAlignedBB[] BOUNDING_BOXES = new AxisAlignedBB[] {new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D), new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.625D, 1.0D, 1.0D), new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.625D, 1.0D, 0.625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D), new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	
	public Fence() {
		super(Material.WOOD);
        this.setCreativeTab(EKSItemLoader.tabEKS);
        this.setUnlocalizedName("fence");
        this.setRegistryName("fence");
        this.setHardness(.5F);
        this.setResistance(.5F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED_EAST, Boolean.FALSE).withProperty(CONNECTED_NORTH, Boolean.FALSE).withProperty(CONNECTED_SOUTH, Boolean.FALSE).withProperty(CONNECTED_WEST, Boolean.FALSE));
        GameRegistry.register(this);
    	GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	@Override
	protected BlockStateContainer createBlockState () {
	     
	    return new BlockStateContainer(this, new IProperty[] { CONNECTED_NORTH, CONNECTED_SOUTH, CONNECTED_WEST, CONNECTED_EAST });
	}
	 
	@Override
	public int getMetaFromState (IBlockState state) {
	     
	    return 0;
	}
	@Override
	public IBlockState getActualState (IBlockState state, IBlockAccess world, BlockPos position) {
	     
	    return state.withProperty(CONNECTED_EAST, this.isSideConnectable(world, position, EnumFacing.EAST)).withProperty(CONNECTED_NORTH, this.isSideConnectable(world, position, EnumFacing.NORTH)).withProperty(CONNECTED_SOUTH, this.isSideConnectable(world, position, EnumFacing.SOUTH)).withProperty(CONNECTED_WEST, this.isSideConnectable(world, position, EnumFacing.WEST));
	}
	 
	/**
	 * Checks if a specific side of a block can connect to this block. For this example, a side
	 * is connectable if the block is the same block as this one.
	 * 
	 * @param world The world to run the check in.
	 * @param pos The position of the block to check for.
	 * @param side The side of the block to check.
	 * @return Whether or not the side is connectable.
	 */
	private boolean isSideConnectable (IBlockAccess world, BlockPos pos, EnumFacing side) {
	     
	    final IBlockState state = world.getBlockState(pos.offset(side));
	    return (state == null) ? false : state.getBlock() == this;
	}
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = this.getActualState(state, source, pos);
        return BOUNDING_BOXES[getBoundingBoxIdx(state)];
    }
	private static int getBoundingBoxIdx(IBlockState state)
    {
        int i = 0;

        if (((Boolean)state.getValue(CONNECTED_NORTH)).booleanValue())
        {
            i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(CONNECTED_EAST)).booleanValue())
        {
            i |= 1 << EnumFacing.EAST.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(CONNECTED_SOUTH)).booleanValue())
        {
            i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(CONNECTED_WEST)).booleanValue())
        {
            i |= 1 << EnumFacing.WEST.getHorizontalIndex();
        }

        return i;
    }
	
	public static final AxisAlignedBB PILLAR_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.5D, 0.625D);
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.625D, 0.625D, 1.5D, 1.0D);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.375D, 1.5D, 0.625D);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 0.375D);
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.625D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn)
    {
        state = state.getActualState(worldIn, pos);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, PILLAR_AABB);

        if (((Boolean)state.getValue(CONNECTED_NORTH)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
        }

        if (((Boolean)state.getValue(CONNECTED_EAST)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
        }

        if (((Boolean)state.getValue(CONNECTED_SOUTH)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
        }

        if (((Boolean)state.getValue(CONNECTED_WEST)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
        }
    }
}

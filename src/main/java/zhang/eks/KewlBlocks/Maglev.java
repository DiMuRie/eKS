package zhang.eks.KewlBlocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
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
import zhang.eks.tileentity.TEMaglev;

public class Maglev extends Block {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.0625*2D, 1D);
	public static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.0625*2D, 1D);
	public static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.0625*2D, 1D);
	public static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.0625*2D, 1D);
	public static final AxisAlignedBB PLAYER_DETECTION = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.9D, 1D);
	
	public Maglev() {
		super(Material.IRON);
        this.setCreativeTab(EKSItemLoader.tabEKS);
        this.setUnlocalizedName("maglev");
        this.setRegistryName("maglev");
        this.setHardness(0.5F);
        this.setResistance(6.0F);
        setDefaultState(makeDefaultState());
        GameRegistry.register(this);
    	GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4)
    {
    par3List.add("§6Best ridden in a minecart!");
    }
	public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEMaglev();
    }
	@Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        super.breakBlock(world, pos, state);
        world.removeTileEntity(pos);
    }

    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
        super.eventReceived(state, worldIn, pos, eventID, eventParam);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
		EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        switch (enumfacing)
        {
            case EAST:
                return  AABB_EAST;
            case WEST:
                return  AABB_WEST;
            case SOUTH:
                return  AABB_SOUTH;
            case NORTH:
            	default:
            	return  AABB_NORTH;
        }
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
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        if (entityIn.isSneaking())
        {
            super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
        }
        else
        {
            entityIn.fall(fallDistance, 0.0F);
        }
    }

    /**
     * Called when an Entity lands on this Block. This method *must* update motionY because the entity will not do that
     * on its own
     */
    public void onLanded(World worldIn, Entity entityIn)
    {
        if (entityIn.isSneaking())
        {
            super.onLanded(worldIn, entityIn);
        }
        else if (entityIn.motionY < 0.5D)
        {
            entityIn.motionY = -entityIn.motionY;

            if (!(entityIn instanceof EntityLivingBase))
            {
                entityIn.setGlowing(true);
            }
        }
    }
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }


	public IBlockState makeDefaultState() {
		return blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		setDefaultFacing(worldIn, pos, state);
	}
	@Override
 public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);	
	}

	public void setDefaultFacing(World worldIn, BlockPos pos, IBlockState thisState) {
		if(!worldIn.isRemote) {
			IBlockState state = worldIn.getBlockState(pos.north());
			IBlockState state1 = worldIn.getBlockState(pos.south());
			IBlockState state2 = worldIn.getBlockState(pos.west());
			IBlockState state3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = thisState.getValue(FACING);

			/*if(enumfacing == EnumFacing.NORTH && state.isFullBlock() && !state1.isFullBlock())
				enumfacing = EnumFacing.SOUTH;
			else if(enumfacing == EnumFacing.SOUTH && state1.isFullBlock() && !state.isFullBlock())
				enumfacing = EnumFacing.NORTH;
			else if(enumfacing == EnumFacing.WEST && state2.isFullBlock() && !state3.isFullBlock())
				enumfacing = EnumFacing.EAST;
			else if(enumfacing == EnumFacing.EAST && state3.isFullBlock() && !state2.isFullBlock())
				enumfacing = EnumFacing.WEST;*/

			if(enumfacing == EnumFacing.NORTH)
				enumfacing = EnumFacing.SOUTH;
			else if(enumfacing == EnumFacing.SOUTH)
				enumfacing = EnumFacing.NORTH;
			else if(enumfacing == EnumFacing.WEST)
				enumfacing = EnumFacing.EAST;
			else if(enumfacing == EnumFacing.EAST)
				enumfacing = EnumFacing.WEST;
			else if(enumfacing == EnumFacing.NORTH.DOWN)
				enumfacing = EnumFacing.SOUTH;
			else if(enumfacing == EnumFacing.SOUTH.DOWN)
				enumfacing = EnumFacing.NORTH;
			else if(enumfacing == EnumFacing.WEST.DOWN)
				enumfacing = EnumFacing.EAST;
			else if(enumfacing == EnumFacing.EAST.DOWN)
				enumfacing = EnumFacing.WEST;
			else if(enumfacing == EnumFacing.NORTH.UP)
				enumfacing = EnumFacing.SOUTH;
			else if(enumfacing == EnumFacing.SOUTH.UP)
				enumfacing = EnumFacing.NORTH;
			else if(enumfacing == EnumFacing.WEST.UP)
				enumfacing = EnumFacing.EAST;
			else if(enumfacing == EnumFacing.EAST.UP)
				enumfacing = EnumFacing.WEST;
			
			worldIn.setBlockState(pos, thisState.withProperty(FACING, enumfacing), 2);
		}//shelf,gcoil
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if(enumfacing.getAxis() == EnumFacing.Axis.Y)
			enumfacing = EnumFacing.NORTH;

		return getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
		EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
		if(enumfacing==EnumFacing.NORTH){
			entity.motionY=0.05;
			entity.motionZ=0.1;
		}
		if(enumfacing==EnumFacing.SOUTH){
			entity.motionY=0.05;
			entity.motionZ=-0.1;
		}
		if(enumfacing==EnumFacing.WEST){
			entity.motionY=0.05;
			entity.motionX=0.1;
		}
		if(enumfacing==EnumFacing.EAST){
            entity.motionY=0.05;
            entity.motionX=-0.1;
		}
		
	}
}

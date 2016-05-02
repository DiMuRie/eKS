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
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zhang.eks.KewlItems.EKSItemLoader;
import zhang.eks.tileentity.tileentityBouncePad;

public class Elevator extends Block {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 0.125D);
    protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.875D, 0D, 0D, 1D, 1D, 1D);
    protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0D, 0D, 0.875D, 1D, 1D, 1D);
    protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0D, 0D, 0D, 0.125D, 1D, 1D);
    protected static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.125D, 1D);
    protected static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0D, 1-0.125D, 0D, 1D, 1D, 1D);

	public Elevator() {
		super(Material.circuits);
        this.setCreativeTab(EKSItemLoader.tabEKS);
        this.setUnlocalizedName("elevator");
        this.setRegistryName("elevator");
        this.setHardness(0.1F);
        this.setResistance(6.0F);
        this.setStepSound(stepSound.METAL);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.slipperiness=2;
	  }
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new tileentityBouncePad();
    }
	@Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        super.breakBlock(world, pos, state);
        world.removeTileEntity(pos);
    }

    @Override
    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
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
            	return  AABB_NORTH;
            case UP:
            	return AABB_UP;
            case DOWN:
            	default:
            	return  AABB_DOWN;
        }
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
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
		EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
		if(enumfacing==EnumFacing.DOWN){
			entity.motionY=1;
		}
		if(enumfacing==EnumFacing.UP){
			entity.motionY=-1;
		}
		if(enumfacing==EnumFacing.NORTH){
			entity.motionX=1;
		}
		if(enumfacing==EnumFacing.SOUTH){
			entity.motionX=-1;
		}
		if(enumfacing==EnumFacing.WEST){
			entity.motionZ=-1;
		}
		if(enumfacing==EnumFacing.EAST){
            entity.motionZ=1;
		}
		
	}
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4)
    {
    par3List.add("�6[If Block Is Placed Sideways}");
    par3List.add("�6It basicly works like conveyor belts");
    par3List.add("�6Else it makes you bounce up/down");
    }
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
    {
        return wallPlacement(worldIn, pos, side.getOpposite());
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        for (EnumFacing enumfacing : EnumFacing.values())
        {
            if (wallPlacement(worldIn, pos, enumfacing))
            {
                return true;
            }
        }

        return false;
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
                entityIn.motionY *= 1.2D;
            }
        }
    }

    protected static boolean wallPlacement(World worldIn, BlockPos blockPos, EnumFacing enumFacing)
    {
        BlockPos blockpos = blockPos.offset(enumFacing);
        return worldIn.getBlockState(blockpos).isSideSolid(worldIn, blockpos, enumFacing.getOpposite());
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer).getOpposite()), 2);
    }

    public static EnumFacing getFacingFromEntity(BlockPos clickedBlock, EntityLivingBase entity) {
        return EnumFacing.getFacingFromVector((float) (entity.posX - clickedBlock.getX()), (float) (entity.posY - clickedBlock.getY()), (float) (entity.posZ - clickedBlock.getZ()));
    }
	@Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
                .withProperty(FACING, EnumFacing.getFront(meta & 7));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
    
}
	


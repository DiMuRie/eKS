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

	public static final PropertyDirection FACING = PropertyDirection.func_177714_a("facing");
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 0.125D);
    protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.875D, 0D, 0D, 1D, 1D, 1D);
    protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0D, 0D, 0.875D, 1D, 1D, 1D);
    protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0D, 0D, 0D, 0.125D, 1D, 1D);
    protected static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.125D, 1D);
    protected static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0D, 1-0.125D, 0D, 1D, 1D, 1D);

	public Elevator() {
		super(Material.field_151594_q);
        this.func_149647_a(EKSItemLoader.tabEKS);
        this.func_149663_c("elevator");
        this.setRegistryName("elevator");
        this.func_149711_c(0.1F);
        this.func_149752_b(6.0F);
        this.func_149672_a(field_149762_H.field_185852_e);
        func_180632_j(field_176227_L.func_177621_b().func_177226_a(FACING, EnumFacing.NORTH));
        this.field_149765_K=2;
	  }
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new tileentityBouncePad();
    }
	@Override
    public void func_180663_b(World world, BlockPos pos, IBlockState state) {
        super.func_180663_b(world, pos, state);
        world.func_175713_t(pos);
    }

    @Override
    public boolean func_180648_a(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
        super.func_180648_a(worldIn, pos, state, eventID, eventParam);
        TileEntity tileentity = worldIn.func_175625_s(pos);
        return tileentity == null ? false : tileentity.func_145842_c(eventID, eventParam);
    }
	public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        EnumFacing enumfacing = (EnumFacing)state.func_177229_b(FACING);
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
    public BlockRenderLayer func_180664_k()
    {
        return BlockRenderLayer.CUTOUT;
    }
	public boolean func_149686_d(IBlockState state)
    {
        return false;
    }
	public boolean func_149662_c(IBlockState state)
    {
        return false;
    }
	public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity)
    {
		EnumFacing enumfacing = (EnumFacing)state.func_177229_b(FACING);
		if(enumfacing==EnumFacing.DOWN){
			entity.field_70181_x=1;
		}
		if(enumfacing==EnumFacing.UP){
			entity.field_70181_x=-1;
		}
		if(enumfacing==EnumFacing.NORTH){
			entity.field_70159_w=1;
		}
		if(enumfacing==EnumFacing.SOUTH){
			entity.field_70159_w=-1;
		}
		if(enumfacing==EnumFacing.WEST){
			entity.field_70179_y=-1;
		}
		if(enumfacing==EnumFacing.EAST){
            entity.field_70179_y=1;
		}
		
	}
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4)
    {
    par3List.add("�6[If Block Is Placed Sideways}");
    par3List.add("�6It basicly works like conveyor belts");
    par3List.add("�6Else it makes you bounce up/down");
    }
	public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side)
    {
        return wallPlacement(worldIn, pos, side.func_176734_d());
    }

    public boolean func_176196_c(World worldIn, BlockPos pos)
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
    public void func_180658_a(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        if (entityIn.func_70093_af())
        {
            super.func_180658_a(worldIn, pos, entityIn, fallDistance);
        }
        else
        {
            entityIn.func_180430_e(fallDistance, 0.0F);
        }
    }

    /**
     * Called when an Entity lands on this Block. This method *must* update motionY because the entity will not do that
     * on its own
     */
    public void func_176216_a(World worldIn, Entity entityIn)
    {
        if (entityIn.func_70093_af())
        {
            super.func_176216_a(worldIn, entityIn);
        }
        else if (entityIn.field_70181_x < 0.5D)
        {
            entityIn.field_70181_x = -entityIn.field_70181_x;

            if (!(entityIn instanceof EntityLivingBase))
            {
                entityIn.field_70181_x *= 1.2D;
            }
        }
    }

    protected static boolean wallPlacement(World worldIn, BlockPos blockPos, EnumFacing enumFacing)
    {
        BlockPos blockpos = blockPos.func_177972_a(enumFacing);
        return worldIn.func_180495_p(blockpos).isSideSolid(worldIn, blockpos, enumFacing.func_176734_d());
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    @Override
    public void func_180633_a(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.func_180501_a(pos, state.func_177226_a(FACING, getFacingFromEntity(pos, placer).func_176734_d()), 2);
    }

    public static EnumFacing getFacingFromEntity(BlockPos clickedBlock, EntityLivingBase entity) {
        return EnumFacing.func_176737_a((float) (entity.field_70165_t - clickedBlock.func_177958_n()), (float) (entity.field_70163_u - clickedBlock.func_177956_o()), (float) (entity.field_70161_v - clickedBlock.func_177952_p()));
    }
	@Override
    public IBlockState func_176203_a(int meta) {
        return func_176223_P()
                .func_177226_a(FACING, EnumFacing.func_82600_a(meta & 7));
    }

    @Override
    public int func_176201_c(IBlockState state) {
        return state.func_177229_b(FACING).func_176745_a();
    }
    protected BlockStateContainer func_180661_e()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
    
}
	


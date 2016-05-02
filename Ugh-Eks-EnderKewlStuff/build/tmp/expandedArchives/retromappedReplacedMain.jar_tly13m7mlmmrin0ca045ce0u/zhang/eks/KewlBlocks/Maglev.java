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
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zhang.eks.KewlItems.EKSItemLoader;
import zhang.eks.tileentity.TEMaglev;

public class Maglev extends Block {
	
	public static final PropertyDirection FACING = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
	public static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.0625*6D, 1D);
	public static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.0625*6D, 1D);
	public static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.0625*6D, 1D);
	public static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.0625*6D, 1D);
	public static final AxisAlignedBB PLAYER_DETECTION = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.9D, 1D);
	
	public Maglev() {
		super(Material.field_151573_f);
        this.func_149647_a(EKSItemLoader.tabEKS);
        this.func_149663_c("maglev");
        this.setRegistryName("maglev");
        this.func_149711_c(0.5F);
        this.func_149752_b(6.0F);
        this.func_149672_a(field_149762_H.field_185849_b);
        func_180632_j(makeDefaultState());
	}
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4)
    {
    par3List.add("�6This block`s model is wip");
    par3List.add("�6But this block is 100% operational");
    par3List.add("�6Best ridden in a minecart!");
    }
	public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEMaglev();
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
	public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos){
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
            	default:
            	return  AABB_NORTH;
        }
	}
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer func_180664_k()
    {
        return BlockRenderLayer.CUTOUT;
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
                entityIn.func_184195_f(true);
            }
        }
    }
    public boolean func_149686_d(IBlockState state)
    {
        return false;
    }
	public boolean func_149662_c(IBlockState state)
    {
        return false;
    }


	public IBlockState makeDefaultState() {
		return field_176227_L.func_177621_b().func_177226_a(FACING, EnumFacing.NORTH);
	}

	@Override
	public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {
		setDefaultFacing(worldIn, pos, state);
	}

	@Override
	public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.func_180501_a(pos, state.func_177226_a(FACING, placer.func_174811_aO().func_176734_d()), 2);
	}

	public void setDefaultFacing(World worldIn, BlockPos pos, IBlockState thisState) {
		if(!worldIn.field_72995_K) {
			IBlockState state = worldIn.func_180495_p(pos.func_177978_c());
			IBlockState state1 = worldIn.func_180495_p(pos.func_177968_d());
			IBlockState state2 = worldIn.func_180495_p(pos.func_177976_e());
			IBlockState state3 = worldIn.func_180495_p(pos.func_177974_f());
			EnumFacing enumfacing = thisState.func_177229_b(FACING);

			if(enumfacing == EnumFacing.NORTH && state.func_185913_b() && !state1.func_185913_b())
				enumfacing = EnumFacing.SOUTH;
			else if(enumfacing == EnumFacing.SOUTH && state1.func_185913_b() && !state.func_185913_b())
				enumfacing = EnumFacing.NORTH;
			else if(enumfacing == EnumFacing.WEST && state2.func_185913_b() && !state3.func_185913_b())
				enumfacing = EnumFacing.EAST;
			else if(enumfacing == EnumFacing.EAST && state3.func_185913_b() && !state2.func_185913_b())
				enumfacing = EnumFacing.WEST;

			worldIn.func_180501_a(pos, thisState.func_177226_a(FACING, enumfacing), 2);
		}
	}

	@Override
	public IBlockState func_176203_a(int meta) {
		EnumFacing enumfacing = EnumFacing.func_82600_a(meta);

		if(enumfacing.func_176740_k() == EnumFacing.Axis.Y)
			enumfacing = EnumFacing.NORTH;

		return func_176223_P().func_177226_a(FACING, enumfacing);
	}

	@Override
	public int func_176201_c(IBlockState state) {
		return state.func_177229_b(FACING).func_176745_a();
	}

	@Override
	protected BlockStateContainer func_180661_e() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity)
    {
		EnumFacing enumfacing = (EnumFacing)state.func_177229_b(FACING);
		if(enumfacing==EnumFacing.NORTH){
			entity.field_70181_x=0.08;
			entity.field_70179_y=0.1;
		}
		if(enumfacing==EnumFacing.SOUTH){
			entity.field_70181_x=0.08;
			entity.field_70179_y=-0.1;
		}
		if(enumfacing==EnumFacing.WEST){
			entity.field_70181_x=0.08;
			entity.field_70159_w=0.1;
		}
		if(enumfacing==EnumFacing.EAST){
            entity.field_70181_x=0.08;
            entity.field_70159_w=-0.1;
		}
		
	}
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,EntityMinecart cart , EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
	if(worldIn.field_72995_K){
		return true;
	}
	else {
		if(playerIn.func_184614_ca().func_77973_b()==Items.field_151143_au){
			worldIn.func_72838_d(cart);
			}
		if(playerIn.func_184592_cb().func_77973_b()==Items.field_151143_au){
			worldIn.func_72838_d(cart);
			}
		}
	return true;
	}
}

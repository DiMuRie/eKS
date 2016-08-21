package zhang.eks.KewlBlocks;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zhang.eks.KewlItems.EKSItemLoader;

public class WoodSteps extends Block {

	public static final PropertyInteger LAYERZ = PropertyInteger.create("layerz", 1, 8);
	protected static final AxisAlignedBB AABB_1 = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.125D, 1D);
	protected static final AxisAlignedBB AABB_2 = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.125*2D, 1D);
	protected static final AxisAlignedBB AABB_3 = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.125*3D, 1D);
	protected static final AxisAlignedBB AABB_4 = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.125*4D, 1D);
	protected static final AxisAlignedBB AABB_5 = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.125*5D, 1D);
	protected static final AxisAlignedBB AABB_6 = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.125*6D, 1D);
	protected static final AxisAlignedBB AABB_7 = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.125*7D, 1D);
	protected static final AxisAlignedBB AABB_8 = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.125*8D, 1D);
	
	public WoodSteps() {
		super(Material.CIRCUITS);
        this.setCreativeTab(EKSItemLoader.tabEKS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LAYERZ, Integer.valueOf(1)));
        //this.setUnlocalizedName("woodsteps");
        //this.setRegistryName("woodsteps");
        this.setHardness(0.5F);
        this.setSoundType(SoundType.WOOD);
        //GameRegistry.register(this);
        //GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		Integer intlay = ((Integer)state.getValue(LAYERZ));
		switch (intlay)
        {
            case 8:
                return  AABB_8;
            case 7:
                return  AABB_7;
            case 6:
                return  AABB_6;
            case 5:
            	return  AABB_5;
            case 4:
            	return AABB_4;
            case 3:
            	return AABB_3;
            case 2:
            	return AABB_2;
            case 1:
            	default:
            	return  AABB_1;
        }
    }
	public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(LAYERZ));
    }
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(LAYERZ, Integer.valueOf(MathHelper.clamp_int(meta, 1, 8)));
    }
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {LAYERZ});
    }
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (heldItem == null)
        {
            return true;
        }
        else
        {
            int i = ((Integer)state.getValue(LAYERZ)).intValue();
            Item item = heldItem.getItem();

            if (item == EKSItemLoader.Saw)
            {
                if (i == 1)
                {
                    this.setWoodLAYERZ2(worldIn, pos, state, 8);
                }
                else if (i == 2)
                {
                    this.setWoodLAYERZ3(worldIn, pos, state, 8);
                }
                else if (i == 3)
                {
                    this.setWoodLAYERZ4(worldIn, pos, state, 8);
                }
                else if (i == 4)
                {
                    this.setWoodLAYERZ5(worldIn, pos, state, 8);
                }
                else if (i == 5)
                {
                    this.setWoodLAYERZ6(worldIn, pos, state, 8);
                }
                else if (i == 6)
                {
                    this.setWoodLAYERZ7(worldIn, pos, state, 8);
                }
                else if (i == 7)
                {
                    this.setWoodLAYERZ8(worldIn, pos, state, 8);
                }
                else if (i == 8)
                {
                    this.setWoodLAYERZ1(worldIn, pos, state, 8);
                }

                return true;
            }
            else{
            	return false;
            }
        }
    }
	public void setWoodLAYERZ1(World worldIn, BlockPos pos, IBlockState state, int WUDLAY)
    {
        worldIn.setBlockState(pos, state.withProperty(LAYERZ, Integer.valueOf(MathHelper.clamp_int(WUDLAY, 0, 1))), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }
	public void setWoodLAYERZ2(World worldIn, BlockPos pos, IBlockState state, int WUDLAY)
    {
        worldIn.setBlockState(pos, state.withProperty(LAYERZ, Integer.valueOf(MathHelper.clamp_int(WUDLAY, 0, 2))), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }
	public void setWoodLAYERZ3(World worldIn, BlockPos pos, IBlockState state, int WUDLAY)
    {
        worldIn.setBlockState(pos, state.withProperty(LAYERZ, Integer.valueOf(MathHelper.clamp_int(WUDLAY, 0, 3))), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }
	public void setWoodLAYERZ4(World worldIn, BlockPos pos, IBlockState state, int WUDLAY)
    {
        worldIn.setBlockState(pos, state.withProperty(LAYERZ, Integer.valueOf(MathHelper.clamp_int(WUDLAY, 0, 4))), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }
	public void setWoodLAYERZ5(World worldIn, BlockPos pos, IBlockState state, int WUDLAY)
    {
        worldIn.setBlockState(pos, state.withProperty(LAYERZ, Integer.valueOf(MathHelper.clamp_int(WUDLAY, 0, 5))), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }
	public void setWoodLAYERZ6(World worldIn, BlockPos pos, IBlockState state, int WUDLAY)
    {
        worldIn.setBlockState(pos, state.withProperty(LAYERZ, Integer.valueOf(MathHelper.clamp_int(WUDLAY, 0, 6))), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }
	public void setWoodLAYERZ7(World worldIn, BlockPos pos, IBlockState state, int WUDLAY)
    {
        worldIn.setBlockState(pos, state.withProperty(LAYERZ, Integer.valueOf(MathHelper.clamp_int(WUDLAY, 0, 7))), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }
	public void setWoodLAYERZ8(World worldIn, BlockPos pos, IBlockState state, int WUDLAY)
    {
        worldIn.setBlockState(pos, state.withProperty(LAYERZ, Integer.valueOf(MathHelper.clamp_int(WUDLAY, 0, 8))), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }

}

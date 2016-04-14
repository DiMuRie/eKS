package zhang.eks.KewlBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zhang.eks.KewlItems.EKSItemLoader;

public class EscalatorReverse extends EscalatorBase {

	public static final PropertyEnum<EscalatorBase.EnumRailDirection> SHAPE = PropertyEnum.<EscalatorBase.EnumRailDirection>create("shape", EscalatorBase.EnumRailDirection.class);

	
	public EscalatorReverse(boolean isPowered) {
		super(false);
        this.setCreativeTab(EKSItemLoader.tabEKS);
        this.setUnlocalizedName("escalator_reverse");
        this.setHardness(0.5F);
        this.setResistance(600.0F);
        this.setStepSound(stepSound.GROUND);
        this.setDefaultState(this.blockState.getBaseState().withProperty(SHAPE, EscalatorBase.EnumRailDirection.NORTH));
        this.translucent = true;
	}
    public IProperty<EscalatorBase.EnumRailDirection> getShapeProperty()
    {
        return SHAPE;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(SHAPE, EscalatorBase.EnumRailDirection.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EscalatorBase.EnumRailDirection)state.getValue(SHAPE)).getMetadata();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {SHAPE});
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();
                return true;
    }
	
}

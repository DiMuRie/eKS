package zhang.eks.KewlBlocks;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class MaglevBase extends Block {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final AxisAlignedBB AABB_NORMAL = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.0625*6D, 1D);
    public static final AxisAlignedBB AABB_ANOTHER = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.0625*6D, 1D);
    public final boolean isPowered;

    public static boolean isRailBlock(World worldIn, BlockPos pos)
    {
        return isRailBlock(worldIn.getBlockState(pos));
    }

    public static boolean isRailBlock(IBlockState state)
    {
        Block block = state.getBlock();
        return block instanceof MaglevBase;
    }

    public MaglevBase(boolean isPowered)
    {
        super(Material.iron);
        this.isPowered = isPowered;
        this.setCreativeTab(CreativeTabs.tabTransport);
    }

    public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }
    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.NORMAL;
    }

    /**
     * Returns the max speed of the rail at the specified position.
     * @param world The world.
     * @param cart The cart on the rail, may be null.
     * @param pod Block's position in world
     * @return The max speed of the current rail.
     */
    public float getRailMaxSpeed(World world, net.minecraft.entity.item.EntityMinecart cart, BlockPos pos)
    {
        return 0.6f;
    }

    /**
     * This function is called by any minecart that passes over this rail.
     * It is called once per update tick that the minecart is on the rail.
     * @param world The world.
     * @param cart The cart on the rail.
     * @param pod Block's position in world
     */
    public void onMinecartPass(World world, net.minecraft.entity.item.EntityMinecart cart, BlockPos pos)
    {
    }
    public class Rail
    {
        private final World world;
        private final BlockPos pos;
        private final MaglevBase block;
        private IBlockState state;
        private final List<BlockPos> connections = Lists.<BlockPos>newArrayList();

        public Rail(World worldIn, BlockPos pos, IBlockState state)
        {
            this.world = worldIn;
            this.pos = pos;
            this.state = state;
            this.block = (MaglevBase)state.getBlock();
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
            this.conectMaglev(enumfacing);
        }

		public List<BlockPos> func_185763_a()
        {
            return this.connections;
        }

        private void conectMaglev(EnumFacing enumfacing)
        {
            this.connections.clear();

            switch (enumfacing)
            {
                case NORTH:
                    this.connections.add(this.pos.north());
                    this.connections.add(this.pos.south());
                    break;
                case EAST:
                    this.connections.add(this.pos.west());
                    this.connections.add(this.pos.east());
                    break;
                case SOUTH:
                    this.connections.add(this.pos.west());
                    this.connections.add(this.pos.east().up());
                    break;
                case WEST:
                	default:
                    this.connections.add(this.pos.west().up());
                    this.connections.add(this.pos.east());
            }
        }

        private void func_150651_b()
        {
            for (int i = 0; i < this.connections.size(); ++i)
            {
                MaglevBase.Rail MaglevBase$rail = this.findRailAt((BlockPos)this.connections.get(i));

                if (MaglevBase$rail != null && MaglevBase$rail.funcmbdr(this))
                {
                    this.connections.set(i, MaglevBase$rail.pos);
                }
                else
                {
                    this.connections.remove(i--);
                }
            }
        }

        private boolean hasRailAt(BlockPos pos)
        {
            return MaglevBase.isRailBlock(this.world, pos) || MaglevBase.isRailBlock(this.world, pos.up()) || MaglevBase.isRailBlock(this.world, pos.down());
        }

        private MaglevBase.Rail findRailAt(BlockPos pos)
        {
            IBlockState iblockstate = this.world.getBlockState(pos);

            if (MaglevBase.isRailBlock(iblockstate))
            {
                return MaglevBase.this.new Rail(this.world, pos, iblockstate);
            }
            else
            {
                BlockPos lvt_2_1_ = pos.up();
                iblockstate = this.world.getBlockState(lvt_2_1_);

                if (MaglevBase.isRailBlock(iblockstate))
                {
                    return MaglevBase.this.new Rail(this.world, lvt_2_1_, iblockstate);
                }
                else
                {
                    lvt_2_1_ = pos.down();
                    iblockstate = this.world.getBlockState(lvt_2_1_);
                    return MaglevBase.isRailBlock(iblockstate) ? MaglevBase.this.new Rail(this.world, lvt_2_1_, iblockstate) : null;
                }
            }
        }

        private boolean funcmbdr(MaglevBase.Rail MBDR)
        {
            return this.funcbpt(MBDR.pos);
        }

        private boolean funcbpt(BlockPos pos)
        {
            for (int i = 0; i < this.connections.size(); ++i)
            {
                BlockPos blockpos = (BlockPos)this.connections.get(i);

                if (blockpos.getX() == pos.getX() && blockpos.getZ() == pos.getZ())
                {
                    return true;
                }
            }

            return false;
        }

        /**
         * Counts the number of rails adjacent to this rail.
         */
        public int countAdjacentRails()
        {
            int i = 0;

            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                if (this.hasRailAt(this.pos.offset(enumfacing)))
                {
                    ++i;
                }
            }

            return i;
        }

        private boolean funcmbdrisrail(MaglevBase.Rail rail)
        {
            return this.funcmbdr(rail) || this.connections.size() != 2;
        }
        /*public MaglevBase.Rail func_180364_a(boolean p_180364_1_, boolean p_180364_2_)
        {
            BlockPos blockpos = this.pos.north();
            BlockPos blockpos1 = this.pos.south();
            BlockPos blockpos2 = this.pos.west();
            BlockPos blockpos3 = this.pos.east();
            boolean flag = this.func_180361_d(blockpos);
            boolean flag1 = this.func_180361_d(blockpos1);
            boolean flag2 = this.func_180361_d(blockpos2);
            boolean flag3 = this.func_180361_d(blockpos3);
            MaglevBase.EnumRailDirection MaglevBase$enumraildirection = null;

            if ((flag || flag1) && !flag2 && !flag3)
            {
                MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.NORTH_SOUTH;
            }

            if ((flag2 || flag3) && !flag && !flag1)
            {
                MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.EAST_WEST;
            }

            if (!this.isPowered)
            {
                if (flag1 && flag3 && !flag && !flag2)
                {
                    MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.SOUTH_EAST;
                }

                if (flag1 && flag2 && !flag && !flag3)
                {
                    MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.SOUTH_WEST;
                }

                if (flag && flag2 && !flag1 && !flag3)
                {
                    MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.NORTH_WEST;
                }

                if (flag && flag3 && !flag1 && !flag2)
                {
                    MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.NORTH_EAST;
                }
            }

            if (MaglevBase$enumraildirection == null)
            {
                if (flag || flag1)
                {
                    MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.NORTH_SOUTH;
                }

                if (flag2 || flag3)
                {
                    MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.EAST_WEST;
                }

                if (!this.isPowered)
                {
                    if (p_180364_1_)
                    {
                        if (flag1 && flag3)
                        {
                            MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.SOUTH_EAST;
                        }

                        if (flag2 && flag1)
                        {
                            MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.SOUTH_WEST;
                        }

                        if (flag3 && flag)
                        {
                            MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.NORTH_EAST;
                        }

                        if (flag && flag2)
                        {
                            MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.NORTH_WEST;
                        }
                    }
                    else
                    {
                        if (flag && flag2)
                        {
                            MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.NORTH_WEST;
                        }

                        if (flag3 && flag)
                        {
                            MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.NORTH_EAST;
                        }

                        if (flag2 && flag1)
                        {
                            MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.SOUTH_WEST;
                        }

                        if (flag1 && flag3)
                        {
                            MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.SOUTH_EAST;
                        }
                    }
                }
            }

            if (MaglevBase$enumraildirection == MaglevBase.EnumRailDirection.NORTH_SOUTH && canMakeSlopes)
            {
                if (MaglevBase.isRailBlock(this.world, blockpos.up()))
                {
                    MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.ASCENDING_NORTH;
                }

                if (MaglevBase.isRailBlock(this.world, blockpos1.up()))
                {
                    MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.ASCENDING_SOUTH;
                }
            }

            if (MaglevBase$enumraildirection == MaglevBase.EnumRailDirection.EAST_WEST && canMakeSlopes)
            {
                if (MaglevBase.isRailBlock(this.world, blockpos3.up()))
                {
                    MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.ASCENDING_EAST;
                }

                if (MaglevBase.isRailBlock(this.world, blockpos2.up()))
                {
                    MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.ASCENDING_WEST;
                }
            }

            if (MaglevBase$enumraildirection == null)
            {
                MaglevBase$enumraildirection = MaglevBase.EnumRailDirection.NORTH_SOUTH;
            }

            this.conectMaglev(MaglevBase$enumraildirection);
            this.state = this.state.withProperty(this.block.getShapeProperty(), MaglevBase$enumraildirection);

            if (p_180364_2_ || this.world.getBlockState(this.pos) != this.state)
            {
                this.world.setBlockState(this.pos, this.state, 3);

                for (int i = 0; i < this.connections.size(); ++i)
                {
                    MaglevBase.Rail MaglevBase$rail = this.findRailAt((BlockPos)this.connections.get(i));

                    if (MaglevBase$rail != null)
                    {
                        MaglevBase$rail.func_150651_b();

                        if (MaglevBase$rail.funcmbdrisrail(this))
                        {
                            MaglevBase$rail.funcanothermbdrisr(this);
                        }
                    }
                }
            }

            return this;
        }*/

        public IBlockState getBlockState()
        {
            return this.state;
        }
    }
	
}

package zhang.eks.KewlBlocks;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EscalatorBase extends Block {

    protected final boolean isPowered;

    public static boolean isRailBlock(World worldIn, BlockPos pos)
    {
        return isRailBlock(worldIn.getBlockState(pos));
    }

    public static boolean isRailBlock(IBlockState state)
    {
        Block block = state.getBlock();
        return block instanceof Escalator ;
    }

    public EscalatorBase(boolean isPowered)
    {
        super(Material.circuits);
        this.isPowered = isPowered;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        EscalatorBase.EnumRailDirection EscalatorBase$enumraildirection = iblockstate.getBlock() == this ? (EscalatorBase.EnumRailDirection)iblockstate.getValue(this.getShapeProperty()) : null;

        if (EscalatorBase$enumraildirection != null && EscalatorBase$enumraildirection.isAscending())
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1F, 1.0F);
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1F, 1.0F);
        }
    }

    private void setBlockBounds(float f, float g, float h, float i, float j, float k) {
		
	}

	public boolean isFullCube()
    {
        return false;
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            state = this.func_176564_a(worldIn, pos, state, true);

            if (this.isPowered)
            {
                this.onNeighborBlockChange(worldIn, pos, state, this);
            }
        }
    }

    /**
     * Called when a neighboring block changes.
     */
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        if (!worldIn.isRemote)
        {
            EscalatorBase.EnumRailDirection EscalatorBase$enumraildirection = (EscalatorBase.EnumRailDirection)state.getValue(this.getShapeProperty());
            boolean flag = false;

            if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP))
            {
                flag = false;
            }

            if (EscalatorBase$enumraildirection == EscalatorBase.EnumRailDirection.ASCENDING_EAST && !worldIn.getBlockState(pos.east()).isSideSolid(worldIn, pos.east(), EnumFacing.UP))
            {
                flag = false;
            }
            else if (EscalatorBase$enumraildirection == EscalatorBase.EnumRailDirection.ASCENDING_WEST && !worldIn.getBlockState(pos.west()).isSideSolid(worldIn, pos.west(), EnumFacing.UP))
            {
                flag = false;
            }
            else if (EscalatorBase$enumraildirection == EscalatorBase.EnumRailDirection.ASCENDING_NORTH && !worldIn.getBlockState(pos.north()).isSideSolid(worldIn, pos.north(), EnumFacing.UP))
            {
                flag = false;
            }
            else if (EscalatorBase$enumraildirection == EscalatorBase.EnumRailDirection.ASCENDING_SOUTH && !worldIn.getBlockState(pos.south()).isSideSolid(worldIn, pos.south(), EnumFacing.UP))
            {
                flag = false;
            }
            else
            {
                this.onNeighborChangedInternal(worldIn, pos, state, neighborBlock);
            }
        }
    }

    protected void onNeighborChangedInternal(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
    }

    protected IBlockState func_176564_a(World worldIn, BlockPos p_176564_2_, IBlockState p_176564_3_, boolean p_176564_4_)
    {
        return worldIn.isRemote ? p_176564_3_ : (new EscalatorBase.Rail(worldIn, p_176564_2_, p_176564_3_)).func_180364_a(worldIn.isBlockPowered(p_176564_2_), p_176564_4_).getBlockState();
    }

    public int getMobilityFlag()
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    public abstract IProperty<EscalatorBase.EnumRailDirection> getShapeProperty();

    /* ======================================== FORGE START =====================================*/
    /**
     * Return true if the rail can make corners.
     * Used by placement logic.
     * @param world The world.
     * @param pod Block's position in world
     * @return True if the rail can make corners.
     */
    public boolean isFlexibleRail(IBlockAccess world, BlockPos pos)
    {
        return !this.isPowered;
    }

    /**
     * Returns true if the rail can make up and down slopes.
     * Used by placement logic.
     * @param world The world.
     * @param pod Block's position in world
     * @return True if the rail can make slopes.
     */
    public boolean canMakeSlopes(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    /**
     * Returns the max speed of the rail at the specified position.
     * @param world The world.
     * @param cart The cart on the rail, may be null.
     * @param pod Block's position in world
     * @return The max speed of the current rail.
     */
    public float getRailMaxSpeed(World world, net.minecraft.entity.Entity cart, BlockPos pos)
    {
        return 0.2f;
    }

    /**
     * This function is called by any minecart that passes over this rail.
     * It is called once per update tick that the minecart is on the rail.
     * @param world The world.
     * @param cart The cart on the rail.
     * @param pod Block's position in world
     */
    public void onMinecartPass(World world, Entity cart, BlockPos pos)
    {
    }

    /**
     * Rotate the block. For vanilla blocks this rotates around the axis passed in (generally, it should be the "face" that was hit).
     * Note: for mod blocks, this is up to the block and modder to decide. It is not mandated that it be a rotation around the
     * face, but could be a rotation to orient *to* that face, or a visiting of possible rotations.
     * The method should return true if the rotation was successful though.
     *
     * @param world The world
     * @param pos Block position in world
     * @param axis The axis to rotate around
     * @return True if the rotation was successful, False if the rotation failed, or is not possible
     */
    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis)
    {
        IBlockState state = world.getBlockState(pos);
        for (IProperty prop : state.getProperties().keySet())
        {
            if (prop.getName().equals("shape"))
            {
                world.setBlockState(pos, state.cycleProperty(prop));
                return true;
            }
        }
        return false;
    }

    /* ======================================== FORGE END =====================================*/

    public static enum EnumRailDirection implements IStringSerializable
    {
        NORTH(0, "north"),
        SOUTH(1, "south"),
        WEST(2, "west"),
        EAST(3, "east"),
        ASCENDING_EAST(4, "ascending_east"),
        ASCENDING_WEST(5, "ascending_west"),
        ASCENDING_NORTH(6, "ascending_north"),
        ASCENDING_SOUTH(7, "ascending_south");

        private static final EscalatorBase.EnumRailDirection[] META_LOOKUP = new EscalatorBase.EnumRailDirection[values().length];
        private final int meta;
        private final String name;

        private EnumRailDirection(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public boolean isAscending()
        {
            return this == ASCENDING_NORTH || this == ASCENDING_EAST || this == ASCENDING_SOUTH || this == ASCENDING_WEST;
        }

        public static EscalatorBase.EnumRailDirection byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        static
        {
            for (EscalatorBase.EnumRailDirection EscalatorBase$enumraildirection : values())
            {
                META_LOOKUP[EscalatorBase$enumraildirection.getMetadata()] = EscalatorBase$enumraildirection;
            }
        }
    }

    public class Rail
    {
        private final World world;
        private final BlockPos pos;
        private final EscalatorBase block;
        private IBlockState state;
        private final boolean isPowered;
        private final List<BlockPos> field_150657_g = Lists.<BlockPos>newArrayList();
        private final boolean canMakeSlopes;

        public Rail(World worldIn, BlockPos pos, IBlockState state)
        {
            this.world = worldIn;
            this.pos = pos;
            this.state = state;
            this.block = (EscalatorBase)state.getBlock();
            EscalatorBase.EnumRailDirection EscalatorBase$enumraildirection = (EscalatorBase.EnumRailDirection)state.getValue(EscalatorBase.this.getShapeProperty());
            this.isPowered = !this.block.isFlexibleRail(worldIn, pos);
            canMakeSlopes = this.block.canMakeSlopes(worldIn, pos);
            this.func_180360_a(EscalatorBase$enumraildirection);
        }

        private void func_180360_a(EscalatorBase.EnumRailDirection p_180360_1_)
        {
            this.field_150657_g.clear();

            switch (p_180360_1_)
            {
                case SOUTH:
                    this.field_150657_g.add(this.pos.south());
                    this.field_150657_g.add(this.pos.north());
                    break;
                case NORTH:
                    this.field_150657_g.add(this.pos.north());
                    this.field_150657_g.add(this.pos.south());
                    break;
                case EAST:
                    this.field_150657_g.add(this.pos.east());
                    this.field_150657_g.add(this.pos.west());
                    break;
                case WEST:
                    this.field_150657_g.add(this.pos.west());
                    this.field_150657_g.add(this.pos.east());
                    break;
                case ASCENDING_EAST:
                    this.field_150657_g.add(this.pos.west());
                    this.field_150657_g.add(this.pos.east().up());
                    break;
                case ASCENDING_WEST:
                    this.field_150657_g.add(this.pos.west().up());
                    this.field_150657_g.add(this.pos.east());
                    break;
                case ASCENDING_NORTH:
                    this.field_150657_g.add(this.pos.north().up());
                    this.field_150657_g.add(this.pos.south());
                    break;
                case ASCENDING_SOUTH:
                    this.field_150657_g.add(this.pos.north());
                    this.field_150657_g.add(this.pos.south().up());
                    break;
            }
        }

        private void func_150651_b()
        {
            for (int i = 0; i < this.field_150657_g.size(); ++i)
            {
                EscalatorBase.Rail EscalatorBase$rail = this.findRailAt((BlockPos)this.field_150657_g.get(i));

                if (EscalatorBase$rail != null && EscalatorBase$rail.func_150653_a(this))
                {
                    this.field_150657_g.set(i, EscalatorBase$rail.pos);
                }
                else
                {
                    this.field_150657_g.remove(i--);
                }
            }
        }

        private boolean hasRailAt(BlockPos pos)
        {
            return EscalatorBase.isRailBlock(this.world, pos) || EscalatorBase.isRailBlock(this.world, pos.up()) || EscalatorBase.isRailBlock(this.world, pos.down());
        }

        private EscalatorBase.Rail findRailAt(BlockPos pos)
        {
            IBlockState iblockstate = this.world.getBlockState(pos);

            if (EscalatorBase.isRailBlock(iblockstate))
            {
                return EscalatorBase.this.new Rail(this.world, pos, iblockstate);
            }
            else
            {
                BlockPos lvt_2_1_ = pos.up();
                iblockstate = this.world.getBlockState(lvt_2_1_);

                if (EscalatorBase.isRailBlock(iblockstate))
                {
                    return EscalatorBase.this.new Rail(this.world, lvt_2_1_, iblockstate);
                }
                else
                {
                    lvt_2_1_ = pos.down();
                    iblockstate = this.world.getBlockState(lvt_2_1_);
                    return EscalatorBase.isRailBlock(iblockstate) ? EscalatorBase.this.new Rail(this.world, lvt_2_1_, iblockstate) : null;
                }
            }
        }

        private boolean func_150653_a(EscalatorBase.Rail p_150653_1_)
        {
            return this.func_180363_c(p_150653_1_.pos);
        }

        private boolean func_180363_c(BlockPos p_180363_1_)
        {
            for (int i = 0; i < this.field_150657_g.size(); ++i)
            {
                BlockPos blockpos = (BlockPos)this.field_150657_g.get(i);

                if (blockpos.getX() == p_180363_1_.getX() && blockpos.getZ() == p_180363_1_.getZ())
                {
                    return true;
                }
            }

            return false;
        }

        /**
         * Counts the number of rails adjacent to this rail.
         */
        protected int countAdjacentRails()
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

        private boolean func_150649_b(EscalatorBase.Rail rail)
        {
            return this.func_150653_a(rail) || this.field_150657_g.size() != 2;
        }
        private void func_150645_c(EscalatorBase.Rail p_150645_1_)
        {
            this.field_150657_g.add(p_150645_1_.pos);
            BlockPos blockpos = this.pos.north();
            BlockPos blockpos1 = this.pos.south();
            BlockPos blockpos2 = this.pos.west();
            BlockPos blockpos3 = this.pos.east();
            boolean flag = this.func_180363_c(blockpos);
            boolean flag1 = this.func_180363_c(blockpos1);
            boolean flag2 = this.func_180363_c(blockpos2);
            boolean flag3 = this.func_180363_c(blockpos3);
            EscalatorBase.EnumRailDirection EscalatorBase$enumraildirection = null;

            if (flag || flag1)
            {
                EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.SOUTH;
            }
            if (flag1 || flag)
            {
                EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.NORTH;
            }

            if (flag2 || flag3)
            {
                EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.WEST;
            }
            if (flag3 || flag2)
            {
                EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.EAST;
            }

            if (EscalatorBase$enumraildirection == EscalatorBase.EnumRailDirection.NORTH && canMakeSlopes)
            {
                if (EscalatorBase.isRailBlock(this.world, blockpos.up()))
                {
                    EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.ASCENDING_NORTH;
                }
            }

            if (EscalatorBase$enumraildirection == EscalatorBase.EnumRailDirection.EAST && canMakeSlopes)
            {
                if (EscalatorBase.isRailBlock(this.world, blockpos3.up()))
                {
                    EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.ASCENDING_EAST;
                }
                    
            }
            if (EscalatorBase$enumraildirection == EscalatorBase.EnumRailDirection.SOUTH && canMakeSlopes)
            {
                if (EscalatorBase.isRailBlock(this.world, blockpos.up()))
                {
                    EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.ASCENDING_SOUTH;
                }
            }

            if (EscalatorBase$enumraildirection == EscalatorBase.EnumRailDirection.WEST && canMakeSlopes)
            {
                if (EscalatorBase.isRailBlock(this.world, blockpos3.up()))
                {
                    EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.ASCENDING_WEST;
                }
            }

            if (EscalatorBase$enumraildirection == null)
            {
                EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.NORTH;
            }

            this.state = this.state.withProperty(this.block.getShapeProperty(), EscalatorBase$enumraildirection);
            this.world.setBlockState(this.pos, this.state, 3);
        }

        private boolean func_180361_d(BlockPos p_180361_1_)
        {
            EscalatorBase.Rail EscalatorBase$rail = this.findRailAt(p_180361_1_);

            if (EscalatorBase$rail == null)
            {
                return false;
            }
            else
            {
                EscalatorBase$rail.func_150651_b();
                return EscalatorBase$rail.func_150649_b(this);
            }
        }

        public EscalatorBase.Rail func_180364_a(boolean p_180364_1_, boolean p_180364_2_)
        {
            BlockPos blockpos = this.pos.north();
            BlockPos blockpos1 = this.pos.south();
            BlockPos blockpos2 = this.pos.west();
            BlockPos blockpos3 = this.pos.east();
            boolean flag = this.func_180361_d(blockpos);
            boolean flag1 = this.func_180361_d(blockpos1);
            boolean flag2 = this.func_180361_d(blockpos2);
            boolean flag3 = this.func_180361_d(blockpos3);
            EscalatorBase.EnumRailDirection EscalatorBase$enumraildirection = null;

            if ((flag || flag1) && !flag2 && !flag3)
            {
                EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.SOUTH;
            }

            if ((flag2 || flag3) && !flag && !flag1)
            {
                EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.WEST;
            }
            if ((flag1 || flag) && !flag2 && !flag3)
            {
                EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.NORTH;
            }

            if ((flag3 || flag2) && !flag && !flag1)
            {
                EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.EAST;
            }

            if (EscalatorBase$enumraildirection == null)
            {
                if (flag || flag1)
                {
                    EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.SOUTH;
                }

                if (flag2 || flag3)
                {
                    EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.WEST;
                }
                if (flag1 || flag)
                {
                    EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.NORTH;
                }

                if (flag3 || flag2)
                {
                    EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.EAST;
                }
            }

            if (EscalatorBase$enumraildirection == EscalatorBase.EnumRailDirection.NORTH && canMakeSlopes)
            {
                if (EscalatorBase.isRailBlock(this.world, blockpos.up()))
                {
                    EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.ASCENDING_NORTH;
                }
            }

            if (EscalatorBase$enumraildirection == EscalatorBase.EnumRailDirection.EAST && canMakeSlopes)
            {
                if (EscalatorBase.isRailBlock(this.world, blockpos3.up()))
                {
                    EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.ASCENDING_EAST;
                }
                if (EscalatorBase$enumraildirection == EscalatorBase.EnumRailDirection.SOUTH && canMakeSlopes)
                {
                    if (EscalatorBase.isRailBlock(this.world, blockpos.up()))
                    {
                        EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.ASCENDING_SOUTH;
                    }
                }

                if (EscalatorBase$enumraildirection == EscalatorBase.EnumRailDirection.WEST && canMakeSlopes)
                {
                    if (EscalatorBase.isRailBlock(this.world, blockpos3.up()))
                    {
                        EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.ASCENDING_WEST;
                    }
                }
            

            if (EscalatorBase$enumraildirection == null)
            {
                EscalatorBase$enumraildirection = EscalatorBase.EnumRailDirection.NORTH;
            }

            this.func_180360_a(EscalatorBase$enumraildirection);
            this.state = this.state.withProperty(this.block.getShapeProperty(), EscalatorBase$enumraildirection);

            if (p_180364_2_ || this.world.getBlockState(this.pos) != this.state)
            {
                this.world.setBlockState(this.pos, this.state, 3);

                for (int i = 0; i < this.field_150657_g.size(); ++i)
                {
                    EscalatorBase.Rail EscalatorBase$rail = this.findRailAt((BlockPos)this.field_150657_g.get(i));

                    if (EscalatorBase$rail != null)
                    {
                        EscalatorBase$rail.func_150651_b();

                        if (EscalatorBase$rail.func_150649_b(this))
                        {
                            EscalatorBase$rail.func_150645_c(this);
                        }
                    }
                }
            }
            }

            return this;
        }

        public IBlockState getBlockState()
        {
            return this.state;
        }
    }
	
}

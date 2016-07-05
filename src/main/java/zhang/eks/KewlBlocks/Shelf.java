package zhang.eks.KewlBlocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zhang.eks.KewlItems.EKSItemLoader;
import zhang.eks.tileentity.TEShelf;
import zhang.eks.tileentity.TEShelfSR;

public class Shelf extends Block implements ITileEntityProvider {

	
	public Shelf() {
		super(Material.CIRCUITS);
        this.setCreativeTab(EKSItemLoader.tabEKS);
        this.setUnlocalizedName("shelf");
        this.setRegistryName("shelf");
        this.setHardness(0.5F);
        this.setResistance(60.0F);
        GameRegistry.register(this);
    	GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        ClientRegistry.bindTileEntitySpecialRenderer(TEShelf.class, new TEShelfSR());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEShelf();
    }
    private TEShelf getTE(World world, BlockPos pos) {
        return (TEShelf) world.getTileEntity(pos);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
        	TEShelf te = getTE(world, pos);
            if (te.getStack() == null) {
                if (player.getHeldItem(hand) != null) {
                    // There is no item in the pedestal and the player is holding an item. We move that item
                    // to the pedestal
                    te.setStack(player.getHeldItem(hand));
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                    // Make sure the client knows about the changes in the player inventory
                    player.openContainer.detectAndSendChanges();
                }
            } else {
                // There is a stack in the pedestal. In this case we remove it and try to put it in the
                // players inventory if there is room
                ItemStack stack = te.getStack();
                te.setStack(null);
                if (!player.inventory.addItemStackToInventory(stack)) {
                    // Not possible. Throw item in the world
                    EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
                    world.spawnEntityInWorld(entityItem);
                } else {
                    player.openContainer.detectAndSendChanges();
                }
            }
        }

        // Return true also on the client to make sure that MC knows we handled this and will not try to place
        // a block on the client
        return true;
    }
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4)
    {
    par3List.add("§rStores a maximum of 64 items of the same type");
    par3List.add("§bAvailable actions:");
    par3List.add("§b[Right Click]§rPuts curent ItemStack from");
    par3List.add("§rplayer`s main hand in the block`s inventory");
    par3List.add("§b[Right Click while block has item and player`s hand is empty]");
    par3List.add("§rtakes the ItemStack from the block and transfers to the player`s main hand");
    }
}

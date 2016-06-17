package zhang.eks.KewlBlocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zhang.eks.KewlItems.EKSItemLoader;

public class Fakeiron extends Block {

	public Fakeiron() {
		super(Material.IRON);
        this.setCreativeTab(EKSItemLoader.tabEKS);
        this.setUnlocalizedName("fakeiron");
        this.setRegistryName("fakeiron");
        this.setHardness(0.9F);
        this.setResistance(6.0F);
        GameRegistry.register(this);
    	GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4)
    {
    par3List.add("§5Fake iron,but still pretty precious....like mei");
    par3List.add("§5which is bae");
    }
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	
}

package zhang.eks.KewlItems;

import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Saw extends ItemPickaxe {

	public Saw(ToolMaterial bhod) {
		super(bhod);
        setRegistryName("saw");
        setUnlocalizedName("saw");     
        setCreativeTab(EKSItemLoader.tabEKS);
        setMaxStackSize(1);
	
	}
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4)
    {
    par3List.add("§6I saw a saw!");
    par3List.add("§5And it was epic!");
    }
	public ActionResult<ItemStack> onItemRightClick(ActionResult<ItemStack> par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, EnumHand hand)
	{
	par3EntityPlayer.capabilities.allowFlying = true;
	par3EntityPlayer.capabilities.setFlySpeed(0.15F);
	par3EntityPlayer.capabilities.setPlayerWalkSpeed(0.15F);
		 return par1ItemStack;
	}
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
}

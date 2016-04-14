package zhang.eks.KewlItems;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Saw extends ItemPickaxe {

	public Saw(ToolMaterial bhod) {
		super(bhod);
        setRegistryName("saw");
        setUnlocalizedName("saw");     
        GameRegistry.registerItem(this);
        setCreativeTab(EKSItemLoader.tabEKS);
        setMaxStackSize(1);
	
	}
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4)
    {
    par3List.add("§6I saw a saw!");
    par3List.add("§5And it was epic!");
    }
	public ActionResult onItemRightClick(ActionResult par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, EnumHand hand)
	{
	par3EntityPlayer.capabilities.allowFlying = true;
	par3EntityPlayer.capabilities.setFlySpeed(0.15F);
	par3EntityPlayer.capabilities.setPlayerWalkSpeed(0.15F);
		 return par1ItemStack;
	}
	
}

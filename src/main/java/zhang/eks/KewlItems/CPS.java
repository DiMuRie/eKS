package zhang.eks.KewlItems;

import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CPS extends ItemSword {


	
	public CPS() {
		super(EKSItemLoader.CPSM);
        setRegistryName("crystal_plasma_sword");
        setUnlocalizedName("crystal_plasma_sword");     
        setCreativeTab(EKSItemLoader.tabEKS);
        setMaxStackSize(1);
        GameRegistry.register(this);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity ent, int par4, boolean par5) {
		stack.setItemDamage(stack.getItemDamage()+2);
	}
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4)
    {
    par3List.add("§6The Crystal Plasma Sword");
    par3List.add("§6Is the best");
    }
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}

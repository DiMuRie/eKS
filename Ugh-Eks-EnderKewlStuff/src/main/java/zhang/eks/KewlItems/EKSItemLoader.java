package zhang.eks.KewlItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zhang.eks.EnderKewlStuff;
import zhang.eks.EnderKewlStuffLib;

public class EKSItemLoader {

	public static final CreativeTabs tabEKS = new CreativeTabs("eks")
	{
		@Override public Item getTabIconItem() {
			return EKSItemLoader.Saw;
		}
	};

	//public static ToolMaterial strondium = EnumHelper.addToolMaterial("aw", 3, 1233, 26F, 18.0F, 160);//m level,durability,miningspeed,atk dmg,enchantinility
	public static ToolMaterial BHOD = EnumHelper.addToolMaterial("bhod", 3, -1, 50F, 10F, 500);

	public static Saw Saw;


	public static void init() {
	Saw = new Saw(BHOD);
	}

	public static void register()
	{
	GameRegistry.registerItem(Saw);
	}

	public static void RegisterItemRenders()
	{
	registerRender(Saw);
	}

	public static void registerRender(Item item)
	{
	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0 , new ModelResourceLocation(EnderKewlStuffLib.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
}

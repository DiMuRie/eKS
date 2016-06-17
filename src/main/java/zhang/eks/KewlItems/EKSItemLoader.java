package zhang.eks.KewlItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EKSItemLoader {

	public static final CreativeTabs tabEKS = new CreativeTabs("eks")
	{
		@Override public Item getTabIconItem() {
			return EKSItemLoader.Saw;
		}
	};

	//public static ToolMaterial strondium = EnumHelper.addToolMaterial("aw", 3, 1233, 26F, 18.0F, 160);//m level,durability,miningspeed,atk dmg,enchantinility
	public static ToolMaterial BHOD = EnumHelper.addToolMaterial("bhod", 3, -1, 50F, 10F, 500);
	public static ToolMaterial CPSM = EnumHelper.addToolMaterial("cps", 3, 750, 150, 9, 500);
			

	public static Saw Saw;
	public static CPS CPS;


	public static void init() {
	Saw = new Saw();
	CPS = new CPS();
	}
	@SideOnly(Side.CLIENT)
	public static void registerModelsI()
	{
	Saw.initModel();
	CPS.initModel();
	}
	
}

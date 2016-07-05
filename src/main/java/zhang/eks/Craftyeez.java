package zhang.eks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import zhang.eks.KewlBlocks.EKSBlockLoader;
import zhang.eks.KewlItems.EKSItemLoader;

public final class Craftyeez {

	public static void initCrafting() {
		GameRegistry.addRecipe(new ItemStack(EKSItemLoader.Saw, 1), new Object[] {"###", " G ", "###", '#', Blocks.LOG , 'G', Items.IRON_INGOT});
	}
	public static void initOreRecipes(){
		addOreDictRecipe(new ItemStack(EKSBlockLoader.Elevator,8),
				"ISI", "SES", "ISI",
				'E', "blockEmerald",
				'S', "slimeball",
				'I', "blockIron");
		addOreDictRecipe(new ItemStack(EKSBlockLoader.Maglev,8),
				"ISI", "SIS", "ISI",
				'S', "choruseks",
				'I', "blockIron");
		addOreDictRecipe(new ItemStack(EKSBlockLoader.CTMrailing,1),
				"ISI", "SIS", "ISI",
				'S', "blockGlass",
				'I', "stickWood");
		addOreDictRecipe(new ItemStack(EKSItemLoader.CPS,1),
				"ISI", "SIS", "ISI",
				'S', "chorus",
				'I', "stickWood");
		addOreDictRecipe(new ItemStack(EKSBlockLoader.Fakeiron,2),
				"III", "ISI", "III",
				'S', "blockIron",
				'I', "stickWood");
		addOreDictRecipe(new ItemStack(EKSBlockLoader.Schafolding,64),
				"ISI", "SIS", "ISI",
				'S', "ingotIron",
				'I', "stickWood");
		addOreDictRecipe(new ItemStack(EKSBlockLoader.NoGCoil,64),
				"ISI", "SIS", "ISI",
				'S', "blockQuartz",
				'I', "bone");
		addOreDictRecipe(new ItemStack(EKSBlockLoader.Shelf,64),
				"LSL", "SIS", "LSL",
				'S', "blockGlass",
				'I', "chest",
				'L', "stone");
	}
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}
	
}

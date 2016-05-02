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
		GameRegistry.addRecipe(new ItemStack(EKSItemLoader.Saw, 1), new Object[] {"###", " G ", "###", '#', Blocks.field_150364_r , 'G', Items.field_151042_j});
	}
	public static void initOreRecipes(){
		addOreDictRecipe(new ItemStack(EKSBlockLoader.Elevator,8),
				"ISI", "SES", "ISI",
				'E', "blockEmerald",
				'S', "slimeball",
				'I', "blockIron");
		addOreDictRecipe(new ItemStack(EKSBlockLoader.Maglev,8),
				"ISI", "SIS", "ISI",
				'S', "chorus",
				'I', "blockIron");
	}
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.func_77594_a().func_77592_b().add(new ShapedOreRecipe(output, recipe));
	}
	
}

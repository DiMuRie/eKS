package zhang.eks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import zhang.eks.KewlBlocks.EKSBlockLoader;
import zhang.eks.KewlItems.EKSItemLoader;

public class EKSOredict {

	
	
	public static void oreRegistration(){
    	OreDictionary.registerOre("blockIron", new ItemStack(zhang.eks.KewlBlocks.EKSBlockLoader.Elevator));
    	OreDictionary.registerOre("blockEmerald", new ItemStack(zhang.eks.KewlItems.EKSItemLoader.Saw));
    	OreDictionary.registerOre("choruseks", new ItemStack(Blocks.CHORUS_FLOWER));
    	OreDictionary.registerOre("cps", new ItemStack(EKSItemLoader.CPS));
    	OreDictionary.registerOre("blockIron", new ItemStack(EKSBlockLoader.Fakeiron));
    	OreDictionary.registerOre("fakeIron", new ItemStack(EKSBlockLoader.Fakeiron));
    	OreDictionary.registerOre("Schafolding", new ItemStack(EKSBlockLoader.Schafolding));
    	ItemStack acaciaWood = new ItemStack(Blocks.PLANKS);
    	acaciaWood.setItemDamage(4);
		OreDictionary.registerOre("acacia_wood_eks", acaciaWood);
		ItemStack birchWood = new ItemStack(Blocks.PLANKS);
    	birchWood.setItemDamage(2);
    	OreDictionary.registerOre("birch_wood_eks", birchWood);
    	ItemStack oakWood = new ItemStack(Blocks.PLANKS);
    	oakWood.setItemDamage(0);
    	OreDictionary.registerOre("oak_wood_eks", oakWood);
    	ItemStack spruceWood = new ItemStack(Blocks.PLANKS);
    	spruceWood.setItemDamage(1);
    	OreDictionary.registerOre("spruce_wood_eks", spruceWood);
    	ItemStack jungleWood = new ItemStack(Blocks.PLANKS);
    	jungleWood.setItemDamage(3);
    	OreDictionary.registerOre("jungle_wood_eks", jungleWood);
    	ItemStack darkoakWood = new ItemStack(Blocks.PLANKS);
    	darkoakWood.setItemDamage(5);
    	OreDictionary.registerOre("darkoak_wood_eks", darkoakWood);
    }
	
}

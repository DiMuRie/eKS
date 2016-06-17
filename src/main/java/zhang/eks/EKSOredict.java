package zhang.eks;

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
    }
	
}

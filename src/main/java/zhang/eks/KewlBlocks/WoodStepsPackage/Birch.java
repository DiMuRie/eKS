package zhang.eks.KewlBlocks.WoodStepsPackage;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zhang.eks.KewlBlocks.WoodSteps;

public class Birch extends WoodSteps {
	public Birch(){
		this.setUnlocalizedName("woodsteps_birch");
        this.setRegistryName("woodsteps_birch");
		GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}

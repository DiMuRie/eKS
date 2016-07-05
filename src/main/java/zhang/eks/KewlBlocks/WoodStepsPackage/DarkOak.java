package zhang.eks.KewlBlocks.WoodStepsPackage;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zhang.eks.KewlBlocks.WoodSteps;

public class DarkOak extends WoodSteps {
	public DarkOak(){
		this.setUnlocalizedName("woodsteps_dark_oak");
        this.setRegistryName("woodsteps_dark_oak");
		GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}

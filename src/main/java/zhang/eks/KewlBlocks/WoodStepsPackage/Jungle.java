package zhang.eks.KewlBlocks.WoodStepsPackage;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zhang.eks.KewlBlocks.WoodSteps;

public class Jungle extends WoodSteps {
	public Jungle(){
		this.setUnlocalizedName("woodsteps_jungle");
        this.setRegistryName("woodsteps_jungle");
		GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}

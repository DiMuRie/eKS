package zhang.eks.KewlBlocks.WoodStepsPackage;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zhang.eks.KewlBlocks.WoodSteps;

public class Spruce extends WoodSteps {
	public Spruce(){
		this.setUnlocalizedName("woodsteps_spruce");
        this.setRegistryName("woodsteps_spruce");
		GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}

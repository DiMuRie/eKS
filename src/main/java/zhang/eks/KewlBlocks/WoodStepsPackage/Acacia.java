package zhang.eks.KewlBlocks.WoodStepsPackage;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zhang.eks.KewlBlocks.WoodSteps;

public class Acacia extends WoodSteps {
	public Acacia(){
		this.setUnlocalizedName("woodsteps_acacia");
        this.setRegistryName("woodsteps_acacia");
		GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}

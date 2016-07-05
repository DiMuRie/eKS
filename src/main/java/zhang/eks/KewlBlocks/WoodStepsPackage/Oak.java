package zhang.eks.KewlBlocks.WoodStepsPackage;

import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zhang.eks.KewlBlocks.WoodSteps;

public class Oak extends WoodSteps {

	public Oak(){
		this.setUnlocalizedName("woodsteps_oak");
        this.setRegistryName("woodsteps_oak");
		GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
}

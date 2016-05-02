package zhang.eks.KewlBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EKSBlockLoader {

	public static Elevator Elevator;
	public static Maglev Maglev;

    public static void init() {
        Elevator = new Elevator();
        Maglev= new Maglev(false);
    }

	public static void register()
    {
    	GameRegistry.register(Elevator);
    	GameRegistry.register(new ItemBlock(Elevator).setRegistryName(Elevator.getRegistryName()));
    	GameRegistry.register(Maglev);
    	GameRegistry.register(new ItemBlock(Maglev).setRegistryName(Maglev.getRegistryName()));
    }
	@SideOnly(Side.CLIENT)
    public static void registerModelsB()
    {
    	Elevator.initModel();
    	Maglev.initModel();
    }
    
	
}

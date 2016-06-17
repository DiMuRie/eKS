package zhang.eks.KewlBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EKSBlockLoader {

	public static Elevator Elevator;
	public static Maglev Maglev;
	public static CTMrailing CTMrailing;
	public static NoGCoil NoGCoil;
	public static Fakeiron Fakeiron;
	public static Schafolding Schafolding;

    public static void init() {
        Elevator = new Elevator();
        Maglev= new Maglev();
        CTMrailing=new CTMrailing();
        NoGCoil=new NoGCoil();
        Fakeiron=new Fakeiron();
        Schafolding=new Schafolding();
    }
	@SideOnly(Side.CLIENT)
    public static void registerModelsB()
    {
    	Elevator.initModel();
    	Maglev.initModel();
    	CTMrailing.initModel();
    	NoGCoil.initModel();
    	Fakeiron.initModel();
    	Schafolding.initModel();
    }
    
	
}

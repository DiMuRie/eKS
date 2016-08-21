package zhang.eks.KewlBlocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zhang.eks.KewlBlocks.WoodStepsPackage.Acacia;
import zhang.eks.KewlBlocks.WoodStepsPackage.Birch;
import zhang.eks.KewlBlocks.WoodStepsPackage.DarkOak;
import zhang.eks.KewlBlocks.WoodStepsPackage.Jungle;
import zhang.eks.KewlBlocks.WoodStepsPackage.Oak;
import zhang.eks.KewlBlocks.WoodStepsPackage.Spruce;

public class EKSBlockLoader {

	public static Elevator Elevator;
	public static Maglev Maglev;
	public static CTMrailing CTMrailing;
	public static NoGCoil NoGCoil;
	public static Fakeiron Fakeiron;
	public static Schafolding Schafolding;
	public static Shelf Shelf;
	public static WoodSteps WoodSteps;
	public static MiniGigaRail MiniGigaRail;
	public static Acacia Acacia;
	public static Birch Birch;
	public static DarkOak DarkOak;
	public static Jungle Jungle;
	public static Oak Oak;
	public static Spruce Spruce;
	public static Fence Fence;

    public static void init() {
        Elevator = new Elevator();
        Maglev= new Maglev();
        CTMrailing=new CTMrailing();
        NoGCoil=new NoGCoil();
        Fakeiron=new Fakeiron();
        Schafolding=new Schafolding();
        Shelf=new Shelf();
        MiniGigaRail=new MiniGigaRail();
    	Acacia = new Acacia();
    	Birch= new Birch();
    	DarkOak=new DarkOak();
    	Jungle=new Jungle();
    	Oak=new Oak();
    	Spruce=new Spruce();
    	Fence=new Fence();
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
    	Shelf.initModel();
    	MiniGigaRail.initModel();
    	Acacia.initModel();
    	Birch.initModel();
    	DarkOak.initModel();
    	Jungle.initModel();
    	Oak.initModel();
    	Spruce.initModel();
    	Fence.initModel();
    }    	
    
	
}

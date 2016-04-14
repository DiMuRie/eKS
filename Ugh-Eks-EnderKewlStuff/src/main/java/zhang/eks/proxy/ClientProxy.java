package zhang.eks.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zhang.eks.KewlBlocks.EKSBlockLoader;
import zhang.eks.KewlItems.EKSItemLoader;

public class ClientProxy extends CommonProxy {
    
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        //OBJLoader.instance.addDomain(EnderKewlStuffLib.MODID);

        // Typically initialization of models and such goes here:
        EKSBlockLoader.registerRenders();
        EKSItemLoader.RegisterItemRenders();
    }
	@Override
    public void init(FMLInitializationEvent e) {
        super.init(e);

        //// Initialize our input handler so we can listen to keys
        //MinecraftForge.EVENT_BUS.register(new InputHandler());
        //KeyBindings.init();
    }
}

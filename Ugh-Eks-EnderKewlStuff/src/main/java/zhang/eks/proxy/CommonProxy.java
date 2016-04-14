package zhang.eks.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zhang.eks.Craftyeez;
import zhang.eks.EnderKewlStuff;
import zhang.eks.Entity.EKSEntityLoader;
import zhang.eks.KewlBlocks.EKSBlockLoader;
import zhang.eks.KewlItems.EKSItemLoader;
import zhang.eks.tileentity.EKSTELoader;

public class CommonProxy {
        public void preInit(FMLPreInitializationEvent e) {
            // Initialize our packet handler. Make sure the name is
            // 20 characters or less!
            ////PacketHandler.registerMessages("eks");

            // Initialization of blocks and items typically goes here:
            EKSBlockLoader.init();
            EKSItemLoader.init();
            EKSBlockLoader.register();
            EKSItemLoader.register();
            EKSEntityLoader.init();
            EKSTELoader.init();
            
        }

        public void init(FMLInitializationEvent e) {
        	EnderKewlStuff.oreRegistration();
        	Craftyeez.initCrafting();
        	Craftyeez.initOreRecipes();
        }

        public void postInit(FMLPostInitializationEvent e) {

       }
}

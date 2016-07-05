package zhang.eks;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zhang.eks.KewlBlocks.EKSBlockLoader;
import zhang.eks.KewlItems.EKSItemLoader;
import zhang.eks.eventz.DUNGEONLT;
import zhang.eks.eventz.MOLT;
import zhang.eks.eventz.PigLootTables;
import zhang.eks.eventz.SheepLootTables;
import zhang.eks.tileentity.EKSTELoader;
import zhang.eks.worldgen.LumberJackGenerate;

@Mod(modid = EnderKewlStuff.MODID, name = EnderKewlStuff.MODNAME, version = EnderKewlStuff.MODVERSION, dependencies = "required-after:Forge@[11.16.0.1865,)", useMetadata = true)
public class EnderKewlStuff {

    public static final String MODID = "eks";
    public static final String MODNAME = "EnderKewlStuff";
    public static final String MODVERSION = "1.5.0";

    @SidedProxy
    public static CommonProxy proxy;

    @Mod.Instance
    public static EnderKewlStuff instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    public static class CommonProxy {
        public void preInit(FMLPreInitializationEvent e) {
            // Initialize our packet handler. Make sure the name is
            // 20 characters or less!
            //PacketHandler.registerMessages("EnderKewlStuff");

            // Initialization of blocks and items typically goes here:
            EKSBlockLoader.init();
            EKSItemLoader.init();
            //fffffffffffffffffffffffffffffff
            EKSTELoader.init();
            

            //MainCompatHandler.registerWaila();
            //MainCompatHandler.registerTOP();

        }

        public void init(FMLInitializationEvent e) {
        	EKSOredict.oreRegistration();
        	Craftyeez.initCrafting();
            Craftyeez.initOreRecipes();
            MinecraftForge.EVENT_BUS.register(new PigLootTables());
            MinecraftForge.EVENT_BUS.register(new SheepLootTables());
            MinecraftForge.EVENT_BUS.register(new DUNGEONLT());
            MinecraftForge.EVENT_BUS.register(new MOLT());
            GameRegistry.registerWorldGenerator(new LumberJackGenerate(), 0);
        }

        public void postInit(FMLPostInitializationEvent e) {

        }
    }


    public static class ClientProxy extends CommonProxy {
        @Override
        public void preInit(FMLPreInitializationEvent e) {
            super.preInit(e);

            OBJLoader.INSTANCE.addDomain(MODID);
            // Typically initialization of models and such goes here:
            EKSBlockLoader.registerModelsB();
            EKSItemLoader.registerModelsI();
        }

        @Override
        public void init(FMLInitializationEvent e) {
            super.init(e);
            // Initialize our input handler so we can listen to keys
            //MinecraftForge.EVENT_BUS.register(new InputHandler());
            //KeyBindings.init();

            //ModBlocks.initItemModels();
        }
    }

    public static class ServerProxy extends CommonProxy {

    }
}
//Old Class VVV
/*@Mod(modid = EnderKewlStuffLib.MODID, name = EnderKewlStuffLib.MODNAME, version = EnderKewlStuffLib.MODVERSION, dependencies = "required-after:Forge@[12.16.0.1826,)", useMetadata = true)
public class EnderKewlStuff {



    @SidedProxy(serverSide = EnderKewlStuffLib.PROXY_COMMON, clientSide = EnderKewlStuffLib.PROXY_CLIENT)
    public static CommonProxy proxy;

    @Mod.Instance
    public static EnderKewlStuff instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        proxy.preInit(event);
        EKSItemLoader.registerModelsI();
        EKSBlockLoader.registerModelsB();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
    public static void oreRegistration(){
    	OreDictionary.registerOre("blockIron", new ItemStack(zhang.eks.KewlBlocks.EKSBlockLoader.Elevator));
    	OreDictionary.registerOre("blockEmerald", new ItemStack(zhang.eks.KewlItems.EKSItemLoader.Saw));
    	OreDictionary.registerOre("chorus", new ItemStack(Blocks.CHORUS_FLOWER));
    	OreDictionary.registerOre("cps", new ItemStack(EKSItemLoader.CPS));
    }

}*/
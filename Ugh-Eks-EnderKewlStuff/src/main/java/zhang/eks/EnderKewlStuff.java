package zhang.eks;

import org.apache.logging.log4j.Logger;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import zhang.eks.KewlBlocks.EKSBlockLoader;
import zhang.eks.KewlItems.EKSItemLoader;
import zhang.eks.proxy.CommonProxy;

@Mod(modid = EnderKewlStuffLib.MODID, name = EnderKewlStuffLib.MODNAME, version = EnderKewlStuffLib.MODVERSION, dependencies = "required-after:Forge@[12.16.0.1826,)", useMetadata = true)
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
    	OreDictionary.registerOre("chorus", new ItemStack(Blocks.chorus_flower));
    }
    


    
}
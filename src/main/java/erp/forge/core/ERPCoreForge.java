package erp.forge.core;

import erp.forge.core.common.commands.CommandTest;
import erp.forge.core.network.PacketHandler;
import erp.forge.core.proxy.ClientProxy;
import erp.forge.core.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = ERPCoreForge.MODID, name = "erp-core-forge",version = ERPCoreForge.VERSION)
public class ERPCoreForge {

    public static final String MODID = "erp-core-forge";
	public static final String VERSION = "0.1";
    public static Logger logger;
    public static File MOD_DIR;

    private SimpleNetworkWrapper erpCoreForgePacketChannel;

    @Mod.Instance
    public static ERPCoreForge instance;

    //here you must specify the path to the folder where the proxy files are located
   	@SidedProxy(clientSide = "erp.forge.core.proxy.ClientProxy", serverSide = "erp.forge.core.proxy.ServerProxy")
   	public static CommonProxy proxy;

    @SideOnly(Side.CLIENT)
    public static ClientProxy getClientProxy() {
        return (ClientProxy) proxy;
    }


    public SimpleNetworkWrapper getPacketChannel() {
        return this.erpCoreForgePacketChannel;
    }


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);

        MOD_DIR = new File(event.getModConfigurationDirectory().getParent(), "config");

        new ModConfig(new File(MOD_DIR, "erp-core_config.json"));


        this.erpCoreForgePacketChannel = NetworkRegistry.INSTANCE.newSimpleChannel("erpcore");
        PacketHandler.init();
        MinecraftForge.EVENT_BUS.register(this);

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent e){
        e.registerServerCommand(new CommandTest());
    }

}

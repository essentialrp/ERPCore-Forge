package fr.nathanael2611.simpledatabasemanager;

import fr.nathanael2611.simpledatabasemanager.command.CommandCustomPlayerdData;
import fr.nathanael2611.simpledatabasemanager.command.CommandDatabase;
import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.nathanael2611.simpledatabasemanager.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = "customplayerdata")
public class CustomPlayerData {

    public static final String MOD_ID = "simpledatabasemanager";
    public static final String MOD_NAME = "SimpleDatabaseManager";

    @SidedProxy(
            serverSide = "fr.nathanael2611.simpledatabasemanager.proxy.ServerProxy",
            clientSide = "fr.nathanael2611.simpledatabasemanager.proxy.ClientProxy"
    )
    private static CommonProxy proxy;

    @Mod.Instance
    private static CustomPlayerData instance;

    @Mod.EventHandler
    public void preInitialization(FMLPreInitializationEvent e){
        getProxy().preInitialization(e.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void initialization(FMLInitializationEvent e){
        getProxy().initialization();
    }

    public static CommonProxy getProxy() {
        return proxy;
    }

    public static CustomPlayerData getInstance() {
        return instance;
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent e){
        Databases.onServerStarting(e);
        e.registerServerCommand(new CommandCustomPlayerdData());
        e.registerServerCommand(new CommandDatabase());
    }

}

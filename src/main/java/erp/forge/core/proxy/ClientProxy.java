package erp.forge.core.proxy;


import erp.forge.core.client.money.MoneyHUDEntry;
import erp.forge.core.client.money.MoneyHUDManager;
import erp.forge.core.client.money.MoneyHUDRender;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    private MoneyHUDManager moneyHUDManager;

    public MoneyHUDManager getMoneyHUDManager() {
        return this.moneyHUDManager;
    }


    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        //MONEY HUD
        this.moneyHUDManager = new MoneyHUDManager();
        MinecraftForge.EVENT_BUS.register(new MoneyHUDRender(this.moneyHUDManager));
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }



}

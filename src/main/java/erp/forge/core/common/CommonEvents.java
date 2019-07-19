package erp.forge.core.common;

import erp.forge.core.ERPCoreForge;
import erp.forge.core.ModConfig;
import erp.forge.core.player.ERPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CommonEvents {

    @SubscribeEvent
    @SideOnly(Side.SERVER)
    public void onPlayerLogIn(final PlayerEvent.PlayerLoggedInEvent event) {
        final EntityPlayerMP player = (EntityPlayerMP)event.player;
        ERPCoreForge.logger.info("Loading account");
        ERPlayer erPlayer = new ERPlayer(player.getUniqueID());
        erPlayer.loadAccount();
    }


    @SubscribeEvent
    @SideOnly(Side.SERVER)
    public void onPlayerDie(final LivingDeathEvent event) {
        if (event.getEntity() instanceof EntityPlayerMP) {
            final EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
            ERPlayer erPlayer = new ERPlayer(player.getUniqueID());
            if(ModConfig.INSTANCE.resetMoneyOnDeath) {
                erPlayer.setMoney(0);
            }
        }
    }


}

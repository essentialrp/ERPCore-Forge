package erp.forge.core.common;


import erp.forge.core.ERPCoreForge;
import erp.forge.core.player.ERPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CommonEvents {

    @SubscribeEvent
    @SideOnly(Side.SERVER)
    public void onPlayerLogIn(final PlayerEvent.PlayerLoggedInEvent event) {
        final EntityPlayerMP player = (EntityPlayerMP)event.player;
        ERPCoreForge.logger.info("Creating account");
        ERPlayer.loadAccount(player);
    }

}

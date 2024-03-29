package erp.forge.core.common.commands;

import erp.forge.core.player.ERPlayer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandTest extends CommandBase {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getUsage(final ICommandSender sender) {
        return "test";
    }


    @Override
    public boolean checkPermission(final MinecraftServer server, final ICommandSender sender) {
        return true;
    }

    // TODO add a cooldown to the command

    @Override
    public void execute(final MinecraftServer server, final ICommandSender sender, final String[] args) {
        if (sender instanceof EntityPlayerMP) {
            final EntityPlayerMP player = (EntityPlayerMP) sender;
            ERPlayer erPlayer = new ERPlayer(player.getUniqueID());
            boolean minus = Boolean.valueOf(args[0]);
            int money = Integer.parseInt(args[1]);
            if (minus) {
                erPlayer.removeMoney(money);
            } else {
                erPlayer.addMoney(money);
            }

        }
    }


}

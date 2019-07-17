package erp.forge.core.network.message;

import erp.forge.core.ERPCoreForge;
import erp.forge.core.client.money.MoneyHUDEntry;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePlayerMoneyHUD implements IMessage {

    String text;

    public MessagePlayerMoneyHUD() {
    }

    public MessagePlayerMoneyHUD(String text) {
        this.text = text;
    }

    public void fromBytes(final ByteBuf buf) {
        this.text = ByteBufUtils.readUTF8String(buf);
    }

    public void toBytes(final ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.text);
    }


    public static class Handler implements IMessageHandler<MessagePlayerMoneyHUD, IMessage> {
        public IMessage onMessage(final MessagePlayerMoneyHUD message, final MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                ERPCoreForge.getClientProxy().getMoneyHUDManager().add(new MoneyHUDEntry(message.text));
                return;
            });
            return null;
        }
    }
}
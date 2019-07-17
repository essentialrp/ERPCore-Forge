package erp.forge.core.client.money;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MoneyHUDRender {
    private MoneyHUDManager manager;
    private Minecraft mc;

    public MoneyHUDRender(final MoneyHUDManager manager) {
        this.mc = Minecraft.getMinecraft();
        this.manager = manager;
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            this.manager.getEntries().stream().filter(entry -> entry.incrementTimeLived() > 200).forEach(entry -> this.manager.remove(entry));
        }

    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRender(final RenderGameOverlayEvent.Post event) {

        this.mc.fontRenderer.drawString("500$", event.getResolution().getScaledWidth()-this.mc.fontRenderer.getStringWidth("500$")- 15, 15, Integer.MAX_VALUE);


        if(this.manager.getEntries().isEmpty()){
            return;
        }
        GlStateManager.pushMatrix();
        final float scale = 1f;
        GlStateManager.scale(scale, scale, scale);
        final List<MoneyHUDEntry> entries = this.manager.getEntries().stream().sorted(Comparator.comparingInt(MoneyHUDEntry::getTimeLived).reversed()).collect(Collectors.toList());
        final int chatHeight = calculateChatboxHeight(this.mc.gameSettings.chatHeightFocused);
        int bottom = 90;
        final int left = event.getResolution().getScaledWidth()-45;
        final int messagesHeight = entries.size() * this.mc.fontRenderer.FONT_HEIGHT + entries.size();
        bottom -= messagesHeight;
        int messagesWidth = 0;
        for (final MoneyHUDEntry entry : entries) {
            messagesWidth = (int)Math.max(messagesWidth, this.mc.fontRenderer.getStringWidth(entry.getText()) * scale);
        }

        int msgY = bottom;
        for (final MoneyHUDEntry entry2 : entries) {
            this.mc.fontRenderer.drawString(entry2.getText(), (int)(left / scale), (int)(msgY / scale), Integer.MAX_VALUE);
            msgY += this.mc.fontRenderer.FONT_HEIGHT + 1;
        }
        GlStateManager.popMatrix();
    }

    public static int calculateChatboxHeight(final float scale) {
        return MathHelper.floor(scale * 160.0f + 20.0f);
    }
}

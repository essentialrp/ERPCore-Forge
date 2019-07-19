package erp.forge.core.client.money;

import erp.forge.core.client.render.RenderHelper;
import erp.forge.core.player.ERPlayerClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
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

    public static final ResourceLocation bank = new ResourceLocation("erp-core-forge", "textures/icons/bank.png");
    public static final ResourceLocation money = new ResourceLocation("erp-core-forge", "textures/icons/money.png");


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

        ERPlayerClient playerClient = new ERPlayerClient(mc.player.getUniqueID());


        GlStateManager.pushMatrix();


        this.mc.fontRenderer.drawString(playerClient.getBank()+"$", event.getResolution().getScaledWidth()-this.mc.fontRenderer.getStringWidth(playerClient.getBank()+"$")- 15, 10, Integer.MAX_VALUE);
        this.mc.fontRenderer.drawString(playerClient.getMoney()+"$", event.getResolution().getScaledWidth()-this.mc.fontRenderer.getStringWidth(playerClient.getMoney()+"$")- 15, 25, Integer.MAX_VALUE);

        GlStateManager.disableRescaleNormal();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        RenderHelper.renderImage(event.getResolution().getScaledWidth()-12, 9, bank, 9,9);
        RenderHelper.renderImage(event.getResolution().getScaledWidth()-12, 24, money, 9,9);
        GlStateManager.disableBlend();


        GlStateManager.popMatrix();


        if(this.manager.getEntries().isEmpty()){
            return;
        }
        GlStateManager.pushMatrix();
        final float scale = 1f;
        GlStateManager.scale(scale, scale, scale);
        final List<MoneyHUDEntry> entries = this.manager.getEntries().stream().sorted(Comparator.comparingInt(MoneyHUDEntry::getTimeLived).reversed()).collect(Collectors.toList());
        int bottom = 100;
        final int messagesHeight = entries.size() * this.mc.fontRenderer.FONT_HEIGHT + entries.size();
        bottom -= messagesHeight;
        int messagesWidth = 0;
        for (final MoneyHUDEntry entry : entries) {
            messagesWidth = (int)Math.max(messagesWidth, this.mc.fontRenderer.getStringWidth(entry.getText()) * scale);
        }

        int msgY = bottom;
        for (final MoneyHUDEntry entry2 : entries) {
            final int left = event.getResolution().getScaledWidth()-this.mc.fontRenderer.getStringWidth(entry2.getText()+"$")- 10;
            this.mc.fontRenderer.drawString(entry2.getText(), (int)(left / scale), (int)(msgY / scale), Integer.MAX_VALUE);
            msgY += this.mc.fontRenderer.FONT_HEIGHT + 1;
        }
        GlStateManager.popMatrix();
    }
}

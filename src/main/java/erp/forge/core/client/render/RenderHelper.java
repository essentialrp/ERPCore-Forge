package erp.forge.core.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class RenderHelper {

  public static float swing = 0.0F;
  public static int gameColor;
  public static boolean dM;
  //public static final FakePlayerRendering PLAYER_RENDERER;
  public static float minegrounds_1e7495cf0 = 0.0f;

  public static void renderText(String text, int posX, int posY, int color)
  {
    Minecraft mc = Minecraft.getMinecraft();
    mc.fontRenderer.drawString(text, posX, posY, color);
  }

  public static void renderTextWithShadow(String text, int posX, int posY, int color)
  {
    Minecraft mc = Minecraft.getMinecraft();
    mc.fontRenderer.drawStringWithShadow(text, posX, posY, color);
  }

  public static void renderCenteredText(String text, int posX, int posY, int color)
  {
    Minecraft mc = Minecraft.getMinecraft();
    renderText(text, posX - mc.fontRenderer.getStringWidth(text) / 2, posY, color);
  }

  public static void renderCenteredTextWithShadow(String text, int posX, int posY, int color)
  {
    Minecraft mc = Minecraft.getMinecraft();
    renderTextWithShadow(text, posX - mc.fontRenderer.getStringWidth(text) / 2, posY, color);
  }

  public static void renderTextScaled(String text, int posX, int posY, int color, double givenScale)
  {
    GL11.glPushMatrix();
    GL11.glTranslated(posX, posY, 0.0D);
    GL11.glScaled(givenScale, givenScale, givenScale);
    renderText(text, 0, 0, color);
    GL11.glPopMatrix();
  }

  public static void renderCenteredTextScaled(String text, int posX, int posY, int color, double givenScale)
  {
    GL11.glPushMatrix();
    GL11.glTranslated(posX, posY, 0.0D);
    GL11.glScaled(givenScale, givenScale, givenScale);
    renderCenteredText(text, 0, 0, color);
    GL11.glPopMatrix();
  }

  public static void renderCenteredTextScaledWithShadow(String text, int posX, int posY, int color, double givenScale)
  {
    GL11.glPushMatrix();
    GL11.glTranslated(posX, posY, 0.0D);
    GL11.glScaled(givenScale, givenScale, givenScale);
    renderCenteredTextWithShadow(text, 0, 0, color);
    GL11.glPopMatrix();
  }

  public static void renderTextWithOutline(String text, int x, int y, int color, int outlineColor)
  {
    renderText(text, x - 1, y + 1, outlineColor);
    renderText(text, x, y + 1, outlineColor);
    renderText(text, x + 1, y + 1, outlineColor);
    renderText(text, x - 1, y, outlineColor);
    renderText(text, x + 1, y, outlineColor);
    renderText(text, x - 1, y - 1, outlineColor);
    renderText(text, x, y - 1, outlineColor);
    renderText(text, x + 1, y - 1, outlineColor);

    renderText(text, x, y, color);
  }

  public static void renderTextScaledWithOutline(String text, int x, int y, int color, int outlineColor, double givenScale)
  {
    renderTextScaled(text, x - 1, y + 1, outlineColor, givenScale);
    renderTextScaled(text, x, y + 1, outlineColor, givenScale);
    renderTextScaled(text, x + 1, y + 1, outlineColor, givenScale);
    renderTextScaled(text, x - 1, y, outlineColor, givenScale);
    renderTextScaled(text, x + 1, y, outlineColor, givenScale);
    renderTextScaled(text, x - 1, y - 1, outlineColor, givenScale);
    renderTextScaled(text, x, y - 1, outlineColor, givenScale);
    renderTextScaled(text, x + 1, y - 1, outlineColor, givenScale);

    renderTextScaled(text, x, y, color, givenScale);
  }

  public static void renderCenteredTextWithOutline(String text, int x, int y, int color, int outlineColor)
  {
    Minecraft mc = Minecraft.getMinecraft();
    FontRenderer fr = mc.fontRenderer;

    renderText(text, x - 1 - fr.getStringWidth(text) / 2, y + 1, outlineColor);
    renderText(text, x - fr.getStringWidth(text) / 2, y + 1, outlineColor);
    renderText(text, x + 1 - fr.getStringWidth(text) / 2, y + 1, outlineColor);
    renderText(text, x - 1 - fr.getStringWidth(text) / 2, y, outlineColor);
    renderText(text, x + 1 - fr.getStringWidth(text) / 2, y, outlineColor);
    renderText(text, x - 1 - fr.getStringWidth(text) / 2, y - 1, outlineColor);
    renderText(text, x - fr.getStringWidth(text) / 2, y - 1, outlineColor);
    renderText(text, x + 1 - fr.getStringWidth(text) / 2, y - 1, outlineColor);

    renderText(text, x - fr.getStringWidth(text) / 2, y, color);
  }

  public static void renderRect(int givenPosX, int givenPosY, int givenWidth, int givenHeight, int givenColor)
  {
    GL11.glPushMatrix();

    givenWidth = givenPosX + givenWidth;
    givenHeight = givenPosY + givenHeight;
    if (givenPosX < givenWidth)
    {
      int i = givenPosX;
      givenPosX = givenWidth;
      givenWidth = i;
    }
    if (givenPosY < givenHeight)
    {
      int j = givenPosY;
      givenPosY = givenHeight;
      givenHeight = j;
    }
    float f = (givenColor >> 16 & 0xFF) / 255.0F;
    float f1 = (givenColor >> 8 & 0xFF) / 255.0F;
    float f2 = (givenColor & 0xFF) / 255.0F;
    float f3 = (givenColor >> 24 & 0xFF) / 255.0F;

    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    GlStateManager.enableBlend();
    GlStateManager.disableTexture2D();
    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    GlStateManager.color(f, f1, f2, f3);
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
    bufferbuilder.pos(givenPosX, givenHeight, 0.0D).endVertex();
    bufferbuilder.pos(givenWidth, givenHeight, 0.0D).endVertex();
    bufferbuilder.pos(givenWidth, givenPosY, 0.0D).endVertex();
    bufferbuilder.pos(givenPosX, givenPosY, 0.0D).endVertex();
    tessellator.draw();
    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();

    GL11.glPopMatrix();
  }

  public static void renderRectWithOutline(int givenPosX, int givenPosY, int givenWidth, int givenHeight, int givenColor, int givenOutlineColor, int outlineThickness)
  {
    GL11.glPushMatrix();
    renderRect(givenPosX - outlineThickness, givenPosY - outlineThickness, givenWidth + outlineThickness * 2, givenHeight + outlineThickness * 2, givenOutlineColor);
    renderRect(givenPosX, givenPosY, givenWidth, givenHeight, givenColor);
    GL11.glPopMatrix();
  }

  public static void renderRectWithGradient(int givenPosX, int givenPosY, int givenWidth, int givenHeight, int startColor, int endColor, double givenZLevel)
  {
    GL11.glPushMatrix();

    givenWidth = givenPosX + givenWidth;
    givenHeight = givenPosY + givenHeight;

    float f = (startColor >> 24 & 0xFF) / 255.0F;
    float f1 = (startColor >> 16 & 0xFF) / 255.0F;
    float f2 = (startColor >> 8 & 0xFF) / 255.0F;
    float f3 = (startColor & 0xFF) / 255.0F;
    float f4 = (endColor >> 24 & 0xFF) / 255.0F;
    float f5 = (endColor >> 16 & 0xFF) / 255.0F;
    float f6 = (endColor >> 8 & 0xFF) / 255.0F;
    float f7 = (endColor & 0xFF) / 255.0F;
    GlStateManager.disableTexture2D();
    GlStateManager.enableBlend();
    GlStateManager.disableAlpha();
    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    GlStateManager.shadeModel(7425);
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(givenWidth, givenPosY, givenZLevel).color(f1, f2, f3, f).endVertex();
    bufferbuilder.pos(givenPosX, givenPosY, givenZLevel).color(f1, f2, f3, f).endVertex();
    bufferbuilder.pos(givenPosX, givenHeight, givenZLevel).color(f5, f6, f7, f4).endVertex();
    bufferbuilder.pos(givenWidth, givenHeight, givenZLevel).color(f5, f6, f7, f4).endVertex();
    tessellator.draw();
    GlStateManager.shadeModel(7424);
    GlStateManager.disableBlend();
    GlStateManager.enableAlpha();
    GlStateManager.enableTexture2D();

    GL11.glPopMatrix();
  }

  public static void renderPositionedImageNoDepth(ResourceLocation par1, double par2, double par3, double par4, float par5, float width, float height)
  {
    GL11.glPushMatrix();

    GL11.glDepthMask(false);
    GL11.glDisable(2929);
    renderPositionedImage(par1, par2, par3, par4, par5, width, height);
    GL11.glDepthMask(true);
    GL11.glEnable(2929);

    GL11.glPopMatrix();
  }

  public static void renderPositionedImage(ResourceLocation par1, double par2, double par3, double par4, float par5, float width, float height)
  {
    Minecraft mc = Minecraft.getMinecraft();
    EntityPlayer player = mc.player;

    GL11.glPushMatrix();

    GL11.glTranslated(par2, par3, par4);
    GL11.glTranslated(-mc.getRenderManager().viewerPosX, -mc.getRenderManager().viewerPosY, -mc.getRenderManager().viewerPosZ);
    GL11.glNormal3f(0.0F, 1.0F, 0.0F);

    GL11.glRotatef(-player.rotationYaw, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(player.rotationPitch, 1.0F, 0.0F, 0.0F);

    GL11.glScalef(-0.03F, -0.03F, 0.03F);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);

    renderImage(-width / 2.0F, -height / 2.0F, par1, width, height);

    GL11.glDisable(3042);
    GL11.glPopMatrix();
  }

  public static void renderPositionedTextScaled(String givenText, double par2, double par3, double par4, float par5, int givenColor)
  {
    Minecraft mc = Minecraft.getMinecraft();
    EntityPlayer player = mc.player;

    GL11.glPushMatrix();

    GL11.glTranslated(par2, par3, par4);
    GL11.glTranslated(-mc.getRenderManager().viewerPosX, -mc.getRenderManager().viewerPosY, -mc.getRenderManager().viewerPosZ);
    GL11.glNormal3f(0.0F, 1.0F, 0.0F);

    GL11.glRotatef(-player.rotationYaw, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(player.rotationPitch, 1.0F, 0.0F, 0.0F);

    GL11.glScalef(-0.03F, -0.03F, 0.03F);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);

    renderCenteredTextScaled(givenText, 0, 0, givenColor, par5);

    GL11.glDisable(3042);
    GL11.glPopMatrix();
  }

  public static void renderImageCenteredScaled(double givenX, double givenY, ResourceLocation givenTexture, double givenWidth, double givenHeight, double givenScale)
  {
    GL11.glPushMatrix();
    GL11.glTranslated(givenX, givenY, 0.0D);
    GL11.glScaled(givenScale, givenScale, givenScale);
    renderImageCentered(givenX - givenWidth / 2.0D, givenY, givenTexture, givenWidth, givenHeight);
    GL11.glPopMatrix();
  }

  public static void renderImageCentered(double givenX, double givenY, ResourceLocation givenTexture, double givenWidth, double givenHeight)
  {
    GL11.glPushMatrix();
    renderImage(givenX - givenWidth / 2.0D, givenY, givenTexture, givenWidth, givenHeight);
    GL11.glPopMatrix();
  }

  public static void renderImage(double x, double y, ResourceLocation image, double width, double height) {

    GL11.glColor3f(1.0F, 1.0F, 1.0F);

    Minecraft.getMinecraft().renderEngine.bindTexture(image);

    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();

    GL11.glEnable(3042);
    GL11.glEnable(2832);
    GL11.glHint(3153, 4353);

    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);

    bufferbuilder.pos(x, y + height, 0.0D).tex(0.0D, 1.0D).endVertex();
    bufferbuilder.pos(x + width, y + height, 0.0D).tex(1.0D, 1.0D).endVertex();
    bufferbuilder.pos(x + width, y, 0.0D).tex(1.0D, 0.0D).endVertex();
    bufferbuilder.pos(x, y, 0.0D).tex(0.0D, 0.0D).endVertex();

    tessellator.draw();

    GL11.glDisable(3042);
    GL11.glDisable(2832);
  }

  public static void renderImageSpecial(double x, double y, double i, double j, double k, double l, float alpha, ResourceLocation image) {

    GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);

    Minecraft.getMinecraft().renderEngine.bindTexture(image);

    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();

    GL11.glEnable(3042);
    GL11.glEnable(2832);
    GL11.glHint(3153, 4353);

    double w = i;
    double h = j;
    double we = k;
    double he = l;

    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);

    bufferbuilder.pos(x + w, y + he, -90.0D).tex(0.0D, 1.0D).endVertex();
    bufferbuilder.pos(x + we, y + he, -90.0D).tex(1.0D, 1.0D).endVertex();
    bufferbuilder.pos(x + we, y + h, -90.0D).tex(1.0D, 0.0D).endVertex();
    bufferbuilder.pos(x + w, y + h, -90.0D).tex(0.0D, 0.0D).endVertex();

    tessellator.draw();

    GL11.glDisable(3042);
    GL11.glDisable(2832);
  }

  public static void renderImageAlpha(double x, double y, ResourceLocation image, double width, double height, double alpha) {

    GL11.glColor4d(255.0, 255.0, 255.0, alpha);

    Minecraft.getMinecraft().renderEngine.bindTexture(image);

    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();

    GL11.glEnable(3042);
    GL11.glEnable(2832);
    GL11.glHint(3153, 4353);

    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);

    bufferbuilder.pos(x, y + height, 0.0D).tex(0.0D, 1.0D).endVertex();
    bufferbuilder.pos(x + width, y + height, 0.0D).tex(1.0D, 1.0D).endVertex();
    bufferbuilder.pos(x + width, y, 0.0D).tex(1.0D, 0.0D).endVertex();
    bufferbuilder.pos(x, y, 0.0D).tex(0.0D, 0.0D).endVertex();

    tessellator.draw();

    GL11.glDisable(3042);
    GL11.glDisable(2832);
  }

  public static void renderTranspImage(double x, double y, ResourceLocation image, double width, double height) {

    GL11.glColor3f(1.0F, 1.0F, 1.0F);

    Minecraft.getMinecraft().renderEngine.bindTexture(image);

    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();

    GL11.glEnable(3042);
    GL11.glEnable(2832);
    GL11.glHint(3153, 4353);

    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);

    bufferbuilder.pos(x, y + height, 0.0D).tex(0.0D, 1.0D).endVertex();
    bufferbuilder.pos(x + width, y + height, 0.0D).tex(1.0D, 1.0D).endVertex();
    bufferbuilder.pos(x + width, y, 0.0D).tex(1.0D, 0.0D).endVertex();
    bufferbuilder.pos(x, y, 0.0D).tex(0.0D, 0.0D).endVertex();

    tessellator.draw();

    GL11.glDisable(3042);
    GL11.glDisable(2832);
  }

  public static void renderCenteredTextScaledWithOutlineFade(final String text, final int posX, final int posY, final double par4, final int givenColor, final double givenFade) {
    final Minecraft mc = Minecraft.getMinecraft();
    final double width = mc.fontRenderer.getStringWidth(text) / 2 * par4;
    GL11.glPushMatrix();
    GL11.glTranslated(posX - width, (double)posY, 0.0);
    GL11.glScaled(par4, par4, par4);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, -1, -1, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, 1, -1, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, -1, 1, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, 1, 1, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, 0, -1, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, -1, 0, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, 1, 0, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, 0, 1, 0);
    mc.fontRenderer.drawString(text, 0, 0, givenColor);
    GL11.glPopMatrix();
  }

  public static void renderCenteredTextScaledWithOutline(final String text, final int posX, final int posY, final double par4, final int givenColor) {
    final Minecraft mc = Minecraft.getMinecraft();
    final double width = mc.fontRenderer.getStringWidth(text) / 2 * par4;
    GL11.glPushMatrix();
    GL11.glTranslated(posX - width, (double)posY, 0.0);
    GL11.glScaled(par4, par4, par4);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, -1, -1, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, 1, -1, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, -1, 1, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, 1, 1, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, 0, -1, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, -1, 0, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, 1, 0, 0);
    mc.fontRenderer.drawString(TextFormatting.BLACK + text, 0, 1, 0);
    mc.fontRenderer.drawString(text, 0, 0, givenColor);
    GL11.glPopMatrix();
  }

  public static void renderPositionedTextInView(final String par1, final double par2, final double par3, final double par4, final float par5) {
    renderPositionedTextInView(par1, par2, par3, par4, par5, 1.0f);
  }

  public static void renderPositionedTextInView(final String par1, final double par2, final double par3, final double par4, final float par5, final float alpha) {
    final Minecraft mc = Minecraft.getMinecraft();
    final EntityPlayer player = (EntityPlayer)mc.player;
    GL11.glPushMatrix();
    GL11.glTranslated(par2, par3, par4);

    GL11.glTranslated(-mc.getRenderManager().viewerPosX, -mc.getRenderManager().viewerPosY, -mc.getRenderManager().viewerPosZ);
    GL11.glNormal3f(0.0f, 1.0f, 0.0f);
    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    final int width = mc.fontRenderer.getStringWidth(par1);
    GL11.glRotatef(-player.rotationYaw, 0.0f, 1.0f, 0.0f);
    GL11.glRotatef(player.rotationPitch, 1.0f, 0.0f, 0.0f);
    GL11.glScalef(-0.01f, -0.01f, 0.01f);
    GL11.glDisable(2896);
    if (RenderHelper.dM) {
      GL11.glDepthMask(false);
    }
    GL11.glDisable(2929);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    final Color color = new Color(1.0f, 1.0f, 1.0f, alpha);
    final FontRenderer fr = mc.fontRenderer;
    fr.drawString(par1, -(width / 2), 0, color.getRGB());
    GL11.glDepthMask(true);
    GL11.glEnable(2929);
    GL11.glEnable(2896);
    GL11.glDisable(3042);
    GL11.glPopMatrix();
  }

  public static void renderPlayerHead(final String playerName, final int xPos, final int yPos, final String givenPlayerUUID) {
    final ResourceLocation resourceLocation = new ResourceLocation("textures/hologram/steve.png");
    if (playerName.length() > 0) {
      getDownloadImageSkin(resourceLocation, givenPlayerUUID);
    }
    GL11.glPushMatrix();
    renderRect(xPos - 1, yPos - 1, 20, 21, 1140850688);
    Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
    GL11.glTranslated((double)xPos, (double)yPos, 30.0);
    GL11.glScaled(0.75, 0.39, 0.0);
    final double scale = 0.75;
    GL11.glScaled(scale, scale, scale);
    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
    Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(0, 0, 32, 64, 32, 64);
    Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(0, 0, 160, 64, 32, 64);
    GL11.glPopMatrix();
  }

  public static void renderLine(final double posX, final double posY, final double posZ, final double posX2, final double posY2, final double posZ2, final int givenColor, final float width) {
    final Minecraft mc = Minecraft.getMinecraft();
    final float red = (givenColor >> 16 & 0xFF) / 255.0f;
    final float blue = (givenColor >> 8 & 0xFF) / 255.0f;
    final float green = (givenColor & 0xFF) / 255.0f;
    final float alpha = (givenColor >> 24 & 0xFF) / 255.0f;
    final double d0 = mc.player.prevPosX + (mc.player.posX - mc.player.prevPosX);
    final double d2 = mc.player.prevPosY + (mc.player.posY - mc.player.prevPosY);
    final double d3 = mc.player.prevPosZ + (mc.player.posZ - mc.player.prevPosZ);
    GL11.glPushMatrix();
    GL11.glDisable(2896);
    GL11.glDisable(3553);
    GL11.glDisable(2929);
    GL11.glLineWidth(width);
    GL11.glTranslated(-d0, -d2, -d3);
    GL11.glColor4f(red, green, blue, alpha);
    GL11.glEnable(2848);
    GL11.glHint(3154, 4354);
    GL11.glBegin(3);
    GL11.glVertex3d(posX, posY, posZ);
    GL11.glVertex3d(posX2, posY2, posZ2);
    GL11.glEnd();
    GL11.glEnable(2896);
    GL11.glEnable(3553);
    GL11.glEnable(2929);
    GL11.glPopMatrix();
  }

  private static final String pad(String s)
  {
    return s.length() == 1 ? "0" + s : s;
  }

  public static int toHex(org.lwjgl.util.Color color)
  {
    String alpha = pad(Integer.toHexString(color.getAlpha()));
    String red = pad(Integer.toHexString(color.getRed()));
    String green = pad(Integer.toHexString(color.getGreen()));
    String blue = pad(Integer.toHexString(color.getBlue()));
    String hex = "0x" + alpha + red + green + blue;
    return Integer.parseInt(hex, 16);
  }

  public static void renderPlayer(final int x, final int y, final float givenScale, final float givenRotation) {
    GL11.glPushMatrix();
    //PLAYER_RENDERER.renderPlayerModel(x, y, givenScale, givenRotation);
    GL11.glPopMatrix();
  }

  public static ThreadDownloadImageData getDownloadImageSkin(final ResourceLocation resourceLocationIn, final String givenUUID) {
    final TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
    Object object = texturemanager.getTexture(resourceLocationIn);
    if (object == null) {
      object = new ThreadDownloadImageData((File)null, String.format("https://crafatar.com/skins/%s.png", StringUtils.stripControlCodes(givenUUID)), new ResourceLocation("textures/hologram/steve.png"), (IImageBuffer)new ImageBufferDownload());
      texturemanager.loadTexture(resourceLocationIn, (ITextureObject)object);
    }
    return (ThreadDownloadImageData)object;
  }

  public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
  {
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder buffer = tessellator.getBuffer();
    buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
    buffer.pos((double)(x + 0), (double)(y + height), 0.0D).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + height) * f1)).endVertex();;
    buffer.pos((double)(x + width), (double)(y + height), 0.0D).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + height) * f1)).endVertex();
    buffer.pos((double)(x + width), (double)(y + 0), 0.0D).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + 0) * f1)).endVertex();
    buffer.pos((double)(x + 0), (double)(y + 0), 0.0D).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + 0) * f1)).endVertex();
    tessellator.draw();
  }

  public static void renderBackgroundImage(final double offsx, final double offsy, final int width, final int height, final float brightness, final ResourceLocation background) {
    GL11.glPushMatrix();
    GL11.glDisable(2929);
    GL11.glDepthMask(false);
    GL11.glBlendFunc(770, 771);
    GL11.glColor4f(brightness, brightness, brightness, 1.0f);
    GL11.glDisable(3008);
    GL11.glEnable(3042);

    Minecraft.getMinecraft().renderEngine.bindTexture(background);

    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();

    GL11.glEnable(3042);
    GL11.glEnable(2832);
    GL11.glHint(3153, 4353);

    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);

    bufferbuilder.pos(offsx, offsy + height, 0.0D).tex(0.0D, 1.0D).endVertex();
    bufferbuilder.pos(offsx + width, offsy + height, 0.0D).tex(1.0D, 1.0D).endVertex();
    bufferbuilder.pos(offsx + width, offsy, 0.0D).tex(1.0D, 0.0D).endVertex();
    bufferbuilder.pos(offsx, offsy, 0.0D).tex(0.0D, 0.0D).endVertex();

    tessellator.draw();

    GL11.glDepthMask(true);
    GL11.glEnable(2929);
    GL11.glEnable(3008);
    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    GL11.glPopMatrix();
  }

  public static long getTime() {
    return Sys.getTime() * 1000L / Sys.getTimerResolution();
  }

  public static void renderMainMenuPlayer(final int width, final int height, final float offsetx, final float offsety, final int par1, final int par2) {

  }


  public static void renderBackground(final int width, final int height, final int par1, final int par2, final float par3) {
    try {
      final Calendar calendar = Calendar.getInstance();
      int minutes = calendar.get(13);
      calendar.get(11);
      if (minutes > 30) {
        minutes = 30 - Math.abs(minutes - 30);
      }
      final float timeofday = 1.0f;
      renderBackgroundImage(0.0, 0.0, width, height, timeofday, new ResourceLocation("minegrounds", "textures/gui/background_01_un.png"));
      GL11.glPushMatrix();
      final float var0 = getTime() / 512.0f;
      final float var2 = 56.695778f + (float)(Math.cos(var0) / 180.0) * 8.0f;
      final float var3 = 56.695778f + (float)(Math.sin(var0) / 180.0) * 8.0f;
      final double xx = (par1 - width / 2) / (width / 28 + width / 8) + 10;
      double yy = -((par2 - height / 3) / (height / 28) - height / 24) + 10;
      GL11.glScalef(1.1f, 1.0f, 1.0f);
      GL11.glTranslatef(-5.0f - (var2 * 8.0f - 456.0f) / 3.0f, -5.0f - (var3 * 8.0f - 456.0f) / 5.0f, 0.0f);
      if (minegrounds_1e7495cf0 < width) {
        minegrounds_1e7495cf0 += par3 * 0.4f;
      }
      else {
        minegrounds_1e7495cf0 = 0.0f;
      }
      renderBackgroundImage(-xx / 9.0 + minegrounds_1e7495cf0, yy / 12.0, width + width / 10, height - height / 6, Math.min(timeofday + 0.1f, 1.0f), new ResourceLocation("minegrounds", "textures/gui/background_01_clouds.png"));
      renderBackgroundImage(-xx / 9.0 + minegrounds_1e7495cf0 - width, yy / 12.0, width + width / 10, height - height / 6, Math.min(timeofday + 0.1f, 1.0f), new ResourceLocation("minegrounds", "textures/gui/background_01_clouds.png"));
      final Random random = new Random();
      random.setSeed(203L);
      yy -= 100.0;
      renderBackgroundImage(-xx / 16.0, yy / 28.0, width + width / 8, height + height / 24, 0.75f + timeofday / 4.0f, new ResourceLocation("minegrounds", "textures/gui/background_01.png"));

      GL11.glPopMatrix();
      renderBackgroundImage(0.0, 0.0, width, height, 1.0f, new ResourceLocation("minegrounds", "textures/gui/background_01_ov.png"));
      renderMainMenuPlayer(width, height, 0.0f, 0.0f, par1, par2);
    }
    catch (Exception ex) {}
  }

  public static void drawGradientRect(int left, int top, int right, int bottom, int colour1, int colour2, float fade, double zLevel) {
    float f = ((colour1 >> 24 & 255) / 255.0F) * fade;
    float f1 = (float) (colour1 >> 16 & 255) / 255.0F;
    float f2 = (float) (colour1 >> 8 & 255) / 255.0F;
    float f3 = (float) (colour1 & 255) / 255.0F;
    float f4 = ((colour2 >> 24 & 255) / 255.0F) * fade;
    float f5 = (float) (colour2 >> 16 & 255) / 255.0F;
    float f6 = (float) (colour2 >> 8 & 255) / 255.0F;
    float f7 = (float) (colour2 & 255) / 255.0F;
    GlStateManager.disableTexture2D();
    GlStateManager.enableBlend();
    GlStateManager.disableAlpha();
    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    GlStateManager.shadeModel(GL11.GL_SMOOTH);
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder vertexbuffer = tessellator.getBuffer();
    vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
    vertexbuffer.pos((double) right, (double) top, zLevel).color(f1, f2, f3, f).endVertex();
    vertexbuffer.pos((double) left, (double) top, zLevel).color(f1, f2, f3, f).endVertex();
    vertexbuffer.pos((double) left, (double) bottom, zLevel).color(f5, f6, f7, f4).endVertex();
    vertexbuffer.pos((double) right, (double) bottom, zLevel).color(f5, f6, f7, f4).endVertex();
    tessellator.draw();
    GlStateManager.shadeModel(GL11.GL_FLAT);
    GlStateManager.disableBlend();
    GlStateManager.enableAlpha();
    GlStateManager.enableTexture2D();
  }

  public static String formatNumber(double value) {
    if (value < 1000D) return String.valueOf(value);
    else if (value < 1000000D) return String.valueOf(Math.round(value) / 1000D) + "K";
    else if (value < 1000000000D) return String.valueOf(Math.round(value / 1000D) / 1000D) + "M";
    else if (value < 1000000000000D) return String.valueOf(Math.round(value / 1000000D) / 1000D) + "B";
    else return String.valueOf(Math.round(value / 1000000000D) / 1000D) + "T";
  }

  public static String formatNumber(long value) {
    if (value < 1000L) return String.valueOf(value);
    else if (value < 1000000L) return String.valueOf(Math.round(value) / 1000D) + "K";
    else if (value < 1000000000L) return String.valueOf(Math.round(value / 1000L) / 1000D) + "M";
    else if (value < 1000000000000L) return String.valueOf(Math.round(value / 1000000L) / 1000D) + "G";
    else if (value < 1000000000000000L) return String.valueOf(Math.round(value / 1000000000L) / 1000D) + "T";
    else if (value < 1000000000000000000L) return String.valueOf(Math.round(value / 1000000000000L) / 1000D) + "P";
    else if (value <= Long.MAX_VALUE) return String.valueOf(Math.round(value / 1000000000000000L) / 1000D) + "E";
    else return "Something is very broken!!!!";
  }

  private static DecimalFormat energyValue = new DecimalFormat("###,###,###,###,###");

  /**
   * Add commas to a number e.g. 161253126 > 161,253,126
   */
  public static String addCommas(int value) {
    return energyValue.format(value);
  }

  /**
   * Add commas to a number e.g. 161253126 > 161,253,126
   */
  public static String addCommas(long value) {
    return energyValue.format(value);
  }

  public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
    return ((mouseX >= x && mouseX < x + xSize) && (mouseY >= y && mouseY < y + ySize));
  }

  public static void drawHoveringText(List list, int x, int y, FontRenderer font, int guiWidth, int guiHeight) {
    net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(list, x, y, guiWidth, guiHeight, -1, font);
  }

  /**
   * Draws an energy bar in a gui at the given position
   *
   * @param size       is the length of the energy bar.
   * @param horizontal will rotate the bar clockwise 90 degrees.
   */
  @SuppressWarnings("all")
  public static void drawEnergyBar(Gui gui, int posX, int posY, int size, boolean horizontal, long energy, long maxEnergy, boolean toolTip, int mouseX, int mouseY) {
    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minegrounds", "textures/gui/energy_gui.png"));
    int draw = (int) ((double) energy / (double) maxEnergy * (size - 2));

    boolean inRect = isInRect(posX, posY, size, 14, mouseX, mouseY);

    if (horizontal) {
      int x = posY;
      posY = posX;
      posX = x;
      GlStateManager.pushMatrix();
      GlStateManager.translate(size + (posY * 2), 0, 0);
      GlStateManager.rotate(90, 0, 0, 1);
    }

    GlStateManager.color(1F, 1F, 1F);
    gui.drawTexturedModalRect(posX, posY, 0, 0, 14, size);
    gui.drawTexturedModalRect(posX, posY + size - 1, 0, 255, 14, 1);
    gui.drawTexturedModalRect(posX + 1, posY + size - draw - 1, 14, size - draw, 12, draw);

    if (horizontal) {
      GlStateManager.popMatrix();
    }

    if (toolTip && inRect) {
      List<String> list = new ArrayList<String>();
      list.add(I18n.translateToLocal("gui.de.energyStorage.txt"));
      list.add(formatNumber(energy) + " / " + formatNumber(maxEnergy));
      list.add(TextFormatting.GRAY + "[" + addCommas(energy) + " RF]");
      drawHoveringText(list, mouseX, mouseY, Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
    }
  }

  public static void drawColouredRect(int posX, int posY, int xSize, int ySize, int colour) {
    drawGradientRect(posX, posY, posX + xSize, posY + ySize, colour, colour, 1F, 0);
  }

  public static void drawTextInWorld(Minecraft mc, RenderManager renderManager, String text, double x, double y, double z, int color) {
    drawTextInWorld(mc, renderManager, text, x, y, z, color, 0x7f000000, 1F);
  }

  public static void drawTextInWorld(Minecraft mc, RenderManager renderManager, String text, double x, double y, double z, int color, int backgroundColour, float scale) {
    int strWidth = renderManager.getFontRenderer().getStringWidth(text);
    int strCenter = strWidth / 2;
    int yOffset = -4;

    GL11.glPushMatrix();
    GlStateManager.translate(x, y, z);
    GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
    GlStateManager.rotate(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
    GlStateManager.rotate(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
    GlStateManager.scale(-0.025F * scale, -0.025F * scale, 0.025F * scale);
    GlStateManager.disableLighting();
    GlStateManager.depthMask(false);

    GlStateManager.enableBlend();
    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

    GlStateManager.pushMatrix();
    drawColouredRect(-strCenter - 1, yOffset - 1, strWidth + 1, 9, backgroundColour);
    mc.fontRenderer.drawString(text, -strCenter, yOffset, color);
    GlStateManager.popMatrix();

    GlStateManager.enableLighting();
    GlStateManager.disableBlend();
    GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    GlStateManager.popMatrix();
  }

  public static void drawNameplate(FontRenderer fontRendererIn, String str, float x, float y, float z, int verticalShift, float viewerYaw, float viewerPitch, boolean isThirdPersonFrontal, boolean isSneaking)
  {
    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y, z);
    GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
    GlStateManager.rotate(-viewerYaw, 0.0F, 1.0F, 0.0F);
    GlStateManager.rotate((float)(isThirdPersonFrontal ? -1 : 1) * viewerPitch, 1.0F, 0.0F, 0.0F);
    GlStateManager.scale(-0.025F, -0.025F, 0.025F);
    GlStateManager.disableLighting();
    GlStateManager.depthMask(false);

    if (!isSneaking)
    {
      GlStateManager.disableDepth();
    }

    GlStateManager.enableBlend();
    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    int i = fontRendererIn.getStringWidth(str) / 2;
    GlStateManager.disableTexture2D();
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos((double)(-i - 1), (double)(-1 + verticalShift), 0.0D).color(0.0F, 0.0F, 0.0F, 0.50F).endVertex();
    bufferbuilder.pos((double)(-i - 1), (double)(8 + verticalShift), 0.0D).color(0.0F, 0.0F, 0.0F, 0.50F).endVertex();
    bufferbuilder.pos((double)(i + 1), (double)(8 + verticalShift), 0.0D).color(0.0F, 0.0F, 0.0F, 0.50F).endVertex();
    bufferbuilder.pos((double)(i + 1), (double)(-1 + verticalShift), 0.0D).color(0.0F, 0.0F, 0.0F, 0.50F).endVertex();
    tessellator.draw();
    GlStateManager.enableTexture2D();

    if (!isSneaking)
    {
      fontRendererIn.drawString(str, -fontRendererIn.getStringWidth(str) / 2, verticalShift, 553648127);
      GlStateManager.enableDepth();
    }

    GlStateManager.depthMask(true);
    fontRendererIn.drawString(str, -fontRendererIn.getStringWidth(str) / 2, verticalShift, isSneaking ? 553648127 : -1);
    GlStateManager.enableLighting();
    GlStateManager.disableBlend();
    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    GlStateManager.popMatrix();
  }


  static {
    RenderHelper.gameColor = 16777215;
    RenderHelper.dM = true;
  }
}

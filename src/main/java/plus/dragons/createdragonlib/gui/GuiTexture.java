package plus.dragons.createdragonlib.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.gui.UIRenderHelper;
import com.simibubi.create.foundation.gui.element.ScreenElement;
import com.simibubi.create.foundation.utility.Color;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;

public class GuiTexture implements ScreenElement {
    public final ResourceLocation location;
    public final int width, height;
    public final int startX, startY;

    public GuiTexture(String namespace, String location, int startX, int startY, int width, int height) {
        this.location = new ResourceLocation(namespace, "textures/gui/" + location + ".png");
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.startY = startY;
    }

    public GuiTexture(String namespace, String location, int width, int height) {
        this(namespace, location, 0, 0, width, height);
    }

    @Environment(EnvType.CLIENT)
    public void bind() {
        RenderSystem.setShaderTexture(0, location);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void render(PoseStack ms, int x, int y) {
        bind();
        GuiComponent.blit(ms, x, y, 0, startX, startY, width, height, 256, 256);
    }

    @Environment(EnvType.CLIENT)
    public void render(PoseStack ms, int x, int y, GuiComponent component) {
        bind();
        component.blit(ms, x, y, startX, startY, width, height);
    }

    @Environment(EnvType.CLIENT)
    public void render(PoseStack ms, int x, int y, Color c) {
        bind();
        UIRenderHelper.drawColoredTexture(ms, c, x, y, startX, startY, width, height);
    }

}

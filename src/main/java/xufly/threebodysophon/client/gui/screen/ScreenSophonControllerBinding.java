package xufly.threebodysophon.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xufly.threebodysophon.ThreebodySophon;

public class ScreenSophonControllerBinding extends Screen
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(ThreebodySophon.MODID, "textures/gui/sophon_controller_binding.png");
    private final int xSize = 176;
    private final int ySize = 127;
    private int guiLeft;
    private int guiTop;
    private boolean usePos;

    protected ScreenSophonControllerBinding()
    {
        super(new StringTextComponent("screen.sophon_controller_binding.title"));
        this.usePos = true;
    }

    @Override
    protected void init()
    {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        TranslationTextComponent title = new TranslationTextComponent("screen.sophon_controller_binding.title");
        this.font.drawText(matrixStack, title, (this.width - this.font.getStringWidth(title.getString())) / 2.0F, this.guiTop + 4.5F, 0x000000);
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        if (this.minecraft.gameSettings.keyBindInventory.isActiveAndMatches(InputMappings.getInputByCode(keyCode, scanCode)))
        {
            this.closeScreen();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}

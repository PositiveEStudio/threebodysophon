package xufly.threebodysophon.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xufly.threebodysophon.ThreebodySophon;
import xufly.threebodysophon.entity.EntitySophon;

import java.util.UUID;

public class ScreenSophonController extends Screen
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(ThreebodySophon.MODID, "textures/gui/sophon_controller.png");
    private final int xSize = 176;
    private final int ySize = 166;
    private int guiLeft;
    private int guiTop;
    private UUID id;
    private TextFieldWidget textFieldX;
    private TextFieldWidget textFieldY;
    private TextFieldWidget textFieldZ;
    private boolean usePos;

    protected ScreenSophonController(UUID sophonId)
    {
        super(new TranslationTextComponent("screen.sophon_controller.title"));
        this.guiLeft = (this.width - xSize) / 2;
        this.usePos = true;
        this.id = sophonId;
    }

    @Override
    protected void init()
    {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.addButton(new ImageButton(this.guiLeft + 48, this.guiTop + 20, 21, 12, 176, 0, 12, TEXTURE, (p) -> {
            this.usePos = !usePos;
        }));

        this.textFieldX = new TextFieldWidget(this.font, this.guiLeft + 24, this.guiTop + 38, 40, 9, new TranslationTextComponent("screen.catapult_controller.field_x"));
        this.textFieldX.setMaxStringLength(10);
        this.textFieldX.setEnableBackgroundDrawing(false);
        this.textFieldX.setVisible(true);
        this.textFieldX.setTextColor(0x62FF7F);
        this.children.add(this.textFieldX);

        this.textFieldY = new TextFieldWidget(this.font, this.guiLeft + 24, this.guiTop + 52, 40, 9, new TranslationTextComponent("screen.catapult_controller.field_y"));
        this.textFieldY.setMaxStringLength(10);
        this.textFieldY.setEnableBackgroundDrawing(false);
        this.textFieldY.setVisible(true);
        this.textFieldY.setTextColor(0x62FF7F);
        this.children.add(this.textFieldY);

        this.textFieldZ = new TextFieldWidget(this.font, this.guiLeft + 24, this.guiTop + 66, 40, 9, new TranslationTextComponent("screen.catapult_controller.field_z"));
        this.textFieldZ.setMaxStringLength(10);
        this.textFieldZ.setEnableBackgroundDrawing(false);
        this.textFieldZ.setVisible(true);
        this.textFieldZ.setTextColor(0x62FF7F);
        this.children.add(this.textFieldZ);

        try
        {
            Entity sophon = null;
            for (Entity entity : this.minecraft.world.getAllEntities())
            {
                if (entity instanceof EntitySophon)
                {
                    if (entity.getUniqueID().equals(this.id))
                    {
                        sophon = entity;
                    }
                }
            }
            this.textFieldX.setText(String.valueOf((int) sophon.getPosX()));
            this.textFieldY.setText(String.valueOf((int) sophon.getPosY()));
            this.textFieldZ.setText(String.valueOf((int) sophon.getPosZ()));
        }
        catch (NullPointerException e)
        {
            this.textFieldX.setText("0");
            this.textFieldY.setText("0");
            this.textFieldZ.setText("0");
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        TranslationTextComponent title = new TranslationTextComponent("screen.sophon_controller.title");
        this.font.drawText(matrixStack, title, (this.width - this.font.getStringWidth(title.getString())) / 2.0F, this.guiTop + 6.0F, 0xFF9999);

        TranslationTextComponent pos = new TranslationTextComponent("screen.sophon_controller.pos");
        this.font.drawText(matrixStack, pos, this.guiLeft + 9, this.guiTop + 20, 0xFF9999);

        TranslationTextComponent changeMode = new TranslationTextComponent("screen.sophon_controller.change_mode");
        this.font.drawText(matrixStack, changeMode, this.guiLeft + 50, this.guiTop + 22, 0xFF9999);

        if (this.usePos) {
            this.font.drawText(matrixStack, new StringTextComponent("X: "), this.guiLeft + 13, this.guiTop + 38, 0xFF9999);
            this.font.drawText(matrixStack, new StringTextComponent("Y: "), this.guiLeft + 13, this.guiTop + 52, 0xFF9999);
            this.font.drawText(matrixStack, new StringTextComponent("Z: "), this.guiLeft + 13, this.guiTop + 66, 0xFF9999);
            this.textFieldX.render(matrixStack, mouseX, mouseY, partialTicks);
            this.textFieldY.render(matrixStack, mouseX, mouseY, partialTicks);
            this.textFieldZ.render(matrixStack, mouseX, mouseY, partialTicks);
        }
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

    private static class Button extends ImageButton
    {
        public Button(int xIn, int yIn, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn, int yDiffTextIn, ResourceLocation resourceLocationIn, IPressable onPressIn)
        {
            super(xIn, yIn, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, resourceLocationIn, onPressIn);
        }
    }
}

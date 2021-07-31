package xufly.threebodysophon.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import xufly.threebodysophon.ThreebodySophon;
import xufly.threebodysophon.entity.EntitySophon;
import xufly.threebodysophon.network.NetworkHandler;
import xufly.threebodysophon.network.client.CSophonExpansionPacket;
import xufly.threebodysophon.network.client.CSophonPositionPacket;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ScreenSophonController extends Screen
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(ThreebodySophon.MODID, "textures/gui/sophon_controller.png");
    private final int xSize = 176;
    private final int ySize = 166;
    private final UUID sophonId;
    private int guiLeft;
    private int guiTop;
    @Nullable
    private UUID trackingPlayer;
    @Nullable
    private EntitySophon sophon = null;
    private TextFieldWidget textFieldX;
    private TextFieldWidget textFieldY;
    private TextFieldWidget textFieldZ;
    private boolean usePos;
    private boolean numberIncorrect;
    private int page;

    protected ScreenSophonController(UUID sophonId)
    {
        super(new TranslationTextComponent("screen.sophon_controller.title"));
        this.guiLeft = (this.width - xSize) / 2;
        this.usePos = true;
        this.numberIncorrect = false;
        this.sophonId = sophonId;
        this.trackingPlayer = null;
        this.page = 0;
    }

    @Override
    protected void init()
    {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.buttons.clear();
        this.children.clear();
        try
        {
            for (Entity entity : this.minecraft.world.getAllEntities())
            {
                if (entity instanceof EntitySophon && entity.getUniqueID().equals(this.sophonId))
                {
                    this.sophon = (EntitySophon) entity;
                }
            }

            this.addButton(new CheckboxButton(this.guiLeft + 6, this.guiTop + 15, 20, 20, new TranslationTextComponent("screen.sophon_controller.expansion").mergeStyle(TextFormatting.LIGHT_PURPLE), this.sophon.getLowDimensionalExpansion())
            {
                @Override
                public void onPress()
                {
                    super.onPress();
                    NetworkHandler.SOPHON_EXPANSION.sendToServer(new CSophonExpansionPacket(ScreenSophonController.this.sophonId, this.isChecked()));
                }
            });
        }
        catch (NullPointerException e)
        {
            this.sophon = null;
        }

        this.initPosSetPanel();
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

        this.renderPosSetPanel(matrixStack, mouseX, mouseY, partialTicks);
    }


    private void initPosSetPanel()
    {
        this.addButton(new ImageButton(this.guiLeft + 125, this.guiTop + 39, 21, 12, 176, 0, 12, TEXTURE, (p) -> {
            this.usePos = !usePos;
            this.init();
        }));
        this.addButton(new ImageButton(this.guiLeft + 147, this.guiTop + 39, 21, 12, 176, 0, 12, TEXTURE, (p) -> {
            if (this.usePos && !this.numberIncorrect)
            {
                NetworkHandler.SOPHON_POSITION.sendToServer(new CSophonPositionPacket(this.sophonId, true, Double.parseDouble(this.textFieldX.getText()), Double.parseDouble(this.textFieldY.getText()), Double.parseDouble(this.textFieldZ.getText()), this.trackingPlayer));
            }
        }));

        if (this.usePos)
        {
            this.addButton(new ImageButton(this.guiLeft + 103, this.guiTop + 39, 21, 12, 176, 0, 12, TEXTURE, (p) -> {
                this.textFieldX.setText("");
                this.textFieldY.setText("");
                this.textFieldZ.setText("");
            }));
        }
        else
        {
            List<AbstractClientPlayerEntity> list = this.minecraft.world.getPlayers();
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    this.addButton(new ImageButton(this.guiLeft + 9 + 53 * j, this.guiTop + 56 + 14 * i, 51, 12, 197, 0, 12, TEXTURE, (p) -> {
                    })
                    {
                        @Override
                        public void onPress()
                        {
                            int i1 = (this.y - ScreenSophonController.this.guiTop - 56) / 14;
                            int j1 = (this.x - ScreenSophonController.this.guiLeft - 9) / 53;
                            if ((i1 * 3) + (ScreenSophonController.this.page * 9) + j1 < list.size())
                            {
                                NetworkHandler.SOPHON_POSITION.sendToServer(new CSophonPositionPacket(ScreenSophonController.this.sophonId, false, 0, 0, 0, list.get((i1 * 3) + (ScreenSophonController.this.page * 9) + j1).getUniqueID()));
                            }
                        }
                    });
                }
            }

            this.addButton(new ImageButton(this.guiLeft + 103, this.guiTop + 39, 21, 12, 176, 24, 12, TEXTURE, (p) -> {
                if (this.page < Math.ceil(list.size() / 9.0D)) this.page++;
            }));
            this.addButton(new ImageButton(this.guiLeft + 81, this.guiTop + 39, 21, 12, 197, 24, 12, TEXTURE, (p) -> {
                if (this.page > 0) this.page--;
            }));
        }

        this.textFieldX = new TextFieldWidget(this.font, this.guiLeft + 54, this.guiTop + 58, 78, 9, new TranslationTextComponent("screen.catapult_controller.field_x"));
        this.textFieldX.setMaxStringLength(18);
        this.textFieldX.setEnableBackgroundDrawing(false);
        this.textFieldX.setTextColor(0x62FF7F);
        this.children.add(this.textFieldX);

        this.textFieldY = new TextFieldWidget(this.font, this.guiLeft + 54, this.guiTop + 72, 78, 9, new TranslationTextComponent("screen.catapult_controller.field_y"));
        this.textFieldY.setMaxStringLength(18);
        this.textFieldY.setEnableBackgroundDrawing(false);
        this.textFieldY.setTextColor(0x62FF7F);
        this.children.add(this.textFieldY);

        this.textFieldZ = new TextFieldWidget(this.font, this.guiLeft + 54, this.guiTop + 86, 78, 9, new TranslationTextComponent("screen.catapult_controller.field_z"));
        this.textFieldZ.setMaxStringLength(18);
        this.textFieldZ.setEnableBackgroundDrawing(false);
        this.textFieldZ.setTextColor(0x62FF7F);
        this.children.add(this.textFieldZ);

        try
        {
            this.textFieldX.setText(String.valueOf((int) this.sophon.getPosX()));
            this.textFieldY.setText(String.valueOf((int) this.sophon.getPosY()));
            this.textFieldZ.setText(String.valueOf((int) this.sophon.getPosZ()));
        }
        catch (NullPointerException e)
        {
            this.textFieldX.setText("0");
            this.textFieldY.setText("0");
            this.textFieldZ.setText("0");
        }

        if (this.usePos)
        {
            this.textFieldX.setVisible(true);
            this.textFieldY.setVisible(true);
            this.textFieldZ.setVisible(true);
        }
        else
        {
            this.textFieldX.setVisible(false);
            this.textFieldY.setVisible(false);
            this.textFieldZ.setVisible(false);
        }
    }

    private void renderPosSetPanel(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        TranslationTextComponent pos = new TranslationTextComponent("screen.sophon_controller.pos");
        this.font.drawText(matrixStack, pos, this.guiLeft + 9, this.guiTop + 40, 0xFF9999);
        TranslationTextComponent changeMode = new TranslationTextComponent("screen.sophon_controller.change_mode");
        this.font.drawText(matrixStack, changeMode, this.guiLeft + 127, this.guiTop + 41, 0xFF9999);
        TranslationTextComponent done = new TranslationTextComponent("screen.sophon_controller.done");
        this.font.drawText(matrixStack, done, this.guiLeft + 149, this.guiTop + 41, 0xFF9999);

        if (this.usePos)
        {
            this.minecraft.getTextureManager().bindTexture(TEXTURE);
            this.blit(matrixStack, this.guiLeft + 51, this.guiTop + 56, 0, 166, 80, 40);

            TranslationTextComponent clear = new TranslationTextComponent("screen.sophon_controller.clear");
            this.font.drawText(matrixStack, clear, this.guiLeft + 105, this.guiTop + 41, 0xFF9999);
            this.font.drawText(matrixStack, new StringTextComponent("X: "), this.guiLeft + 43, this.guiTop + 58, 0xFF9999);
            this.font.drawText(matrixStack, new StringTextComponent("Y: "), this.guiLeft + 43, this.guiTop + 72, 0xFF9999);
            this.font.drawText(matrixStack, new StringTextComponent("Z: "), this.guiLeft + 43, this.guiTop + 86, 0xFF9999);
            this.textFieldX.render(matrixStack, mouseX, mouseY, partialTicks);
            this.textFieldY.render(matrixStack, mouseX, mouseY, partialTicks);
            this.textFieldZ.render(matrixStack, mouseX, mouseY, partialTicks);

            try
            {
                Double.parseDouble(this.textFieldX.getText());
                Double.parseDouble(this.textFieldX.getText());
                Double.parseDouble(this.textFieldX.getText());
                this.numberIncorrect = false;
            }
            catch (NumberFormatException e)
            {
                this.font.drawText(matrixStack, new TranslationTextComponent("screen.catapult_controller.number_incorrect"), this.guiLeft + 54, this.guiTop + 41, 0xFF0000);
                this.numberIncorrect = true;
            }
        }
        else
        {
            List<AbstractClientPlayerEntity> list = this.minecraft.world.getPlayers();
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    this.addButton(new ImageButton(this.guiLeft + 9 + 53 * j, this.guiTop + 56 + 14 * i, 51, 12, 197, 0, 12, TEXTURE, (p) -> {

                    }));
                    if ((i * 3) + (this.page * 9) + j < list.size())
                    {
                        this.font.drawText(matrixStack, list.get((i * 3) + (this.page * 9) + j).getDisplayName(), this.guiLeft + 11 + 53 * j, this.guiTop + 58 + 14 * i, 0xFF9999);
                    }
                }
            }
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
}

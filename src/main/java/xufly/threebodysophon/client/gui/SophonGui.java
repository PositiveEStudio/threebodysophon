package xufly.threebodysophon.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SophonGui extends AbstractGui
{
    private final MatrixStack matrixStack;
    private final CompoundNBT nbt;
    private final FontRenderer fontRenderer;
    private final int width;
    private final int height;

    public SophonGui(MatrixStack matrixStack, ClientPlayerEntity player)
    {
        this.matrixStack = matrixStack;
        this.nbt = player.getPersistentData();
        this.fontRenderer = Minecraft.getInstance().fontRenderer;
        this.width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        this.height = Minecraft.getInstance().getMainWindow().getScaledHeight();
    }

    public void render()
    {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (nbt.contains("TextOnEye", 9))
        {
            ListNBT texts = nbt.getList("TextOnEye", 8);
            for (int i = 0; i < texts.size(); i++)
            {
                String text = texts.getString(i);
                this.fontRenderer.drawText(this.matrixStack, new StringTextComponent(text), (float) (this.width - this.fontRenderer.getStringWidth(text)) / 2.0F, (this.height - texts.size() * 16) / 2.0F + i * 16.0F, 0xFF9999);
            }
        }
    }
}

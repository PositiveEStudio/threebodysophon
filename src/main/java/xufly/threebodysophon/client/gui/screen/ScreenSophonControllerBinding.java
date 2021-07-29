package xufly.threebodysophon.client.gui.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import xufly.threebodysophon.ThreebodySophon;

public class ScreenSophonControllerBinding extends Screen
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(ThreebodySophon.MODID, "textures/gui/sophon_controller_binding.png");
    private final int xSize = 84;
    private final int ySize = 32;
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


}

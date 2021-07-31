package xufly.threebodysophon.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import xufly.threebodysophon.ThreebodySophon;
import xufly.threebodysophon.client.renderer.entity.model.ModelSophon;
import xufly.threebodysophon.entity.EntitySophon;

@OnlyIn(Dist.CLIENT)
public class RendererSophon extends EntityRenderer<EntitySophon>
{
    private ModelSophon model = new ModelSophon();

    protected RendererSophon(EntityRendererManager renderManager)
    {
        super(renderManager);
    }

    @Override
    public ResourceLocation getEntityTexture(EntitySophon entity)
    {
        return new ResourceLocation(ThreebodySophon.MODID, "textures/entity/sophon.png");
    }

    @Override
    public void render(EntitySophon entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
    {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        if (entityIn.getLowDimensionalExpansion())
        {
            matrixStackIn.push();
            IVertexBuilder builder = bufferIn.getBuffer(this.model.getRenderType(this.getEntityTexture(entityIn)));
            this.model.render(matrixStackIn, builder, 200, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.pop();

            if (!entityIn.getDisplayText().getString().isEmpty())
            {
                double distanceSq = this.renderManager.squareDistanceTo(entityIn);
                if (ForgeHooksClient.isNameplateInRenderDistance(entityIn, distanceSq))
                {
                    matrixStackIn.push();
                    double distance = Math.sqrt(distanceSq);
                    double xDistance = Minecraft.getInstance().player.getPosX() - entityIn.getPosX();
                    double yDistance = Minecraft.getInstance().player.getPosYEye() - entityIn.getPosY();
                    double zDistance = Minecraft.getInstance().player.getPosZ() - entityIn.getPosZ();
                    matrixStackIn.translate(xDistance / distance * 2, yDistance / distance * 2 + 0.575D, zDistance / distance * 2);
                    matrixStackIn.rotate(this.renderManager.getCameraOrientation());
                    matrixStackIn.scale(-0.025F, -0.025F, 0.025F);
                    Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
                    ITextComponent displayNameIn = entityIn.getDisplayText();
                    float f1 = Minecraft.getInstance().gameSettings.getTextBackgroundOpacity(0.25F);
                    int j = (int) (f1 * 255.0F) << 24;
                    FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
                    float x = -fontrenderer.getStringPropertyWidth(displayNameIn) / 2.0F;
                    fontrenderer.func_243247_a(displayNameIn, x, 0.0F, 553648127, false, matrix4f, bufferIn, true, j, 200);
                    fontrenderer.func_243247_a(displayNameIn, x, 0.0F, -1, false, matrix4f, bufferIn, false, 0, 200);
                    matrixStackIn.pop();
                }
            }
        }
    }
}

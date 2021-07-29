package xufly.threebodysophon.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
            this.model.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.pop();
        }
    }
}

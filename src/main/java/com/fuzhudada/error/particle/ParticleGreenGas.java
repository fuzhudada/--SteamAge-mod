package com.fuzhudada.error.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class ParticleGreenGas extends Particle {
    private static final ResourceLocation TEXTURE = new ResourceLocation("error", "textures/particle/big_smoke_0.png");
    private final TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();

    protected ParticleGreenGas(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn,
                               double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        this.motionX = this.motionX * 0.009999999776482582D + xSpeedIn;
        this.motionY = this.motionY * 0.009999999776482582D + ySpeedIn;
        this.motionZ = this.motionZ * 0.009999999776482582D + zSpeedIn;
        this.posX += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.3F;
        this.posY += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.3F;
        this.posZ += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.3F;
        this.particleRed = 0.2F;
        this.particleGreen = 0.9F;
        this.particleBlue = 0.3F;
        this.particleAlpha = 0.6F;          // 初始透明度，可调整
        this.particleScale = 3.0F + this.rand.nextFloat() * 2.0F;
        this.particleMaxAge = 80;
    }

    @Override
    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
        this.resetPositionToBB();
    }

    @Override
    public void renderParticle(@Nonnull BufferBuilder buffer, @Nonnull Entity entityIn, float partialTicks,
                               float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        // 计算生命进度
        float f = ((float) this.particleAge + partialTicks) / (float) this.particleMaxAge;
        // 透明度：初始值 * (1 - 进度 * 衰减系数)，这里衰减系数0.6表示结束时剩40%透明度
        float alpha = this.particleAlpha * (1.0F - f * 0.6F);

        // 绑定纹理
        this.textureManager.bindTexture(TEXTURE);

        // 计算粒子中心位置（世界坐标插值）
        float f5 = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
        float f6 = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
        float f7 = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);

        // 粒子大小（半边长）
        float halfSize = 0.5F * this.particleScale;

        // 构建四个顶点相对于中心的方向（考虑相机的旋转）
        Vec3d[] corners = new Vec3d[]{
                new Vec3d(-rotationX * halfSize - rotationXY * halfSize, -rotationZ * halfSize, -rotationYZ * halfSize - rotationXZ * halfSize),
                new Vec3d(-rotationX * halfSize + rotationXY * halfSize, rotationZ * halfSize, -rotationYZ * halfSize + rotationXZ * halfSize),
                new Vec3d(rotationX * halfSize + rotationXY * halfSize, rotationZ * halfSize, rotationYZ * halfSize + rotationXZ * halfSize),
                new Vec3d(rotationX * halfSize - rotationXY * halfSize, -rotationZ * halfSize, rotationYZ * halfSize - rotationXZ * halfSize)
        };

        // 如果粒子有旋转角度，对顶点进行旋转
        if (this.particleAngle != 0.0F) {
            float angle = this.particleAngle + (this.particleAngle - this.prevParticleAngle) * partialTicks;
            float cos = MathHelper.cos(angle * 0.5F);
            float sin = MathHelper.sin(angle * 0.5F) * (float) cameraViewDir.x;
            float sinY = MathHelper.sin(angle * 0.5F) * (float) cameraViewDir.y;
            float sinZ = MathHelper.sin(angle * 0.5F) * (float) cameraViewDir.z;
            Vec3d axis = new Vec3d(sin, sinY, sinZ);

            for (int i = 0; i < 4; ++i) {
                Vec3d v = corners[i];
                // 四元数旋转: v' = 2 * axis * (axis · v) + (cos² - |axis|²) * v + 2*cos * (axis × v)
                corners[i] = axis.scale(2.0D * v.dotProduct(axis))
                        .add(v.scale((double) (cos * cos) - axis.dotProduct(axis)))
                        .add(axis.crossProduct(v).scale(2.0F * cos));
            }
        }

        // 获取光照亮度
        int light = this.getBrightnessForRender(partialTicks);
        int lightMapU = light >> 16 & 65535;
        int lightMapV = light & 65535;

        // 启用混合（透明度）
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

        // 开始绘制（使用粒子专用格式）
        buffer.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        // 顶点顺序：右下、右上、左上、左下（对应纹理坐标 1,1 -> 1,0 -> 0,0 -> 0,1）
        buffer.pos(f5 + corners[0].x, f6 + corners[0].y, f7 + corners[0].z)
                .tex(1.0F, 1.0F)
                .color(this.particleRed, this.particleGreen, this.particleBlue, alpha)
                .lightmap(lightMapU, lightMapV).endVertex();
        buffer.pos(f5 + corners[1].x, f6 + corners[1].y, f7 + corners[1].z)
                .tex(1.0F, 0.0F)
                .color(this.particleRed, this.particleGreen, this.particleBlue, alpha)
                .lightmap(lightMapU, lightMapV).endVertex();
        buffer.pos(f5 + corners[2].x, f6 + corners[2].y, f7 + corners[2].z)
                .tex(0.0F, 0.0F)
                .color(this.particleRed, this.particleGreen, this.particleBlue, alpha)
                .lightmap(lightMapU, lightMapV).endVertex();
        buffer.pos(f5 + corners[3].x, f6 + corners[3].y, f7 + corners[3].z)
                .tex(0.0F, 1.0F)
                .color(this.particleRed, this.particleGreen, this.particleBlue, alpha)
                .lightmap(lightMapU, lightMapV).endVertex();

        Tessellator.getInstance().draw();

        // 恢复混合状态（可选，但建议恢复以防止影响后续渲染）
        GlStateManager.disableBlend();
    }

    @Override
    public int getBrightnessForRender(float partialTicks) {
        // 固定全亮（或可根据需要调整）
        return 15728880;
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setExpired();
        }

        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;
        this.motionY += 0.002D;

        if (this.onGround) {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }

    @Override
    public int getFXLayer() {
        return 3; // 使用粒子纹理层（通常为3表示自定义纹理）
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        @Override
        public Particle createParticle(int particleID, @Nonnull World worldIn,
                                       double xCoordIn, double yCoordIn, double zCoordIn,
                                       double xSpeedIn, double ySpeedIn, double zSpeedIn,
                                       @Nonnull int... p_178902_15_) {
            return new ParticleGreenGas(worldIn, xCoordIn, yCoordIn, zCoordIn,
                    xSpeedIn, ySpeedIn, zSpeedIn);
        }
    }
}
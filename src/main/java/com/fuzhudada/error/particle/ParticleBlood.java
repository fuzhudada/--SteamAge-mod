package com.fuzhudada.error.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleSplash;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ParticleBlood extends ParticleSplash {
    protected ParticleBlood(World worldIn , double xCoordIn , double yCoordIn , double zCoordIn , double xSpeedIn , double ySpeedIn , double zSpeedIn) {
        super(worldIn , xCoordIn , yCoordIn , zCoordIn , xSpeedIn , ySpeedIn , zSpeedIn);
        this.particleRed=1.0F;
        this.particleGreen=0.0F;
        this.particleBlue=0.0F;

        if (ySpeedIn != 0.0D || xSpeedIn != 0.0D || zSpeedIn != 0.0D)
        {
            this.motionX = xSpeedIn;
            this.motionY = ySpeedIn;
            this.motionZ = zSpeedIn;
            this.particleGravity = 0.0F;
        }
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
    {
        public Particle createParticle(int particleID, @Nonnull World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, @Nonnull int... p_178902_15_)
        {
            return new ParticleBlood(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        }
    }
}

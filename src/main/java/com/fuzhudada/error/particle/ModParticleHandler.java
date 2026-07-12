package com.fuzhudada.error.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.util.EnumHelper;

public class ModParticleHandler {
    public static final Class<?>[] classes={String.class,int.class,boolean.class};
    public static Minecraft mc=Minecraft.getMinecraft();
    public static EnumParticleTypes TEST;
    public static EnumParticleTypes BUBBLE;
    public static EnumParticleTypes BLOODY_WIND;
    public static EnumParticleTypes BLOOD;
    public static EnumParticleTypes GLOW;
    public static EnumParticleTypes CLOUD;
    public static EnumParticleTypes INVISIBLE_LIGHT_SOURCE;
    public static EnumParticleTypes CAMPFIRE;
    public static EnumParticleTypes BLACK_FLAME;
    public static EnumParticleTypes GREEN_GAS;

    public static void init(){
        TEST = registerParticle("testParticle",true,new ParticleTest.Factory());
        BUBBLE = registerParticle("modBubble",true,new ParticleModBubble.Factory());
        BLOODY_WIND = registerParticle("bloodyWind",true,new ParticleBloodyWind.Factory());
        BLOOD = registerParticle("blood",true,new ParticleBlood.Factory());
        GLOW = registerParticle("glow",true,new ParticleGlow.Factory());
        CLOUD = registerParticle("modCloud",true,new ParticleModCloud.Factory());
        INVISIBLE_LIGHT_SOURCE = registerParticle("invisible_light_source",true,new ParticleInvisibleLightSource.Factory());
        CAMPFIRE = registerParticle("campfire",true,new ParticleCampfire.Factory());
        BLACK_FLAME = registerParticle("black_flame",true,new ParticleBlackFlame.Factory());
        GREEN_GAS = registerParticle("green_gas",true,new ParticleGreenGas.Factory());
    }

    private static EnumParticleTypes registerParticle(String name, boolean ignoreRange, IParticleFactory factory){
        int id=EnumParticleTypes.values().length;
        mc.effectRenderer.registerParticle(id, factory);
        return EnumHelper.addEnum(EnumParticleTypes.class,name,classes,name,id,ignoreRange);
    }
}

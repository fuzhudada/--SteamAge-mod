package com.fuzhudada.error.packet;

import com.fuzhudada.error.packet.ParticleAnimation.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import static com.somebody.idlframewok.IdlFramework.MODID;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
    static int id = 0;

    public static void init() {
        PacketHandler handler = new PacketHandler();
        //注册该数据包类
        INSTANCE.registerMessage(MessageAASSkillManager.class, MessageAASSkill.class, id++, Side.SERVER);
        INSTANCE.registerMessage(MessageParticleManager.class, MessageParticle.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(MessageAssaultManager.class, MessageAssault.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(MessageExecManager.class, MessageExec.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(PointManager.class, Point.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(PointShrinkManager.class, PointShrink.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(CircleManager.class, Circle.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(CircleShrinkManager.class, CircleShrink.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(LineManager.class, Line.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(MessageBirdChestManager.class, MessageBirdChest.class, id++, Side.SERVER);
        INSTANCE.registerMessage(SphereManager.class, Sphere.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(LineShrinkManager.class, LineShrink.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(SphereShrinkManager.class, SphereShrink.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(HelixManager.class, Helix.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(HelixShrinkManager.class, HelixShrink.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(MessageCCBManager.class, MessageCCB.class, id++, Side.SERVER);

        MinecraftForge.EVENT_BUS.register(handler);
    }

}


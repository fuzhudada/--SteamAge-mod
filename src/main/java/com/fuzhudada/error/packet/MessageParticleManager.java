package com.fuzhudada.error.packet;

import com.fuzhudada.error.item.sword.ItemNewYearHammer;
import com.fuzhudada.error.particle.ModParticleHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;

public class MessageParticleManager implements IMessageHandler<MessageParticle, IMessage> {
    @Override
    public IMessage onMessage(MessageParticle message , MessageContext ctx) {
        Minecraft mc=Minecraft.getMinecraft();
        mc.addScheduledTask(() -> {        if(message.type==0){
            for(int i=0;i<50;i++){
                mc.world.spawnParticle(EnumParticleTypes.FLAME, message.x+message.f.x,message.y, message.z+message.f.z,message.f.x+0.2*Math.random()-0.1,message.f.y+0.2*Math.random()-0.1,message.f.z+0.2*Math.random()-0.1);
            }
        }else if(message.type==1){
            for(int i=0;i<3;i++) {
                Vec3d F1;
                if (i == 0) {
                    F1 = message.f;
                } else if (i == 1) {
                    F1 = new Vec3d(0.9848 * message.f.x - 0.1736 * message.f.z , 0 , 0.1736 * message.f.x + 0.9848 * message.f.z);
                } else {
                    F1 = new Vec3d(0.9848 * message.f.x + 0.1736 * message.f.z , 0 , -0.1736 * message.f.x + 0.9848 * message.f.z);
                }
                for(double d=0.5;d<8;d+=0.3){
                    mc.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY,message.x+F1.x*d,message.y,message.z+F1.z*d,0,0,0);
                }
            }
        }else if(message.type==2){
            mc.world.makeFireworks(message.x, message.y , message.z , message.f.x, message.f.y, message.f.z, ItemNewYearHammer.nbt1);
        }else if(message.type==3){
            int id= Block.getIdFromBlock(mc.world.getBlockState(new BlockPos(message.x,message.y-1, message.z)).getBlock());

            for(int i=0;i<5;i++){
                for(int j=0;j<20*i;j++){
                    double x= message.x+i*Math.cos(j*Math.PI/10/i);
                    double z= message.z+i*Math.sin(j*Math.PI/10/i);
                    mc.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK,x,message.y+0.1,z,0,0,0,id);
                }
            }
        }else if(message.type==4){
            for(int i=0;i<1.5*(message.f.y-message.f.x);i++){
                double r=Math.random();
                double a=Math.random()*Math.PI*2;
                double x= message.x+((1-r)*message.f.x+r*message.f.y)*Math.cos(a);
                double z= message.z+((1-r)*message.f.x+r*message.f.y)*Math.sin(a);
                mc.world.spawnParticle(EnumParticleTypes.CLOUD,x,message.y+0.1,z,0,0.3,0);
                mc.world.spawnParticle(EnumParticleTypes.CLOUD,x,message.y+0.1,z,0,0,0);
            }
        }else if(message.type==5){
            Vec3d u=getVector(message.f);
            //mc.player.sendMessage(new TextComponentTranslation(""+(u==null)));
            if(u != null){
                Vec3d v=message.f.crossProduct(u);

                for(int i=0;i<5;i++){
                    double r=Math.sqrt(i+3)/2.5;
                    double d=((double) (4-i))/4;
                    for(int j=0;j<15;j++){
                        double a=j*Math.PI*2/15;
                        double x=message.x+d*message.f.x+r*u.x*Math.cos(a)+r*v.x*Math.sin(a);
                        double y=message.y+r*u.y*Math.cos(a)+r*v.y*Math.sin(a);
                        double z=message.z+d*message.f.z+r*u.z*Math.cos(a)+r*v.z*Math.sin(a);
                        //mc.player.sendMessage(new TextComponentTranslation(x+" "+y+" "+z));

                        mc.world.spawnParticle(EnumParticleTypes.CRIT,x,y,z,0,0,0);
                    }
                }
            }
        }else if(message.type==6){
            for(int i=0;i<50;i++){
                mc.world.spawnParticle(ModParticleHandler.TEST , message.x+message.f.x,message.y, message.z+message.f.z,message.f.x+0.2*Math.random()-0.1,message.f.y+0.2*Math.random()-0.1,message.f.z+0.2*Math.random()-0.1);
            }
        }else if(message.type==7){
            ArrayList<Vec3d> list=new ArrayList<>();
            list.add(new Vec3d(message.x,message.y,message.z));
            double dis=Math.sqrt((message.x-message.f.x)*(message.x-message.f.x)+(message.y-message.f.y)*(message.y-message.f.y)+(message.z-message.f.z)*(message.z-message.f.z));
            for(int i=1;i<dis;i++){
                double x=message.x+(message.f.x-message.x)*i/dis+Math.random()-0.5;
                double y=message.y+(message.f.y-message.y)*i/dis+Math.random()-0.5;
                double z=message.z+(message.f.z-message.z)*i/dis+Math.random()-0.5;
                list.add(new Vec3d(x,y,z));
            }
            list.add(new Vec3d(message.f.x,message.f.y,message.f.z));

            for(int i=0;i<list.size()-1;i++){
                double x1=list.get(i).x;
                double y1=list.get(i).y;
                double z1=list.get(i).z;
                double x2=list.get(i+1).x;
                double y2=list.get(i+1).y;
                double z2=list.get(i+1).z;
                for(int j=0;j<10;j++){
                    double x=x1+(x2-x1)*j/10;
                    double y=y1+(y2-y1)*j/10;
                    double z=z1+(z2-z1)*j/10;
                    mc.world.spawnParticle(ModParticleHandler.GLOW,true,x,y,z,0,0,0);
                }
            }

            mc.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE,true,message.x,message.y+0.5,message.z,0,0,0);
            for(int i=0;i<20;i++){
                mc.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE,true,message.x+0.6*Math.random()-0.3,message.y+0.6*Math.random()-0.3,message.z+0.6*Math.random()-0.3,0.1*Math.random()-0.05,0.2,0.1*Math.random()-0.05);
            }
        }else if(message.type==8){
            ArrayList<Vec3d> list=new ArrayList<>();
            list.add(new Vec3d(message.x,message.y,message.z));
            double dis=Math.sqrt((message.x-message.f.x)*(message.x-message.f.x)+(message.y-message.f.y)*(message.y-message.f.y)+(message.z-message.f.z)*(message.z-message.f.z));
            for(int i=1;i<dis;i++){
                double x=message.x+(message.f.x-message.x)*i/dis+Math.random()-0.5;
                double y=message.y+(message.f.y-message.y)*i/dis+Math.random()-0.5;
                double z=message.z+(message.f.z-message.z)*i/dis+Math.random()-0.5;
                list.add(new Vec3d(x,y,z));
            }
            list.add(new Vec3d(message.f.x,message.f.y,message.f.z));

            for(int i=0;i<list.size()-1;i++){
                double x1=list.get(i).x;
                double y1=list.get(i).y;
                double z1=list.get(i).z;
                double x2=list.get(i+1).x;
                double y2=list.get(i+1).y;
                double z2=list.get(i+1).z;
                for(int j=0;j<10;j++){
                    double x=x1+(x2-x1)*j/10;
                    double y=y1+(y2-y1)*j/10;
                    double z=z1+(z2-z1)*j/10;
                    mc.world.spawnParticle(ModParticleHandler.GLOW,true,x,y,z,0,0,0);
                }
            }
        }});
        return null;
    }

    private Vec3d getVector(Vec3d n){
        Vec3d u=null;
        if(n.x != 0){
            u=new Vec3d(-(n.y+n.z)/n.x,1,1).normalize();
        }else if(n.y != 0){
            u=new Vec3d(1,-(n.x+n.z)/n.y,1).normalize();
        }else if(n.z != 0){
            u=new Vec3d(1,1,-(n.x+n.y)/n.z).normalize();
        }

        return u;
    }
}

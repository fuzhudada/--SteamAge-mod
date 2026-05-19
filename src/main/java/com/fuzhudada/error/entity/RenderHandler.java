package com.fuzhudada.error.entity;

import com.fuzhudada.error.entity.creatures.EntityYunShi;
import com.fuzhudada.error.entity.model.*;
import com.fuzhudada.error.entity.projectiles.*;
import com.fuzhudada.error.entity.weapon.*;
import com.fuzhudada.error.item.ModItems;
import its_meow.betteranimalsplus_sa.client.renderer.entity.RenderLammergeier;
import its_meow.betteranimalsplus_sa.client.renderer.entity.RenderMoose;
import its_meow.betteranimalsplus_sa.common.entity.EntityLammergeier;
import its_meow.betteranimalsplus_sa.common.entity.EntityMoose;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;


public class RenderHandler {

    public static void registerEntityRenders(){

        RenderingRegistry.registerEntityRenderingHandler(EntityYunShi.class, new IRenderFactory<EntityYunShi>()
        {
            @Override
            public Render<? super EntityYunShi> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN1,Minecraft.getMinecraft().getRenderItem(),1,1,1);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityZhaDan.class, new IRenderFactory<EntityZhaDan>()
        {
            @Override
            public Render<? super EntityZhaDan> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZHA_DAN,Minecraft.getMinecraft().getRenderItem(),1,1,1);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityStone.class, new IRenderFactory<EntityStone>()
        {
            @Override
            public Render<? super EntityStone> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, Item.getItemFromBlock(Blocks.COBBLESTONE),Minecraft.getMinecraft().getRenderItem(),10,10,10);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityHuoYanZiDan.class, new IRenderFactory<EntityHuoYanZiDan>()
        {
            @Override
            public Render<? super EntityHuoYanZiDan> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN1_HUO_YAN,Minecraft.getMinecraft().getRenderItem(),1,1,1);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityPoJiaDan.class, new IRenderFactory<EntityPoJiaDan>()
        {
            @Override
            public Render<? super EntityPoJiaDan> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN2_PO_JIA,Minecraft.getMinecraft().getRenderItem(),1,1,1);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityShuangZi.class, new IRenderFactory<EntityShuangZi>()
        {
            @Override
            public Render<? super EntityShuangZi> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN2_PO_JIA,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityShiMoXiang.class, new IRenderFactory<EntityShiMoXiang>()
        {
            @Override
            public Render<? super EntityShiMoXiang> createRenderFor(RenderManager manager)
            {
                return new RenderShiMoXiang(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityYuDeng.class, new IRenderFactory<EntityYuDeng>()
        {
            @Override
            public Render<? super EntityYuDeng> createRenderFor(RenderManager manager)
            {
                return new RenderYuDeng(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityDaPao.class, new IRenderFactory<EntityDaPao>()
        {
            @Override
            public Render<? super EntityDaPao> createRenderFor(RenderManager manager)
            {
                return new RenderDaPao(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityGongZhengZhiCi.class, new IRenderFactory<EntityGongZhengZhiCi>()
        {
            @Override
            public Render<? super EntityGongZhengZhiCi> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN2_PO_JIA,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityShouXuanZheZhiBing.class, new IRenderFactory<EntityShouXuanZheZhiBing>()
        {
            @Override
            public Render<? super EntityShouXuanZheZhiBing> createRenderFor(RenderManager manager)
            {
                return new RenderShouXuanZheZhiBing(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityZhengQiChe.class, new IRenderFactory<EntityZhengQiChe>()
        {
            @Override
            public Render<? super EntityZhengQiChe> createRenderFor(RenderManager manager)
            {
                return new RenderZhengQiChe(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityJianZuo.class, new IRenderFactory<EntityJianZuo>()
        {
            @Override
            public Render<? super EntityJianZuo> createRenderFor(RenderManager manager)
            {
                return new RenderJianZuo(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityNuMu.class, new IRenderFactory<EntityNuMu>()
        {
            @Override
            public Render<? super EntityNuMu> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.JI_GUANG,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityFanSheJing.class, new IRenderFactory<EntityFanSheJing>()
        {
            @Override
            public Render<? super EntityFanSheJing> createRenderFor(RenderManager manager)
            {
                return new RenderFanSheJing(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityFeiDao.class, new IRenderFactory<EntityFeiDao>()
        {
            @Override
            public Render<? super EntityFeiDao> createRenderFor(RenderManager manager)
            {
                return new RenderFeiDao<>(manager, ModItems.FEI_DAO,Minecraft.getMinecraft().getRenderItem(),1,1,1);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityCuiDuDan.class, new IRenderFactory<EntityCuiDuDan>()
        {
            @Override
            public Render<? super EntityCuiDuDan> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN3_CUI_DU,Minecraft.getMinecraft().getRenderItem(),1,1,1);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityXianDan.class, new IRenderFactory<EntityXianDan>()
        {
            @Override
            public Render<? super EntityXianDan> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN3_CUI_DU,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityDiCi.class, new IRenderFactory<EntityDiCi>()
        {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                return new RenderDiCi(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityFlower.class, new IRenderFactory<EntityFlower>()
        {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                return new RenderFlower(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, new IRenderFactory<EntityBomb>()
        {
            @Override
            public Render<? super EntityBomb> createRenderFor(RenderManager manager)
            {
                return new RenderBomb(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityWuZuoBianTa.class, new IRenderFactory<EntityWuZuoBianTa>()
        {
            @Override
            public Render<? super EntityWuZuoBianTa> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN3_CUI_DU,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityHuoLong.class, new IRenderFactory<EntityHuoLong>()
        {
            @Override
            public Render<? super EntityHuoLong> createRenderFor(RenderManager manager)
            {
                return new RenderShouXuanZheZhiBing(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntitySmallFlower.class, new IRenderFactory<EntitySmallFlower>()
        {
            @Override
            public Render<? super EntitySmallFlower> createRenderFor(RenderManager manager)
            {
                return new RenderSmallFlower(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityWind.class, new IRenderFactory<EntityWind>()
        {
            @Override
            public Render<? super EntityWind> createRenderFor(RenderManager manager)
            {
                return new RenderShouXuanZheZhiBing(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityWindWand.class, new IRenderFactory<EntityWindWand>()
        {
            @Override
            public Render<? super EntityWindWand> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN3_CUI_DU,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityDanJiaQiang.class, new IRenderFactory<EntityDanJiaQiang>()
        {
            @Override
            public Render<? super EntityDanJiaQiang> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN2_PO_JIA,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityZhongDanPao.class, new IRenderFactory<EntityZhongDanPao>()
        {
            @Override
            public Render<? super EntityZhongDanPao> createRenderFor(RenderManager manager)
            {
                return new RenderZhongDanPao(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityHuangJinJian.class, new IRenderFactory<EntityHuangJinJian>()
        {
            @Override
            public Render<? super EntityHuangJinJian> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN2_PO_JIA,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityTeXiao.class, new IRenderFactory<EntityTeXiao>()
        {
            @Override
            public Render<? super EntityTeXiao> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN2_PO_JIA,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityBombFruit.class, new IRenderFactory<EntityBombFruit>()
        {
            @Override
            public Render<? super EntityBombFruit> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.BOMB_FRUIT,Minecraft.getMinecraft().getRenderItem(),1,1,1);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityTank.class, new IRenderFactory<EntityTank>()
        {
            @Override
            public Render<? super EntityTank> createRenderFor(RenderManager manager)
            {
                return new RenderTank(manager,3);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityAntiTankLandmine.class, new IRenderFactory<EntityAntiTankLandmine>()
        {
            @Override
            public Render<? super EntityAntiTankLandmine> createRenderFor(RenderManager manager)
            {
                return new RenderAntiTankLandmine(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityGeneralProjectiles.class, new IRenderFactory<EntityGeneralProjectiles>()
        {
            @Override
            public Render<? super EntityGeneralProjectiles> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN2_PO_JIA,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityLieFengQiQiang.class, new IRenderFactory<EntityLieFengQiQiang>()
        {
            @Override
            public Render<? super EntityLieFengQiQiang> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN3_CUI_DU,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityQiShiDao.class, new IRenderFactory<EntityQiShiDao>()
        {
            @Override
            public Render<? super EntityQiShiDao> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN2_PO_JIA,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityGunCarrier.class, new IRenderFactory<EntityGunCarrier>()
        {
            @Override
            public Render<? super EntityGunCarrier> createRenderFor(RenderManager manager)
            {
                return new RenderGunCarrier(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityGunCarrier1.class, new IRenderFactory<EntityGunCarrier1>()
        {
            @Override
            public Render<? super EntityGunCarrier1> createRenderFor(RenderManager manager)
            {
                return new RenderGunCarrier1(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntitySteamGolem1.class, new IRenderFactory<EntitySteamGolem1>()
        {
            @Override
            public Render<? super EntitySteamGolem1> createRenderFor(RenderManager manager)
            {
                return new RenderSteamGolem1(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntitySteamGolem2.class, new IRenderFactory<EntitySteamGolem2>()
        {
            @Override
            public Render<? super EntitySteamGolem2> createRenderFor(RenderManager manager)
            {
                return new RenderSteamGolem2(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntitySteamGolem3.class, new IRenderFactory<EntitySteamGolem3>()
        {
            @Override
            public Render<? super EntitySteamGolem3> createRenderFor(RenderManager manager)
            {
                return new RenderSteamGolem3(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityZhanFengDaJian.class, new IRenderFactory<EntityZhanFengDaJian>()
        {
            @Override
            public Render<? super EntityZhanFengDaJian> createRenderFor(RenderManager manager)
            {
                return new RenderShouXuanZheZhiBing(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntitySandCannon.class, new IRenderFactory<EntitySandCannon>()
        {
            @Override
            public Render<? super EntitySandCannon> createRenderFor(RenderManager manager)
            {
                return new RenderSandCannon<>(manager, Item.getItemFromBlock(Blocks.SAND),Minecraft.getMinecraft().getRenderItem());
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityPoison.class, new IRenderFactory<EntityPoison>()
        {
            @Override
            public Render<? super EntityPoison> createRenderFor(RenderManager manager)
            {
                return new RenderShouXuanZheZhiBing(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityFu.class, new IRenderFactory<EntityFu>()
        {
            @Override
            public Render<? super EntityFu> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.FU,Minecraft.getMinecraft().getRenderItem(),1,1,1);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityGatling.class, new IRenderFactory<EntityGatling>()
        {
            @Override
            public Render<? super EntityGatling> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN2_PO_JIA,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntitySandGiant.class, new IRenderFactory<EntitySandGiant>()
        {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                return new RenderSandGiant(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityBloodyWind.class, new IRenderFactory<EntityBloodyWind>()
        {
            @Override
            public Render<? super EntityBloodyWind> createRenderFor(RenderManager manager)
            {
                return new RenderShouXuanZheZhiBing(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityModArt.class, new IRenderFactory<EntityModArt>()
        {
            @Override
            public Render<? super EntityModArt> createRenderFor(RenderManager manager)
            {
                return new RenderModArt(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityArrowPig.class, new IRenderFactory<EntityArrowPig>()
        {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                return new RenderArrowPig(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityLammergeier.class, new IRenderFactory<EntityLammergeier>()
        {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                return new RenderLammergeier(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityFeather.class, new IRenderFactory<EntityFeather>()
        {
            @Override
            public Render<? super EntityFeather> createRenderFor(RenderManager manager)
            {
                return new RenderFeiDao<>(manager, ModItems.GOLDEN_FEATHER,Minecraft.getMinecraft().getRenderItem(),1,1,1);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityCrystalShield.class, new IRenderFactory<EntityCrystalShield>()
        {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                return new RenderCrystalShield(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityBook.class, new IRenderFactory<EntityBook>()
        {
            @Override
            public Render<? super EntityBook> createRenderFor(RenderManager manager)
            {
                return new RenderBook(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityBookOpen.class, new IRenderFactory<EntityBookOpen>()
        {
            @Override
            public Render<? super EntityBookOpen> createRenderFor(RenderManager manager)
            {
                return new RenderBookOpen(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityBookClose.class, new IRenderFactory<EntityBookClose>()
        {
            @Override
            public Render<? super EntityBookClose> createRenderFor(RenderManager manager)
            {
                return new RenderBookClose(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityNaturalSoul.class, new IRenderFactory<EntityNaturalSoul>()
        {
            @Override
            public Render<? super EntityNaturalSoul> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.ZI_DAN1_HUO_YAN,Minecraft.getMinecraft().getRenderItem(),0,0,0);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityWindCharge.class, new IRenderFactory<EntityWindCharge>()
        {
            @Override
            public Render<? super EntityWindCharge> createRenderFor(RenderManager manager)
            {
                return new RenderYunShi<>(manager, ModItems.WIND_CHARGE,Minecraft.getMinecraft().getRenderItem(),1,1,1);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityStormSword.class, new IRenderFactory<EntityStormSword>()
        {
            @Override
            public Render<? super EntityStormSword> createRenderFor(RenderManager manager)
            {
                return new RenderShouXuanZheZhiBing(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityLightningCow.class, new IRenderFactory<EntityLightningCow>()
        {
            @Override
            public Render<? super EntityLightningCow> createRenderFor(RenderManager manager)
            {
                return new RenderLightningCow(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityLightningCowHorn.class, new IRenderFactory<EntityLightningCowHorn>()
        {
            @Override
            public Render<? super EntityLightningCowHorn> createRenderFor(RenderManager manager)
            {
                return new RenderLightningCowHorn(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityMoose.class, new IRenderFactory<EntityMoose>()
        {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                return new RenderMoose(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityWhiteSnackWand.class, new IRenderFactory<EntityWhiteSnackWand>()
        {
            @Override
            public Render<? super EntityWhiteSnackWand> createRenderFor(RenderManager manager)
            {
                return new RenderShouXuanZheZhiBing(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityFireLantern.class, new IRenderFactory<EntityFireLantern>()
        {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                return new RenderFireLantern(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityElephant.class, new IRenderFactory<EntityElephant>()
        {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                return new RenderElephant(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityRiderElephant.class, new IRenderFactory<EntityRiderElephant>()
        {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                return new RenderRiderElephant(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityFireHorse.class, new IRenderFactory<EntityFireHorse>()
        {
            @Override
            public Render createRenderFor(RenderManager manager)
            {
                return new RenderFireHorse(manager);
            }
        });
    }
}

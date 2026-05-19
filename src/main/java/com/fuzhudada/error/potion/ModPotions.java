package com.somebody.idlframewok.potion;

import com.fuzhudada.error.potion.BasePotion;
import com.somebody.idlframewok.IdlFramework;
import com.somebody.idlframewok.util.Reference;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModPotions {

    public static final List<Potion> INSTANCES = new ArrayList<Potion>();

//    public static final PotionDeadly DEADLY = new PotionDeadly(false, 0x333333, "deadly", 0);
//    public static final PotionZenHeart ZEN_HEART = new PotionZenHeart(false, 0xcccc00, "zen_heart", 1);
    public static final Potion CHAO_YONG_ZHI_HU = new BasePotion(false, 0x009900, "chao_yong_zhi_hu",0);
    public static final Potion JI_XING_ZHONG_DU = new BasePotion(true, 0x990000, "ji_xin_zhong_du",1);
    public static final Potion CHONG_NENG = new BasePotion(false, 0x993322, "chong_neng",2);
    public static final Potion KANG_KAI_JI_ANG = new BasePotion(false, 0x0077bb, "kang_kai_ji_ang",3);
    public static final Potion JU_DU_KONG_QI = new BasePotion(true, 0x990000, "ju_du_kong_qi",4);
    public static final Potion RAN_SHAO_KONG_QI = new BasePotion(true, 0x990000, "ran_shao_kong_qi",5);
    public static final Potion WU_ZHI = new BasePotion(true, 0xffffff, "wu_zhi",6);
    public static final Potion ZHI_TI_PO_HUAI = new BasePotion(true, 0x991100, "zhi_ti_po_huai",7);
    public static final Potion CEN_DIAN = new BasePotion(false, 0x11af33, "cen_dian",9);
    public static final Potion PO_ZHAN = new BasePotion(true, 0x000000, "po_zhan",10);
    public static final Potion XIN_FEN = new BasePotion(false, 0x772233, "xin_fen",12);
    public static final Potion JIAN_REN_FENG_BAO = new BasePotion(false, 0xbbbbbb, "jian_ren_feng_bao",13);
    public static final Potion QU_SAN_XIANG = new BasePotion(false, 0xaaaaaa, "qu_san_xiang",14);
    public static final Potion MIN_XIANG_XIANG = new BasePotion(false, 0xaaaaaa, "min_xiang_xiang",14);
    public static final Potion XIU_BAI_XIANG = new BasePotion(false, 0xaaaaaa, "xiu_bai_xiang",14);
    public static final Potion JIE_YAN_XIANG = new BasePotion(false, 0xaaaaaa, "jie_yan_xiang",14);
    public static final Potion XU_RUO = new BasePotion(true, 0x881122, "xu_ruo",15);
    public static final Potion ZAI_SHENG_TING_ZHI = new BasePotion(true, 0x444444, "zai_sheng_ting_zhi",16);
    public static final Potion MO_LI_QIN_HE = new BasePotion(false, 0xbf1e9c, "mo_li_qin_he",17);
    public static final Potion SU_FU = new BasePotion(true, 0xc3c3c3, "su_fu",18);
    public static final Potion XIAN_XIN = new BasePotion(true, 0x000033, "xian_xin",19);
    public static final Potion JIN_SHI = new BasePotion(false, 0x550000, "jin_shi",20);
    public static final Potion MO_LI_PIN_JI = new BasePotion(true, new Color(76 , 79 , 82).getRGB(), "mo_li_pin_ji",21);
    public static final Potion FEI_XING = new BasePotion(false, new Color(200,200,200).getRGB(), "fei_xing",22);

    @Nullable
    private static Potion getRegisteredMobEffect(String id)
    {
        Potion potion = Potion.REGISTRY.getObject(new ResourceLocation(id));

        if (potion == null)
        {
            throw new IllegalStateException("Invalid MobEffect requested: " + id);
        }
        else
        {
            return potion;
        }
    }

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> evt)
    {
        //VIRUS_ONE.tuples.add(new EffectTuple(0.2f, MobEffects.NAUSEA, 100));
        INSTANCES.add(CHAO_YONG_ZHI_HU);
        INSTANCES.add(JI_XING_ZHONG_DU);
        INSTANCES.add(CHONG_NENG);
        INSTANCES.add(KANG_KAI_JI_ANG);
        INSTANCES.add(JU_DU_KONG_QI);
        INSTANCES.add(RAN_SHAO_KONG_QI);
        INSTANCES.add(WU_ZHI);
        INSTANCES.add(ZHI_TI_PO_HUAI);
        INSTANCES.add(CEN_DIAN);
        INSTANCES.add(PO_ZHAN);
        INSTANCES.add(XIN_FEN);
        INSTANCES.add(JIAN_REN_FENG_BAO);
        INSTANCES.add(QU_SAN_XIANG);
        INSTANCES.add(MIN_XIANG_XIANG);
        INSTANCES.add(XIU_BAI_XIANG);
        INSTANCES.add(JIE_YAN_XIANG);
        INSTANCES.add(XU_RUO);
        INSTANCES.add(ZAI_SHENG_TING_ZHI);
        INSTANCES.add(MO_LI_QIN_HE);
        INSTANCES.add(SU_FU);
        INSTANCES.add(XIAN_XIN);
        INSTANCES.add(JIN_SHI);
        INSTANCES.add(MO_LI_PIN_JI);
        INSTANCES.add(FEI_XING);
        evt.getRegistry().registerAll(INSTANCES.toArray(new Potion[0]));
        IdlFramework.LogWarning("registered %d potion", INSTANCES.size());
    }
}

package com.somebody.idlframewok;

import com.fuzhudada.error.ClientForgeHookHandler;
import com.fuzhudada.error.ForgeHookHandler;
import com.fuzhudada.error.assassin.assaultManager;
import com.fuzhudada.error.key.KeyBoardManager;
import com.fuzhudada.error.packet.PacketHandler;
import com.fuzhudada.error.particle.ModParticleHandler;
import com.fuzhudada.error.init.ModRecipes;
import com.fuzhudada.error.init.RegistryHandler;
import com.somebody.idlframewok.meta.MetaUtil;
import com.somebody.idlframewok.proxy.ClientProxy;
import com.somebody.idlframewok.proxy.ProxyBase;
import com.somebody.idlframewok.util.CommonDef;
import com.somebody.idlframewok.util.Reference;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import static com.fuzhudada.error.init.RegistryHandler.initRegistries;

//To let the player be a traveling god who plays yin-yang magic.

@Mod(modid = IdlFramework.MODID, name = IdlFramework.NAME, version = IdlFramework.VERSION)//dependencies = "required-after:Forge@[14.23.5.2705,)"
public class IdlFramework {
    public static final String MODID = "error";
    public static final String NAME = "error";
    public static final String VERSION = "0.1.101";

    public static Logger logger;

    public static final boolean SHOW_WARN = true;

    public static final assaultManager assaultManager = new assaultManager();

    public static ForgeHookHandler forgeHookHandler;
    public static ClientForgeHookHandler clientForgeHookHandler;

    @Mod.Instance
    public static IdlFramework instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static ProxyBase proxy;

    static { FluidRegistry.enableUniversalBucket(); }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PacketHandler.init();
        logger = event.getModLog();

        if (MODID.equals("untitled"))
        {
            logger.error("Please change your mod id in the main class.");
            
        }

        if (Reference.CLIENT_PROXY_CLASS.indexOf("somebody.idlframewok.proxy.ClientProxy") > 0)
        {
            logger.warn("Have you changed your package name to author and modname?");
            
        }

        if(forgeHookHandler == null) forgeHookHandler=new ForgeHookHandler();
        RegistryHandler.preInitRegistries(event);
    }

    @EventHandler
    public void Init(FMLInitializationEvent event) {
        ModRecipes.Init();
        GeckoLib.initialize();
        RegisterTileEntity();
        initRegistries(event);

        if(!proxy.isServer()){
            KeyBoardManager.init();
            NetworkRegistry.INSTANCE.registerGuiHandler(this, new ClientProxy());
            if(clientForgeHookHandler == null) clientForgeHookHandler=new ClientForgeHookHandler();
        }

		LogWarning("%s has finished its initializations", MODID);
	}

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // Moved Spawning registry to last since forge doesn't auto-generate sub
        // "M' biomes until late

        MetaUtil.isIDLLoaded = Loader.isModLoaded("idealland");
        MetaUtil.isIRRLoaded = Loader.isModLoaded("itemrender");
        MetaUtil.isLoaded_TiC = Loader.isModLoaded("tconstruct");
        MetaUtil.isLoaded_Slashblade = Loader.isModLoaded("flammpfeil.slashblade");
        MetaUtil.isLoaded_Botania = Loader.isModLoaded("botania");
        MetaUtil.isLoaded_DWeapon = Loader.isModLoaded("dweapon");
        MetaUtil.isLoaded_AOA3 = Loader.isModLoaded(CommonDef.MOD_NAME_AOA3);
        MetaUtil.isLoaded_GC = Loader.isModLoaded("galacticraftcore");
        MetaUtil.isLoaded_Taoism = Loader.isModLoaded("taoism");
        MetaUtil.isLoaded_GOG = Loader.isModLoaded(CommonDef.MOD_NAME_GOG);

        TrashTalking();

        if(!proxy.isServer()){
            ModParticleHandler.init();
        }

        RegistryHandler.postInitReg();
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries(event);
    }


    private void TrashTalking() {
        if (MetaUtil.isIDLLoaded)
        {
            IdlFramework.Log("[Idealland Framework] Bow to Idealland.");
        }
        else {
            IdlFramework.Log("[Idealland Framework] Made with Idealland Framework.");
        }
    }

    private static void RegisterTileEntity() {
//        GameRegistry.registerTileEntity(TileEntityDeBoomOrb.class, new ResourceLocation(MODID, "deboom_orb_basic"));

        //GameRegistry.registerTileEntity(TileEntityBuilderFarm.class, new ResourceLocation(MODID, "builder_farm_basic"));
        //GameRegistry.registerTileEntity(TileEntityBuilderOne.class, new ResourceLocation(MODID, "builder.builder_one"));
    }

    public static void LogWarning(String str, Object... args) {
        if (SHOW_WARN) {
            logger.warn(String.format(str, args));
        }
    }

    public static void LogWarning(String str) {
        if (SHOW_WARN) {
            logger.warn(str);
        }
    }

    public static void Log(String str) {
//        if (ModConfig.GeneralConf.LOG_ON)
//        {
        logger.info(str);
//        }
    }

    public static void Log(String str, Object... args) {
//        if (ModConfig.GeneralConf.LOG_ON)
//        {
        logger.info(String.format(str, args));
//        }
    }
}
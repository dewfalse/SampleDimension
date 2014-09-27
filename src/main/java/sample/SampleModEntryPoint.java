package sample;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.DimensionManager;

@Mod(modid = SampleModEntryPoint.MODID, name = SampleModEntryPoint.MODNAME, version = SampleModEntryPoint.VERSION)
public class SampleModEntryPoint {

    public static final String MODID = "SampleDimension";
    public static final String MODNAME = "SampleDimension";
    public static final String VERSION = "1.0.0";

    // 独自ディメンションのID
    public static int dimensionID = -2;

    // 独自ディメンションへのポータルブロック
    public static Block portal;

    // 独自ディメンション用のワールドプロバイダ
    public static int providerType = -2;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // 独自ディメンションへのポータルブロックを追加
        portal = new BlockSamplePortal(Material.portal).setBlockName("SampleDimension:portal").setCreativeTab(CreativeTabs.tabMisc);
        GameRegistry.registerBlock(portal, "portal");

        // 独自ディメンション用のワールドプロバイダを登録
        DimensionManager.registerProviderType(providerType, WorldProviderSample.class, false);

        // 独自ディメンションを登録
        DimensionManager.registerDimension(dimensionID, providerType);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}

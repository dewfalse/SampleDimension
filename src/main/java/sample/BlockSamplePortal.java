package sample;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockSamplePortal extends Block {

    protected BlockSamplePortal(Material p_i45394_1_) {
        super(p_i45394_1_);
    }

    @Override
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        // 現在地が独自ディメンション以外
        if (p_149727_1_.provider.dimensionId != SampleModEntryPoint.dimensionID) {
            if (p_149727_5_ instanceof EntityPlayerMP) {
                // １行で書くと長過ぎるので一旦ローカル変数に格納
                EntityPlayerMP entityPlayerMP = (EntityPlayerMP) p_149727_5_;
                ServerConfigurationManager serverConfigurationManager = entityPlayerMP.mcServer.getConfigurationManager();
                WorldServer worldServer = entityPlayerMP.mcServer.worldServerForDimension(SampleModEntryPoint.dimensionID);

                // 移動後にネザーポータルが作成されるので即座に再送還されないように
                entityPlayerMP.timeUntilPortal = 10;
                entityPlayerMP.setInPortal();
                //entityPlayerMP.getPortalCooldown();

                // 独自ディメンションに移動する
                serverConfigurationManager.transferPlayerToDimension(entityPlayerMP, SampleModEntryPoint.dimensionID, new TeleporterSample(worldServer));
            }
        }
        // 現在地が独自ディメンション
        else {
            if (p_149727_5_ instanceof EntityPlayerMP) {
                // １行で書くと長過ぎるので一旦ローカル変数に格納
                EntityPlayerMP entityPlayerMP = (EntityPlayerMP) p_149727_5_;
                ServerConfigurationManager serverConfigurationManager = entityPlayerMP.mcServer.getConfigurationManager();

                entityPlayerMP.setInPortal();
                //entityPlayerMP.timeUntilPortal = 10;

                // 独自ディメンションからはオーバーワールドに移動
                serverConfigurationManager.transferPlayerToDimension(entityPlayerMP, 0);
            }
        }
        return true;
    }
}

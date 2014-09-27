package sample;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderSample extends WorldProvider {

    @Override
    public String getDimensionName() {
        return "Sample";
    }

    // 独自のワールドタイプやワールドチャンクマネージャーを設定
    @Override
    protected void registerWorldChunkManager() {
        worldObj.getWorldInfo().setTerrainType(WorldTypeSample.worldTypeSample);
        worldChunkMgr = new WorldChunkManagerSample(worldObj);
        setDimension(SampleModEntryPoint.dimensionID);
        hasNoSky = false;
    }

    // チャンク生成は独自のチャンクプロバイダが担当する
    @Override
    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderSample(worldObj, worldObj.getSeed(), true);
    }
}

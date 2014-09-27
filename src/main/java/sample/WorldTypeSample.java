package sample;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldTypeSample extends WorldType {

    public static WorldType worldTypeSample = new WorldTypeSample("Sample");

    private WorldTypeSample(String name) {
        super(name);
    }

    @Override
    public WorldChunkManager getChunkManager(World world) {
        return new WorldChunkManagerSample(world);
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions) {
        return new ChunkProviderSample(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }
}

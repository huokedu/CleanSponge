package org.spongepowered.clean.world.gen.base;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBlockVolume;
import org.spongepowered.api.world.gen.GenerationPopulator;

public class FlatGenerator implements GenerationPopulator {

    public FlatGenerator() {

    }

    private void fillLayer(MutableBlockVolume buffer, int y, BlockState block) {
        int minx = buffer.getBlockMin().getX();
        int maxx = buffer.getBlockMax().getX();
        int minz = buffer.getBlockMin().getZ();
        int maxz = buffer.getBlockMax().getZ();
        for (int x = minx; x <= maxx; x++) {
            for (int z = minz; z <= maxz; z++) {
                buffer.setBlock(x, y, z, block, null);
            }
        }
    }

    @Override
    public void populate(World world, MutableBlockVolume buffer, ImmutableBiomeVolume biomes) {
        fillLayer(buffer, 0, BlockTypes.BEDROCK.getDefaultState());
        for (int y = 1; y < 4; y++) {
            fillLayer(buffer, y, BlockTypes.DIRT.getDefaultState());
        }
        fillLayer(buffer, 4, BlockTypes.GRASS.getDefaultState());
    }

}

package org.spongepowered.clean.world.buffer;

import org.spongepowered.api.util.DiscreteTransform3;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.MutableBiomeVolume;
import org.spongepowered.api.world.extent.StorageType;
import org.spongepowered.api.world.extent.UnmodifiableBiomeVolume;
import org.spongepowered.api.world.extent.worker.MutableBiomeVolumeWorker;
import org.spongepowered.clean.world.biome.SBiomeType;

import com.flowpowered.math.vector.Vector3i;

public class SMutableBiomeVolume extends AbstractVolume implements MutableBiomeVolume {

    private final byte[] data;

    public SMutableBiomeVolume(Vector3i min, Vector3i size) {
        super(min, size);
        this.data = new byte[size.getX() * size.getY() * size.getZ()];
    }

    @Override
    public Vector3i getBiomeMin() {
        return this.min;
    }

    @Override
    public Vector3i getBiomeMax() {
        return this.max;
    }

    @Override
    public Vector3i getBiomeSize() {
        return this.size;
    }

    @Override
    public boolean containsBiome(int x, int y, int z) {
        return contains(x, y, z);
    }

    @Override
    public BiomeType getBiome(int x, int y, int z) {
        int id = this.data[getIndex(x, y, z)] & 0xFF;
        BiomeType type = SBiomeType.getBiome(id);
        return type;
    }

    @Override
    public void setBiome(int x, int y, int z, BiomeType biome) {
        this.data[getIndex(x, y, z)] = (byte) ((SBiomeType) biome).getBiomeId();
    }

    @Override
    public UnmodifiableBiomeVolume getUnmodifiableBiomeView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableBiomeVolume getBiomeCopy(StorageType type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImmutableBiomeVolume getImmutableBiomeCopy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableBiomeVolume getBiomeView(Vector3i newMin, Vector3i newMax) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableBiomeVolume getBiomeView(DiscreteTransform3 transform) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableBiomeVolumeWorker<? extends MutableBiomeVolume> getBiomeWorker() {
        // TODO Auto-generated method stub
        return null;
    }

}

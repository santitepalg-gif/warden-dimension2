package com.wardendimension;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GlowcapBlock extends BushBlock {
    // Mismo conjunto que usa el worldgen (data/warden_dimension/tags/blocks/glowcap_placeable.json)
    public static final TagKey<Block> PLACEABLE_ON =
            TagKey.create(Registries.BLOCK, new ResourceLocation(WardenDimension.MODID, "glowcap_placeable"));
    private static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 12, 14);

    public GlowcapBlock(Properties props) { super(props); }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(PLACEABLE_ON);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext ctx) { return SHAPE; }

    // esporas que flotan de vez en cuando
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(12) == 0) {
            double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.6;
            double y = pos.getY() + 0.6 + random.nextDouble() * 0.5;
            double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.6;
            level.addParticle(ParticleTypes.GLOW, x, y, z, 0.0, 0.02, 0.0);
        }
    }
}

package zhang.eks.worldgen;

import java.util.Random;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;

public class LumberjackHouse extends WorldGenerator
{
    private static final ResourceLocation structure = new ResourceLocation("eks:house/ekscbhouse");
    private static final ResourceLocation[] sgen = new ResourceLocation[] {structure};

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        Random random = worldIn.getChunkFromChunkCoords(position.getX(), position.getZ()).getRandomWithSeed(987234911L);
        MinecraftServer minecraftserver = worldIn.getMinecraftServer();
        Rotation[] arotation = Rotation.values();
        Rotation rotation = arotation[random.nextInt(arotation.length)];
        int i = random.nextInt(sgen.length);
        TemplateManager templatemanager = worldIn.getSaveHandler().getStructureTemplateManager();
        Template template = templatemanager.getTemplate(minecraftserver, sgen[i]);
        ChunkPos chunkpos = new ChunkPos(position);
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(chunkpos.getXStart(), 0, chunkpos.getZStart(), chunkpos.getXEnd(), 256, chunkpos.getZEnd());
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setBoundingBox(structureboundingbox).func_189950_a(random);
        BlockPos blockpos = template.transformedSize(rotation);
        int j = random.nextInt(16 - blockpos.getX());
        int k = random.nextInt(16 - blockpos.getZ());
        int l = 256;

        for (int i1 = 0; i1 < blockpos.getX(); ++i1)
        {
            for (int j1 = 0; j1 < blockpos.getX(); ++j1)
            {
                l = Math.min(l, worldIn.func_189649_b(position.getX() + i1 + j, position.getZ() + j1 + k));
            }
        }

        int k1 = Math.max(l - 15 - random.nextInt(10), 10);
        BlockPos blockpos1 = template.func_189961_a(position.add(j, k1, k), Mirror.NONE, rotation);
        placementsettings.func_189946_a(0.9F);
        template.func_189962_a(worldIn, blockpos1, placementsettings, 4);
        return true;
    }
}

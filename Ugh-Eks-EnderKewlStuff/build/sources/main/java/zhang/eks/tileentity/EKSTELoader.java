package zhang.eks.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public final class EKSTELoader {

	public static void init() {
        GameRegistry.registerTileEntity(tileentityBouncePad.class, "bounce_pad_tile_entity");
        GameRegistry.registerTileEntity(TEMaglev.class, "maglev_tile_entity");
    }
	
}

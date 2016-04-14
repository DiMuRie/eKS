package zhang.eks.Entity;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import zhang.eks.EnderKewlStuff;
import zhang.eks.LibEntityNames;

public final class EKSEntityLoader {

	public static void init() {
		int id = 0;

		EntityRegistry.registerModEntity(NonMovingKewlEntityItem.class, LibEntityNames.NMLEI, id++, EnderKewlStuff.instance, 64, 10, false);

	}
	
}

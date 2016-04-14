package zhang.eks.tileentity;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zhang.eks.KewlBlocks.Elevator;

public class tileentityBouncePad extends TileEntity implements ITickable {

	@Override
	public void update() {
		if(!this.worldObj.isRemote)
		{
		//	List<Entity> interestingItems = worldObj.getEntitiesWithinAABB(Entity.class, getAABB().expand(3, 3, 3), entitySelector);
			List<Entity> interestingItems = worldObj.getEntitiesWithinAABB(Entity.class, Elevator.FULL_BLOCK_AABB.expand(3, 3, 3));
		for (Entity entity : interestingItems) {
			double dx = (pos.getX() + 0.5D - entity.posX);
			double dy = (pos.getY() + 0.5D - entity.posY);
			double dz = (pos.getZ() + 0.5D - entity.posZ);

			double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
			if (distance < 1.1) {
			} else {
				double var11 = 1.0 - distance / 15.0;

				if (var11 > 0.0D) {
					var11 *= var11;
					entity.motionX += dx / distance * var11 * 0.05;
					entity.motionY += dy / distance * var11 * 0.2;
					entity.motionZ += dz / distance * var11 * 0.05;
				}
			}
			}
		}
	}
}
		
    
	


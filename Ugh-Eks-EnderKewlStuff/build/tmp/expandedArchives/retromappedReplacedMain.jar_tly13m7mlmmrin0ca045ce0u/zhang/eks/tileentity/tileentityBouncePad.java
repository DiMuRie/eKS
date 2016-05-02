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
	public void func_73660_a() {
		if(!this.field_145850_b.field_72995_K)
		{
		//	List<Entity> interestingItems = worldObj.getEntitiesWithinAABB(Entity.class, getAABB().expand(3, 3, 3), entitySelector);
			List<Entity> interestingItems = field_145850_b.func_72872_a(Entity.class, Elevator.field_185505_j.func_72314_b(3, 3, 3));
		for (Entity entity : interestingItems) {
			double dx = (field_174879_c.func_177958_n() + 0.5D - entity.field_70165_t);
			double dy = (field_174879_c.func_177956_o() + 0.5D - entity.field_70163_u);
			double dz = (field_174879_c.func_177952_p() + 0.5D - entity.field_70161_v);

			double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
			if (distance < 1.1) {
			} else {
				double var11 = 1.0 - distance / 15.0;

				if (var11 > 0.0D) {
					var11 *= var11;
					entity.field_70159_w += dx / distance * var11 * 0.05;
					entity.field_70181_x += dy / distance * var11 * 0.2;
					entity.field_70179_y += dz / distance * var11 * 0.05;
				}
			}
			}
		}
	}
}
		
    
	


package zhang.eks.Entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class NonMovingKewlEntityItem extends EntityItem {

	public NonMovingKewlEntityItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack){

		super(par1World, par2, par4, par6, par8ItemStack);
		}

		public NonMovingKewlEntityItem(World par1world){

		super(par1world);
		}

		protected void func_70081_e(int par1)
		{
		}

		public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
		{
		         return false;
		}
		}

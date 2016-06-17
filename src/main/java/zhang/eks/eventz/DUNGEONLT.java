package zhang.eks.eventz;

import net.minecraft.init.Items;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetDamage;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DUNGEONLT {

	@SubscribeEvent
	public void onLootTablesLoaded(LootTableLoadEvent event) {
	 
	    if (event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {
	 
	        final LootPool pool2 = event.getTable().getPool("pool2");
	 
	        if (pool2 != null) {
	 
	            // pool2.addEntry(new LootEntryItem(ITEM, WEIGHT, QUALITY, FUNCTIONS, CONDITIONS, NAME));
	            pool2.addEntry(new LootEntryItem(Items.BED, 10, 0, new LootFunction[] {new SetDamage(new LootCondition[0], new RandomValueRange(50, 50))}, new LootCondition[0], "eks:bed"));
	        }
	    }
	}
	
}

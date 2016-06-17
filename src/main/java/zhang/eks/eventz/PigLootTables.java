package zhang.eks.eventz;

import net.minecraft.init.Items;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.KilledByPlayer;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PigLootTables {

	@SubscribeEvent
	public void onLootTablesLoaded (LootTableLoadEvent event) {
	     
	    if (event.getName().equals(LootTableList.ENTITIES_PIG)) {
	         
	        final LootPool main = event.getTable().getPool("main");
	         
	        if (main != null) {
	             
	            // pool2.addEntry(new LootEntryItem(ITEM, WEIGHT, QUALITY, FUNCTIONS, CONDITIONS, NAME));
	            main.addEntry(new LootEntryItem(Items.CARROT, 2, 0, new LootFunction[0], new LootCondition[] { new KilledByPlayer(false)}, "eks:carrot"));
	        }
	    }
	}
	
}

package net.hotline_fishing;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.hotline_fishing.item.HotlineFishingRodItem;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.hotline_fishing.HotlineFishing.hotlineId;
import static net.minecraft.util.registry.Registry.register;

public class HotlineRegistry{
	
	// Everything here is fireproof.
	private static final Item.Settings TABBED = new Item.Settings().group(HotlineFishing.ITEMS).fireproof();
	private static final Item.Settings SINGLE_TABBED = new Item.Settings().group(HotlineFishing.ITEMS).maxCount(1).fireproof();
	
	public static Identifier HOTLINE_FISHING_GAMEPLAY = hotlineId("gameplay/hotline_fishing");
	
	public static Item HOTLINE_FISHING_ROD = new HotlineFishingRodItem(SINGLE_TABBED);
	public static Item LAVA_BOTTLE = new Item(SINGLE_TABBED);
	
	public static Item GOLD_FISH = new Item(TABBED);
	public static Item SCALY_SNAPPER = new Item(TABBED);
	public static Item SULFURIC_SEA_JELLY = new Item(TABBED);
	
	public static StatusEffect HOTLINE_SKILL, GOLDEN_SCENT, SHOCKING_AURA;
	
	public static EntityType<?> HOTLINE_FISHING_BOBBER = FabricEntityTypeBuilder
			.create(SpawnGroup.MISC)
			.disableSaving()
			.disableSummon()
			.dimensions(new EntityDimensions(.25f, .25f, true))
			.trackRangeChunks(4)
			.trackedUpdateRate(5)
			.fireImmune()
			.build();
	
	public static void registerObjects(){
		register(Registry.ITEM, hotlineId("hotline_fishing_rod"), HOTLINE_FISHING_ROD);
		register(Registry.ITEM, hotlineId("lava_bottle"), LAVA_BOTTLE);
		
		register(Registry.ITEM, hotlineId("gold_fish"), GOLD_FISH);
		register(Registry.ITEM, hotlineId("scaly_snapper"), SCALY_SNAPPER);
		register(Registry.ITEM, hotlineId("sulfuric_sea_jelly"), SULFURIC_SEA_JELLY);
		
		register(Registry.ENTITY_TYPE, hotlineId("hotline_fishing_bobber"), HOTLINE_FISHING_BOBBER);
	}
}
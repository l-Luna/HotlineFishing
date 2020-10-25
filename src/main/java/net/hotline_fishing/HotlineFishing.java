package net.hotline_fishing;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class HotlineFishing implements ModInitializer{
	
	public static final String MODID = "hotline_fishing";
	
	public static final ItemGroup ITEMS = FabricItemGroupBuilder
			.create(hotlineId("items"))
			.icon(() -> new ItemStack(HotlineRegistry.HOTLINE_FISHING_ROD))
			.build();
	
	@Override
	public void onInitialize(){
		HotlineRegistry.registerObjects();
	}
	
	public static Identifier hotlineId(String path){
		return new Identifier(MODID, path);
	}
}
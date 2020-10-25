package net.hotline_fishing.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.hotline_fishing.HotlineRegistry;

@Environment(EnvType.CLIENT)
public class ClientInit implements ClientModInitializer{
	
	@Override
	public void onInitializeClient(){
		EntityRendererRegistry.INSTANCE.register(HotlineRegistry.HOTLINE_FISHING_BOBBER, (manager, context) -> new HotlineFishingBobberEntityRenderer(manager));
	}
}
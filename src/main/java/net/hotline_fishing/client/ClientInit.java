package net.hotline_fishing.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.hotline_fishing.HotlineRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ClientInit implements ClientModInitializer{
	
	@Override
	public void onInitializeClient(){
		EntityRendererRegistry.INSTANCE.register(HotlineRegistry.HOTLINE_FISHING_BOBBER, (manager, context) -> new HotlineFishingBobberEntityRenderer(manager));
		
		FabricModelPredicateProviderRegistry.register(HotlineRegistry.HOTLINE_FISHING_ROD, new Identifier("cast"), (stack, world, entity) -> {
			if(entity == null)
				return 0;
			else{
				boolean mainhand = entity.getMainHandStack() == stack;
				boolean offhand = entity.getOffHandStack() == stack;
				if(entity.getMainHandStack().getItem() instanceof FishingRodItem)
					offhand = false;
				
				return (mainhand || offhand) && entity instanceof PlayerEntity && ((PlayerEntity)entity).fishHook != null ? 1 : 0;
			}
		});
	}
}
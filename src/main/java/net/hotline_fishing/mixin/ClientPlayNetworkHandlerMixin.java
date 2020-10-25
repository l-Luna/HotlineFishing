package net.hotline_fishing.mixin;

import net.hotline_fishing.HotlineRegistry;
import net.hotline_fishing.entity.HotlineFishingBobberEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin{
	
	@Shadow private ClientWorld world;
	
	@ModifyVariable(method = "onEntitySpawn",
	                at = @At(value = "STORE", ordinal = 36),
	                ordinal = 0)
	private Entity modifyEntity(Entity whatever, EntitySpawnS2CPacket packet){
		double x = packet.getX();
		double y = packet.getY();
		double z = packet.getZ();
		EntityType<?> type = packet.getEntityTypeId();
		Entity player = world.getEntityById(packet.getEntityData());
		if(player instanceof PlayerEntity && type == HotlineRegistry.HOTLINE_FISHING_BOBBER)
			return new HotlineFishingBobberEntity(world, (PlayerEntity)player, x, y, z);
		return null;
	}
}
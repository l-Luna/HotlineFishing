package net.hotline_fishing.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface EntityAccessor{
	
	@Mutable
	@Accessor
	void setType(EntityType<?> type);
	
	@Accessor
	void setDimensions(EntityDimensions dimensions);
}
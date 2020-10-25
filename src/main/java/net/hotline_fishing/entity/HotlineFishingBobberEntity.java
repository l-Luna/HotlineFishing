package net.hotline_fishing.entity;

import net.hotline_fishing.HotlineRegistry;
import net.hotline_fishing.ducks.FishingBobber;
import net.hotline_fishing.mixin.EntityAccessor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTables;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HotlineFishingBobberEntity extends FishingBobberEntity implements FishingBobber{
	
	// lots of vanilla copying going on
	
	protected HotlineFishingBobberEntity(World world, PlayerEntity owner, int lureLevel, int luckOfTheSeaLevel){
		super(world, owner, lureLevel, luckOfTheSeaLevel);
		((EntityAccessor)this).setType(HotlineRegistry.HOTLINE_FISHING_BOBBER);
		((EntityAccessor)this).setDimensions(HotlineRegistry.HOTLINE_FISHING_BOBBER.getDimensions());
	}
	
	public HotlineFishingBobberEntity(World world, PlayerEntity thrower, double x, double y, double z){
		this(world, thrower, 0, 0);
		this.updatePosition(x, y, z);
		this.prevX = this.getX();
		this.prevY = this.getY();
		this.prevZ = this.getZ();
	}
	
	public HotlineFishingBobberEntity(PlayerEntity thrower, World world, int lureLevel, int luckOfTheSeaLevel){
		this(world, thrower, lureLevel, luckOfTheSeaLevel);
		float f = thrower.pitch;
		float g = thrower.yaw;
		float h = MathHelper.cos(-g * 0.017453292F - 3.1415927F);
		float i = MathHelper.sin(-g * 0.017453292F - 3.1415927F);
		float j = -MathHelper.cos(-f * 0.017453292F);
		float k = MathHelper.sin(-f * 0.017453292F);
		double d = thrower.getX() - (double)i * 0.3D;
		double e = thrower.getEyeY();
		double l = thrower.getZ() - (double)h * 0.3D;
		this.refreshPositionAndAngles(d, e, l, g, f);
		Vec3d vec3d = new Vec3d(-i, MathHelper.clamp(-(k / j), -5.0F, 5.0F), -h);
		double m = vec3d.length();
		vec3d = vec3d.multiply(0.6D / m + 0.5D + this.random.nextGaussian() * 0.0045D, 0.6D / m + 0.5D + this.random.nextGaussian() * 0.0045D, 0.6D / m + 0.5D + this.random.nextGaussian() * 0.0045D);
		this.setVelocity(vec3d);
		this.yaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D);
		this.pitch = (float)(MathHelper.atan2(vec3d.y, MathHelper.sqrt(squaredHorizontalLength(vec3d))) * 57.2957763671875D);
		this.prevYaw = this.yaw;
		this.prevPitch = this.pitch;
	}
	
	public Item rod(){
		return HotlineRegistry.HOTLINE_FISHING_ROD;
	}
	
	public boolean worksInFluid(FluidState state){
		return state.isIn(FluidTags.WATER) || state.isIn(FluidTags.LAVA);
	}
	
	public Identifier tableForFluid(FluidState state){
		return state.isIn(FluidTags.WATER) ? LootTables.FISHING_GAMEPLAY : HotlineRegistry.HOTLINE_FISHING_GAMEPLAY;
	}
	
	public void setFireTicks(int ticks){
		// no-op
	}
}
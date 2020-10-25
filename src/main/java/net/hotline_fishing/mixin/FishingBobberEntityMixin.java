package net.hotline_fishing.mixin;

import net.hotline_fishing.ducks.FishingBobber;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTables;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberEntityMixin extends Entity implements FishingBobber{
	
	public FishingBobberEntityMixin(EntityType<?> type, World world){
		super(type, world);
	}
	
	public Item rod(){
		return Items.FISHING_ROD;
	}
	
	public boolean worksInFluid(FluidState state){
		return state.isIn(FluidTags.WATER);
	}
	
	public Identifier tableForFluid(FluidState fluid){
		return LootTables.FISHING_GAMEPLAY;
	}
	
	@Redirect(method = {"tick", "getPositionType(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/entity/projectile/FishingBobberEntity$PositionType;"},
	          at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/tag/Tag;)Z"))
	private boolean redirectFluidTag(FluidState state, Tag<Fluid> tag){
		return worksInFluid(state);
	}
	
	/**
	 * TODO: replace with ModifyVariable.
	 *
	 * @author Hotline Fishing (TM)
	 */
	@Overwrite
	private boolean removeIfInvalid(PlayerEntity playerEntity){
		ItemStack itemStack = playerEntity.getMainHandStack();
		ItemStack itemStack2 = playerEntity.getOffHandStack();
		boolean bl = itemStack.getItem() == rod();
		boolean bl2 = itemStack2.getItem() == rod();
		if(!playerEntity.removed && playerEntity.isAlive() && (bl || bl2) && this.squaredDistanceTo(playerEntity) <= 1024.0D)
			return false;
		else{
			this.remove();
			return true;
		}
	}
	
	@ModifyArg(method = "use",
	           at = @At(value = "INVOKE", target = "Lnet/minecraft/loot/LootManager;getTable(Lnet/minecraft/util/Identifier;)Lnet/minecraft/loot/LootTable;"))
	private Identifier adjustLootTable(Identifier originalLootTable){
		FluidState state = world.getFluidState(getBlockPos());
		return tableForFluid(state);
	}
}
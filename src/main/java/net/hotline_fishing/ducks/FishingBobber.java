package net.hotline_fishing.ducks;

import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public interface FishingBobber{
	
	Item rod();
	
	boolean worksInFluid(FluidState state);
	
	Identifier tableForFluid(FluidState fluid);
}
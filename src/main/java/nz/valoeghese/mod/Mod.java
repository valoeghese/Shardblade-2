package nz.valoeghese.mod;

import gravity_changer.GravityComponent;
import net.fabricmc.api.ModInitializer;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import nz.valoeghese.mod.items.MagicSword;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mod implements ModInitializer {
	public static final String MOD_ID = "mod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// load items class
		NewItems.HONORBLADE.isEdible();

		GravityComponent.GRAVITY_UPDATE_EVENT.register((entity, component) -> {
			for (ItemStack slot : entity.getAllSlots()) {
				if (slot.getItem() instanceof MagicSword) {
					@Nullable CompoundTag tag = slot.getTagElement("magic_gravity");

					if (tag != null) {
						component.applyGravityDirectionEffect(
								Direction.values()[(tag.getInt("direction"))],
								null, 8000
						);
						return;
					}
				}
			}
		});
	}
}
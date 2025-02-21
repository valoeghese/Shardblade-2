package nz.valoeghese.mod;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import nz.valoeghese.mod.items.ItemPower;
import nz.valoeghese.mod.items.MagicSword;

import java.util.List;

public class NewItems {
    public static final Item HONORBLADE = Registry.register(
            BuiltInRegistries.ITEM,
            new ResourceLocation(Mod.MOD_ID, "windrunner_blade"),
            new MagicSword(new FabricItemSettings().maxCount(1), List.of(ItemPower.GRAVITY_CHANGE))
    );
}

package nz.valoeghese.mod.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

import java.util.List;

public class MagicSword extends SwordItem {
    public MagicSword(Properties settings, List<ItemPower> powers) {
        super(Tiers.IRON, 3, -2.4F, settings);
        this.powers = powers;
    }

    private final List<ItemPower> powers;

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);

        if (this.powers.isEmpty()) {
            return InteractionResultHolder.fail(itemStack);
        }

        if (!level.isClientSide) {
            this.powers.get(0).onUse(player, level, itemStack);
        }

        return InteractionResultHolder.success(itemStack);
    }
}

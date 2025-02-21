package nz.valoeghese.mod.items;

import gravity_changer.api.GravityChangerAPI;
import gravity_changer.util.RotationUtil;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.Nullable;

public interface ItemPower {
    void onUse(Player player, Level level, ItemStack stack);

    ItemPower GRAVITY_CHANGE = new ItemPower() {
        @Override
        public void onUse(Player player, Level level, ItemStack stack) {
            CompoundTag tag = stack.getOrCreateTagElement("magic_gravity");

            final Direction gravityDirection = GravityChangerAPI.getGravityDirection(player);

            Vec2 yawPitch = RotationUtil.rotPlayerToWorld(player.getYHeadRot(), player.getXRot(), gravityDirection);
            Direction newDirection = ItemPower.viewDirection(yawPitch.x, yawPitch.y);
            Direction existingDirection = Direction.values()[tag.contains("direction") ? tag.getInt("direction") : Direction.DOWN.ordinal()];

            // don't keep direction. use same direction as a shortcut to switch to down, or if already down, up
            if (existingDirection == newDirection) {
                newDirection = existingDirection == Direction.DOWN ? Direction.UP : Direction.DOWN;
            }

            tag.putInt("direction", newDirection.ordinal());
        }
    };

    private static Direction viewDirection(float yaw, float pitch) {
        if (pitch > 60.0) {
            return Direction.DOWN;
        } else if (pitch < -60.0) {
            return Direction.UP;
        } else {
            return Direction.fromYRot(yaw);
        }
    }
}

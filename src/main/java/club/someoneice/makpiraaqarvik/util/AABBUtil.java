package club.someoneice.makpiraaqarvik.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class AABBUtil {
    public static AABB getAABBWithRange(double x, double y, double z, double range) {
        return new AABB(x - range, y - range, z - range, x + range, y + range, z + range);
    }

    public static AABB getAABBWithRange(Vec3 pos, double range) {
        return getAABBWithRange(pos.x, pos.y, pos.z, range);
    }

    public static AABB getAABBWithRange(BlockPos pos, double range) {
        return getAABBWithRange(pos.getX(), pos.getY(), pos.getZ(), range);
    }

    public static AABB getAABBWithGroundRange(double x, double y, double z, double range) {
        return new AABB(x - range, y, z - range, x + range, y, z + range);
    }

    public static AABB getAABBWithGroundRange(Vec3 pos, double range) {
        return getAABBWithGroundRange(pos.x, pos.y, pos.z, range);
    }

    public static AABB getAABBWithGroundRange(BlockPos pos, double range) {
        return getAABBWithGroundRange(pos.getX(), pos.getY(), pos.getZ(), range);
    }
}

package club.someoneice.makpiraaqarvik.util.math;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class Pos {
    public int x;
    public int y;
    public int z;

    public Pos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BlockPos toBlockPos() {
        return new BlockPos(this.x, this.y, this.z);
    }

    public static Pos fromBlockPos(BlockPos pos) {
        return new Pos(pos.getX(), pos.getY(), pos.getZ());
    }

    public Pos above() {
        return this.relative(Direction.UP);
    }

    public Pos above(int size) {
        return this.relative(Direction.UP, size);
    }

    public Pos below() {
        return this.relative(Direction.DOWN);
    }

    public Pos below(int size) {
        return this.relative(Direction.DOWN, size);
    }

    public Pos north() {
        return this.relative(Direction.NORTH);
    }

    public Pos north(int size) {
        return this.relative(Direction.NORTH, size);
    }

    public Pos south() {
        return this.relative(Direction.SOUTH);
    }

    public Pos south(int size) {
        return this.relative(Direction.SOUTH, size);
    }

    public Pos west() {
        return this.relative(Direction.WEST);
    }

    public Pos west(int size) {
        return this.relative(Direction.WEST, size);
    }

    public Pos east() {
        return this.relative(Direction.EAST);
    }

    public Pos east(int size) {
        return this.relative(Direction.EAST, size);
    }

    public Pos relative(Direction direction) {
        return new Pos(this.x + direction.getStepX(), this.y + direction.getStepY(), this.z + direction.getStepZ());
    }

    public Pos relative(Direction direction, int size) {
        return size == 0 ? this : new Pos(
                this.x + direction.getStepX() * size, this.y + direction.getStepY() * size, this.z + direction.getStepZ() * size
        );
    }
}

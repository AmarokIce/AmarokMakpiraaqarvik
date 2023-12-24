package club.someoneice.makpiraaqarvik.tile;

import club.someoneice.makpiraaqarvik.Main;
import club.someoneice.makpiraaqarvik.api.TileNbt;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public abstract class TileBase extends BlockEntity {
    public TileBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    abstract void writeToNbt(CompoundTag nbt);
    abstract void readFromNbt(CompoundTag nbt);

    @Override
    public CompoundTag serializeNBT() {
        var nbt = super.serializeNBT();
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                putToNBT(field, nbt);
            } catch (Exception e) {
                Main.LOGGER.error(e);
            }
        }

        writeToNbt(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);

        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                getFromNBT(field, nbt);
            } catch (Exception e) {
                Main.LOGGER.error(e);
            }
        }

        readFromNbt(nbt);
    }

    private void putToNBT(Field field, CompoundTag nbt) throws IllegalAccessException {
        field.setAccessible(true);
        if (!field.isAnnotationPresent(TileNbt.class)) return;
        String name = field.getAnnotation(TileNbt.class).name();

        Object obj = field.get(this);
        if (obj instanceof String)       nbt.putString(name, (String) obj);
        else if (obj instanceof Integer) nbt.putInt(name, (int) obj);
        else if (obj instanceof Float)   nbt.putFloat(name, (float) obj);
        else if (obj instanceof Double)  nbt.putDouble(name, (double) obj);
        else if (obj instanceof Boolean) nbt.putBoolean(name, (boolean) obj);
    }

    private void getFromNBT(Field field, CompoundTag nbt) throws IllegalAccessException {
        field.setAccessible(true);
        if (!field.isAnnotationPresent(TileNbt.class)) return;
        String name = field.getAnnotation(TileNbt.class).name();

        Object obj = field.get(this);

        if (obj instanceof String)       field.set(this, nbt.getString(name));
        else if (obj instanceof Integer) field.set(this, nbt.getInt(name));
        else if (obj instanceof Float)   field.set(this, nbt.getFloat(name));
        else if (obj instanceof Double)  field.set(this, nbt.getDouble(name));
        else if (obj instanceof Boolean) field.set(this, nbt.getBoolean(name));
    }
}

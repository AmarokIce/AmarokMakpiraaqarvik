package club.someoneice.makpiraaqarvik.tile;

import club.someoneice.makpiraaqarvik.Main;
import club.someoneice.makpiraaqarvik.api.TileNbt;
import club.someoneice.makpiraaqarvik.util.ObjectUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public abstract class TileBase extends BlockEntity {
    public TileBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    abstract public void writeToNbt(CompoundTag nbt);
    abstract public void readFromNbt(CompoundTag nbt);

    public void markDirt() {
        super.setChanged();
        ObjectUtil.let(this.getLevel(), it -> {
           if (it.isClientSide) return;
           ((ServerLevel) it).players().forEach(player -> {
                player.connection.send(this.getUpdatePacket());
            });
        });
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag()  {
        var tag = super.getUpdateTag();
        this.saveAdditional(tag);
        return tag;
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                putToNBT(field, nbt);
            } catch (Exception e) {
                Main.LOGGER.error(e);
            }
        }

        writeToNbt(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

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

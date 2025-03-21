package club.someoneice.makpiraaqarvik.lib.bean.tile;

import club.someoneice.makpiraaqarvik.core.AmarokMakpiraaqarvik;
import club.someoneice.makpiraaqarvik.lib.ObjectUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public abstract class TileBase extends BlockEntity {
    public TileBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    abstract public void writeToNbt(CompoundTag nbt, HolderLookup.Provider registries);
    abstract public void readFromNbt(CompoundTag nbt, HolderLookup.Provider registries);

    public void markDirt() {
        super.setChanged();
        ObjectUtils.let(this.getLevel(), it -> {
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
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        var tag = super.getUpdateTag(registries);
        this.saveAdditional(tag, registries);
        return tag;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                putToNBT(field, tag, registries);
            } catch (Exception e) {
                AmarokMakpiraaqarvik.LOGGER.error(e);
            }
        }

        writeToNbt(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                getFromNBT(field, tag, registries);
            } catch (Exception e) {
                AmarokMakpiraaqarvik.LOGGER.error(e);
            }
        }

        readFromNbt(tag, registries);
    }

    private void putToNBT(Field field, CompoundTag nbt, HolderLookup.Provider registries) throws IllegalAccessException {
        field.setAccessible(true);
        if (!field.isAnnotationPresent(TileNbt.class)) return;
        String name = field.getAnnotation(TileNbt.class).name();

        Object obj = field.get(this);
        if (obj instanceof String)       nbt.putString(name, (String) obj);
        else if (obj instanceof Integer) nbt.putInt(name, (int) obj);
        else if (obj instanceof Float)   nbt.putFloat(name, (float) obj);
        else if (obj instanceof Double)  nbt.putDouble(name, (double) obj);
        else if (obj instanceof Boolean) nbt.putBoolean(name, (boolean) obj);
        else if (obj instanceof Tag)     nbt.put(name, (Tag) obj);
        else if (obj instanceof ItemStack) nbt.put(name, ((ItemStack) obj).save(registries));
    }

    private void getFromNBT(Field field, CompoundTag nbt, HolderLookup.Provider registries) throws IllegalAccessException {
        field.setAccessible(true);
        if (!field.isAnnotationPresent(TileNbt.class)) return;
        String name = field.getAnnotation(TileNbt.class).name();

        Object obj = field.get(this);

        if (obj instanceof String)              field.set(this, nbt.getString(name));
        else if (obj instanceof Integer)        field.set(this, nbt.getInt(name));
        else if (obj instanceof Float)          field.set(this, nbt.getFloat(name));
        else if (obj instanceof Double)         field.set(this, nbt.getDouble(name));
        else if (obj instanceof Boolean)        field.set(this, nbt.getBoolean(name));
        else if (obj instanceof CompoundTag)    field.set(this, nbt.getCompound(name));
        else if (obj instanceof Tag)            field.set(this, nbt.get(name));
        else if (obj instanceof ItemStack)      field.set(this, ItemStack.parse(registries, nbt.getCompound(name)));
    }
}

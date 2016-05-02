package net.minecraft.util.registry;

import java.util.Set;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IRegistry<K, V> extends Iterable<V>
{
    @SideOnly(Side.CLIENT)
    V getObject(K name);

    /**
     * Register an object on this registry.
     */
    void putObject(K key, V value);

    @SideOnly(Side.CLIENT)
    Set<K> getKeys();
}
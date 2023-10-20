package com.illusivesoulworks.polymorph.mixin.integration.toms_storage;

import com.tom.storagemod.gui.StorageTerminalMenu;
import com.tom.storagemod.tile.StorageTerminalBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StorageTerminalMenu.class)
public interface AccessorStorageTerminalMenu {

    @Accessor(remap = false)
    StorageTerminalBlockEntity getTe();
}

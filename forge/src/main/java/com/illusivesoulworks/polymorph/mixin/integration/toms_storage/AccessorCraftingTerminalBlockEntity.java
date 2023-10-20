package com.illusivesoulworks.polymorph.mixin.integration.toms_storage;

import com.tom.storagemod.platform.PlatformRecipe;
import com.tom.storagemod.tile.CraftingTerminalBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CraftingTerminalBlockEntity.class)
public interface AccessorCraftingTerminalBlockEntity {

    @Accessor(remap = false)
    void setCurrentRecipe(PlatformRecipe pRecipe);

    @Invoker(remap = false)
    void callOnCraftingMatrixChanged();
}

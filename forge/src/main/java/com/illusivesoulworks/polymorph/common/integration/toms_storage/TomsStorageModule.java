package com.illusivesoulworks.polymorph.common.integration.toms_storage;

import com.illusivesoulworks.polymorph.api.PolymorphApi;
import com.illusivesoulworks.polymorph.api.client.base.IPolymorphClient;
import com.illusivesoulworks.polymorph.api.common.base.IPolymorphCommon;
import com.illusivesoulworks.polymorph.common.integration.AbstractCompatibilityModule;
import com.illusivesoulworks.polymorph.mixin.integration.toms_storage.AccessorCraftingTerminalBlockEntity;
import com.illusivesoulworks.polymorph.mixin.integration.toms_storage.AccessorStorageTerminalMenu;
import com.tom.storagemod.gui.CraftingTerminalMenu;
import com.tom.storagemod.platform.PlatformRecipe;
import com.tom.storagemod.tile.CraftingTerminalBlockEntity;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntity;

public class TomsStorageModule extends AbstractCompatibilityModule {

    @Override
    public void setup() {
        IPolymorphCommon commonApi = PolymorphApi.common();
        commonApi.registerBlockEntity2RecipeData(pTileEntity -> {
            if (pTileEntity instanceof CraftingTerminalBlockEntity) {
                return new CraftingTerminalRecipeData((CraftingTerminalBlockEntity) pTileEntity);
            }
            return null;
        });
        commonApi.registerContainer2BlockEntity(pContainer -> {
            if (pContainer instanceof CraftingTerminalMenu) {
                return ((AccessorStorageTerminalMenu) pContainer).getTe();
            }
            return null;
        });
    }

    @Override
    public void clientSetup() {
        IPolymorphClient clientApi = PolymorphApi.client();
        clientApi.registerWidget(containerScreen -> {
            if (containerScreen.getMenu() instanceof CraftingTerminalMenu) {
                return clientApi.findCraftingResultSlot(containerScreen)
                        .map(slot -> new CraftingTerminalRecipesWidget(containerScreen, slot))
                        .orElse(null);
            }
            return null;
        });
    }

    @Override
    public boolean selectRecipe(BlockEntity tileEntity, Recipe<?> recipe) {

        if (recipe instanceof CraftingRecipe && tileEntity instanceof CraftingTerminalBlockEntity) {
            AccessorCraftingTerminalBlockEntity te = (AccessorCraftingTerminalBlockEntity) tileEntity;
            te.setCurrentRecipe(PlatformRecipe.of((CraftingRecipe) recipe));
            te.callOnCraftingMatrixChanged();
            return true;
        }
        return false;
    }
}

package com.illusivesoulworks.polymorph.common.integration.appliedenergistics2;

import appeng.menu.me.items.CraftingTermMenu;
import appeng.menu.me.items.PatternEncodingTermMenu;
import appeng.menu.slot.CraftingTermSlot;
import appeng.menu.slot.PatternTermSlot;
import appeng.parts.encoding.EncodingMode;
import com.illusivesoulworks.polymorph.api.PolymorphApi;
import com.illusivesoulworks.polymorph.common.integration.AbstractCompatibilityModule;
import com.illusivesoulworks.polymorph.mixin.integration.appliedenergistics2.AccessorCraftingTermMenu;
import com.illusivesoulworks.polymorph.mixin.integration.appliedenergistics2.AccessorPatternTermMenu;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;

public class AppliedEnergisticsModule extends AbstractCompatibilityModule {

    @Override
    protected void clientSetup() {
        PolymorphApi.client().registerWidget(pContainerScreen -> {
            if (pContainerScreen.getMenu() instanceof CraftingTermMenu) {
                for (Slot inventorySlot : pContainerScreen.getMenu().slots) {
                    if (inventorySlot instanceof CraftingTermSlot) {
                        return new CraftingTermRecipesWidget((CraftingTermMenu) pContainerScreen.getMenu(),
                                pContainerScreen, inventorySlot);
                    }
                }
            } else if (pContainerScreen.getMenu() instanceof PatternEncodingTermMenu encodingTermMenu) {
                for (Slot inventorySlot : pContainerScreen.getMenu().slots) {
                    if (encodingTermMenu.getMode().equals(EncodingMode.CRAFTING)) {
                        if (inventorySlot instanceof PatternTermSlot) {
                            return new PatternTermRecipesWidget(pContainerScreen,
                                    (PatternEncodingTermMenu) pContainerScreen.getMenu(), inventorySlot);
                        }
                    }
                }
            }
            return null;
        });
    }

    @Override
    public boolean selectRecipe(AbstractContainerMenu container, Recipe<?> recipe) {
        if (recipe instanceof CraftingRecipe) {
            if (container instanceof CraftingTermMenu) {
                ((AccessorCraftingTermMenu) container).setCurrentRecipe((CraftingRecipe) recipe);
                ((AccessorCraftingTermMenu) container).callUpdateCurrentRecipeAndOutput(true);
                return true;
            } else if (container instanceof PatternEncodingTermMenu) {
                ((AccessorPatternTermMenu) container).setCurrentRecipe((CraftingRecipe) recipe);
                ((AccessorPatternTermMenu) container).callGetAndUpdateOutput();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean openContainer(AbstractContainerMenu container, ServerPlayer serverPlayerEntity) {
        if (container instanceof CraftingTermMenu) {
            ((AccessorCraftingTermMenu) container).callUpdateCurrentRecipeAndOutput(true);
            return true;
        } else if (container instanceof PatternEncodingTermMenu) {
            ((AccessorPatternTermMenu) container).callGetAndUpdateOutput();
            return true;
        }
        return false;
    }
}

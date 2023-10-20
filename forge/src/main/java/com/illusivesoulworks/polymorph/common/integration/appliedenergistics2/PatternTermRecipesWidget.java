package com.illusivesoulworks.polymorph.common.integration.appliedenergistics2;

import appeng.menu.me.items.PatternEncodingTermMenu;
import appeng.parts.encoding.EncodingMode;
import com.illusivesoulworks.polymorph.client.recipe.widget.PlayerRecipesWidget;
import com.illusivesoulworks.polymorph.mixin.integration.appliedenergistics2.AccessorPatternTermMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.CraftingRecipe;

public class PatternTermRecipesWidget extends PlayerRecipesWidget {

    private final PatternEncodingTermMenu container;

    public PatternTermRecipesWidget(AbstractContainerScreen<?> containerScreen,
                                    PatternEncodingTermMenu container, Slot outputSlot) {
        super(containerScreen, outputSlot);
        this.container = container;
    }

    @Override
    public void selectRecipe(ResourceLocation pResourceLocation) {
        super.selectRecipe(pResourceLocation);
        this.container.getPlayerInventory().player.level().getRecipeManager()
                .byKey(pResourceLocation).ifPresent(recipe -> {
                    ((AccessorPatternTermMenu) this.container).setCurrentRecipe((CraftingRecipe) recipe);
                    ((AccessorPatternTermMenu) this.container).callGetAndUpdateOutput();
                });
    }

/*    @Override
    public void render(PoseStack pMatrixStack, int pMouseX, int pMouseY, float pRenderPartialTicks) {

        if (container.mode == EncodingMode.CRAFTING) {
            super.render(pMatrixStack, pMouseX, pMouseY, pRenderPartialTicks);
        }
    }*/

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (container.mode != EncodingMode.CRAFTING) {
            return false;
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }
}

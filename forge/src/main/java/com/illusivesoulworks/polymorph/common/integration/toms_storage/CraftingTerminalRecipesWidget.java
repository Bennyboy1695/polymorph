package com.illusivesoulworks.polymorph.common.integration.toms_storage;

import com.illusivesoulworks.polymorph.client.recipe.widget.PersistentRecipesWidget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;

public class CraftingTerminalRecipesWidget extends PersistentRecipesWidget {

    private final Slot outputSlot;

    public CraftingTerminalRecipesWidget(AbstractContainerScreen<?> containerScreen,
                                         Slot outputSlot) {
        super(containerScreen);
        this.outputSlot = outputSlot;
    }

    @Override
    public Slot getOutputSlot() {
        return this.outputSlot;
    }
}

package com.illusivesoulworks.polymorph.mixin.integration.appliedenergistics2;

import appeng.menu.me.items.CraftingTermMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CraftingTermMenu.class)
public interface AccessorCraftingTermMenu {

    @Accessor(remap = false)
    void setCurrentRecipe(Recipe<CraftingContainer> pCurrentRecipe);

    @Invoker(remap = false)
    void callUpdateCurrentRecipeAndOutput(boolean force);
}

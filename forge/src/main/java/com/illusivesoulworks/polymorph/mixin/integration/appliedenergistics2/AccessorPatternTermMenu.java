package com.illusivesoulworks.polymorph.mixin.integration.appliedenergistics2;

import appeng.menu.me.items.PatternEncodingTermMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PatternEncodingTermMenu.class)
public interface AccessorPatternTermMenu {

    @Accessor(remap = false)
    void setCurrentRecipe(CraftingRecipe pCurrentRecipe);

    @Invoker(remap = false)
    ItemStack callGetAndUpdateOutput();
}
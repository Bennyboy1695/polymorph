package com.illusivesoulworks.polymorph.mixin.integration.toms_storage;

import com.illusivesoulworks.polymorph.common.crafting.RecipeSelection;
import com.tom.storagemod.tile.CraftingTerminalBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@SuppressWarnings("unused")
@Mixin(CraftingTerminalBlockEntity.class)
public class MixinCraftingTerminalBlockEntity {

    @SuppressWarnings("ConstantConditions")
    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/world/item/crafting/RecipeManager.getRecipeFor(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/Container;Lnet/minecraft/world/level/Level;)Ljava/util/Optional;"),
            method = "onCraftingMatrixChanged")
    private <C extends Container, T extends Recipe<C>> Optional<T> polymorph$getRecipe(
            RecipeManager recipeManager, RecipeType<T> type, C inventory, Level world) {
        return RecipeSelection.getBlockEntityRecipe(type, inventory, world,
                (CraftingTerminalBlockEntity) (Object) this);
    }
}

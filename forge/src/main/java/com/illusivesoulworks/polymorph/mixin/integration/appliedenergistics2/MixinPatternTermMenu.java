package com.illusivesoulworks.polymorph.mixin.integration.appliedenergistics2;

import appeng.api.storage.ITerminalHost;
import appeng.menu.me.common.MEStorageMenu;
import appeng.menu.me.items.PatternEncodingTermMenu;
import com.illusivesoulworks.polymorph.common.crafting.RecipeSelection;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@SuppressWarnings("unused")
@Mixin(PatternEncodingTermMenu.class)
public abstract class MixinPatternTermMenu extends MEStorageMenu {

    public MixinPatternTermMenu(MenuType<?> menuType, int id, Inventory ip, ITerminalHost host) {
        super(menuType, id, ip, host);
    }

    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/world/item/crafting/RecipeManager.getRecipeFor(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/Container;Lnet/minecraft/world/level/Level;)Ljava/util/Optional;"),
            method = "getAndUpdateOutput")
    private <C extends Container, T extends Recipe<C>> Optional<T> polymorph$getRecipe(
            RecipeManager recipeManager, RecipeType<T> type, C inventory, Level world) {
        return RecipeSelection.getPlayerRecipe(this, type, inventory, world,
                this.getPlayerInventory().player);
    }
}

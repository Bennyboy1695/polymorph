/*
 * Copyright (C) 2020-2021 C4
 *
 * This file is part of Polymorph.
 *
 * Polymorph is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Polymorph is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * and the GNU Lesser General Public License along with Polymorph.
 * If not, see <https://www.gnu.org/licenses/>.
 *
 */

package top.theillusivec4.polymorph.common.integration.refinedstorage;

import com.refinedmods.refinedstorage.blockentity.grid.GridBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.polymorph.common.capability.AbstractBlockEntityRecipeData;

public class GridBlockEntityRecipeData extends AbstractBlockEntityRecipeData<GridBlockEntity> {

  public GridBlockEntityRecipeData(GridBlockEntity pOwner) {
    super(pOwner);
  }

  @Override
  protected NonNullList<ItemStack> getInput() {
    CraftingContainer craftingInventory = this.getOwner().getNode().getCraftingMatrix();

    if (craftingInventory != null) {
      NonNullList<ItemStack> stacks =
          NonNullList.withSize(craftingInventory.getContainerSize(), ItemStack.EMPTY);

      for (int i = 0; i < craftingInventory.getContainerSize(); i++) {
        stacks.set(i, craftingInventory.getItem(i));
      }
      return stacks;
    }
    return NonNullList.create();
  }
}

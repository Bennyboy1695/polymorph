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

package top.theillusivec4.polymorph.common.capability;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import top.theillusivec4.polymorph.mixin.core.AccessorAbstractFurnaceBlockEntity;

public class FurnaceRecipeData extends AbstractHighlightedRecipeData<AbstractFurnaceBlockEntity> {

  public FurnaceRecipeData(AbstractFurnaceBlockEntity pOwner) {
    super(pOwner);
  }

  @Override
  protected NonNullList<ItemStack> getInput() {

    if (((AccessorAbstractFurnaceBlockEntity) this.getOwner()).getItems() != null) {
      return NonNullList.of(ItemStack.EMPTY, this.getOwner().getItem(0));
    } else {
      return NonNullList.create();
    }
  }

  @Override
  public boolean isEmpty() {
    return this.getInput().get(0).isEmpty();
  }
}

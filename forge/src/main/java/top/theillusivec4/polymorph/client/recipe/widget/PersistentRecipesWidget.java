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

package top.theillusivec4.polymorph.client.recipe.widget;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import top.theillusivec4.polymorph.api.PolymorphApi;
import top.theillusivec4.polymorph.api.client.widget.AbstractRecipesWidget;

public abstract class PersistentRecipesWidget extends AbstractRecipesWidget {

  public PersistentRecipesWidget(AbstractContainerScreen<?> pContainerScreen) {
    super(pContainerScreen);
  }

  @Override
  public void selectRecipe(ResourceLocation pResourceLocation) {
    PolymorphApi.common().getPacketDistributor()
        .sendPersistentRecipeSelectionC2S(pResourceLocation);
  }
}

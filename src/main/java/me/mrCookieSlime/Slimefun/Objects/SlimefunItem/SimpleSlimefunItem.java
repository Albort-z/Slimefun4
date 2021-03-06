package me.mrCookieSlime.Slimefun.Objects.SlimefunItem;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

/**
 * This is basically a quickstart class for your very first {@link SlimefunItem}.
 * This class easily allows you to add one {@link ItemHandler} to your {@link SlimefunItem}.
 * 
 * You could use an {@link ItemUseHandler} for example to give your {@link SlimefunItem}
 * very basic right-click functionalities.
 * 
 * @author TheBusyBiscuit
 * 
 * @see ItemHandler
 * @see ItemUseHandler
 * @see SlimefunItem
 *
 * @param <T>
 *            The Type of {@link ItemHandler} to add to this {@link SlimefunItem}
 */
public abstract class SimpleSlimefunItem<T extends ItemHandler> extends SlimefunItem {

    public SimpleSlimefunItem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    public SimpleSlimefunItem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(category, item, recipeType, recipe, recipeOutput);
    }

    public SimpleSlimefunItem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, String[] keys, Object[] values) {
        super(category, item, recipeType, recipe, keys, values);
    }

    @Override
    protected boolean areItemHandlersPrivate() {
        return true;
    }

    @Override
    public void preRegister() {
        addItemHandler(getItemHandler());
    }

    public abstract T getItemHandler();

}

package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.cscorelib2.chat.ChatColors;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.github.thebusybiscuit.cscorelib2.math.DoubleHandler;
import io.github.thebusybiscuit.cscorelib2.protection.ProtectableAction;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.NumberUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import me.mrCookieSlime.Slimefun.Objects.handlers.GeneratorTicker;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;

public abstract class AGenerator extends SlimefunItem implements RecipeDisplayItem, InventoryBlock, EnergyNetComponent {

    public static Map<Location, MachineFuel> processing = new HashMap<>();
    public static Map<Location, Integer> progress = new HashMap<>();

    private final Set<MachineFuel> recipes = new HashSet<>();

    private static final int[] border = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44 };
    private static final int[] border_in = { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 };
    private static final int[] border_out = { 14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };

    public AGenerator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        new BlockMenuPreset(id, getInventoryTitle()) {

            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public boolean canOpen(Block b, Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || SlimefunPlugin.getProtectionManager().hasPermission(p, b.getLocation(), ProtectableAction.ACCESS_INVENTORIES);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.INSERT) return getInputSlots();
                else return getOutputSlots();
            }
        };

        registerBlockHandler(id, (p, b, tool, reason) -> {
            BlockMenu inv = BlockStorage.getInventory(b);

            if (inv != null) {
                for (int slot : getInputSlots()) {
                    if (inv.getItemInSlot(slot) != null) {
                        b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                        inv.replaceExistingItem(slot, null);
                    }
                }
                for (int slot : getOutputSlots()) {
                    if (inv.getItemInSlot(slot) != null) {
                        b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                        inv.replaceExistingItem(slot, null);
                    }
                }
            }
            progress.remove(b.getLocation());
            processing.remove(b.getLocation());
            return true;
        });

        this.registerDefaultFuelTypes();
    }

    public AGenerator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        this(category, item, recipeType, recipe);
        this.recipeOutput = recipeOutput;
    }

    private void constructMenu(BlockMenuPreset preset) {
        for (int i : border) {
            preset.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : border_in) {
            preset.addItem(i, new CustomItem(new ItemStack(Material.CYAN_STAINED_GLASS_PANE), " "), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : border_out) {
            preset.addItem(i, new CustomItem(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE), " "), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : getOutputSlots()) {
            preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {

                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    return cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR;
                }
            });
        }

        preset.addItem(22, new CustomItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), " "), ChestMenuUtils.getEmptyClickHandler());
    }

    /**
     * This method returns the title that is used for the {@link Inventory} of an
     * {@link AGenerator} that has been opened by a Player.
     * 
     * Override this method to set the title.
     * 
     * @return The title of the {@link Inventory} of this {@link AGenerator}
     */
    public abstract String getInventoryTitle();

    /**
     * This method returns the {@link ItemStack} that this {@link AGenerator} will
     * use as a progress bar.
     * 
     * Override this method to set the progress bar.
     * 
     * @return The {@link ItemStack} to use as the progress bar
     */
    public abstract ItemStack getProgressBar();

    /**
     * This method returns the amount of energy that is produced per tick.
     * 
     * @return The rate of energy generation
     */
    public abstract int getEnergyProduction();

    /**
     * This method is used to register the default fuel types.
     */
    protected abstract void registerDefaultFuelTypes();

    @Override
    public int[] getInputSlots() {
        return new int[] { 19, 20 };
    }

    @Override
    public int[] getOutputSlots() {
        return new int[] { 24, 25 };
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    public MachineFuel getProcessing(Location l) {
        return processing.get(l);
    }

    public boolean isProcessing(Location l) {
        return progress.containsKey(l);
    }

    public void registerFuel(MachineFuel fuel) {
        this.recipes.add(fuel);
    }

    @Override
    public void preRegister() {
        addItemHandler(new GeneratorTicker() {

            @Override
            public double generateEnergy(Location l, SlimefunItem sf, Config data) {
                BlockMenu inv = BlockStorage.getInventory(l);

                if (isProcessing(l)) {
                    int timeleft = progress.get(l);

                    if (timeleft > 0) {
                        ChestMenuUtils.updateProgressbar(inv, 22, timeleft, processing.get(l).getTicks(), getProgressBar());

                        if (ChargableBlock.isChargable(l)) {
                            if (ChargableBlock.getMaxCharge(l) - ChargableBlock.getCharge(l) >= getEnergyProduction()) {
                                ChargableBlock.addCharge(l, getEnergyProduction());
                                progress.put(l, timeleft - 1);
                                return ChargableBlock.getCharge(l);
                            }
                            return 0;
                        }
                        else {
                            progress.put(l, timeleft - 1);
                            return getEnergyProduction();
                        }
                    }
                    else {
                        ItemStack fuel = processing.get(l).getInput();

                        if (SlimefunManager.isItemSimilar(fuel, new ItemStack(Material.LAVA_BUCKET), true) || SlimefunManager.isItemSimilar(fuel, SlimefunItems.BUCKET_OF_FUEL, true) || SlimefunManager.isItemSimilar(fuel, SlimefunItems.BUCKET_OF_OIL, true)) {
                            inv.pushItem(new ItemStack(Material.BUCKET), getOutputSlots());
                        }

                        inv.replaceExistingItem(22, new CustomItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), " "));

                        progress.remove(l);
                        processing.remove(l);
                        return 0;
                    }
                }
                else {
                    Map<Integer, Integer> found = new HashMap<>();
                    MachineFuel fuel = findRecipe(inv, found);

                    if (fuel != null) {
                        for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                            inv.consumeItem(entry.getKey(), entry.getValue());
                        }

                        processing.put(l, fuel);
                        progress.put(l, fuel.getTicks());
                    }
                    return 0;
                }
            }

            @Override
            public boolean explode(Location l) {
                return false;
            }
        });
    }

    private MachineFuel findRecipe(BlockMenu menu, Map<Integer, Integer> found) {
        for (MachineFuel recipe : recipes) {
            for (int slot : getInputSlots()) {
                if (SlimefunManager.isItemSimilar(menu.getItemInSlot(slot), recipe.getInput(), true)) {
                    found.put(slot, recipe.getInput().getAmount());
                    return recipe;
                }
            }
        }

        return null;
    }

    public Set<MachineFuel> getFuelTypes() {
        return this.recipes;
    }

    @Override
    public String getLabelLocalPath() {
        return "guide.tooltips.recipes.generator";
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> list = new ArrayList<>();

        for (MachineFuel fuel : recipes) {
            ItemStack item = fuel.getInput().clone();
            ItemMeta im = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add(ChatColors.color("&8\u21E8 &7Lasts " + NumberUtils.getTimeLeft(fuel.getTicks() / 2)));
            lore.add(ChatColors.color("&8\u21E8 &e\u26A1 &7" + getEnergyProduction() * 2) + " J/s");
            lore.add(ChatColors.color("&8\u21E8 &e\u26A1 &7" + DoubleHandler.getFancyDouble((double) fuel.getTicks() * getEnergyProduction()) + " J in total"));
            im.setLore(lore);
            item.setItemMeta(im);
            list.add(item);
        }

        return list;
    }

}

package io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.multiblocks.OreWasher;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

public abstract class ElectricDustWasher extends AContainer {

    private OreWasher oreWasher;

    public ElectricDustWasher(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        super.preRegister();

        oreWasher = (OreWasher) SlimefunItem.getByID("ORE_WASHER");
    }

    @Override
    public String getInventoryTitle() {
        return "&bElectric Dust Washer";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.GOLDEN_SHOVEL);
    }

    public abstract int getSpeed();

    @Override
    protected void tick(Block b) {
        BlockMenu menu = BlockStorage.getInventory(b);

        if (isProcessing(b)) {
            int timeleft = progress.get(b);

            if (timeleft > 0 && getSpeed() < 10) {
                ChestMenuUtils.updateProgressbar(menu, 22, timeleft, processing.get(b).getTicks(), getProgressBar());

                if (ChargableBlock.isChargable(b)) {
                    if (ChargableBlock.getCharge(b) < getEnergyConsumption()) return;
                    ChargableBlock.addCharge(b, -getEnergyConsumption());
                    progress.put(b, timeleft - 1);
                }
                else progress.put(b, timeleft - 1);
            }
            else if (ChargableBlock.isChargable(b)) {
                if (ChargableBlock.getCharge(b) < getEnergyConsumption()) return;
                ChargableBlock.addCharge(b, -getEnergyConsumption());

                menu.replaceExistingItem(22, new CustomItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), " "));
                menu.pushItem(processing.get(b).getOutput()[0].clone(), getOutputSlots());

                progress.remove(b);
                processing.remove(b);
            }
        }
        else {
            for (int slot : getInputSlots()) {
                if (SlimefunManager.isItemSimilar(menu.getItemInSlot(slot), SlimefunItems.SIFTED_ORE, true)) {
                    if (!SlimefunPlugin.getSettings().legacyDustWasher) {
                        boolean emptySlot = false;

                        for (int outputSlot : getOutputSlots()) {
                            if (menu.getItemInSlot(outputSlot) == null) {
                                emptySlot = true;
                                break;
                            }
                        }
                        if (!emptySlot) return;
                    }

                    ItemStack adding = oreWasher.getRandomDust();
                    MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] { adding });
                    if (SlimefunPlugin.getSettings().legacyDustWasher && !menu.fits(r.getOutput()[0], getOutputSlots())) return;
                    menu.consumeItem(slot);
                    processing.put(b, r);
                    progress.put(b, r.getTicks());
                    break;
                }
                else if (SlimefunManager.isItemSimilar(menu.getItemInSlot(slot), SlimefunItems.PULVERIZED_ORE, true)) {
                    MachineRecipe r = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] { SlimefunItems.PURE_ORE_CLUSTER });
                    if (!menu.fits(r.getOutput()[0], getOutputSlots())) return;
                    menu.consumeItem(slot);
                    processing.put(b, r);
                    progress.put(b, r.getTicks());
                    break;
                }
            }
        }
    }

    @Override
    public String getMachineIdentifier() {
        return "ELECTRIC_DUST_WASHER";
    }

}

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**List of Releases**

- [Release Candidate 10 (TBD)](#release-candidate-10-tbd)
  - [Additions](#additions)
  - [Changes](#changes)
  - [Fixes](#fixes)
- [Release Candidate 9 (07 Mar 2020)](#release-candidate-9-07-mar-2020)
  - [Fixes](#fixes-1)
- [Release Candidate 8 (06 Mar 2020)](#release-candidate-8-06-mar-2020)
- [Fixes](#fixes-2)
- [Release Candidate 7 (06 Mar 2020)](#release-candidate-7-06-mar-2020)
  - [Additions](#additions-1)
  - [Changes](#changes-1)
  - [Fixes](#fixes-3)
- [Release Candidate 6 (16 Feb 2020)](#release-candidate-6-16-feb-2020)
  - [Additions](#additions-2)
  - [Changes](#changes-2)
  - [Fixes](#fixes-4)
- [Release Candidate 5 (09 Feb 2020)](#release-candidate-5-09-feb-2020)
  - [Additions](#additions-3)
  - [Changes](#changes-3)
  - [Fixes](#fixes-5)
- [Release Candidate 4 (06 Jan 2020)](#release-candidate-4-06-jan-2020)
  - [Additions](#additions-4)
  - [Changes](#changes-4)
  - [Fixes](#fixes-6)
- [Release Candidate 3 (21 Nov 2019)](#release-candidate-3-21-nov-2019)
  - [Additions](#additions-5)
  - [Changes](#changes-5)
  - [Fixes](#fixes-7)
- [Release Candidate 2 (29 Sep 2019)](#release-candidate-2-29-sep-2019)
  - [Additions](#additions-6)
  - [Changes](#changes-6)
  - [Fixes](#fixes-8)
- [Release Candidate 1 (26 Sep 2019)](#release-candidate-1-26-sep-2019)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Release Candidate 10 (TBD)

### Additions
* Added some new charts to bStats
* Added Turkish

### Changes
* Removed some deprecated parts of the API
* Changed Automatic Ignition Chamber to be a Dropper

### Fixes

## Release Candidate 9 (07 Mar 2020)

### Fixes
* Fixed Solar Generators not working

## Release Candidate 8 (06 Mar 2020)

## Fixes
Fixed bStats Metrics not sending properly

## Release Candidate 7 (06 Mar 2020)

### Additions
* Added translations for Recipe Types
* Added Rainbow Concrete
* Added Rainbow Glazed Terracotta
* Added more internal documentation

### Changes
* Researches now use their namespaced keys in the Researches.yml
* A lot of API changes

### Fixes
* Fixed #1553
* Fixed #1513
* Fixed #1557
* Fixed #1558
* Fixed a translation not showing properly
* Fixed #1577
* Fixed #1597
* Fixed disabled Slimefun Addons not showing under /sf versions
* Fixed #1613

## Release Candidate 6 (16 Feb 2020)

### Additions
* Added a new language: Japanese
* Added a new language: Swedish
* Added a new language: Czech
* Added a new language: Portuguese (Brazil)
* Added a new language: Arabic

### Changes
* /sf research now uses namespaced keys instead of ids

### Fixes
* Fixed #1515
* Fixed back-button in guide-settings not working via commands
* Fixed #1516
* Fixed magician talisman not being able to enchant books

## Release Candidate 5 (09 Feb 2020)

### Additions
* Aded preset messages.yml files
* Added user-configurable localization
* Added many more options to the messages.yml
* Added custom model data support for Languages
* Added Grind Stone Recipes for Prismarine
* Added String to the Bio Reactor
* Added a config setting to limit how many Nodes a Network can have
* Added support for Furnaces and Brewing Stands to Cargo Networks
* Added Organic Food/Fertilizer for Dried Kelp
* Added many more strings to the messages.yml
* Added ability to translate messages for Players
* Added the ability to translate Researches
* Added StatusEffect API
* Added translatibility to categories
* Added translatibility to geo-resources

### Changes
* Removed Solar Array
* A lot of internal cleanup
* Performance improvements for GEO Miner and Oil Pump
* General performance improvements
* Changed Startup console message
* Changed GEO-Resources API

### Fixes
* Fixed #1355
* Fixed Localization mistakes
* Fixed #1366
* Fixed GitHub cache
* Fixed #1364
* Fixed Bio Reactor not accepting melons
* Fixed Cargo Networks particles being broken
* Fixed #1379
* Fixed #1212
* Fixed #114
* Fixed #1385
* Fixed #1390
* Fixed #1394
* Fixed #1313
* Fixed #1396
* Fixed Backpacks being placeable
* Fixed wrong file encoding for translations
* Fixed Minecraft recipes not showing correctly
* Fixed #1428
* Fixed #1435
* Fixed #1438
* Fixed Multi Tool functioning as unlimited Shears
* Fixed #1383
* Fixed Android Script Component textures

## Release Candidate 4 (06 Jan 2020)
https://thebusybiscuit.github.io/builds/TheBusyBiscuit/Slimefun4/stable/#4

### Additions
* Added 1.15 support (1.14 and 1.15 are both supported)
* Added custom model support to Slimefun Guide and some Recipe Types
* Added Nether Gold Pan
* Added Iron Nuggets to Gold Pan drops
* Added CS-CoreLib version to the guide info
* Added AndroidMineEvent
* Added Electric Press
* Added Soulbound Trident
* Added "Andesite, Granite and Diorite to Gravel" recipes to the Grinder
* Added "nuggets to ingots and ingots to blocks" recipes to the Electric Press
* Added Salt to the GEO - Miner
* Added Magnesium Salt
* Added Magnesium-powered Generator
* Added "Gravel to Sand" recipe to the Grinder
* Added config option for circuit board drops
* Added player option to toggle research fireworks in the guide settings
* Added Kelp Cookies
* Added support for multiple recipes on vanilla items
* Added a "Craft last" button to the Automated Crafting Chamber
* Added more ore-doubling Recipes to the Ore Crusher
* Added Addons to the guide settings

### Changes
* Revamped Guide Settings menu
* Changed some Category icons
* Changed Grappling Hook recipe
* Searching the guide now shows the Category of the item
* Contributors now also show their minecraft username (if possible)
* Changed teleporter sounds
* Electric Gold Pan now also supports Nether Gold Pan drops
* More performance improvements
* Improved Cargo performance
* Removed Nether Drill
* Tweaked Enhanced Furnace Recipes
* Changed tooltips for Radiation
* Oil Pump now shows its "Bucket -> Oil" recipe

### Fixes
* Fixed Research Titles
* Fixed #1264
* Fixed #1261
* Fixed #1266
* Fixed #1272
* Fixed #1273
* Fixed christmas items
* Fixed Multi Tools
* Fixed credits not showing all contributors
* Fixed exception when viewing the second page of the credits
* Fixed #1269
* Fixed #1276
* Fixed GEO-Miner dupes
* Fixed Output Chest not working
* Fixed #1281
* Fixed #1280
* Fixed a lot of issues with Crucibles
* Fixed Grind Stone dupes
* Fixed #1316
* Fixed performance issues with Oil Pumps
* Fixed #1318
* Fixed #1298
* Fixed #1325
* Fixed #1295
* Fixed MultiBlocks not accepting different fence types
* Fixed #1337
* Fixed Applie Pie ID mismatch
* Fixed #1344
* Fixed #1349
* Fixed #1332
* Fixed #1356 and maybe other concurrency issues
* Fixed Ore Crusher's missing recipes
* Fixed #1354

## Release Candidate 3 (21 Nov 2019)
https://thebusybiscuit.github.io/builds/TheBusyBiscuit/Slimefun4/stable/#3

### Additions
* Smeltery now shows some recipes in the guide
* Added Sweet Berry Juice
* MultiBlocks that require fences will now accept all types of wooden fences
* Added craftable Totems of Undying
* Added back some wiki-pages to the guide
* Added support for all new minecraft recipes to correctly display in the guide
* Added support for custom model data for items
* Added Output Chest support to the Table Saw
* Added Output Chest support to the Automated Panning Machine
* Added Jerky recipes to the Auto-Drier
* Added AutoDisenchantEvent
* Added "Flint to Cobblestone" Recipe to the Compressor


### Changes
* Changed Ignition Chamber Recipe
* /sf cheat no longer allows you to spawn in MultiBlocks
* Removed Heavy Armor
* Massive performance improvements with a new item-id system
* Huge performance improvements with skippable tickers
* Changed Elytra Scale Recipe
* Revamped Reactor Access Port
* Performance improvements for multi tools
* Performance improvements for armor
* Performance improvements for the Slimefun Guide

### Fixes
* Fixed Stone Chunk -> Cobblestone Recipe not working
* Fixed #1145
* Fixed #1157
* Fixed #1180
* Fixed Backpacks not working
* Fixed /sf cheat not showing locked categories
* Fixed #1200
* Fixed #1196
* Fixed #1153
* Fixed some food items
* Fixed multi tools not working
* Fixed #1202
* Fixed #1211
* Fixed #1219
* Fixed #1226
* Fixed #1224
* Fixed repair-cost getting wiped after disenchanting
* Fixed GPS transmitters transmitting wrong locations
* Fixed Ancient Altar allowing you to craft locked items

## Release Candidate 2 (29 Sep 2019)
https://thebusybiscuit.github.io/builds/TheBusyBiscuit/Slimefun4/stable/#2

### Additions
* Added GEO - Miner
* Added more bStats Charts

### Changes
* Reworked MultiBlocks
* Removed the Saw Mill

### Fixes
* Fixed Basic Machines not showing all recipes
* Fixed #1129
* Fixed #1130
* Fixed Auto-Updater for stable builds

## Release Candidate 1 (26 Sep 2019)
https://thebusybiscuit.github.io/builds/TheBusyBiscuit/Slimefun4/stable/#1

* First "stable" release since over a year. Stable builds will NOT receive support for bug reports since they are technically outdated. This change log will only be updated every so often and may be incomplete.

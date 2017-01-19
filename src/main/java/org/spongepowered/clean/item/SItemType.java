package org.spongepowered.clean.item;

import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.block.SBlockType;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

import java.util.Optional;

public class SItemType extends AbstractCatalogType implements ItemType {

    private BlockType blockType;
    private int itemId;
    private int maxQuantity = 64;

    public SItemType(String id, String name, int itemid) {
        super(id, name);
        this.blockType = null;
        this.itemId = itemid;
    }

    public SItemType(BlockType block) {
        super(block.getId(), block.getName());
        this.blockType = block;
        ((SBlockType) block).setItem(this);
        this.itemId = ((SBlockType) block).getBlockId();
    }

    public int getItemId() {
        return this.itemId;
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ItemType getType() {
        return this;
    }

    @Override
    public boolean matches(ItemStack stack) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isSpecific() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ItemStackSnapshot getTemplate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockType> getBlock() {
        return Optional.ofNullable(this.blockType);
    }

    @Override
    public int getMaxStackQuantity() {
        return this.maxQuantity;
    }

    private void setMaxStackQuantity(int max) {
        this.maxQuantity = max;
    }

    @Override
    public <T extends Property<?, ?>> Optional<T> getDefaultProperty(Class<T> propertyClass) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void registerTypes(FixedCatalogRegistryModule<ItemType> registry) {
        registry.register(new Builder(BlockTypes.AIR).build());
        registry.register(new Builder(BlockTypes.STONE).build());
        registry.register(new Builder(BlockTypes.GRASS).build());
        registry.register(new Builder(BlockTypes.DIRT).build());
        registry.register(new Builder(BlockTypes.COBBLESTONE).build());
        registry.register(new Builder(BlockTypes.PLANKS).build());
        registry.register(new Builder(BlockTypes.SAPLING).build());
        registry.register(new Builder(BlockTypes.BEDROCK).build());
        registry.register(new Builder(BlockTypes.SAND).build());
        registry.register(new Builder(BlockTypes.GRAVEL).build());
        registry.register(new Builder(BlockTypes.GOLD_ORE).build());
        registry.register(new Builder(BlockTypes.IRON_ORE).build());
        registry.register(new Builder(BlockTypes.COAL_ORE).build());
        registry.register(new Builder(BlockTypes.LOG).build());
        registry.register(new Builder(BlockTypes.LOG2).build());
        registry.register(new Builder(BlockTypes.LEAVES).build());
        registry.register(new Builder(BlockTypes.LEAVES2).build());
        registry.register(new Builder(BlockTypes.SPONGE).build());
        registry.register(new Builder(BlockTypes.GLASS).build());
        registry.register(new Builder(BlockTypes.LAPIS_ORE).build());
        registry.register(new Builder(BlockTypes.LAPIS_BLOCK).build());
        registry.register(new Builder(BlockTypes.DISPENSER).build());
        registry.register(new Builder(BlockTypes.SANDSTONE).build());
        registry.register(new Builder(BlockTypes.NOTEBLOCK).build());
        registry.register(new Builder(BlockTypes.GOLDEN_RAIL).build());
        registry.register(new Builder(BlockTypes.DETECTOR_RAIL).build());
        registry.register(new Builder(BlockTypes.STICKY_PISTON).build());
        registry.register(new Builder(BlockTypes.WEB).build());
        registry.register(new Builder(BlockTypes.TALLGRASS).build());
        registry.register(new Builder(BlockTypes.DEADBUSH).build());
        registry.register(new Builder(BlockTypes.PISTON).build());
        registry.register(new Builder(BlockTypes.WOOL).build());
        registry.register(new Builder(BlockTypes.YELLOW_FLOWER).build());
        registry.register(new Builder(BlockTypes.RED_FLOWER).build());
        registry.register(new Builder(BlockTypes.BROWN_MUSHROOM).build());
        registry.register(new Builder(BlockTypes.RED_MUSHROOM).build());
        registry.register(new Builder(BlockTypes.GOLD_BLOCK).build());
        registry.register(new Builder(BlockTypes.IRON_BLOCK).build());
        registry.register(new Builder(BlockTypes.STONE_SLAB).build());
        registry.register(new Builder(BlockTypes.BRICK_BLOCK).build());
        registry.register(new Builder(BlockTypes.TNT).build());
        registry.register(new Builder(BlockTypes.BOOKSHELF).build());
        registry.register(new Builder(BlockTypes.MOSSY_COBBLESTONE).build());
        registry.register(new Builder(BlockTypes.OBSIDIAN).build());
        registry.register(new Builder(BlockTypes.TORCH).build());
        registry.register(new Builder(BlockTypes.END_ROD).build());
        registry.register(new Builder(BlockTypes.CHORUS_PLANT).build());
        registry.register(new Builder(BlockTypes.CHORUS_FLOWER).build());
        registry.register(new Builder(BlockTypes.PURPUR_BLOCK).build());
        registry.register(new Builder(BlockTypes.PURPUR_PILLAR).build());
        registry.register(new Builder(BlockTypes.PURPUR_STAIRS).build());
        registry.register(new Builder(BlockTypes.PURPUR_SLAB).build());
        registry.register(new Builder(BlockTypes.MOB_SPAWNER).build());
        registry.register(new Builder(BlockTypes.OAK_STAIRS).build());
        registry.register(new Builder(BlockTypes.CHEST).build());
        registry.register(new Builder(BlockTypes.DIAMOND_ORE).build());
        registry.register(new Builder(BlockTypes.DIAMOND_BLOCK).build());
        registry.register(new Builder(BlockTypes.CRAFTING_TABLE).build());
        registry.register(new Builder(BlockTypes.FARMLAND).build());
        registry.register(new Builder(BlockTypes.FURNACE).build());
        registry.register(new Builder(BlockTypes.LADDER).build());
        registry.register(new Builder(BlockTypes.RAIL).build());
        registry.register(new Builder(BlockTypes.STONE_STAIRS).build());
        registry.register(new Builder(BlockTypes.LEVER).build());
        registry.register(new Builder(BlockTypes.STONE_PRESSURE_PLATE).build());
        registry.register(new Builder(BlockTypes.WOODEN_PRESSURE_PLATE).build());
        registry.register(new Builder(BlockTypes.REDSTONE_ORE).build());
        registry.register(new Builder(BlockTypes.REDSTONE_TORCH).build());
        registry.register(new Builder(BlockTypes.STONE_BUTTON).build());
        registry.register(new Builder(BlockTypes.SNOW_LAYER).build());
        registry.register(new Builder(BlockTypes.ICE).build());
        registry.register(new Builder(BlockTypes.SNOW).build());
        registry.register(new Builder(BlockTypes.CACTUS).build());
        registry.register(new Builder(BlockTypes.CLAY).build());
        registry.register(new Builder(BlockTypes.JUKEBOX).build());
        registry.register(new Builder(BlockTypes.FENCE).build());
        registry.register(new Builder(BlockTypes.SPRUCE_FENCE).build());
        registry.register(new Builder(BlockTypes.BIRCH_FENCE).build());
        registry.register(new Builder(BlockTypes.JUNGLE_FENCE).build());
        registry.register(new Builder(BlockTypes.DARK_OAK_FENCE).build());
        registry.register(new Builder(BlockTypes.ACACIA_FENCE).build());
        registry.register(new Builder(BlockTypes.PUMPKIN).build());
        registry.register(new Builder(BlockTypes.NETHERRACK).build());
        registry.register(new Builder(BlockTypes.SOUL_SAND).build());
        registry.register(new Builder(BlockTypes.GLOWSTONE).build());
        registry.register(new Builder(BlockTypes.LIT_PUMPKIN).build());
        registry.register(new Builder(BlockTypes.TRAPDOOR).build());
        registry.register(new Builder(BlockTypes.MONSTER_EGG).build());
        registry.register(new Builder(BlockTypes.STONEBRICK).build());
        registry.register(new Builder(BlockTypes.BROWN_MUSHROOM_BLOCK).build());
        registry.register(new Builder(BlockTypes.RED_MUSHROOM_BLOCK).build());
        registry.register(new Builder(BlockTypes.IRON_BARS).build());
        registry.register(new Builder(BlockTypes.GLASS_PANE).build());
        registry.register(new Builder(BlockTypes.MELON_BLOCK).build());
        registry.register(new Builder(BlockTypes.VINE).build());
        registry.register(new Builder(BlockTypes.FENCE_GATE).build());
        registry.register(new Builder(BlockTypes.SPRUCE_FENCE_GATE).build());
        registry.register(new Builder(BlockTypes.BIRCH_FENCE_GATE).build());
        registry.register(new Builder(BlockTypes.JUNGLE_FENCE_GATE).build());
        registry.register(new Builder(BlockTypes.DARK_OAK_FENCE_GATE).build());
        registry.register(new Builder(BlockTypes.ACACIA_FENCE_GATE).build());
        registry.register(new Builder(BlockTypes.BRICK_STAIRS).build());
        registry.register(new Builder(BlockTypes.STONE_BRICK_STAIRS).build());
        registry.register(new Builder(BlockTypes.MYCELIUM).build());
        registry.register(new Builder(BlockTypes.WATERLILY).build());
        registry.register(new Builder(BlockTypes.NETHER_BRICK).build());
        registry.register(new Builder(BlockTypes.NETHER_BRICK_FENCE).build());
        registry.register(new Builder(BlockTypes.NETHER_BRICK_STAIRS).build());
        registry.register(new Builder(BlockTypes.ENCHANTING_TABLE).build());
        registry.register(new Builder(BlockTypes.END_PORTAL_FRAME).build());
        registry.register(new Builder(BlockTypes.END_STONE).build());
        registry.register(new Builder(BlockTypes.END_BRICKS).build());
        registry.register(new Builder(BlockTypes.DRAGON_EGG).build());
        registry.register(new Builder(BlockTypes.REDSTONE_LAMP).build());
        registry.register(new Builder(BlockTypes.WOODEN_SLAB).build());
        registry.register(new Builder(BlockTypes.SANDSTONE_STAIRS).build());
        registry.register(new Builder(BlockTypes.EMERALD_ORE).build());
        registry.register(new Builder(BlockTypes.ENDER_CHEST).build());
        registry.register(new Builder(BlockTypes.TRIPWIRE_HOOK).build());
        registry.register(new Builder(BlockTypes.EMERALD_BLOCK).build());
        registry.register(new Builder(BlockTypes.SPRUCE_STAIRS).build());
        registry.register(new Builder(BlockTypes.BIRCH_STAIRS).build());
        registry.register(new Builder(BlockTypes.JUNGLE_STAIRS).build());
        registry.register(new Builder(BlockTypes.COMMAND_BLOCK).build());
        registry.register(new Builder(BlockTypes.BEACON).build());
        registry.register(new Builder(BlockTypes.COBBLESTONE_WALL).build());
        registry.register(new Builder(BlockTypes.WOODEN_BUTTON).build());
        registry.register(new Builder(BlockTypes.ANVIL).build());
        registry.register(new Builder(BlockTypes.TRAPPED_CHEST).build());
        registry.register(new Builder(BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE).build());
        registry.register(new Builder(BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE).build());
        registry.register(new Builder(BlockTypes.DAYLIGHT_DETECTOR).build());
        registry.register(new Builder(BlockTypes.REDSTONE_BLOCK).build());
        registry.register(new Builder(BlockTypes.QUARTZ_ORE).build());
        registry.register(new Builder(BlockTypes.HOPPER).build());
        registry.register(new Builder(BlockTypes.QUARTZ_BLOCK).build());
        registry.register(new Builder(BlockTypes.QUARTZ_STAIRS).build());
        registry.register(new Builder(BlockTypes.ACTIVATOR_RAIL).build());
        registry.register(new Builder(BlockTypes.DROPPER).build());
        registry.register(new Builder(BlockTypes.STAINED_HARDENED_CLAY).build());
        registry.register(new Builder(BlockTypes.BARRIER).build());
        registry.register(new Builder(BlockTypes.IRON_TRAPDOOR).build());
        registry.register(new Builder(BlockTypes.HAY_BLOCK).build());
        registry.register(new Builder(BlockTypes.CARPET).build());
        registry.register(new Builder(BlockTypes.HARDENED_CLAY).build());
        registry.register(new Builder(BlockTypes.COAL_BLOCK).build());
        registry.register(new Builder(BlockTypes.PACKED_ICE).build());
        registry.register(new Builder(BlockTypes.ACACIA_STAIRS).build());
        registry.register(new Builder(BlockTypes.DARK_OAK_STAIRS).build());
        registry.register(new Builder(BlockTypes.SLIME).build());
        registry.register(new Builder(BlockTypes.GRASS_PATH).build());
        registry.register(new Builder(BlockTypes.DOUBLE_PLANT).build());
        registry.register(new Builder(BlockTypes.STAINED_GLASS).build());
        registry.register(new Builder(BlockTypes.STAINED_GLASS_PANE).build());
        registry.register(new Builder(BlockTypes.PRISMARINE).build());
        registry.register(new Builder(BlockTypes.SEA_LANTERN).build());
        registry.register(new Builder(BlockTypes.RED_SANDSTONE).build());
        registry.register(new Builder(BlockTypes.RED_SANDSTONE_STAIRS).build());
        registry.register(new Builder(BlockTypes.STONE_SLAB2).build());
        registry.register(new Builder(BlockTypes.REPEATING_COMMAND_BLOCK).build());
        registry.register(new Builder(BlockTypes.CHAIN_COMMAND_BLOCK).build());
        registry.register(new Builder(BlockTypes.MAGMA).build());
        registry.register(new Builder(BlockTypes.NETHER_WART_BLOCK).build());
        registry.register(new Builder(BlockTypes.RED_NETHER_BRICK).build());
        registry.register(new Builder(BlockTypes.BONE_BLOCK).build());
        registry.register(new Builder(BlockTypes.STRUCTURE_VOID).build());
        registry.register(new Builder(BlockTypes.OBSERVER).build());
        registry.register(new Builder(BlockTypes.WHITE_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.ORANGE_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.MAGENTA_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.LIGHT_BLUE_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.YELLOW_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.LIME_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.PINK_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.GRAY_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.SILVER_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.CYAN_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.PURPLE_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.BLUE_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.BROWN_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.GREEN_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.RED_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.BLACK_SHULKER_BOX).build());
        registry.register(new Builder(BlockTypes.STRUCTURE_BLOCK).build());

        registry.register(new Builder("minecraft:iron_shovel", "Iron Shovel", 256).max(1).build());
        registry.register(new Builder("minecraft:iron_pickaxe", "Iron Pickaxe", 257).max(1).build());
        registry.register(new Builder("minecraft:iron_axe", "Iron Axe", 258).max(1).build());
        registry.register(new Builder("minecraft:flint_and_steel", "Flint and Steel", 259).max(1).build());
        registry.register(new Builder("minecraft:apple", "Apple", 260).build());
        registry.register(new Builder("minecraft:bow", "Bow", 261).max(1).build());
        registry.register(new Builder("minecraft:arrow", "Arrow", 262).build());
        registry.register(new Builder("minecraft:coal", "Coal", 263).build());
        registry.register(new Builder("minecraft:diamond", "Diamond", 264).build());
        registry.register(new Builder("minecraft:iron_ingot", "Iron Ingot", 265).build());
        registry.register(new Builder("minecraft:gold_ingot", "Gold Ingot", 266).build());
        registry.register(new Builder("minecraft:iron_sword", "Iron Sword", 267).max(1).build());
        registry.register(new Builder("minecraft:wooden_sword", "Wooden Sword", 268).max(1).build());
        registry.register(new Builder("minecraft:wooden_shovel", "Wooden Shovel", 269).max(1).build());
        registry.register(new Builder("minecraft:wooden_pickaxe", "Wooden Pickaxe", 270).max(1).build());
        registry.register(new Builder("minecraft:wooden_axe", "Wooden Axe", 271).max(1).build());
        registry.register(new Builder("minecraft:stone_sword", "Stone Sword", 272).max(1).build());
        registry.register(new Builder("minecraft:stone_shovel", "Stone Shovel", 273).max(1).build());
        registry.register(new Builder("minecraft:stone_pickaxe", "Stone Pickaxe", 274).max(1).build());
        registry.register(new Builder("minecraft:stone_axe", "Stone Axe", 275).max(1).build());
        registry.register(new Builder("minecraft:diamond_sword", "Diamond Sword", 276).max(1).build());
        registry.register(new Builder("minecraft:diamond_shovel", "Diamond Shovel", 277).max(1).build());
        registry.register(new Builder("minecraft:diamond_pickaxe", "Diamond Pickaxe", 278).max(1).build());
        registry.register(new Builder("minecraft:diamond_axe", "Diamond Axe", 279).max(1).build());
        registry.register(new Builder("minecraft:stick", "Stick", 280).build());
        registry.register(new Builder("minecraft:bowl", "Bowl", 281).build());
        registry.register(new Builder("minecraft:mushroom_stew", "Mushroom Stew", 282).build());
        registry.register(new Builder("minecraft:golden_sword", "Golden Sword", 283).max(1).build());
        registry.register(new Builder("minecraft:golden_shovel", "Golden Shovel", 284).max(1).build());
        registry.register(new Builder("minecraft:golden_pickaxe", "Golden Pickaxe", 285).max(1).build());
        registry.register(new Builder("minecraft:golden_axe", "Golden Axe", 286).max(1).build());
        registry.register(new Builder("minecraft:string", "String", 287).build());
        registry.register(new Builder("minecraft:feather", "Feather", 288).build());
        registry.register(new Builder("minecraft:gunpowder", "Sulfur", 289).build());
        registry.register(new Builder("minecraft:wooden_hoe", "Wooden Hoe", 290).max(1).build());
        registry.register(new Builder("minecraft:stone_hoe", "Stone Hoe", 291).max(1).build());
        registry.register(new Builder("minecraft:iron_hoe", "Iron Hoe", 292).max(1).build());
        registry.register(new Builder("minecraft:diamond_hoe", "Diamond Hoe", 293).max(1).build());
        registry.register(new Builder("minecraft:golden_hoe", "Golden Hoe", 294).max(1).build());
        registry.register(new Builder("minecraft:wheat_seeds", "Wheat Seeds", 295).build());
        registry.register(new Builder("minecraft:wheat", "Wheat", 296).build());
        registry.register(new Builder("minecraft:bread", "Bread", 297).build());
        registry.register(new Builder("minecraft:leather_helmet", "Leather Helmet", 298).max(1).build());
        registry.register(new Builder("minecraft:leather_chestplate", "Leather Chestplate", 299).max(1).build());
        registry.register(new Builder("minecraft:leather_leggings", "Leather Leggings", 300).max(1).build());
        registry.register(new Builder("minecraft:leather_boots", "Leather Boots", 301).max(1).build());
        registry.register(new Builder("minecraft:chainmail_helmet", "Chainmail Helmet", 302).max(1).build());
        registry.register(new Builder("minecraft:chainmail_chestplate", "Chainmail Chestplate", 303).max(1).build());
        registry.register(new Builder("minecraft:chainmail_leggings", "Chainmail Leggings", 304).max(1).build());
        registry.register(new Builder("minecraft:chainmail_boots", "Chainmail Boots", 305).max(1).build());
        registry.register(new Builder("minecraft:iron_helmet", "Iron Helmet", 306).max(1).build());
        registry.register(new Builder("minecraft:iron_chestplate", "Iron Chestplate", 307).max(1).build());
        registry.register(new Builder("minecraft:iron_leggings", "Iron Leggings", 308).max(1).build());
        registry.register(new Builder("minecraft:iron_boots", "Iron Boots", 309).max(1).build());
        registry.register(new Builder("minecraft:diamond_helmet", "Diamond Helmet", 310).max(1).build());
        registry.register(new Builder("minecraft:diamond_chestplate", "Diamond Chestplate", 311).max(1).build());
        registry.register(new Builder("minecraft:diamond_leggings", "Diamond Leggings", 312).max(1).build());
        registry.register(new Builder("minecraft:diamond_boots", "Diamond Boots", 313).max(1).build());
        registry.register(new Builder("minecraft:golden_helmet", "Golden Helmet", 314).max(1).build());
        registry.register(new Builder("minecraft:golden_chestplate", "Golden Chestplate", 315).max(1).build());
        registry.register(new Builder("minecraft:golden_leggings", "Golden Leggings", 316).max(1).build());
        registry.register(new Builder("minecraft:golden_boots", "Golden Boots", 317).max(1).build());
        registry.register(new Builder("minecraft:flint", "Flint", 318).build());
        registry.register(new Builder("minecraft:porkchop", "Raw Porkchop", 319).build());
        registry.register(new Builder("minecraft:cooked_porkchop", "Cooked Porkchop", 320).build());
        registry.register(new Builder("minecraft:painting", "Painting", 321).build());
        registry.register(new Builder("minecraft:golden_apple", "Golden Apple", 322).build());
        registry.register(new Builder("minecraft:sign", "Sign", 323).build());
        registry.register(new Builder("minecraft:wooden_door", "Wooden Door", 324).build());
        registry.register(new Builder("minecraft:bucket", "Bucket", 325).max(1).build());
        registry.register(new Builder("minecraft:water_bucket", "Water Bucket", 326).max(1).build());
        registry.register(new Builder("minecraft:lava_bucket", "Lava Bucket", 327).max(1).build());
        registry.register(new Builder("minecraft:minecart", "Minecart", 328).max(1).build());
        registry.register(new Builder("minecraft:saddle", "Saddle", 329).build());
        registry.register(new Builder("minecraft:iron_door", "Iron Door", 330).build());
        registry.register(new Builder("minecraft:redstone", "Redstone", 331).build());
        registry.register(new Builder("minecraft:snowball", "Snowball", 332).build());
        registry.register(new Builder("minecraft:boat", "Boat", 333).build());
        registry.register(new Builder("minecraft:leather", "Leather", 334).build());
        registry.register(new Builder("minecraft:milk_bucket", "Milk Bucket", 335).max(1).build());
        registry.register(new Builder("minecraft:brick", "Brick", 336).build());
        registry.register(new Builder("minecraft:clay_ball", "Clay Ball", 337).build());
        registry.register(new Builder("minecraft:reeds", "Reeds", 338).build());
        registry.register(new Builder("minecraft:paper", "Paper", 339).build());
        registry.register(new Builder("minecraft:book", "Book", 340).build());
        registry.register(new Builder("minecraft:slime_ball", "Slime Ball", 341).build());
        registry.register(new Builder("minecraft:chest_minecart", "Chest Minecart", 342).build());
        registry.register(new Builder("minecraft:furnace_minecart", "Furnace Minecart", 343).build());
        registry.register(new Builder("minecraft:egg", "Egg", 344).build());
        registry.register(new Builder("minecraft:compass", "Compass", 345).build());
        registry.register(new Builder("minecraft:fishing_rod", "Fishing Rod", 346).max(1).build());
        registry.register(new Builder("minecraft:clock", "Clock", 347).build());
        registry.register(new Builder("minecraft:glowstone_dust", "Glowstone Dust", 348).build());
        registry.register(new Builder("minecraft:fish", "Raw Fish", 349).build());
        registry.register(new Builder("minecraft:cooked_fish", "Cooked Fish", 350).build());
        registry.register(new Builder("minecraft:dye", "Dye", 351).build());
        registry.register(new Builder("minecraft:bone", "Bone", 352).build());
        registry.register(new Builder("minecraft:sugar", "Sugar", 353).build());
        registry.register(new Builder("minecraft:cake", "Cake", 354).build());
        registry.register(new Builder("minecraft:bed", "Bed", 355).build());
        registry.register(new Builder("minecraft:repeater", "Diode", 356).build());
        registry.register(new Builder("minecraft:cookie", "Cookie", 357).build());
        registry.register(new Builder("minecraft:filled_map", "Map", 358).build());
        registry.register(new Builder("minecraft:shears", "Shears", 359).max(1).build());
        registry.register(new Builder("minecraft:melon", "Melon", 360).build());
        registry.register(new Builder("minecraft:pumpkin_seeds", "Pumpkin Seeds", 361).build());
        registry.register(new Builder("minecraft:melon_seeds", "Melon Seeds", 362).build());
        registry.register(new Builder("minecraft:beef", "Raw Beef", 363).build());
        registry.register(new Builder("minecraft:cooked_beef", "Cooked Beef", 364).build());
        registry.register(new Builder("minecraft:chicken", "Raw Chicken", 365).build());
        registry.register(new Builder("minecraft:cooked_chicken", "Cooked Chicken", 366).build());
        registry.register(new Builder("minecraft:rotten_flesh", "Rotten Flesh", 367).build());
        registry.register(new Builder("minecraft:ender_pearl", "Ender Pearl", 368).build());
        registry.register(new Builder("minecraft:blaze_rod", "Blaze Rod", 369).build());
        registry.register(new Builder("minecraft:ghast_tear", "Ghast Tear", 370).build());
        registry.register(new Builder("minecraft:gold_nugget", "Gold Nugget", 371).build());
        registry.register(new Builder("minecraft:nether_wart", "Nether Wart", 372).build());
        registry.register(new Builder("minecraft:potion", "Potion", 373).max(1).build());
        registry.register(new Builder("minecraft:glass_bottle", "Glass Bottle", 374).build());
        registry.register(new Builder("minecraft:spider_eye", "Spider Eye", 375).build());
        registry.register(new Builder("minecraft:fermented_spider_eye", "Fermented Spider Eye", 376).build());
        registry.register(new Builder("minecraft:blaze_powder", "Blaze Powder", 377).build());
        registry.register(new Builder("minecraft:magma_cream", "Magma Cream", 378).build());
        registry.register(new Builder("minecraft:brewing_stand", "Brewing Stand", 379).build());
        registry.register(new Builder("minecraft:cauldron", "Cauldron", 380).build());
        registry.register(new Builder("minecraft:ender_eye", "Ender Eye", 381).build());
        registry.register(new Builder("minecraft:speckled_melon", "Speckled Melon", 382).build());
        registry.register(new Builder("minecraft:spawn_egg", "Spawn Egg", 383).build());
        registry.register(new Builder("minecraft:experience_bottle", "Experience Bottle", 384).build());
        registry.register(new Builder("minecraft:fire_charge", "Fire Charge", 385).build());
        registry.register(new Builder("minecraft:writable_book", "Writable Book", 386).build());
        registry.register(new Builder("minecraft:written_book", "Written Book", 387).build());
        registry.register(new Builder("minecraft:emerald", "Emerald", 388).build());
        registry.register(new Builder("minecraft:item_frame", "Item Frame", 389).build());
        registry.register(new Builder("minecraft:flower_pot", "Flower Pot", 390).build());
        registry.register(new Builder("minecraft:carrot", "Carrot", 391).build());
        registry.register(new Builder("minecraft:potato", "Potato", 392).build());
        registry.register(new Builder("minecraft:baked_potato", "Baked Potato", 393).build());
        registry.register(new Builder("minecraft:poisonous_potato", "Poisonous Potato", 394).build());
        registry.register(new Builder("minecraft:map", "Map", 395).build());
        registry.register(new Builder("minecraft:golden_carrot", "Golden Carrot", 396).build());
        registry.register(new Builder("minecraft:skull", "Skull", 397).build());
        registry.register(new Builder("minecraft:carrot_on_a_stick", "Carrot on a Stick", 398).build());
        registry.register(new Builder("minecraft:nether_star", "Nether Star", 399).build());
        registry.register(new Builder("minecraft:pumpkin_pie", "Pumpkin Pie", 400).build());
        registry.register(new Builder("minecraft:fireworks", "Fireworks", 401).build());
        registry.register(new Builder("minecraft:firework_charge", "Firework Charge", 402).build());
        registry.register(new Builder("minecraft:enchanted_book", "Enchanted Book", 403).build());
        registry.register(new Builder("minecraft:comparator", "Comparator", 404).build());
        registry.register(new Builder("minecraft:netherbrick", "Netherbrick", 405).build());
        registry.register(new Builder("minecraft:quartz", "Quartz", 406).build());
        registry.register(new Builder("minecraft:tnt_minecart", "TNT Minecart", 407).max(1).build());
        registry.register(new Builder("minecraft:hopper_minecart", "Hopper Minecart", 408).max(1).build());
        registry.register(new Builder("minecraft:prismarine_shard", "Prismarine Shard", 409).build());
        registry.register(new Builder("minecraft:prismarine_crystals", "Prismarine Crystals", 410).build());
        registry.register(new Builder("minecraft:rabbit", "Raw Rabbit", 411).build());
        registry.register(new Builder("minecraft:cooked_rabbit", "Cooked Rabbit", 412).build());
        registry.register(new Builder("minecraft:rabbit_stew", "Rabbit Stew", 413).build());
        registry.register(new Builder("minecraft:rabbit_foot", "Rabbit Foot", 414).build());
        registry.register(new Builder("minecraft:rabbit_hide", "Rabbit Hide", 415).build());
        registry.register(new Builder("minecraft:armor_stand", "Armor Stand", 416).build());
        registry.register(new Builder("minecraft:iron_horse_armor", "Iron Horse Armor", 417).max(1).build());
        registry.register(new Builder("minecraft:golden_horse_armor", "Golden Horse Armor", 418).max(1).build());
        registry.register(new Builder("minecraft:diamond_horse_armor", "Diamond Horse Armor", 419).max(1).build());
        registry.register(new Builder("minecraft:lead", "Lead", 420).build());
        registry.register(new Builder("minecraft:name_tag", "Name Tag", 421).build());
        registry.register(new Builder("minecraft:command_block_minecart", "Command Block Minecart", 422).max(1).build());
        registry.register(new Builder("minecraft:mutton", "Raw Mutton", 423).build());
        registry.register(new Builder("minecraft:cooked_mutton", "Cooked Mutton", 424).build());
        registry.register(new Builder("minecraft:banner", "Banner", 425).build());
        registry.register(new Builder("minecraft:end_crystal", "End Crystal", 426).build());
        registry.register(new Builder("minecraft:spruce_door", "Spruce Door", 427).build());
        registry.register(new Builder("minecraft:birch_door", "Birch Door", 428).build());
        registry.register(new Builder("minecraft:jungle_door", "Jungle Door", 429).build());
        registry.register(new Builder("minecraft:acacia_door", "Acacia Door", 430).build());
        registry.register(new Builder("minecraft:dark_oak_door", "Dark Oak Door", 431).build());
        registry.register(new Builder("minecraft:chorus_fruit", "Chorus Fruit", 432).build());
        registry.register(new Builder("minecraft:chorus_fruit_popped", "Popped Chorus Fruit", 433).build());
        registry.register(new Builder("minecraft:beetroot", "Beetroot", 434).build());
        registry.register(new Builder("minecraft:beetroot_seeds", "Beetroot Seeds", 435).build());
        registry.register(new Builder("minecraft:beetroot_soup", "Beetroot Soup", 436).build());
        registry.register(new Builder("minecraft:dragon_breath", "Dragon Breath", 437).build());
        registry.register(new Builder("minecraft:splash_potion", "Splash Potion", 438).max(1).build());
        registry.register(new Builder("minecraft:spectral_arrow", "Spectral Arrow", 439).build());
        registry.register(new Builder("minecraft:tipped_arrow", "Tipped Arrow", 440).build());
        registry.register(new Builder("minecraft:lingering_potion", "Lingering Potion", 441).max(1).build());
        registry.register(new Builder("minecraft:shield", "Shield", 442).build());
        registry.register(new Builder("minecraft:elytra", "Elytra", 443).build());
        registry.register(new Builder("minecraft:spruce_boat", "Spruce Boat", 444).build());
        registry.register(new Builder("minecraft:birch_boat", "Birch Boat", 445).build());
        registry.register(new Builder("minecraft:jungle_boat", "Jungle Boat", 446).build());
        registry.register(new Builder("minecraft:acacia_boat", "Acacia Boat", 447).build());
        registry.register(new Builder("minecraft:dark_oak_boat", "Dark Oak Boat", 448).build());
        registry.register(new Builder("minecraft:totem_of_undying", "Totem of Undying", 449).max(1).build());
        registry.register(new Builder("minecraft:shulker_shell", "Shulker Shell", 450).build());
        registry.register(new Builder("minecraft:iron_nugget", "Iron Nugget", 452).build());

        registry.register(new Builder("minecraft:record_13", "13", 2256).max(1).build());
        registry.register(new Builder("minecraft:record_cat", "cat", 2257).max(1).build());
        registry.register(new Builder("minecraft:record_blocks", "blocks", 2258).max(1).build());
        registry.register(new Builder("minecraft:record_chirp", "chirp", 2259).max(1).build());
        registry.register(new Builder("minecraft:record_far", "far", 2260).max(1).build());
        registry.register(new Builder("minecraft:record_mall", "mall", 2261).max(1).build());
        registry.register(new Builder("minecraft:record_mellohi", "mallohi", 2262).max(1).build());
        registry.register(new Builder("minecraft:record_stal", "stal", 2263).max(1).build());
        registry.register(new Builder("minecraft:record_strad", "strad", 2264).max(1).build());
        registry.register(new Builder("minecraft:record_ward", "ward", 2265).max(1).build());
        registry.register(new Builder("minecraft:record_11", "11", 2266).max(1).build());
        registry.register(new Builder("minecraft:record_wait", "wait", 2267).max(1).build());
        
        registry.registerAlias("minecraft:none", "minecraft:air");
    }

    private static class Builder {

        private final String id;
        private final String name;
        private final int itemid;
        private final BlockType block;

        private int max = 64;

        public Builder(String id, String name, int itemid) {
            this.id = id;
            this.name = name;
            this.itemid = itemid;
            this.block = null;
        }

        public Builder(BlockType block) {
            this.id = null;
            this.name = null;
            this.itemid = -1;
            this.block = block;
        }

        public Builder max(int max) {
            this.max = max;
            return this;
        }

        public SItemType build() {
            SItemType item;
            if (this.block == null) {
                item = new SItemType(this.id, this.name, this.itemid);
            } else {
                item = new SItemType(this.block);
            }
            item.setMaxStackQuantity(this.max);
            return item;
        }

    }

}

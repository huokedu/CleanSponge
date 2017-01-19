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

    public SItemType(String id, String name, int itemid) {
        super(id, name);
        this.blockType = null;
        this.itemId = itemid;
    }

    public SItemType(BlockType block) {
        super(block.getId(), block.getName());
        this.blockType = block;
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
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <T extends Property<?, ?>> Optional<T> getDefaultProperty(Class<T> propertyClass) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void registerTypes(FixedCatalogRegistryModule<ItemType> registry) {
        registry.register(new SItemType(BlockTypes.AIR));
        registry.register(new SItemType(BlockTypes.STONE));
        registry.register(new SItemType(BlockTypes.GRASS));
        registry.register(new SItemType(BlockTypes.DIRT));
        registry.register(new SItemType(BlockTypes.COBBLESTONE));
        registry.register(new SItemType(BlockTypes.PLANKS));
        registry.register(new SItemType(BlockTypes.SAPLING));
        registry.register(new SItemType(BlockTypes.BEDROCK));
        registry.register(new SItemType(BlockTypes.SAND));
        registry.register(new SItemType(BlockTypes.GRAVEL));
        registry.register(new SItemType(BlockTypes.GOLD_ORE));
        registry.register(new SItemType(BlockTypes.IRON_ORE));
        registry.register(new SItemType(BlockTypes.COAL_ORE));
        registry.register(new SItemType(BlockTypes.LOG));
        registry.register(new SItemType(BlockTypes.LOG2));
        registry.register(new SItemType(BlockTypes.LEAVES));
        registry.register(new SItemType(BlockTypes.LEAVES2));
        registry.register(new SItemType(BlockTypes.SPONGE));
        registry.register(new SItemType(BlockTypes.GLASS));
        registry.register(new SItemType(BlockTypes.LAPIS_ORE));
        registry.register(new SItemType(BlockTypes.LAPIS_BLOCK));
        registry.register(new SItemType(BlockTypes.DISPENSER));
        registry.register(new SItemType(BlockTypes.SANDSTONE));
        registry.register(new SItemType(BlockTypes.NOTEBLOCK));
        registry.register(new SItemType(BlockTypes.GOLDEN_RAIL));
        registry.register(new SItemType(BlockTypes.DETECTOR_RAIL));
        registry.register(new SItemType(BlockTypes.STICKY_PISTON));
        registry.register(new SItemType(BlockTypes.WEB));
        registry.register(new SItemType(BlockTypes.TALLGRASS));
        registry.register(new SItemType(BlockTypes.DEADBUSH));
        registry.register(new SItemType(BlockTypes.PISTON));
        registry.register(new SItemType(BlockTypes.WOOL));
        registry.register(new SItemType(BlockTypes.YELLOW_FLOWER));
        registry.register(new SItemType(BlockTypes.RED_FLOWER));
        registry.register(new SItemType(BlockTypes.BROWN_MUSHROOM));
        registry.register(new SItemType(BlockTypes.RED_MUSHROOM));
        registry.register(new SItemType(BlockTypes.GOLD_BLOCK));
        registry.register(new SItemType(BlockTypes.IRON_BLOCK));
        registry.register(new SItemType(BlockTypes.STONE_SLAB));
        registry.register(new SItemType(BlockTypes.BRICK_BLOCK));
        registry.register(new SItemType(BlockTypes.TNT));
        registry.register(new SItemType(BlockTypes.BOOKSHELF));
        registry.register(new SItemType(BlockTypes.MOSSY_COBBLESTONE));
        registry.register(new SItemType(BlockTypes.OBSIDIAN));
        registry.register(new SItemType(BlockTypes.TORCH));
        registry.register(new SItemType(BlockTypes.END_ROD));
        registry.register(new SItemType(BlockTypes.CHORUS_PLANT));
        registry.register(new SItemType(BlockTypes.CHORUS_FLOWER));
        registry.register(new SItemType(BlockTypes.PURPUR_BLOCK));
        registry.register(new SItemType(BlockTypes.PURPUR_PILLAR));
        registry.register(new SItemType(BlockTypes.PURPUR_STAIRS));
        registry.register(new SItemType(BlockTypes.PURPUR_SLAB));
        registry.register(new SItemType(BlockTypes.MOB_SPAWNER));
        registry.register(new SItemType(BlockTypes.OAK_STAIRS));
        registry.register(new SItemType(BlockTypes.CHEST));
        registry.register(new SItemType(BlockTypes.DIAMOND_ORE));
        registry.register(new SItemType(BlockTypes.DIAMOND_BLOCK));
        registry.register(new SItemType(BlockTypes.CRAFTING_TABLE));
        registry.register(new SItemType(BlockTypes.FARMLAND));
        registry.register(new SItemType(BlockTypes.FURNACE));
        registry.register(new SItemType(BlockTypes.LADDER));
        registry.register(new SItemType(BlockTypes.RAIL));
        registry.register(new SItemType(BlockTypes.STONE_STAIRS));
        registry.register(new SItemType(BlockTypes.LEVER));
        registry.register(new SItemType(BlockTypes.STONE_PRESSURE_PLATE));
        registry.register(new SItemType(BlockTypes.WOODEN_PRESSURE_PLATE));
        registry.register(new SItemType(BlockTypes.REDSTONE_ORE));
        registry.register(new SItemType(BlockTypes.REDSTONE_TORCH));
        registry.register(new SItemType(BlockTypes.STONE_BUTTON));
        registry.register(new SItemType(BlockTypes.SNOW_LAYER));
        registry.register(new SItemType(BlockTypes.ICE));
        registry.register(new SItemType(BlockTypes.SNOW));
        registry.register(new SItemType(BlockTypes.CACTUS));
        registry.register(new SItemType(BlockTypes.CLAY));
        registry.register(new SItemType(BlockTypes.JUKEBOX));
        registry.register(new SItemType(BlockTypes.FENCE));
        registry.register(new SItemType(BlockTypes.SPRUCE_FENCE));
        registry.register(new SItemType(BlockTypes.BIRCH_FENCE));
        registry.register(new SItemType(BlockTypes.JUNGLE_FENCE));
        registry.register(new SItemType(BlockTypes.DARK_OAK_FENCE));
        registry.register(new SItemType(BlockTypes.ACACIA_FENCE));
        registry.register(new SItemType(BlockTypes.PUMPKIN));
        registry.register(new SItemType(BlockTypes.NETHERRACK));
        registry.register(new SItemType(BlockTypes.SOUL_SAND));
        registry.register(new SItemType(BlockTypes.GLOWSTONE));
        registry.register(new SItemType(BlockTypes.LIT_PUMPKIN));
        registry.register(new SItemType(BlockTypes.TRAPDOOR));
        registry.register(new SItemType(BlockTypes.MONSTER_EGG));
        registry.register(new SItemType(BlockTypes.STONEBRICK));
        registry.register(new SItemType(BlockTypes.BROWN_MUSHROOM_BLOCK));
        registry.register(new SItemType(BlockTypes.RED_MUSHROOM_BLOCK));
        registry.register(new SItemType(BlockTypes.IRON_BARS));
        registry.register(new SItemType(BlockTypes.GLASS_PANE));
        registry.register(new SItemType(BlockTypes.MELON_BLOCK));
        registry.register(new SItemType(BlockTypes.VINE));
        registry.register(new SItemType(BlockTypes.FENCE_GATE));
        registry.register(new SItemType(BlockTypes.SPRUCE_FENCE_GATE));
        registry.register(new SItemType(BlockTypes.BIRCH_FENCE_GATE));
        registry.register(new SItemType(BlockTypes.JUNGLE_FENCE_GATE));
        registry.register(new SItemType(BlockTypes.DARK_OAK_FENCE_GATE));
        registry.register(new SItemType(BlockTypes.ACACIA_FENCE_GATE));
        registry.register(new SItemType(BlockTypes.BRICK_STAIRS));
        registry.register(new SItemType(BlockTypes.STONE_BRICK_STAIRS));
        registry.register(new SItemType(BlockTypes.MYCELIUM));
        registry.register(new SItemType(BlockTypes.NETHER_BRICK));
        registry.register(new SItemType(BlockTypes.NETHER_BRICK_FENCE));
        registry.register(new SItemType(BlockTypes.NETHER_BRICK_STAIRS));
        registry.register(new SItemType(BlockTypes.ENCHANTING_TABLE));
        registry.register(new SItemType(BlockTypes.END_PORTAL_FRAME));
        registry.register(new SItemType(BlockTypes.END_STONE));
        registry.register(new SItemType(BlockTypes.END_BRICKS));
        registry.register(new SItemType(BlockTypes.DRAGON_EGG));
        registry.register(new SItemType(BlockTypes.REDSTONE_LAMP));
        registry.register(new SItemType(BlockTypes.WOODEN_SLAB));
        registry.register(new SItemType(BlockTypes.SANDSTONE_STAIRS));
        registry.register(new SItemType(BlockTypes.EMERALD_ORE));
        registry.register(new SItemType(BlockTypes.ENDER_CHEST));
        registry.register(new SItemType(BlockTypes.TRIPWIRE_HOOK));
        registry.register(new SItemType(BlockTypes.EMERALD_BLOCK));
        registry.register(new SItemType(BlockTypes.SPRUCE_STAIRS));
        registry.register(new SItemType(BlockTypes.BIRCH_STAIRS));
        registry.register(new SItemType(BlockTypes.JUNGLE_STAIRS));
        registry.register(new SItemType(BlockTypes.COMMAND_BLOCK));
        registry.register(new SItemType(BlockTypes.BEACON));
        registry.register(new SItemType(BlockTypes.COBBLESTONE_WALL));
        registry.register(new SItemType(BlockTypes.WOODEN_BUTTON));
        registry.register(new SItemType(BlockTypes.ANVIL));
        registry.register(new SItemType(BlockTypes.TRAPPED_CHEST));
        registry.register(new SItemType(BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE));
        registry.register(new SItemType(BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE));
        registry.register(new SItemType(BlockTypes.DAYLIGHT_DETECTOR));
        registry.register(new SItemType(BlockTypes.REDSTONE_BLOCK));
        registry.register(new SItemType(BlockTypes.QUARTZ_ORE));
        registry.register(new SItemType(BlockTypes.HOPPER));
        registry.register(new SItemType(BlockTypes.QUARTZ_BLOCK));
        registry.register(new SItemType(BlockTypes.QUARTZ_STAIRS));
        registry.register(new SItemType(BlockTypes.ACTIVATOR_RAIL));
        registry.register(new SItemType(BlockTypes.DROPPER));
        registry.register(new SItemType(BlockTypes.STAINED_HARDENED_CLAY));
        registry.register(new SItemType(BlockTypes.BARRIER));
        registry.register(new SItemType(BlockTypes.IRON_TRAPDOOR));
        registry.register(new SItemType(BlockTypes.HAY_BLOCK));
        registry.register(new SItemType(BlockTypes.CARPET));
        registry.register(new SItemType(BlockTypes.HARDENED_CLAY));
        registry.register(new SItemType(BlockTypes.COAL_BLOCK));
        registry.register(new SItemType(BlockTypes.PACKED_ICE));
        registry.register(new SItemType(BlockTypes.ACACIA_STAIRS));
        registry.register(new SItemType(BlockTypes.DARK_OAK_STAIRS));
        registry.register(new SItemType(BlockTypes.SLIME));
        registry.register(new SItemType(BlockTypes.GRASS_PATH));
        registry.register(new SItemType(BlockTypes.DOUBLE_PLANT));
        registry.register(new SItemType(BlockTypes.STAINED_GLASS));
        registry.register(new SItemType(BlockTypes.STAINED_GLASS_PANE));
        registry.register(new SItemType(BlockTypes.PRISMARINE));
        registry.register(new SItemType(BlockTypes.SEA_LANTERN));
        registry.register(new SItemType(BlockTypes.RED_SANDSTONE));
        registry.register(new SItemType(BlockTypes.RED_SANDSTONE_STAIRS));
        registry.register(new SItemType(BlockTypes.STONE_SLAB2));
        registry.register(new SItemType(BlockTypes.REPEATING_COMMAND_BLOCK));
        registry.register(new SItemType(BlockTypes.CHAIN_COMMAND_BLOCK));
        registry.register(new SItemType(BlockTypes.MAGMA));
        registry.register(new SItemType(BlockTypes.NETHER_WART_BLOCK));
        registry.register(new SItemType(BlockTypes.RED_NETHER_BRICK));
        registry.register(new SItemType(BlockTypes.BONE_BLOCK));
        registry.register(new SItemType(BlockTypes.STRUCTURE_VOID));
        registry.register(new SItemType(BlockTypes.OBSERVER));
        registry.register(new SItemType(BlockTypes.WHITE_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.ORANGE_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.MAGENTA_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.LIGHT_BLUE_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.YELLOW_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.LIME_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.PINK_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.GRAY_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.SILVER_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.CYAN_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.PURPLE_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.BLUE_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.BROWN_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.GREEN_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.RED_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.BLACK_SHULKER_BOX));
        registry.register(new SItemType(BlockTypes.STRUCTURE_BLOCK));

        registry.register(new SItemType("minecraft:iron_shovel", "Iron Shovel", 256));
        registry.register(new SItemType("minecraft:iron_pickaxe", "Iron Pickaxe", 257));
        registry.register(new SItemType("minecraft:iron_axe", "Iron Axe", 258));
        registry.register(new SItemType("minecraft:flint_and_steel", "Flint and Steel", 259));
        registry.register(new SItemType("minecraft:apple", "Apple", 260));
        registry.register(new SItemType("minecraft:bow", "Bow", 261));
        registry.register(new SItemType("minecraft:arrow", "Arrow", 262));
        registry.register(new SItemType("minecraft:coal", "Coal", 263));
        registry.register(new SItemType("minecraft:diamond", "Diamond", 264));
        registry.register(new SItemType("minecraft:iron_ingot", "Iron Ingot", 265));
        registry.register(new SItemType("minecraft:gold_ingot", "Gold Ingot", 266));
        registry.register(new SItemType("minecraft:iron_sword", "Iron Sword", 267));
        registry.register(new SItemType("minecraft:wooden_sword", "Wooden Sword", 268));
        registry.register(new SItemType("minecraft:wooden_shovel", "Wooden Shovel", 269));
        registry.register(new SItemType("minecraft:wooden_pickaxe", "Wooden Pickaxe", 270));
        registry.register(new SItemType("minecraft:wooden_axe", "Wooden Axe", 271));
        registry.register(new SItemType("minecraft:stone_sword", "Stone Sword", 272));
        registry.register(new SItemType("minecraft:stone_shovel", "Stone Shovel", 273));
        registry.register(new SItemType("minecraft:stone_pickaxe", "Stone Pickaxe", 274));
        registry.register(new SItemType("minecraft:stone_axe", "Stone Axe", 275));
        registry.register(new SItemType("minecraft:diamond_sword", "Diamond Sword", 276));
        registry.register(new SItemType("minecraft:diamond_shovel", "Diamond Shovel", 277));
        registry.register(new SItemType("minecraft:diamond_pickaxe", "Diamond Pickaxe", 278));
        registry.register(new SItemType("minecraft:diamond_axe", "Diamond Axe", 279));
        registry.register(new SItemType("minecraft:stick", "Stick", 280));
        registry.register(new SItemType("minecraft:bowl", "Bowl", 281));
        registry.register(new SItemType("minecraft:mushroom_stew", "Mushroom Stew", 282));
        registry.register(new SItemType("minecraft:golden_sword", "Golden Sword", 283));
        registry.register(new SItemType("minecraft:golden_shovel", "Golden Shovel", 284));
        registry.register(new SItemType("minecraft:golden_pickaxe", "Golden Pickaxe", 285));
        registry.register(new SItemType("minecraft:golden_axe", "Golden Axe", 286));
        registry.register(new SItemType("minecraft:string", "String", 287));
        registry.register(new SItemType("minecraft:feather", "Feather", 288));
        registry.register(new SItemType("minecraft:gunpowder", "Sulfur", 289));
        registry.register(new SItemType("minecraft:wooden_hoe", "Wooden Hoe", 290));
        registry.register(new SItemType("minecraft:stone_hoe", "Stone Hoe", 291));
        registry.register(new SItemType("minecraft:iron_hoe", "Iron Hoe", 292));
        registry.register(new SItemType("minecraft:diamond_hoe", "Diamond Hoe", 293));
        registry.register(new SItemType("minecraft:golden_hoe", "Golden Hoe", 294));
        registry.register(new SItemType("minecraft:wheat_seeds", "Wheat Seeds", 295));
        registry.register(new SItemType("minecraft:wheat", "Wheat", 296));
        registry.register(new SItemType("minecraft:bread", "Bread", 297));
        registry.register(new SItemType("minecraft:leather_helmet", "Leather Helmet", 298));
        registry.register(new SItemType("minecraft:leather_chestplate", "Leather Chestplate", 299));
        registry.register(new SItemType("minecraft:leather_leggings", "Leather Leggings", 300));
        registry.register(new SItemType("minecraft:leather_boots", "Leather Boots", 301));
        registry.register(new SItemType("minecraft:chainmail_helmet", "Chainmail Helmet", 302));
        registry.register(new SItemType("minecraft:chainmail_chestplate", "Chainmail Chestplate", 303));
        registry.register(new SItemType("minecraft:chainmail_leggings", "Chainmail Leggings", 304));
        registry.register(new SItemType("minecraft:chainmail_boots", "Chainmail Boots", 305));
        registry.register(new SItemType("minecraft:iron_helmet", "Iron Helmet", 306));
        registry.register(new SItemType("minecraft:iron_chestplate", "Iron Chestplate", 307));
        registry.register(new SItemType("minecraft:iron_leggings", "Iron Leggings", 308));
        registry.register(new SItemType("minecraft:iron_boots", "Iron Boots", 309));
        registry.register(new SItemType("minecraft:diamond_helmet", "Diamond Helmet", 310));
        registry.register(new SItemType("minecraft:diamond_chestplate", "Diamond Chestplate", 311));
        registry.register(new SItemType("minecraft:diamond_leggings", "Diamond Leggings", 312));
        registry.register(new SItemType("minecraft:diamond_boots", "Diamond Boots", 313));
        registry.register(new SItemType("minecraft:golden_helmet", "Golden Helmet", 314));
        registry.register(new SItemType("minecraft:golden_chestplate", "Golden Chestplate", 315));
        registry.register(new SItemType("minecraft:golden_leggings", "Golden Leggings", 316));
        registry.register(new SItemType("minecraft:golden_boots", "Golden Boots", 317));
        registry.register(new SItemType("minecraft:flint", "Flint", 318));
        registry.register(new SItemType("minecraft:porkchop", "Raw Porkchop", 319));
        registry.register(new SItemType("minecraft:cooked_porkchop", "Cooked Porkchop", 320));
        registry.register(new SItemType("minecraft:painting", "Painting", 321));
        registry.register(new SItemType("minecraft:golden_apple", "Golden Apple", 322));
        registry.register(new SItemType("minecraft:sign", "Sign", 323));
        registry.register(new SItemType("minecraft:wooden_door", "Wooden Door", 324));
        registry.register(new SItemType("minecraft:bucket", "Bucket", 325));
        registry.register(new SItemType("minecraft:water_bucket", "Water Bucket", 326));
        registry.register(new SItemType("minecraft:lava_bucket", "Lava Bucket", 327));
        registry.register(new SItemType("minecraft:minecart", "Minecart", 328));
        registry.register(new SItemType("minecraft:saddle", "Saddle", 329));
        registry.register(new SItemType("minecraft:iron_door", "Iron Door", 330));
        registry.register(new SItemType("minecraft:redstone", "Redstone", 331));
        registry.register(new SItemType("minecraft:snowball", "Snowball", 332));
        registry.register(new SItemType("minecraft:boat", "Boat", 333));
        registry.register(new SItemType("minecraft:leather", "Leather", 334));
        registry.register(new SItemType("minecraft:milk_bucket", "Milk Bucket", 335));
        registry.register(new SItemType("minecraft:brick", "Brick", 336));
        registry.register(new SItemType("minecraft:clay_ball", "Clay Ball", 337));
        registry.register(new SItemType("minecraft:reeds", "Reeds", 338));
        registry.register(new SItemType("minecraft:paper", "Paper", 339));
        registry.register(new SItemType("minecraft:book", "Book", 340));
        registry.register(new SItemType("minecraft:slime_ball", "Slime Ball", 341));
        registry.register(new SItemType("minecraft:chest_minecart", "Chest Minecart", 342));
        registry.register(new SItemType("minecraft:furnace_minecart", "Furnace Minecart", 343));
        registry.register(new SItemType("minecraft:egg", "Egg", 344));
        registry.register(new SItemType("minecraft:compass", "Compass", 345));
        registry.register(new SItemType("minecraft:fishing_rod", "Fishing Rod", 346));
        registry.register(new SItemType("minecraft:clock", "Clock", 347));
        registry.register(new SItemType("minecraft:glowstone_dust", "Glowstone Dust", 348));
        registry.register(new SItemType("minecraft:fish", "Raw Fish", 349));
        registry.register(new SItemType("minecraft:cooked_fish", "Cooked Fish", 350));
        registry.register(new SItemType("minecraft:dye", "Dye", 351));
        registry.register(new SItemType("minecraft:bone", "Bone", 352));
        registry.register(new SItemType("minecraft:suger", "Suger", 353));
        registry.register(new SItemType("minecraft:cake", "Cake", 354));
        registry.register(new SItemType("minecraft:bed", "Bed", 355));
        registry.register(new SItemType("minecraft:repeater", "Diode", 356));
        registry.register(new SItemType("minecraft:cookie", "Cookie", 357));
        registry.register(new SItemType("minecraft:filled_map", "Map", 358));
        registry.register(new SItemType("minecraft:shears", "Shears", 359));
        registry.register(new SItemType("minecraft:melon", "Melon", 360));
        registry.register(new SItemType("minecraft:pumpkin_seeds", "Pumpkin Seeds", 361));
        registry.register(new SItemType("minecraft:melon_seeds", "Melon Seeds", 362));
        registry.register(new SItemType("minecraft:beef", "Raw Beef", 363));
        registry.register(new SItemType("minecraft:cooked_beef", "Cooked Beef", 364));
        registry.register(new SItemType("minecraft:chicken", "Raw Chicken", 365));
        registry.register(new SItemType("minecraft:cooked_chicken", "Cooked Chicken", 366));
        registry.register(new SItemType("minecraft:rotten_flesh", "Rotten Flesh", 367));
        registry.register(new SItemType("minecraft:ender_pearl", "Ender Pearl", 368));
        registry.register(new SItemType("minecraft:blaze_rod", "Blaze Rod", 369));
        registry.register(new SItemType("minecraft:ghast_tear", "Ghast Tear", 370));
        registry.register(new SItemType("minecraft:gold_nugget", "Gold Nugget", 371));
        registry.register(new SItemType("minecraft:nether_wart", "Nether Wart", 372));
        registry.register(new SItemType("minecraft:potion", "Potion", 373));
        registry.register(new SItemType("minecraft:glass_bottle", "Glass Bottle", 374));
        registry.register(new SItemType("minecraft:spider_eye", "Spider Eye", 375));
        registry.register(new SItemType("minecraft:fermented_spider_eye", "Fermented Spider Eye", 376));
        registry.register(new SItemType("minecraft:blaze_powder", "Blaze Powder", 377));
        registry.register(new SItemType("minecraft:magma_cream", "Magma Cream", 378));
        registry.register(new SItemType("minecraft:brewing_stand", "Brewing Stand", 379));
        registry.register(new SItemType("minecraft:cauldron", "Cauldron", 380));
        registry.register(new SItemType("minecraft:ender_eye", "Ender Eye", 381));
        registry.register(new SItemType("minecraft:speckled_melon", "Speckled Melon", 382));
        registry.register(new SItemType("minecraft:spawn_egg", "Spawn Egg", 383));
        registry.register(new SItemType("minecraft:experience_bottle", "Experience Bottle", 384));
        registry.register(new SItemType("minecraft:fire_charge", "Fire Charge", 385));
        registry.register(new SItemType("minecraft:writable_book", "Writable Book", 386));
        registry.register(new SItemType("minecraft:written_book", "Written Book", 387));
        registry.register(new SItemType("minecraft:emerald", "Emerald", 388));
        registry.register(new SItemType("minecraft:item_frame", "Item Frame", 389));
        registry.register(new SItemType("minecraft:flower_pot", "Flower Pot", 390));
        registry.register(new SItemType("minecraft:carrot", "Carrot", 391));
        registry.register(new SItemType("minecraft:potato", "Potato", 392));
        registry.register(new SItemType("minecraft:baked_potato", "Baked Potato", 393));
        registry.register(new SItemType("minecraft:poisonous_potato", "Poisonous Potato", 394));
        registry.register(new SItemType("minecraft:map", "Map", 395));
        registry.register(new SItemType("minecraft:golden_carrot", "Golden Carrot", 396));
        registry.register(new SItemType("minecraft:skull", "Skull", 397));
        registry.register(new SItemType("minecraft:carrot_on_a_stick", "Carrot on a Stick", 398));
        registry.register(new SItemType("minecraft:nether_star", "Nether Star", 399));
        registry.register(new SItemType("minecraft:pumpkin_pie", "Pumpkin Pie", 400));
        registry.register(new SItemType("minecraft:fireworks", "Fireworks", 401));
        registry.register(new SItemType("minecraft:firework_charge", "Firework Charge", 402));
        registry.register(new SItemType("minecraft:enchanted_book", "Enchanted Book", 403));
        registry.register(new SItemType("minecraft:comparator", "Comparator", 404));
        registry.register(new SItemType("minecraft:netherbrick", "Netherbrick", 405));
        registry.register(new SItemType("minecraft:quartz", "Quartz", 406));
        registry.register(new SItemType("minecraft:tnt_minecart", "TNT Minecart", 407));
        registry.register(new SItemType("minecraft:hopper_minecart", "Hopper Minecart", 408));
        registry.register(new SItemType("minecraft:prismarine_shard", "Prismarine Shard", 409));
        registry.register(new SItemType("minecraft:prismarine_crystals", "Prismarine Crystals", 410));
        registry.register(new SItemType("minecraft:rabbit", "Raw Rabbit", 411));
        registry.register(new SItemType("minecraft:cooked_rabbit", "Cooked Rabbit", 412));
        registry.register(new SItemType("minecraft:rabbit_stew", "Rabbit Stew", 413));
        registry.register(new SItemType("minecraft:rabbit_foot", "Rabbit Foot", 414));
        registry.register(new SItemType("minecraft:rabbit_hide", "Rabbit Hide", 415));
        registry.register(new SItemType("minecraft:armor_stand", "Armor Stand", 416));
        registry.register(new SItemType("minecraft:iron_horse_armor", "Iron Horse Armor", 417));
        registry.register(new SItemType("minecraft:golden_horse_armor", "Golden Horse Armor", 418));
        registry.register(new SItemType("minecraft:diamond_horse_armor", "Diamond Horse Armor", 419));
        registry.register(new SItemType("minecraft:lead", "Lead", 420));
        registry.register(new SItemType("minecraft:name_tag", "Name Tag", 421));
        registry.register(new SItemType("minecraft:command_block_minecart", "Command Block Minecart", 422));
        registry.register(new SItemType("minecraft:mutton", "Raw Mutton", 423));
        registry.register(new SItemType("minecraft:cooked_mutton", "Cooked Mutton", 424));
        registry.register(new SItemType("minecraft:banner", "Banner", 425));
        registry.register(new SItemType("minecraft:end_crystal", "End Crystal", 426));
        registry.register(new SItemType("minecraft:spruce_door", "Spruce Door", 427));
        registry.register(new SItemType("minecraft:birch_door", "Birch Door", 428));
        registry.register(new SItemType("minecraft:jungle_door", "Jungle Door", 429));
        registry.register(new SItemType("minecraft:acacia_door", "Acacia Door", 430));
        registry.register(new SItemType("minecraft:dark_oak_door", "Dark Oak Door", 431));
        registry.register(new SItemType("minecraft:chorus_fruit", "Chorus Fruit", 432));
        registry.register(new SItemType("minecraft:chorus_fruit_popped", "Popped Chorus Fruit", 433));
        registry.register(new SItemType("minecraft:beetroot", "Beetroot", 434));
        registry.register(new SItemType("minecraft:beetroot_seeds", "Beetroot Seeds", 435));
        registry.register(new SItemType("minecraft:beetroot_soup", "Beetroot Soup", 436));
        registry.register(new SItemType("minecraft:dragon_breath", "Dragon Breath", 437));
        registry.register(new SItemType("minecraft:splash_potion", "Splash Potion", 438));
        registry.register(new SItemType("minecraft:spectral_arrow", "Spectral Arrow", 439));
        registry.register(new SItemType("minecraft:tipped_arrow", "Tipped Arrow", 440));
        registry.register(new SItemType("minecraft:lingering_potion", "Lingering Potion", 441));
        registry.register(new SItemType("minecraft:shield", "Shield", 442));
        registry.register(new SItemType("minecraft:elytra", "Elytra", 443));
        registry.register(new SItemType("minecraft:spruce_boat", "Spruce Boat", 444));
        registry.register(new SItemType("minecraft:birch_boat", "Birch Boat", 445));
        registry.register(new SItemType("minecraft:jungle_boat", "Jungle Boat", 446));
        registry.register(new SItemType("minecraft:acacia_boat", "Acacia Boat", 447));
        registry.register(new SItemType("minecraft:dark_oak_boat", "Dark Oak Boat", 448));
        registry.register(new SItemType("minecraft:totem_of_undying", "Totem of Undying", 449));
        registry.register(new SItemType("minecraft:shulker_shell", "Shulker Shell", 450));
        registry.register(new SItemType("minecraft:iron_nugget", "Iron Nugget", 452));

        registry.register(new SItemType("minecraft:record_13", "13", 2256));
        registry.register(new SItemType("minecraft:record_cat", "cat", 2257));
        registry.register(new SItemType("minecraft:record_blocks", "blocks", 2258));
        registry.register(new SItemType("minecraft:record_chirp", "chirp", 2259));
        registry.register(new SItemType("minecraft:record_far", "far", 2260));
        registry.register(new SItemType("minecraft:record_mall", "mall", 2261));
        registry.register(new SItemType("minecraft:record_mallohi", "mallohi", 2262));
        registry.register(new SItemType("minecraft:record_stal", "stal", 2263));
        registry.register(new SItemType("minecraft:record_strad", "strad", 2264));
        registry.register(new SItemType("minecraft:record_ward", "ward", 2265));
        registry.register(new SItemType("minecraft:record_11", "11", 2266));
        registry.register(new SItemType("minecraft:record_wait", "wait", 2267));
    }

}

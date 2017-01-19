package org.spongepowered.clean.block;

import java.util.Collection;
import java.util.Optional;

import org.spongepowered.api.block.BlockSoundGroup;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.trait.BlockTrait;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;
import org.spongepowered.clean.world.palette.GlobalPalette;

public class SBlockType extends AbstractCatalogType implements BlockType {

    private final int blockId;
    private SBlockState defaultState;
    private ItemType item = null;

    public SBlockType(String id, String name, int blockId) {
        super(id, name);
        this.blockId = blockId;
        this.defaultState = new SBlockState(this, name);

        GlobalPalette.instance.set(this.blockId << 4, this.defaultState);
    }

    public int getBlockId() {
        return this.blockId;
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T extends Property<?, ?>> Optional<T> getProperty(Class<T> propertyClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Property<?, ?>> getApplicableProperties() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockState getDefaultState() {
        return this.defaultState;
    }

    @Override
    public Optional<ItemType> getItem() {
        return Optional.ofNullable(this.item);
    }
    
    public void setItem(ItemType type) {
        this.item = type;
    }

    @Override
    public boolean getTickRandomly() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setTickRandomly(boolean tickRandomly) {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection<BlockTrait<?>> getTraits() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BlockTrait<?>> getTrait(String blockTrait) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BlockSoundGroup getSoundGroup() {
        // TODO Auto-generated method stub
        return null;
    }

    public static void registerTypes(FixedCatalogRegistryModule<BlockType> registry) {
        registry.register(new Builder("air", 0).build());
        registry.register(new Builder("stone", 1).build());
        registry.register(new Builder("grass", 2).build());
        registry.register(new Builder("dirt", 3).build());
        registry.register(new Builder("cobblestone", 4).build());
        registry.register(new Builder("planks", 5).build());
        registry.register(new Builder("sapling", 6).build());
        registry.register(new Builder("bedrock", 7).build());
        registry.register(new Builder("flowing_water", 8).build());
        registry.register(new Builder("water", "Water", 9).build());
        registry.register(new Builder("flowing_lava", 10).build());
        registry.register(new Builder("lava", 11).build());
        registry.register(new Builder("sand", 12).build());
        registry.register(new Builder("gravel", 13).build());
        registry.register(new Builder("gold_ore", 14).build());
        registry.register(new Builder("iron_ore", 15).build());
        registry.register(new Builder("coal_ore", 16).build());
        registry.register(new Builder("log", 17).build());
        registry.register(new Builder("leaves", 18).build());
        registry.register(new Builder("sponge", 19).build());
        registry.register(new Builder("glass", 20).build());
        registry.register(new Builder("lapis_ore", 21).build());
        registry.register(new Builder("lapis_block", 22).build());
        registry.register(new Builder("dispenser", 23).build());
        registry.register(new Builder("sandstone", 24).build());
        registry.register(new Builder("noteblock", 25).build());
        registry.register(new Builder("bed", 26).build());
        registry.register(new Builder("golden_rail", 27).build());
        registry.register(new Builder("detector_rail", 28).build());
        registry.register(new Builder("sticky_piston", 29).build());
        registry.register(new Builder("web", 30).build());
        registry.register(new Builder("tallgrass", 31).build());
        registry.register(new Builder("deadbush", 32).build());
        registry.register(new Builder("piston", 33).build());
        registry.register(new Builder("piston_head", 34).build());
        registry.register(new Builder("wool", 35).build());
        registry.register(new Builder("piston_extension", 36).build());
        registry.register(new Builder("yellow_flower", 37).build());
        registry.register(new Builder("red_flower", 38).build());
        registry.register(new Builder("brown_mushroom", 39).build());
        registry.register(new Builder("red_mushroom", 40).build());
        registry.register(new Builder("gold_block", 41).build());
        registry.register(new Builder("iron_block", 42).build());
        registry.register(new Builder("double_stone_slab", 43).build());
        registry.register(new Builder("stone_slab", 44).build());
        registry.register(new Builder("brick_block", 45).build());
        registry.register(new Builder("tnt", 46).build());
        registry.register(new Builder("bookshelf", 47).build());
        registry.register(new Builder("mossy_cobblestone", 48).build());
        registry.register(new Builder("obsidian", 49).build());
        registry.register(new Builder("torch", 50).build());
        registry.register(new Builder("fire", 51).build());
        registry.register(new Builder("mob_spawner", 52).build());
        registry.register(new Builder("oak_stairs", 53).build());
        registry.register(new Builder("chest", 54).build());
        registry.register(new Builder("redstone_wire", 55).build());
        registry.register(new Builder("diamond_ore", 56).build());
        registry.register(new Builder("diamond_block", 57).build());
        registry.register(new Builder("crafting_table", 58).build());
        registry.register(new Builder("wheat", 59).build());
        registry.register(new Builder("farmland", 60).build());
        registry.register(new Builder("furnace", 61).build());
        registry.register(new Builder("lit_furnace", 62).build());
        registry.register(new Builder("standing_sign", 63).build());
        registry.register(new Builder("wooden_door", 64).build());
        registry.register(new Builder("ladder", 65).build());
        registry.register(new Builder("rail", 66).build());
        registry.register(new Builder("stone_stairs", 67).build());
        registry.register(new Builder("wall_sign", 68).build());
        registry.register(new Builder("lever", 69).build());
        registry.register(new Builder("stone_pressure_plate", 70).build());
        registry.register(new Builder("iron_door", 71).build());
        registry.register(new Builder("wooden_pressure_plate", 72).build());
        registry.register(new Builder("redstone_ore", 73).build());
        registry.register(new Builder("lit_redstone_ore", 74).build());
        registry.register(new Builder("unlit_redstone_torch", 75).build());
        registry.register(new Builder("redstone_torch", 76).build());
        registry.register(new Builder("stone_button", 77).build());
        registry.register(new Builder("snow_layer", 78).build());
        registry.register(new Builder("ice", 79).build());
        registry.register(new Builder("snow", 80).build());
        registry.register(new Builder("cactus", 81).build());
        registry.register(new Builder("clay", 82).build());
        registry.register(new Builder("reeds", 83).build());
        registry.register(new Builder("jukebox", 84).build());
        registry.register(new Builder("fence", 85).build());
        registry.register(new Builder("pumpkin", 86).build());
        registry.register(new Builder("netherrack", 87).build());
        registry.register(new Builder("soul_sand", 88).build());
        registry.register(new Builder("glowstone", 89).build());
        registry.register(new Builder("portal", 90).build());
        registry.register(new Builder("lit_pumpkin", 91).build());
        registry.register(new Builder("cake", 92).build());
        registry.register(new Builder("unpowered_repeater", 93).build());
        registry.register(new Builder("powered_repeater", 94).build());
        registry.register(new Builder("stained_glass", 95).build());
        registry.register(new Builder("trapdoor", 96).build());
        registry.register(new Builder("monster_egg", 97).build());
        registry.register(new Builder("stonebrick", 98).build());
        registry.register(new Builder("brown_mushroom_block", 99).build());
        registry.register(new Builder("red_mushroom_block", 100).build());
        registry.register(new Builder("iron_bars", 101).build());
        registry.register(new Builder("glass_pane", 102).build());
        registry.register(new Builder("melon_block", 103).build());
        registry.register(new Builder("pumpkin_stem", 104).build());
        registry.register(new Builder("melon_stem", 105).build());
        registry.register(new Builder("vine", 106).build());
        registry.register(new Builder("fence_gate", 107).build());
        registry.register(new Builder("brick_stairs", 108).build());
        registry.register(new Builder("stone_brick_stairs", 109).build());
        registry.register(new Builder("mycelium", 110).build());
        registry.register(new Builder("waterlily", 111).build());
        registry.register(new Builder("nether_brick", 112).build());
        registry.register(new Builder("nether_brick_fence", 113).build());
        registry.register(new Builder("nether_brick_stairs", 114).build());
        registry.register(new Builder("nether_wart", 115).build());
        registry.register(new Builder("enchanting_table", 116).build());
        registry.register(new Builder("brewing_stand", 117).build());
        registry.register(new Builder("cauldron", 118).build());
        registry.register(new Builder("end_portal", 119).build());
        registry.register(new Builder("end_portal_frame", 120).build());
        registry.register(new Builder("end_stone", 121).build());
        registry.register(new Builder("dragon_egg", 122).build());
        registry.register(new Builder("redstone_lamp", 123).build());
        registry.register(new Builder("lit_redstone_lamp", 124).build());
        registry.register(new Builder("double_wooden_slab", 125).build());
        registry.register(new Builder("wooden_slab", 126).build());
        registry.register(new Builder("cocoa", 127).build());
        registry.register(new Builder("sandstone_stairs", 128).build());
        registry.register(new Builder("emerald_ore", 129).build());
        registry.register(new Builder("ender_chest", 130).build());
        registry.register(new Builder("tripwire_hook", 131).build());
        registry.register(new Builder("tripwire", 132).build());
        registry.register(new Builder("emerald_block", 133).build());
        registry.register(new Builder("spruce_stairs", 134).build());
        registry.register(new Builder("birch_stairs", 135).build());
        registry.register(new Builder("jungle_stairs", 136).build());
        registry.register(new Builder("command_block", 137).build());
        registry.register(new Builder("beacon", 138).build());
        registry.register(new Builder("cobblestone_wall", 139).build());
        registry.register(new Builder("flower_pot", 140).build());
        registry.register(new Builder("carrots", 141).build());
        registry.register(new Builder("potatoes", 142).build());
        registry.register(new Builder("wooden_button", 143).build());
        registry.register(new Builder("skull", 144).build());
        registry.register(new Builder("anvil", 145).build());
        registry.register(new Builder("trapped_chest", 146).build());
        registry.register(new Builder("light_weighted_pressure_plate", 147).build());
        registry.register(new Builder("heavy_weighted_pressure_plate", 148).build());
        registry.register(new Builder("unpowered_comparator", 149).build());
        registry.register(new Builder("powered_comparator", 150).build());
        registry.register(new Builder("daylight_detector", 151).build());
        registry.register(new Builder("redstone_block", 152).build());
        registry.register(new Builder("quartz_ore", 153).build());
        registry.register(new Builder("hopper", 154).build());
        registry.register(new Builder("quartz_block", 155).build());
        registry.register(new Builder("quartz_stairs", 156).build());
        registry.register(new Builder("activator_rail", 157).build());
        registry.register(new Builder("dropper", 158).build());
        registry.register(new Builder("stained_hardened_clay", 159).build());
        registry.register(new Builder("stained_glass_pane", 160).build());
        registry.register(new Builder("leaves2", 161).build());
        registry.register(new Builder("log2", 162).build());
        registry.register(new Builder("acacia_stairs", 163).build());
        registry.register(new Builder("dark_oak_stairs", 164).build());
        registry.register(new Builder("slime", 165).build());
        registry.register(new Builder("barrier", 166).build());
        registry.register(new Builder("iron_trapdoor", 167).build());
        registry.register(new Builder("prismarine", 168).build());
        registry.register(new Builder("sea_lantern", 169).build());
        registry.register(new Builder("hay_block", 170).build());
        registry.register(new Builder("carpet", 171).build());
        registry.register(new Builder("hardened_clay", 172).build());
        registry.register(new Builder("coal_block", 173).build());
        registry.register(new Builder("packed_ice", 174).build());
        registry.register(new Builder("double_plant", 175).build());
        registry.register(new Builder("standing_banner", 176).build());
        registry.register(new Builder("wall_banner", 177).build());
        registry.register(new Builder("daylight_detector_inverted", 178).build());
        registry.register(new Builder("red_sandstone", 179).build());
        registry.register(new Builder("red_sandstone_stairs", 180).build());
        registry.register(new Builder("double_stone_slab2", 181).build());
        registry.register(new Builder("stone_slab2", 182).build());
        registry.register(new Builder("spruce_fence_gate", 183).build());
        registry.register(new Builder("birch_fence_gate", 184).build());
        registry.register(new Builder("jungle_fence_gate", 185).build());
        registry.register(new Builder("dark_oak_fence_gate", 186).build());
        registry.register(new Builder("acacia_fence_gate", 187).build());
        registry.register(new Builder("spruce_fence", 188).build());
        registry.register(new Builder("birch_fence", 189).build());
        registry.register(new Builder("jungle_fence", 190).build());
        registry.register(new Builder("dark_oak_fence", 191).build());
        registry.register(new Builder("acacia_fence", 192).build());
        registry.register(new Builder("spruce_door", 193).build());
        registry.register(new Builder("birch_door", 194).build());
        registry.register(new Builder("jungle_door", 195).build());
        registry.register(new Builder("acacia_door", 196).build());
        registry.register(new Builder("dark_oak_door", 197).build());
        registry.register(new Builder("end_rod", 198).build());
        registry.register(new Builder("chorus_plant", 199).build());
        registry.register(new Builder("chorus_flower", 200).build());
        registry.register(new Builder("purpur_block", 201).build());
        registry.register(new Builder("purpur_pillar", 202).build());
        registry.register(new Builder("purpur_stairs", 203).build());
        registry.register(new Builder("purpur_double_slab", 204).build());
        registry.register(new Builder("purpur_slab", 205).build());
        registry.register(new Builder("end_bricks", 206).build());
        registry.register(new Builder("beetroots", 207).build());
        registry.register(new Builder("grass_path", 208).build());
        registry.register(new Builder("end_gateway", 209).build());
        registry.register(new Builder("repeating_command_block", 210).build());
        registry.register(new Builder("chain_command_block", 211).build());
        registry.register(new Builder("frosted_ice", 212).build());
        registry.register(new Builder("magma", 213).build());
        registry.register(new Builder("nether_wart_block", 214).build());
        registry.register(new Builder("red_nether_brick", 215).build());
        registry.register(new Builder("bone_block", 216).build());
        registry.register(new Builder("structure_void", 217).build());
        registry.register(new Builder("observer", 218).build());
        registry.register(new Builder("white_shulker_box", 219).build());
        registry.register(new Builder("orange_shulker_box", 220).build());
        registry.register(new Builder("magenta_shulker_box", 221).build());
        registry.register(new Builder("light_blue_shulker_box", 222).build());
        registry.register(new Builder("yellow_shulker_box", 223).build());
        registry.register(new Builder("lime_shulker_box", 224).build());
        registry.register(new Builder("pink_shulker_box", 225).build());
        registry.register(new Builder("gray_shulker_box", 226).build());
        registry.register(new Builder("silver_shulker_box", 227).build());
        registry.register(new Builder("cyan_shulker_box", 228).build());
        registry.register(new Builder("purple_shulker_box", 229).build());
        registry.register(new Builder("blue_shulker_box", 230).build());
        registry.register(new Builder("brown_shulker_box", 231).build());
        registry.register(new Builder("green_shulker_box", 232).build());
        registry.register(new Builder("red_shulker_box", 233).build());
        registry.register(new Builder("black_shulker_box", 234).build());

        registry.register(new Builder("structure_block", 255).build());
    }

    private static class Builder {

        private static String makeName(String id) {
            boolean nextIsUpper = true;
            String name = "";
            for (int i = 0; i < id.length(); i++) {
                char next = id.charAt(i);
                if (next == '_') {
                    name += " ";
                    nextIsUpper = true;
                } else if (nextIsUpper) {
                    name += Character.toUpperCase(next);
                    nextIsUpper = false;
                } else {
                    name += next;
                }
            }
            return name;
        }

        private final String id;
        private final String name;
        private final int blockId;

        public Builder(String id, int blockId) {
            this.id = id;
            this.name = makeName(id);
            this.blockId = blockId;
        }

        public Builder(String id, String name, int blockId) {
            this.id = id;
            this.name = name;
            this.blockId = blockId;
        }

        public SBlockType build() {
            SBlockType type = new SBlockType("minecraft:" + this.id, this.name, this.blockId);

            return type;
        }

    }

}

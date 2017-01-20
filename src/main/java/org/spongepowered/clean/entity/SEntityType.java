/*
 * This file is part of SpongeClean, licensed under the MIT License (MIT).
 *
 * Copyright (c) The VoxelBox <http://thevoxelbox.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.clean.entity;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.clean.entity.complex.SEnderDragonPart;
import org.spongepowered.clean.entity.explosive.SEnderCrystal;
import org.spongepowered.clean.entity.explosive.SFirework;
import org.spongepowered.clean.entity.explosive.SPrimedTNT;
import org.spongepowered.clean.entity.explosive.SWitherSkull;
import org.spongepowered.clean.entity.hanging.SItemFrame;
import org.spongepowered.clean.entity.hanging.SLeashHitch;
import org.spongepowered.clean.entity.hanging.SPainting;
import org.spongepowered.clean.entity.living.SArmorStand;
import org.spongepowered.clean.entity.living.SHuman;
import org.spongepowered.clean.entity.living.SVillager;
import org.spongepowered.clean.entity.living.boss.SEnderDragon;
import org.spongepowered.clean.entity.living.boss.SWither;
import org.spongepowered.clean.entity.living.monster.SBlaze;
import org.spongepowered.clean.entity.living.monster.SCreeper;
import org.spongepowered.clean.entity.living.monster.SEnderman;
import org.spongepowered.clean.entity.living.monster.SEndermite;
import org.spongepowered.clean.entity.living.monster.SGhast;
import org.spongepowered.clean.entity.living.monster.SGiant;
import org.spongepowered.clean.entity.living.monster.SGuardian;
import org.spongepowered.clean.entity.living.monster.SShulker;
import org.spongepowered.clean.entity.living.monster.SSilverfish;
import org.spongepowered.clean.entity.living.monster.SSkeleton;
import org.spongepowered.clean.entity.living.monster.SSpider;
import org.spongepowered.clean.entity.living.monster.SVex;
import org.spongepowered.clean.entity.living.monster.SWitch;
import org.spongepowered.clean.entity.living.monster.SZombie;
import org.spongepowered.clean.entity.living.passive.SBat;
import org.spongepowered.clean.entity.living.passive.SChicken;
import org.spongepowered.clean.entity.living.passive.SCow;
import org.spongepowered.clean.entity.living.passive.SIronGolem;
import org.spongepowered.clean.entity.living.passive.SMooshroom;
import org.spongepowered.clean.entity.living.passive.SMule;
import org.spongepowered.clean.entity.living.passive.SOcelot;
import org.spongepowered.clean.entity.living.passive.SPig;
import org.spongepowered.clean.entity.living.passive.SPolarBear;
import org.spongepowered.clean.entity.living.passive.SRabbit;
import org.spongepowered.clean.entity.living.passive.SSheep;
import org.spongepowered.clean.entity.living.passive.SSnowGolem;
import org.spongepowered.clean.entity.living.passive.SSquid;
import org.spongepowered.clean.entity.living.passive.SWolf;
import org.spongepowered.clean.entity.living.passive.horse.SDonkey;
import org.spongepowered.clean.entity.living.passive.horse.SHorse;
import org.spongepowered.clean.entity.living.passive.horse.SLlama;
import org.spongepowered.clean.entity.living.passive.horse.SSkeletonHorse;
import org.spongepowered.clean.entity.living.passive.horse.SZombieHorse;
import org.spongepowered.clean.entity.player.SPlayer;
import org.spongepowered.clean.entity.projectile.SEgg;
import org.spongepowered.clean.entity.projectile.SEnderPearl;
import org.spongepowered.clean.entity.projectile.SEyeOfEnder;
import org.spongepowered.clean.entity.projectile.SFishHook;
import org.spongepowered.clean.entity.projectile.SLlamaSpit;
import org.spongepowered.clean.entity.projectile.SShulkerBullet;
import org.spongepowered.clean.entity.projectile.SSnowball;
import org.spongepowered.clean.entity.projectile.arrow.SArrow;
import org.spongepowered.clean.entity.projectile.arrow.STippedArrow;
import org.spongepowered.clean.entity.projectile.explosive.SDragonFireball;
import org.spongepowered.clean.entity.projectile.explosive.SFireball;
import org.spongepowered.clean.entity.projectile.explosive.SSmallFireball;
import org.spongepowered.clean.entity.vehicle.SBoat;
import org.spongepowered.clean.entity.vehicle.minecart.SChestMinecart;
import org.spongepowered.clean.entity.vehicle.minecart.SCommandBlockMinecart;
import org.spongepowered.clean.entity.vehicle.minecart.SFurnaceMinecart;
import org.spongepowered.clean.entity.vehicle.minecart.SHopperMinecart;
import org.spongepowered.clean.entity.vehicle.minecart.SMinecart;
import org.spongepowered.clean.entity.vehicle.minecart.SMobSpawnerMinecart;
import org.spongepowered.clean.entity.vehicle.minecart.STNTMinecart;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SEntityType extends AbstractCatalogType implements EntityType {

    private final Class<? extends Entity> type;
    private final int entityid;

    public SEntityType(String id, String name, int entityid, Class<? extends Entity> type) {
        super(id, name);
        this.entityid = entityid;
        this.type = type;
    }

    public int getEntityId() {
        return this.entityid;
    }

    @Override
    public Translation getTranslation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<? extends Entity> getEntityClass() {
        return this.type;
    }

    public static void registerTypes(FixedCatalogRegistryModule<EntityType> registry) {
        registry.register(new SEntityType("minecraft:unknown", "Unknown", -1, null));

        registry.register(new SEntityType("minecraft:item", "Item", 1, SItem.class));
        registry.register(new SEntityType("minecraft:xp_orb", "Experience Orb", 2, SExperienceOrb.class));
        registry.register(new SEntityType("minecraft:area_effect_cloud", "Area Effect Cloud", 3, SAreaEffectCloud.class));
        registry.register(new SEntityType("minecraft:elder_guardian", "Elder Guardian", 4, null));
        registry.register(new SEntityType("minecraft:wither_skeleton", "Wither Skeleton", 5, null));
        registry.register(new SEntityType("minecraft:stray", "Stray", 6, null));
        registry.register(new SEntityType("minecraft:egg", "Egg", 7, SEgg.class));
        registry.register(new SEntityType("minecraft:leash_knot", "Leash Knot", 8, SLeashHitch.class));
        registry.register(new SEntityType("minecraft:painting", "Painting", 9, SPainting.class));
        registry.register(new SEntityType("minecraft:arrow", "Arrow", 10, SArrow.class));
        registry.register(new SEntityType("minecraft:snowball", "Snowball", 11, SSnowball.class));
        registry.register(new SEntityType("minecraft:fireball", "Fireball", 12, SFireball.class));
        registry.register(new SEntityType("minecraft:small_fireball", "Small Fireball", 13, SSmallFireball.class));
        registry.register(new SEntityType("minecraft:ender_pearl", "Ender Pearl", 14, SEnderPearl.class));
        registry.register(new SEntityType("minecraft:eye_of_ender_signal", "Eye of Ender", 15, SEyeOfEnder.class));
        registry.register(new SEntityType("minecraft:potion", "Potion", 16, null));
        registry.register(new SEntityType("minecraft:xp_bottle", "Experience Bottle", 17, null));
        registry.register(new SEntityType("minecraft:item_frame", "Item Frame", 18, SItemFrame.class));
        registry.register(new SEntityType("minecraft:wither_skull", "Wither Skull", 19, SWitherSkull.class));
        registry.register(new SEntityType("minecraft:tnt", "TNT", 20, null));
        registry.register(new SEntityType("minecraft:falling_block", "Falling Block", 21, SFallingBlock.class));
        registry.register(new SEntityType("minecraft:fireworks_rocket", "Fireworks", 22, SFirework.class));
        registry.register(new SEntityType("minecraft:husk", "Husk", 23, null));
        registry.register(new SEntityType("minecraft:spectral_arrow", "Spectral Arrow", 24, null));
        registry.register(new SEntityType("minecraft:shulker_bullet", "Shulker Bullet", 25, SShulkerBullet.class));
        registry.register(new SEntityType("minecraft:dragon_fireball", "Dragon Fireball", 26, SDragonFireball.class));
        registry.register(new SEntityType("minecraft:zombie_villager", "Zombie Villager", 27, null));
        registry.register(new SEntityType("minecraft:skeleton_horse", "Skeleton Horse", 28, SSkeletonHorse.class));
        registry.register(new SEntityType("minecraft:zombie_horse", "Zombie Horse", 29, SZombieHorse.class));
        registry.register(new SEntityType("minecraft:armor_stand", "Armor Stand", 30, SArmorStand.class));
        registry.register(new SEntityType("minecraft:donkey", "Donkey", 31, SDonkey.class));
        registry.register(new SEntityType("minecraft:mule", "Mule", 32, SMule.class));
        registry.register(new SEntityType("minecraft:evocation_fangs", "Evocation Fangs", 33, null));
        registry.register(new SEntityType("minecraft:evocation_illager", "Evocation Illager", 34, null));
        registry.register(new SEntityType("minecraft:vex", "Vex", 35, SVex.class));
        registry.register(new SEntityType("minecraft:vindication_illager", "Vindication Illager", 36, null));
        registry.register(new SEntityType("minecraft:commandblock_minecart", "CommandBlock Minecart", 40, SCommandBlockMinecart.class));
        registry.register(new SEntityType("minecraft:boat", "Boat", 41, SBoat.class));
        registry.register(new SEntityType("minecraft:minecart", "Minecart", 42, SMinecart.class));
        registry.register(new SEntityType("minecraft:chest_minecart", "Chest Minecart", 43, SChestMinecart.class));
        registry.register(new SEntityType("minecraft:furnace_minecart", "Furnace Minecart", 44, SFurnaceMinecart.class));
        registry.register(new SEntityType("minecraft:tnt_minecart", "TNT Minecart", 45, STNTMinecart.class));
        registry.register(new SEntityType("minecraft:hopper_minecart", "Hopper Minecart", 46, SHopperMinecart.class));
        registry.register(new SEntityType("minecraft:spawner_minecart", "MobSpawner Minecart", 47, SMobSpawnerMinecart.class));
        registry.register(new SEntityType("minecraft:creeper", "Creeper", 50, SCreeper.class));
        registry.register(new SEntityType("minecraft:skeleton", "Skeleton", 51, SSkeleton.class));
        registry.register(new SEntityType("minecraft:spider", "Spider", 52, SSpider.class));
        registry.register(new SEntityType("minecraft:giant", "Giant", 53, SGiant.class));
        registry.register(new SEntityType("minecraft:zombie", "Zombie", 54, SZombie.class));
        registry.register(new SEntityType("minecraft:slime", "Slime", 55, null));
        registry.register(new SEntityType("minecraft:ghast", "Ghast", 56, SGhast.class));
        registry.register(new SEntityType("minecraft:zombie_pigman", "Zombie Pigman", 57, null));
        registry.register(new SEntityType("minecraft:enderman", "Enderman", 58, SEnderman.class));
        registry.register(new SEntityType("minecraft:cave_spider", "Cave Spider", 59, null));
        registry.register(new SEntityType("minecraft:silverfish", "Silverfish", 60, SSilverfish.class));
        registry.register(new SEntityType("minecraft:blaze", "Blaze", 61, SBlaze.class));
        registry.register(new SEntityType("minecraft:magma_cube", "Magma Cube", 62, null));
        registry.register(new SEntityType("minecraft:ender_dragon", "Ender Dragon", 63, SEnderDragon.class));
        registry.register(new SEntityType("minecraft:wither", "Wither", 64, SWither.class));
        registry.register(new SEntityType("minecraft:bat", "Bat", 65, SBat.class));
        registry.register(new SEntityType("minecraft:witch", "Witch", 66, SWitch.class));
        registry.register(new SEntityType("minecraft:endermite", "Endermite", 67, SEndermite.class));
        registry.register(new SEntityType("minecraft:guardian", "Guardian", 68, SGuardian.class));
        registry.register(new SEntityType("minecraft:shulker", "Skulker", 69, SShulker.class));
        registry.register(new SEntityType("minecraft:pig", "Pig", 90, SPig.class));
        registry.register(new SEntityType("minecraft:sheep", "Sheep", 91, SSheep.class));
        registry.register(new SEntityType("minecraft:cow", "Cow", 92, SCow.class));
        registry.register(new SEntityType("minecraft:chicken", "Chicken", 93, SChicken.class));
        registry.register(new SEntityType("minecraft:squid", "Squid", 94, SSquid.class));
        registry.register(new SEntityType("minecraft:wolf", "Wolf", 95, SWolf.class));
        registry.register(new SEntityType("minecraft:mooshroom", "Mooshroom", 96, SMooshroom.class));
        registry.register(new SEntityType("minecraft:snowman", "Snowman", 97, SSnowGolem.class));
        registry.register(new SEntityType("minecraft:ocelot", "Ozelot", 98, SOcelot.class));
        registry.register(new SEntityType("minecraft:villager_golem", "Iron Golem", 99, SIronGolem.class));
        registry.register(new SEntityType("minecraft:horse", "Horse", 100, SHorse.class));
        registry.register(new SEntityType("minecraft:rabbit", "Rabbit", 101, SRabbit.class));
        registry.register(new SEntityType("minecraft:polar_bear", "Polar Bear", 102, SPolarBear.class));
        registry.register(new SEntityType("minecraft:llama", "Llama", 103, SLlama.class));
        registry.register(new SEntityType("minecraft:llama_spit", "Llama Spit", 104, SLlamaSpit.class));
        registry.register(new SEntityType("minecraft:villager", "Villager", 120, SVillager.class));
        registry.register(new SEntityType("minecraft:ender_crystal", "Ender Crystal", 200, SEnderCrystal.class));

        // Not actual minecraft entity ids
        // (some of these are never serialized I believe)
        registry.register(new SEntityType("minecraft:complex_part", "Ender Dragon Part", 220, SEnderDragonPart.class));
        registry.register(new SEntityType("minecraft:fishing_hook", "Fishing Hook", 221, SFishHook.class));
        registry.register(new SEntityType("minecraft:human", "Human", 222, SHuman.class));
        registry.register(new SEntityType("minecraft:lightning", "Lightning", 223, null));
        registry.register(new SEntityType("minecraft:player", "Player", 224, SPlayer.class));
        registry.register(new SEntityType("minecraft:primed_tnt", "Primed TNT", 225, SPrimedTNT.class));
        registry.register(new SEntityType("minecraft:tipped_arrow", "Tipped Arrow", 226, STippedArrow.class));
        registry.register(new SEntityType("minecraft:weather", "Weather", 227, null));

        registry.registerAlias("minecraft:chested_minecart", "minecraft:chest_minecart");
        registry.registerAlias("minecraft:experience_orb", "minecraft:xp_orb");
        registry.registerAlias("minecraft:eye_of_ender", "minecraft:eye_of_ender_signal");
        registry.registerAlias("minecraft:firework", "minecraft:fireworks_rocket");
        registry.registerAlias("minecraft:iron_golem", "minecraft:villager_golem");
        registry.registerAlias("minecraft:leash_hitch", "minecraft:leash_knot");
        registry.registerAlias("minecraft:mob_spawner_minecart", "minecraft:spawner_minecart");
        registry.registerAlias("minecraft:mushroom_cow", "minecraft:mooshroom");
        registry.registerAlias("minecraft:pig_zombie", "minecraft:zombie_pigman");
        registry.registerAlias("minecraft:rideable_minecart", "minecraft:minecart");
        registry.registerAlias("minecraft:splash_potion", "minecraft:potion");
        registry.registerAlias("minecraft:thrown_exp_bottle", "minecraft:xp_bottle");

    }

}

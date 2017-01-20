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
package org.spongepowered.clean.data;

import org.spongepowered.api.data.DataQuery;

public class DataQueries {

    public static final DataQuery LEVEL_NAME = DataQuery.of("LevelName");
    public static final DataQuery LEVEL_VERSION = DataQuery.of("version");
    public static final DataQuery LEVEL_INITIALIZED = DataQuery.of("initialized");
    public static final DataQuery LEVEL_GENERATOR_NAME = DataQuery.of("generatorName");
    public static final DataQuery LEVEL_GENERATOR_VERSION = DataQuery.of("generatorVersion");
    public static final DataQuery LEVEL_GENERATOR_OPTIONS = DataQuery.of("generatorOptions");
    public static final DataQuery LEVEL_RANDOM_SEED = DataQuery.of("RandomSeed");
    public static final DataQuery LEVEL_MAP_FEATURES = DataQuery.of("MapFeatures");
    public static final DataQuery LEVEL_LAST_PLAYED = DataQuery.of("LastPlayed");
    public static final DataQuery LEVEL_SIZE_ON_DISK = DataQuery.of("SizeOnDisk");
    public static final DataQuery LEVEL_ALLOW_COMMANDS = DataQuery.of("allowCommands");
    public static final DataQuery LEVEL_HARDCORE = DataQuery.of("hardcore");
    public static final DataQuery LEVEL_GAME_TYPE = DataQuery.of("GameType");
    public static final DataQuery LEVEL_DIFFICULTY = DataQuery.of("Difficulty");
    public static final DataQuery LEVEL_DIFFICULTY_LOCKED = DataQuery.of("DifficultyLocked");
    public static final DataQuery LEVEL_TIME = DataQuery.of("Time");
    public static final DataQuery LEVEL_DAY_TIME = DataQuery.of("DayTime");
    public static final DataQuery LEVEL_SPAWN_X = DataQuery.of("SpawnX");
    public static final DataQuery LEVEL_SPAWN_Y = DataQuery.of("SpawnY");
    public static final DataQuery LEVEL_SPAWN_Z = DataQuery.of("SpawnZ");
    public static final DataQuery LEVEL_BORDER_CENTER_X = DataQuery.of("BorderCenterX");
    public static final DataQuery LEVEL_BORDER_CENTER_Z = DataQuery.of("BorderCenterZ");
    public static final DataQuery LEVEL_BORDER_SIZE = DataQuery.of("BorderSize");
    public static final DataQuery LEVEL_BORDER_SAFE_ZONE = DataQuery.of("BorderSafeZone");
    public static final DataQuery LEVEL_BORDER_WARNING_BLOCKS = DataQuery.of("BorderWarningBlocks");
    public static final DataQuery LEVEL_BORDER_WARNING_TIME = DataQuery.of("BorderWarningTime");
    public static final DataQuery LEVEL_BORDER_SIZE_LERP_TARGET = DataQuery.of("BorderSizeLerpTarget");
    public static final DataQuery LEVEL_BORDER_SIZE_LERP_TIME = DataQuery.of("BorderSizeLerpTime");
    public static final DataQuery LEVEL_BORDER_DAMAGE_PER_BLOCK = DataQuery.of("BorderDamagePerBlock");
    public static final DataQuery LEVEL_RAINING = DataQuery.of("raining");
    public static final DataQuery LEVEL_RAIN_TIME = DataQuery.of("rainTime");
    public static final DataQuery LEVEL_THUNDERING = DataQuery.of("thundering");
    public static final DataQuery LEVEL_THUNDER_TIME = DataQuery.of("thunderTime");
    public static final DataQuery LEVEL_CLEAR_WEATHER_TIME = DataQuery.of("clearWeatherTime");
    public static final DataQuery LEVEL_GAME_RULES = DataQuery.of("GameRules");
    public static final DataQuery LEVEL_VERSION_ADV = DataQuery.of("Version");

}

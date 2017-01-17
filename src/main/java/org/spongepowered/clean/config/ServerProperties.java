package org.spongepowered.clean.config;

public class ServerProperties {

    public String  generator_settings            = "";
    public int     op_permission_level           = 4;
    public boolean allow_nether                  = true;
    public String  level_name                    = "world";
    public boolean enable_query                  = false;
    public boolean allow_flight                  = false;
    public boolean announce_player_achievements  = true;
    public int     server_port                   = 25565;
    public int     max_world_size                = 29999984;
    public String  level_type                    = "DEFAULT";
    public boolean enable_rcon                   = false;
    public String  level_seed                    = "";
    public boolean force_gamemode                = false;
    public String  server_ip                     = "";
    public int     network_compression_threshold = 256;
    public boolean spawn_npcs                    = true;
    public boolean whitelist                     = false;
    public boolean spawn_animals                 = true;
    public boolean hardcore                      = false;
    public boolean snooper_enabled               = true;
    public String  resource_pack_sha             = "";
    public boolean pvp                           = false;
    public int     difficulty                    = 1;
    public boolean enable_command_blocks         = false;
    public int     gamemode                      = 0;
    public int     player_idle_timeout           = 0;
    public int     max_players                   = 20;
    public int     max_tick_time                 = 60000;
    public boolean spawn_monsters                = true;
    public boolean generate_structures           = true;
    public int     view_distance                 = 10;
    public String  motd                          = "A cleanroom sponge minecraft server";
    // TODO change back to default true
    public boolean online_mode                   = false;

}

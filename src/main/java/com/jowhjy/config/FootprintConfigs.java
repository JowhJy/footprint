package com.jowhjy.config;

import com.jowhjy.Footprint;
import com.mojang.datafixers.util.Pair;

public class FootprintConfigs {

    private static FootprintConfigProvider configs;

    public static SimpleConfig CONFIG;

    public static int MIN_INHABITED_TIME;

    public static void registerConfigs() {

        configs = new FootprintConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(Footprint.MOD_ID + "_config").provider(configs).request();
        
        assignConfigs();

    }


    private static void createConfigs() {
        configs.addComment("The minumum amount of ticks a chunk needs to be loaded for it to be saved");
        configs.addKeyValuePair(new Pair<>("footprint.min_inhabited_time", 200), "(integer)");
        configs.addNewline(15);
        configs.addComment("                                                                                                        (-:");
    }

    private static void assignConfigs() {
        MIN_INHABITED_TIME = CONFIG.getOrDefault("footprint.min_inhabited_time", 200);
    }
    
}

package com.jowhjy.config;

import com.jowhjy.Footprint;
import com.mojang.datafixers.util.Pair;

public class FootprintConfigs {

    private static FootprintConfigProvider configs;

    public static SimpleConfig CONFIG;

    public static int MIN_INHABITED_TIME;

    public static boolean ALWAYS_SAVE_BLOCK_BREAK;
    public static boolean ALWAYS_SAVE_BLOCK_PLACE;
    public static boolean ALWAYS_SAVE_ENTITY_HURT;
    public static boolean ALWAYS_SAVE_ITEM_PICKUP;
    public static boolean ALWAYS_SAVE_ITEM_DROP;

    public static void registerConfigs() {

        configs = new FootprintConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(Footprint.MOD_ID + "_config").provider(configs).request();
        
        assignConfigs();

    }


    private static void createConfigs() {
        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.min_inhabited_time", 200),
                "The minimum amount of ticks a chunk needs to be loaded for it to be saved",
                "(integer)");

        configs.addNewline(1);

        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save.block_break", true),
                "If true, a chunk in which a block has been broken by a player should always be saved",
                "(true/false)");

        configs.addNewline(1);

        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save.block_place", true),
                "If true, a chunk in which a block has been placed by a player (or dispenser) should always be saved",
                "(true/false)");

        configs.addNewline(1);

        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save.entity_hurt", false),
                "If true, a chunk in which a living entity has been hurt should always be saved",
                "(true/false)");


        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save.item_pickup", true),
                "If true, a chunk in which a player picked up an item should always be saved",
                "(true/false)");

        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save.item_drop", true),
                "If true, a chunk in which a player dropped an item and the 8 surrounding chunks should always be saved",
                "(true/false)");


        configs.addNewline(15);
        configs.addComment("                                                                                                        (-:");
    }

    private static void assignConfigs() {
        MIN_INHABITED_TIME = CONFIG.getOrDefault("footprint.min_inhabited_time", 200);

        ALWAYS_SAVE_BLOCK_BREAK = CONFIG.getOrDefault("footprint.always_save.block_break", true);

        ALWAYS_SAVE_BLOCK_PLACE = CONFIG.getOrDefault("footprint.always_save.block_place", true);

        ALWAYS_SAVE_ENTITY_HURT = CONFIG.getOrDefault("footprint.always_save.entity_hurt", false);

        ALWAYS_SAVE_ITEM_PICKUP = CONFIG.getOrDefault("footprint.always_save.item_pickup", true);

        ALWAYS_SAVE_ITEM_DROP = CONFIG.getOrDefault("footprint.always_save.item_drop", true);
    }
    
}

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

    public static int ALWAYS_SAVE_BLOCK_BREAK_RANGE;
    public static int ALWAYS_SAVE_BLOCK_PLACE_RANGE;
    public static int ALWAYS_SAVE_ENTITY_HURT_RANGE;
    public static int ALWAYS_SAVE_ITEM_PICKUP_RANGE;
    public static int ALWAYS_SAVE_ITEM_DROP_RANGE;

    public static void registerConfigs() {

        configs = new FootprintConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(Footprint.MOD_ID + "_config").provider(configs).request();
        
        assignConfigs();

    }


    private static void createConfigs() {
        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.min_inhabited_time", 200),
                "The minimum amount of ticks a chunk needs to be loaded for it to be saved");
        configs.addNewline(1);

        configs.addComment("FORCE CHUNK SAVE OPTIONS");
        configs.addComment("The options that specify a range are only used when the condition itself is 'true'. A range 0 means only the chunk itself, 1 means a 3x3 square, 2 means 5x5 square, etc.");

        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save.block_break", true),
                "If true, a chunk in which a block has been broken by a player should always be saved");
        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save_range.block_break", 0),
                "Range of additional chunks to save around block break");

        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save.block_place", true),
                "If true, a chunk in which a block has been placed by a player (or dispenser) should always be saved");
        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save_range.block_place", 0),
                "Range of additional chunks to save around block placement");

        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save.entity_hurt", false),
                "If true, a chunk in which a living entity has been hurt should always be saved");
        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save_range.entity_hurt", 1),
                "Range of additional chunks to save around entity hurt");

        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save.item_pickup", true),
                "If true, a chunk in which a player picked up an item should always be saved");
        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save_range.item_pickup", 1),
                "Range of additional chunks to save around item pickup");

        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save.item_drop", true),
                "If true, a chunk in which a player dropped an item should always be saved");
        configs.addKeyValuePairWithCommentAbove(new Pair<>("footprint.always_save_range.item_drop", 1),
                "Range of additional chunks to save around dropped item");


        configs.addNewline(15);
        configs.addComment("                                                                                                        (-:");
    }

    private static void assignConfigs() {
        MIN_INHABITED_TIME = CONFIG.getOrDefault("footprint.min_inhabited_time", 1200);

        ALWAYS_SAVE_BLOCK_BREAK = CONFIG.getOrDefault("footprint.always_save.block_break", true);

        ALWAYS_SAVE_BLOCK_PLACE = CONFIG.getOrDefault("footprint.always_save.block_place", true);

        ALWAYS_SAVE_ENTITY_HURT = CONFIG.getOrDefault("footprint.always_save.entity_hurt", false);

        ALWAYS_SAVE_ITEM_PICKUP = CONFIG.getOrDefault("footprint.always_save.item_pickup", true);

        ALWAYS_SAVE_ITEM_DROP = CONFIG.getOrDefault("footprint.always_save.item_drop", true);

        ALWAYS_SAVE_BLOCK_BREAK_RANGE = CONFIG.getOrDefault("footprint.always_save_range.block_break", 0);

        ALWAYS_SAVE_BLOCK_PLACE_RANGE = CONFIG.getOrDefault("footprint.always_save_range.block_place", 0);

        ALWAYS_SAVE_ENTITY_HURT_RANGE = CONFIG.getOrDefault("footprint.always_save_range.entity_hurt", 1);

        ALWAYS_SAVE_ITEM_PICKUP_RANGE = CONFIG.getOrDefault("footprint.always_save_range.item_pickup", 1);

        ALWAYS_SAVE_ITEM_DROP_RANGE = CONFIG.getOrDefault("footprint.always_save_range.item_drop", 1);
    }
    
}

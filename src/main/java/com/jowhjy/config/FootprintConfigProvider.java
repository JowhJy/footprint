package com.jowhjy.config;

import com.mojang.datafixers.util.Pair;

public class FootprintConfigProvider implements SimpleConfig.DefaultConfig {

    private String configContents = "";

    @Override
    public String get(String namespace) {
        return configContents;
    }

    public void addKeyValuePair(Pair<String, ?> keyValuePair, String comment) {
        configContents += keyValuePair.getFirst() + "=" + keyValuePair.getSecond() + " #"
                + comment + " | default: " + keyValuePair.getSecond() + "\n";
    }

    public void addComment(String comment) {
        configContents += "#" + comment + "\n";
    }
    public void addNewline(int amount) {
        configContents += "\n".repeat(amount);
    }
}

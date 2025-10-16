package com.jowhjy.config;

import com.mojang.datafixers.util.Pair;

public class FootprintConfigProvider implements SimpleConfig.DefaultConfig {

    private String configContents = "";

    @Override
    public String get(String namespace) {
        return configContents;
    }

    public void addKeyValuePairWithCommentAbove(Pair<String, ?> keyValuePair, String commentAbove) {
        addComment(commentAbove + " | default: " + keyValuePair.getSecond());
        addKeyValuePair(keyValuePair);
    }

    public void addKeyValuePair(Pair<String, ?> keyValuePair) {
        configContents += keyValuePair.getFirst() + "=" + keyValuePair.getSecond() + "\n";
    }

    public void addComment(String comment) {
        configContents += "#" + comment + "\n";
    }
    public void addNewline(int amount) {
        configContents += "\n".repeat(amount);
    }
}

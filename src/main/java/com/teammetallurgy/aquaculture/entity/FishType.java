package com.teammetallurgy.aquaculture.entity;

public enum FishType {
    SMALL(0.3F, 0.3F),
    MEDIUM(0.5F, 0.3F),
    LARGE(0.6F, 0.4F),
    LONGNOSE(0.8F, 0.4F),
    CATFISH(0.55F, 0.3F),
    JELLYFISH(0.7F, 0.3F),
    HALIBUT(0.7F, 0.2F);

    private final float width;
    private final float height;

    FishType(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
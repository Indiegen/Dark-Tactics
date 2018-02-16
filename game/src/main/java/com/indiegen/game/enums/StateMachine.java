package com.indiegen.game.enums;

public interface StateMachine<CustomActor> {
    void update(CustomActor player, float delta);

    Boolean enter(CustomActor player, float delta);

    Boolean exit(CustomActor player, float delta);

}
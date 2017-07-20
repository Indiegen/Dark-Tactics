package com.indiegen.game;

public interface StateMachine<MyActor> {
    void update(MyActor player, float delta);

    Boolean enter(MyActor player, float delta);

    Boolean exit(MyActor player, float delta);

}

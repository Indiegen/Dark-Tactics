package com.indiegen.game.enums;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.indiegen.game.Actors.CustomActor;
import com.indiegen.game.StateMachine;

public enum GamePlayerState implements StateMachine<CustomActor> {


    MOVING() {

        @Override
        public Boolean enter(CustomActor actor, float delta) {

            actor.addAction(Actions.moveTo(actor.getCurX(), actor.getCurY(), .5f));
            actor.setAnimation(1);
            actor.setDefence(0);
            actor.getWalkSound().play();
            return null;
        }

        @Override
        public Boolean exit(CustomActor actor, float delta) {

            actor.setAnimation(0);

            return null;
        }


        @Override
        public void update(CustomActor actor, float delta) {
            if (actor.getY() == actor.getCurY() && actor.getX() == actor.getCurX()) {
                actor.setPlayerState(GamePlayerState.FINISH);
            }
        }
    },

    ATTACK_TARGETING() {
        @Override
        public Boolean enter(CustomActor actor, float delta) {

            actor.attackRects();
            return null;
        }

        @Override
        public Boolean exit(CustomActor actor, float delta) {

            return null;
        }


        @Override
        public void update(CustomActor actor, float delta) {

        }

    },

    BEING_HITTING() {
        @Override
        public Boolean enter(CustomActor actor, float delta) {

            actor.setFontAlpha(1);
            actor.setAnimation(5);
            return null;
        }

        @Override
        public Boolean exit(CustomActor actor, float delta) {

            actor.setAnimation(0);
            return null;
        }


        @Override
        public void update(CustomActor actor, float delta) {


            actor.setFontAlpha(actor.getFontAlpha() - (1f * delta));
            if (actor.getFontAlpha() <= 0) {
                if (actor.getHP() <= 0) {
                    actor.setPlayerState(GamePlayerState.DEAD);
                } else {
                    if (actor.isAnimationFinished()) {
                        actor.setPlayerState(GamePlayerState.FINISH);
                    }
                }
            } else {

            }


        }
    },

    ATTACKING() {
        @Override
        public Boolean enter(CustomActor actor, float delta) {

            actor.setAnimation(2);
            actor.setDefence(0);
            actor.getAttackSound().play();
            return null;
        }

        @Override
        public Boolean exit(CustomActor actor, float delta) {

            actor.setAnimation(0);
            return null;
        }


        @Override
        public void update(CustomActor actor, float delta) {

            if (actor.isAnimationFinished()) {
                actor.setPlayerState(GamePlayerState.FINISH);


            }

        }

    },

    WAITING_OTHERS() {
        @Override
        public Boolean enter(CustomActor actor, float delta) {

            actor.setAnimation(0);

            return null;
        }

        @Override
        public Boolean exit(CustomActor actor, float delta) {

            return null;
        }


        @Override
        public void update(CustomActor actor, float delta) {

        }

    },

    WAITING() {
        @Override
        public Boolean enter(CustomActor actor, float delta) {
            return null;
        }

        @Override
        public Boolean exit(CustomActor actor, float delta) {

            return null;
        }


        @Override
        public void update(CustomActor actor, float delta) {

        }

    },

    WAITING_TO_MOVE() {
        @Override
        public Boolean enter(CustomActor actor, float delta) {

            actor.moveRects();

            return null;
        }

        @Override
        public Boolean exit(CustomActor actor, float delta) {

            return null;
        }


        @Override
        public void update(CustomActor actor, float delta) {

        }

    },

    READY() {
        @Override
        public Boolean enter(CustomActor actor, float delta) {

            return null;
        }

        @Override
        public Boolean exit(CustomActor actor, float delta) {

            return null;
        }


        @Override
        public void update(CustomActor actor, float delta) {

        }

    },

    FINISH() {
        @Override
        public Boolean enter(CustomActor actor, float delta) {

            return null;
        }

        @Override
        public Boolean exit(CustomActor actor, float delta) {

            return null;
        }


        @Override
        public void update(CustomActor actor, float delta) {

        }

    },

    ITEM() {
        @Override
        public Boolean enter(CustomActor actor, float delta) {

            actor.setFontAlpha(1);
            actor.setDefence(0);

            return null;
        }

        @Override
        public Boolean exit(CustomActor actor, float delta) {


            return null;
        }


        @Override
        public void update(CustomActor actor, float delta) {

            actor.setFontAlpha(actor.getFontAlpha() - 1f * delta);
            if (actor.getFontAlpha() <= 0) {
                actor.setPlayerState(GamePlayerState.FINISH);

            } else {

            }

        }

    },

    GUARD() {
        @Override
        public Boolean enter(CustomActor actor, float delta) {

            actor.setAnimation(3);
            actor.setDefence(10);

            return null;
        }

        @Override
        public Boolean exit(CustomActor actor, float delta) {

            return null;
        }

        @Override
        public void update(CustomActor actor, float delta) {

            actor.setPlayerState(GamePlayerState.FINISH);

        }

    },

    DEAD() {
        @Override
        public Boolean enter(CustomActor actor, float delta) {

            actor.setAnimation(4);
            actor.getDeathSound().play();
            return null;
        }

        @Override
        public Boolean exit(CustomActor actor, float delta) {

            return null;
        }

        @Override
        public void update(CustomActor actor, float delta) {

            if (actor.isAnimationFinished()) {
                actor.setPlayerState(GamePlayerState.FINISH);
                actor.setDead(true);

            }

        }

    };

}

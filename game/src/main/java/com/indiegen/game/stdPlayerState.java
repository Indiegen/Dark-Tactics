package com.indiegen.game;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public enum stdPlayerState implements StateMachine<MyActor> {

    MOVING() {
        @Override
        public Boolean enter(MyActor actor, float delta) {

            actor.addAction(Actions.moveTo(actor.getCurX(), actor.getCurY(), .5f));
            actor.setAnimation(1);
            actor.setDefence(0);

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {

            actor.setAnimation(0);

            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {
//

            if (actor.getY() == actor.getCurY() && actor.getX() == actor.getCurX()) {
                actor.setPlayerState(stdPlayerState.FINISH);
            }
//
        }
    },

    ATTACK_TARGETING() {
        @Override
        public Boolean enter(MyActor actor, float delta) {

            actor.attackRects();
            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {

            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {

            //if(enter(player,delta))


            //{

        }

    },

    BEING_HITTING() {
        @Override
        public Boolean enter(MyActor actor, float delta) {

            actor.setFontAlpha(1);

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {

            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {

            //if(enter(player,delta))
            actor.setFontAlpha(actor.getFontAlpha() - 1f * delta);
            if (actor.getFontAlpha() <= 0) {
                //if(actor.getName()=="player"){
                actor.setPlayerState(stdPlayerState.FINISH);


                if (actor.getHP() <= 0) {
                    actor.dead();
                    actor.setPlayerState(stdPlayerState.FINISH);

                }
            } else {

            }

        }

    },

    ATTACKING() {
        @Override
        public Boolean enter(MyActor actor, float delta) {

            actor.setAnimation(2);
            actor.setDefence(0);

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {

            actor.setAnimation(0);
            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {

            //if(enter(player,delta))
            if (actor.isAnimationFinished()) {
                actor.setPlayerState(stdPlayerState.FINISH);


            }

            //{

        }

    },
    WAITING_OTHERS() {
        @Override
        public Boolean enter(MyActor actor, float delta) {

            actor.setAnimation(0);

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {

            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {

            //actor.setX(1);

        }

    },

    WAITING() {
        @Override
        public Boolean enter(MyActor actor, float delta) {

            //actor.setAnimation(0);
            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {

            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {

            //actor.setX(1);

        }

    },

    WAITING_TO_MOVE() {
        @Override
        public Boolean enter(MyActor actor, float delta) {

            actor.moveRects();

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {

            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {



        }

    },
    READY() {
        @Override
        public Boolean enter(MyActor actor, float delta) {



            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {

            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {



        }

    },
    FINISH() {
        @Override
        public Boolean enter(MyActor actor, float delta) {



            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {

            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {



        }

    },
    ITEM() {
        @Override
        public Boolean enter(MyActor actor, float delta) {

            actor.setFontAlpha(1);
            actor.setDefence(0);

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {


            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {

            //if(enter(player,delta))
            actor.setFontAlpha(actor.getFontAlpha() - 1f * delta);
            if (actor.getFontAlpha() <= 0) {
                //if(actor.getName()=="player"){
                actor.setPlayerState(stdPlayerState.FINISH);

            } else {

            }

            //{

        }

    },
    GUARD() {
        @Override
        public Boolean enter(MyActor actor, float delta) {

            actor.setAnimation(3);
            actor.setDefence(10);

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {

            //actor.setAnimation(0);
            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {

            actor.setPlayerState(stdPlayerState.FINISH);

        }

    }

}

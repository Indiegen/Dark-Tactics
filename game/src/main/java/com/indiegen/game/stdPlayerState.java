package com.indiegen.game;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public enum stdPlayerState implements StateMachine<MyActor> {

    MOVING() {
        @Override
        public Boolean enter(MyActor actor, float delta) {
            // TODO: Implement this method
            actor.addAction(Actions.moveTo(actor.getCurX(), actor.getCurY(), .5f));
            actor.setAnimation(1);
            actor.setDefence(0);

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {
            // TODO: Implement this method
            actor.setAnimation(0);

            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {
//			// TODO: Implement this method

            if (actor.getY() == actor.getCurY() && actor.getX() == actor.getCurX()) {
                actor.setPlayerState(stdPlayerState.FINISH);
            }
//
        }
    },

    ATTACK_TARGETING() {
        @Override
        public Boolean enter(MyActor actor, float delta) {
            // TODO: Implement this method
            actor.attackRects();
            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {
            // TODO: Implement this method
            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {
            // TODO: Implement this method
            //if(enter(player,delta))


            //{

        }

    },

    BEING_HITTING() {
        @Override
        public Boolean enter(MyActor actor, float delta) {
            // TODO: Implement this method
            actor.setFontAlpha(1);

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {
            // TODO: Implement this method
            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {
            // TODO: Implement this method
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
            // TODO: Implement this method
            actor.setAnimation(2);
            actor.setDefence(0);

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {
            // TODO: Implement this method
            actor.setAnimation(0);
            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {
            // TODO: Implement this method
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
            // TODO: Implement this method
            actor.setAnimation(0);

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {
            // TODO: Implement this method
            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {
            // TODO: Implement this method
            //actor.setX(1);

        }

    },

    WAITING() {
        @Override
        public Boolean enter(MyActor actor, float delta) {
            // TODO: Implement this method
            //actor.setAnimation(0);
            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {
            // TODO: Implement this method
            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {
            // TODO: Implement this method
            //actor.setX(1);

        }

    },

    WAITING_TO_MOVE() {
        @Override
        public Boolean enter(MyActor actor, float delta) {
            // TODO: Implement this method
            actor.moveRects();

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {
            // TODO: Implement this method
            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {
            // TODO: Implement this method


        }

    },
    READY() {
        @Override
        public Boolean enter(MyActor actor, float delta) {
            // TODO: Implement this method


            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {
            // TODO: Implement this method
            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {
            // TODO: Implement this method


        }

    },
    FINISH() {
        @Override
        public Boolean enter(MyActor actor, float delta) {
            // TODO: Implement this method


            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {
            // TODO: Implement this method
            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {
            // TODO: Implement this method


        }

    },
    ITEM() {
        @Override
        public Boolean enter(MyActor actor, float delta) {
            // TODO: Implement this method
            actor.setFontAlpha(1);
            actor.setDefence(0);

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {
            // TODO: Implement this method

            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {
            // TODO: Implement this method
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
            // TODO: Implement this method
            actor.setAnimation(3);
            actor.setDefence(10);

            return null;
        }

        @Override
        public Boolean exit(MyActor actor, float delta) {
            // TODO: Implement this method
            //actor.setAnimation(0);
            return null;
        }


        @Override
        public void update(MyActor actor, float delta) {
            // TODO: Implement this method
            actor.setPlayerState(stdPlayerState.FINISH);

        }

    }

}

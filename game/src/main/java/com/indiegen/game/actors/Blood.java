package com.indiegen.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.indiegen.game.MyActor;
import com.indiegen.game.Particle;

import java.util.ArrayList;
import java.util.Iterator;

public class Blood extends Actor {
    ShapeRenderer shape;
    ArrayList<Particle> bloods;

    Blood(ShapeRenderer shape) {
        this.shape = shape;
        bloods = new ArrayList<>();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shape.begin(ShapeRenderer.ShapeType.Filled);

        for (Particle blood : bloods) {
            shape.setColor(blood.getColor());
            shape.rect(blood.getPos().x, blood.getPos().y, blood.getSize(), blood.getSize());
            blood.animate(.008f);
        }

        Iterator<Particle> itr = bloods.listIterator();

        while (itr.hasNext()) {
            if (itr.next().getAlpha() <= 0) {
                bloods.remove(itr);
            }
        }

        shape.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        batch.begin();

    }

    public void createBlood(MyActor actor) {
        bloods.add(new Particle(actor));
        bloods.add(new Particle(actor));
        bloods.add(new Particle(actor));
        bloods.add(new Particle(actor));
        bloods.add(new Particle(actor));
        bloods.add(new Particle(actor));
        bloods.add(new Particle(actor));
        bloods.add(new Particle(actor));
        bloods.add(new Particle(actor));
        bloods.add(new Particle(actor));
        bloods.add(new Particle(actor));
        bloods.add(new Particle(actor));
    }

}

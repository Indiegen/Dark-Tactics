package com.indiegen.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.indiegen.game.Actors.CustomActor;

import java.util.ArrayList;
import java.util.Iterator;

class Blood extends Actor
{
	private ShapeRenderer shape;
	CustomActor actor;
	private ArrayList<Particle> bloods = new ArrayList<Particle>();



	Blood(ShapeRenderer shape){
		this.shape=shape;


	}
	
	@Override
    public void draw(Batch batch, float parentAlpha)
	{
		
		batch.end();	
		Gdx.gl.glEnable(GL20.GL_BLEND);
		//Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		shape.begin(ShapeType.Filled);	
		//shape.setProjectionMatrix(shape.getProjectionMatrix());
		
		for (Particle blood: bloods){
			shape.setColor(blood.getColor());
			shape.rect(blood.getPos().x,blood.getPos().y,blood.getSize(),blood.getSize());
			blood.animate(.008f);
			
			
			
		}
		
		Iterator<Particle> itr = bloods.listIterator();
		
		while(itr.hasNext()){
			if (itr.next().getAlpha()<= 0){

				bloods.remove(itr);
			}
		}

		shape.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);

		batch.begin();
		
	}
	void createBlood(CustomActor actor)
	{
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

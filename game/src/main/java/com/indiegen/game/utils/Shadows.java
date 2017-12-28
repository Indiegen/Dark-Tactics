package com.indiegen.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;

/**
 * Created by Laptop on 26/12/2017.
 */

public class Shadows {
    float delta = .1f;
    float incDir=1;
    float inc=0f;
    Batch batch;
    public Shadows(Batch batch)
    {
     this.batch=batch;
    }

    public void draw(){
        Interpolation inter = new Interpolation.Pow(2);
        inc = inc + Gdx.graphics.getDeltaTime()/9*incDir;
        delta=inter.apply(inc)/6;
        if(inc > 1)
        {
            incDir=-1;
            //inc = (float)(new RandomXS128().nextFloat()*.06+.03);
        }

        if(inc<0){
            incDir=1;
            //inc = (float)(new RandomXS128().nextFloat()*.0006+.0003);

        }


        batch.setColor(0,0,0,.15f);
        batch.draw(AssetsManager.getTreeBranches(),-600,-300,AssetsManager.getTreeBranches().getWidth()*1.5f+AssetsManager.getTreeBranches().getWidth()*delta, AssetsManager.getTreeBranches().getHeight()*1.5f);
        batch.setColor(1,1,1,1);
    }
}

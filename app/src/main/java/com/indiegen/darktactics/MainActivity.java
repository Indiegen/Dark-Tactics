package com.indiegen.darktactics;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button starButton;
    private MediaPlayer player;

    @Override
    public void onClick(View p1) {
        if (p1 == starButton) {
            Intent i = new Intent(this, Game.class);
            startActivity(i);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        starButton = new Button(this);
        player = MediaPlayer.create(this, R.raw.titlescreen);
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);
        player.start();

        starButton = (Button) findViewById(R.id.button);
        TextView link = (TextView) findViewById(R.id.funding);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        starButton.setOnClickListener(this);
        ImageView start = new ImageView(this);
        start = (ImageView) findViewById(R.id.images);
        start.setOnClickListener(this);

        //Intent i = new Intent(this,juego.class);
        //startActivity(i);
    }

    @Override
    protected void onPause() {
        // TODO: Implement this method
        super.onPause();
        player.pause();

    }

    @Override
    protected void onResume() {
        // TODO: Implement this method
        super.onResume();

        player.start();
    }


}

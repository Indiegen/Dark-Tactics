package com.indiegen.game;



import android.app.*;
import android.content.*;
import android.os.*;
import android.text.method.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.media.*;

public class MainActivity extends Activity implements OnClickListener
{
    game initgame;
	
	Button starButton;
	TextView link;
	MediaPlayer player;
	@Override
	public void onClick(View p1)
	{
		if(p1==starButton){
			Intent i = new Intent(this,Game.class);
			startActivity(i);
		}
		// TODO: Implement this method
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		starButton= new Button(this);
		player = MediaPlayer.create(this, R.raw.titlescreen);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
		player.start();
		
		starButton = (Button)findViewById(R.id.button);
		link = (TextView)findViewById(R.id.funding);
		link.setMovementMethod(LinkMovementMethod.getInstance());
		
		starButton.setOnClickListener(this);
		ImageView start= new ImageView(this);
		start= (ImageView) findViewById(R.id.images);
		start.setOnClickListener(this);
		
		//Intent i = new Intent(this,juego.class);
		//startActivity(i);
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		player.pause();
		
	}

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		super.onResume();
		
		player.start();
	}
	
	
}

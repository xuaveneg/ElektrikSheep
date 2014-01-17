package com.game.elektriksheep.activities;

import com.game.elektriksheep.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelChoiceActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Resources res = getResources();
		int highestLevel = res.getInteger(R.integer.highestLevel);
		int levelNumber = res.getInteger(R.integer.levelNumber);
		if (highestLevel > levelNumber)
			highestLevel = 0;
		setContentView(R.layout.activity_levelchoice);
		for (int iLevel = highestLevel; iLevel <= levelNumber; ++iLevel) {
			try {
				Button toHide = (Button)findViewById(R.id.class.getDeclaredField("level"+iLevel+"Choice").getInt(null));
				toHide.setVisibility(View.GONE);
			} catch (Exception e) {
				break;
			}
		}
	}
	
	public void startLevel(View view) {
		int levelNumber = Integer.parseInt((String)view.getTag());
		Intent intent = new Intent(this, LevelActivity.class);
		intent.putExtra("com.game.elektriksheep.activities.LevelNumber", levelNumber);
		startActivity(intent);
	}
	
}

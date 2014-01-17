package com.game.elektriksheep.activities;

import com.game.elektriksheep.R;
import com.game.elektriksheep.utils.MovingDirection;
import com.game.elektriksheep.views.LevelView;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public class LevelActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int levelNuber = getIntent().getIntExtra("", 0);
		setContentView(R.layout.activity_level);
	}
}

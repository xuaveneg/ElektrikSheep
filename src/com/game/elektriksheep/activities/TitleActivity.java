package com.game.elektriksheep.activities;

import com.game.elektriksheep.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class TitleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);
	}
	
	public void startPressed(View view) {
		Intent intent = new Intent(this, LevelChoiceActivity.class);
		startActivity(intent);
	}
	
	public void exitPressed(View view) {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}

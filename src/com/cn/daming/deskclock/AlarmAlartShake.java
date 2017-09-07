package com.cn.daming.deskclock;
import com.cn.daming.deskclock.ShakeDetector.OnShakeListener;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class AlarmAlartShake extends Activity {
	private MediaPlayer m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		m = new MediaPlayer();
		m.reset();// ª÷∏¥µΩŒ¥≥ı ºªØµƒ◊¥Ã¨
		m = MediaPlayer.create(AlarmAlartShake.this, R.raw.memory);// ∂¡»°“Ù∆µ
		m.start();
		setContentView(R.layout.alarm_alert_shake);
		 final ShakeDetector shakeDetector = new ShakeDetector(this);
		    shakeDetector.registerOnShakeListener(new OnShakeListener()
		    {
		      public void onShake()
		      {
		    	finish();
		    	m.stop();
		        shakeDetector.stop();
		      }
		    });
		    shakeDetector.start();
	}

}

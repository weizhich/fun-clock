package com.cn.daming.deskclock;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class AlarmAlertLight extends Activity implements SensorEventListener{
	//定义系统的sensor管理器
	private SensorManager mSensorManager;
	private EditText etLight;
	private MediaPlayer m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 m = new MediaPlayer();
		 m.reset();// 恢复到未初始化的状态
		 m = MediaPlayer.create(AlarmAlertLight.this, R.raw.ling);// 读取音频
		 m.start();
		setContentView(R.layout.alarm_alert_light);
		//获取文本框组件
		etLight = (EditText)findViewById(R.id.etLight);
		//获取传感器管理服务
		mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//为光传感器注册监听器
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onStop() {
		// 程序退出取消传感器注册
		mSensorManager.unregisterListener(this);
		super.onStop();
	}

	@Override
	protected void onPause() {
		// 程序暂停取消传感器注册
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float[] values = event.values;
		StringBuilder sb = null;
		sb = new StringBuilder();
		sb.append("当前光强为：");
		sb.append(values[0]);
		etLight.setText(sb.toString());
		if(values[0] > 500) {
			finish();
			m.stop();
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}
}

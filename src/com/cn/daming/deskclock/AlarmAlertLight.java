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
	//����ϵͳ��sensor������
	private SensorManager mSensorManager;
	private EditText etLight;
	private MediaPlayer m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 m = new MediaPlayer();
		 m.reset();// �ָ���δ��ʼ����״̬
		 m = MediaPlayer.create(AlarmAlertLight.this, R.raw.ling);// ��ȡ��Ƶ
		 m.start();
		setContentView(R.layout.alarm_alert_light);
		//��ȡ�ı������
		etLight = (EditText)findViewById(R.id.etLight);
		//��ȡ�������������
		mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//Ϊ�⴫����ע�������
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onStop() {
		// �����˳�ȡ��������ע��
		mSensorManager.unregisterListener(this);
		super.onStop();
	}

	@Override
	protected void onPause() {
		// ������ͣȡ��������ע��
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float[] values = event.values;
		StringBuilder sb = null;
		sb = new StringBuilder();
		sb.append("��ǰ��ǿΪ��");
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

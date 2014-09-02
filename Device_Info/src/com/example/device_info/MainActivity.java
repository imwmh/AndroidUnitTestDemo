package com.example.device_info;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	private EditText EditTxtDeviceInfo=null;
	
	private Button mGetInfoButton=null;
	private Handler mHandler=null;
	StringBuilder mDeviceInfo=null;
	
	
	protected void onStart()
	{
		super.onStart();
		EditTxtDeviceInfo=(EditText)findViewById(R.id.editText1);
		mGetInfoButton=(Button)findViewById(R.id.button1);
		
		//Set the button onClick listener
		mGetInfoButton.setOnClickListener(new OnClickListenerResolveButton());
	}
	


	//OnClick msg processing 
	class OnClickListenerResolveButton implements android.view.View.OnClickListener
	{  
	    public void onClick(View v) 
	    {  
	    	new Thread()
	    	{  
	            public void run()
	            {    
	            	//call the get device info here
		    		//mDeviceInfo=GetDeviceInfo();
	            	mHandler.post(runnableUi); 
	             }                     
	        }.start();                        
	    }

		Runnable   runnableUi=new  Runnable()
		{  
	        @Override  
	        public void run() 
	        {  
	    		//Put the result back the UI -edittext2
	        	EditTxtDeviceInfo.setText(GetDeviceInfo().toString());
	        }  
	          
	    };
	}   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		//Create a handler
		mHandler=new Handler();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	
	public void OnButtonGetDevindoClick()
	{
		this.EditTxtDeviceInfo.setText(GetDeviceInfo().toString());
	
	}
	public StringBuilder GetDeviceInfo()
	{
		StringBuilder phoneInfo = new StringBuilder();
		phoneInfo.append("Product: " + android.os.Build.PRODUCT + System.getProperty("line.separator"));
		
		phoneInfo.append( "CPU_ABI: " + android.os.Build.CPU_ABI + System.getProperty("line.separator"));
		phoneInfo.append( "TAGS: " + android.os.Build.TAGS + System.getProperty("line.separator"));
		phoneInfo.append( "VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE + System.getProperty("line.separator"));
		phoneInfo.append( "MODEL: " + android.os.Build.MODEL + System.getProperty("line.separator"));
		phoneInfo.append( "SDK: " + android.os.Build.VERSION.SDK + System.getProperty("line.separator"));
		phoneInfo.append( "VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE + System.getProperty("line.separator"));
		phoneInfo.append( "DEVICE: " + android.os.Build.DEVICE + System.getProperty("line.separator"));
		phoneInfo.append( "DISPLAY: " + android.os.Build.DISPLAY + System.getProperty("line.separator"));
		phoneInfo.append( "BRAND: " + android.os.Build.BRAND + System.getProperty("line.separator"));
		phoneInfo.append( "BOARD: " + android.os.Build.BOARD + System.getProperty("line.separator"));
		phoneInfo.append( "FINGERPRINT: " + android.os.Build.FINGERPRINT + System.getProperty("line.separator"));
		phoneInfo.append( "ID: " + android.os.Build.ID + System.getProperty("line.separator"));
		phoneInfo.append( "MANUFACTURER: " + android.os.Build.MANUFACTURER + System.getProperty("line.separator"));
		phoneInfo.append( "USER: " + android.os.Build.USER + System.getProperty("line.separator"));
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		phoneInfo.append("DeviceId(IMEI) = " + tm.getDeviceId() + System.getProperty("line.separator"));
		phoneInfo.append("DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion() + System.getProperty("line.separator"));
		phoneInfo.append("Line1Number = " + tm.getLine1Number() + System.getProperty("line.separator"));
		phoneInfo.append("NetworkCountryIso = " + tm.getNetworkCountryIso() + System.getProperty("line.separator"));
		phoneInfo.append("NetworkOperator = " + tm.getNetworkOperator() + System.getProperty("line.separator"));
		phoneInfo.append("NetworkOperatorName = " + tm.getNetworkOperatorName() + System.getProperty("line.separator"));
		phoneInfo.append("NetworkType = " + tm.getNetworkType() + System.getProperty("line.separator"));
		phoneInfo.append("PhoneType = " + tm.getPhoneType() + System.getProperty("line.separator"));
		phoneInfo.append("SimCountryIso = " + tm.getSimCountryIso() + System.getProperty("line.separator"));
		phoneInfo.append("SimOperator = " + tm.getSimOperator() + System.getProperty("line.separator"));
		phoneInfo.append("SimOperatorName = " + tm.getSimOperatorName() + System.getProperty("line.separator"));
		phoneInfo.append("SimSerialNumber = " + tm.getSimSerialNumber() + System.getProperty("line.separator"));
		phoneInfo.append("SimState = " + tm.getSimState() + System.getProperty("line.separator"));
		phoneInfo.append("SubscriberId(IMSI) = " + tm.getSubscriberId() + System.getProperty("line.separator"));
		phoneInfo.append("VoiceMailNumber = " + tm.getVoiceMailNumber() + System.getProperty("line.separator"));
		Log.v("VVVVVVVVVVVVVVVVVVVVVVVVV","Finished");
		return phoneInfo;
	}
}

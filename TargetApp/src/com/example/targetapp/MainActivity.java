package com.example.targetapp;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

	private EditText EditTxtEquation=null;
	private EditText ResultEditTxt=null;
	private Button mResolveButton=null;
	private Handler mHandler=null;
	private double result=0.0;
	public static int thread_status=0;
	
	protected void onStart()
	{
		super.onStart();
		EditTxtEquation=(EditText)findViewById(R.id.editText1);
		ResultEditTxt=(EditText)findViewById(R.id.editText2);
		mResolveButton=(Button)findViewById(R.id.button1);
		
		//Set the button onClick listener
		mResolveButton.setOnClickListener(new OnClickListenerResolveButton());
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

	//OnClick msg processing 
	class OnClickListenerResolveButton implements android.view.View.OnClickListener
	{  
	    public void onClick(View v) 
	    {  
	    	new Thread()
	    	{  
	            public void run()
	            {    
	            	//Resolve the equation and update the UI here
		        	String strEquation=EditTxtEquation.getText().toString();
		    		//call the equation resolving method here
		    		result=EquationAnalysis.arithmetic(strEquation);
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
	    		ResultEditTxt.setText(Double.toString(result));
	    		thread_status=1;
	        }  
	          
	    };
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
	
	/*public void onResolveEquation(View v)
	{
		//Get the user's input from the UI editText1 and editText2
		String strEquation="";
		EditText EditTxtEquation=(EditText)findViewById(R.id.editText1);
		strEquation=EditTxtEquation.getText().toString();
		
		
		//call the equation resolving method here
		double result=EquationAnalysis.arithmetic(strEquation);
		
		//Put the result back the UI -edittext2
		EditText ResultEditTxt=(EditText)findViewById(R.id.editText2);
		ResultEditTxt.setText(Double.toString(result));
	}*/
}


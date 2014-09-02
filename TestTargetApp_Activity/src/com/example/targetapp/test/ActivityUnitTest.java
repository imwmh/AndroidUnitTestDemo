package com.example.targetapp.test;

import org.junit.Before;
import org.junit.Test;

import android.app.Instrumentation;
import android.os.SystemClock;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;

import com.example.targetapp.MainActivity;
import com.example.targetapp.R;

public class ActivityUnitTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	
	private MainActivity mActivity;
	private EditText mEquationEditText,mResultEditText;
	private Button mResolveButton;
	//private String mEquation0="4*(9-4)*5-30";
	private Instrumentation mInstrumemntation;
	double actualResult=0;
	double expectedResult=49.0;
	
	@SuppressWarnings("deprecation")
	public ActivityUnitTest()
	{
		super("com.example.targetapp",MainActivity.class);
	}

	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		
		//Initialize the controls here
		mActivity=this.getActivity();
		this.mEquationEditText=(EditText)this.mActivity.findViewById(R.id.editText1);
		this.mResultEditText=(EditText)this.mActivity.findViewById(R.id.editText2);
		this.mResolveButton=(Button)this.mActivity.findViewById(R.id.button1);
		
		assertNotNull(this.mActivity);
		assertNotNull(this.mEquationEditText);
		assertNotNull(this.mResolveButton);
		this.mInstrumemntation=this.getInstrumentation();
		
	}

	/*@Test 
	protected void runTest() throws Exception{
		testInAnotherWay();
	}*/
	
	
	
	@UiThreadTest
	public void testFocuseOnEditText(EditText mEditText)
	{
		mEditText.requestFocus();
		//mEditText.performClick();
	}
	
	@UiThreadTest
	public void testFocuse()
	{
		this.mEquationEditText.requestFocus();
		this.mEquationEditText.performClick();
	}
	
	@UiThreadTest
	public void testButtonClick()
	{
		//this.mResolveButton.requestFocus();
		this.mResolveButton.requestFocus();
		assertNotNull(this.mResolveButton);
		this.mResolveButton.performClick();
	}
	
	
	@Test
	public void testWithUiThreadAnnotation()
	{
		//this.mEquationEditText gets focus
		this.testFocuseOnEditText(mEquationEditText);
		
		this.mInstrumemntation.waitForIdleSync();
		//Set the equation string 
		this.sendKeys(" 9 ");
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_ADD);
		this.sendKeys("4");
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_MULTIPLY);
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_LEFT_PAREN);
		this.sendKeys("8");
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_ADD);
		this.sendKeys("2");
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_RIGHT_PAREN);
		this.mInstrumemntation.waitForIdleSync();
		
		//perform the click 
		assertNotNull(this.mResolveButton);
		this.testButtonClick();
		this.mInstrumemntation.waitForIdleSync();
		
		//Check the result here
		//actualResult=Double.parseDouble(this.mResultEditText.getText().toString());
		//assertEquals(expectedResult,actualResult);
	}
	
	@Test
	public void testOnResolveEquation() {
		
		
		mActivity.runOnUiThread(new Runnable(){ 
			public void run()
		{
			assertTrue(mEquationEditText.requestFocus());
		}
		});
		
		
		this.mInstrumemntation.waitForIdleSync();
		this.sendKeys(" 9 ");
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_ADD);
		this.sendKeys("4");
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_MULTIPLY);
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_LEFT_PAREN);
		this.sendKeys("8");
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_ADD);
		this.sendKeys("2");
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_RIGHT_PAREN);
		
		mActivity.runOnUiThread(new Runnable(){ 
			public void run()
		{
			assertTrue(mResolveButton.requestFocus());
			assertTrue(mResolveButton.performClick());
		}
		});
		
		//Delay to wait for the calculation thread to finish the job
		SystemClock.sleep((long)1000.0);
		
		actualResult=Double.parseDouble(this.mResultEditText.getText().toString());
		assertEquals(expectedResult,actualResult);
		
	}
	
	@Test
	public void testWithMainSyncOnInstrumentation()
	{
		this.mInstrumemntation.runOnMainSync(new Runnable(){ 
			public void run()
		{
			assertTrue(mEquationEditText.requestFocus());
		}
		});
		
		
		this.mInstrumemntation.waitForIdleSync();
		this.sendKeys(" 9 ");
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_ADD);
		this.sendKeys("4");
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_MULTIPLY);
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_LEFT_PAREN);
		this.sendKeys("8");
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_ADD);
		this.sendKeys("2");
		this.sendKeys(KeyEvent.KEYCODE_NUMPAD_RIGHT_PAREN);
		
		this.mInstrumemntation.runOnMainSync(new Runnable(){ 
			public void run()
		{
			assertTrue(mResolveButton.requestFocus());
			assertTrue(mResolveButton.performClick());
		}
		});
		
		//Delay to wait for the calculation thread to finish the job
		SystemClock.sleep((long) 1000.0);
		
		actualResult=Double.parseDouble(this.mResultEditText.getText().toString());
		assertEquals(expectedResult,actualResult);
	}
	/* private class PerformClick implements Runnable {
	        Button btn;
	        public PerformClick(Button button) {
	            btn = button;
	        }
	  
	        public void run() {
	            btn.performClick();
	        }
	    }

	 private class PerformGetFocus_EditText implements Runnable
	 {
		 EditText edittext;
		 public PerformGetFocus_EditText(EditText mEditText)
		 {
			 edittext=mEditText;
		 }
		 public void run()
		 {
			 edittext.requestFocus();
		 }
	 }
	 */
}

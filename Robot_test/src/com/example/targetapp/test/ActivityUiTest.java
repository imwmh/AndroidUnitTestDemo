package com.example.targetapp.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.targetapp.MainActivity;
import com.robotium.solo.Solo;

import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;

public class ActivityUiTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	
	public ActivityUiTest()
	{
		super("com.example.targetapp", MainActivity.class);
	}

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		this.solo=new Solo(getInstrumentation(),getActivity());
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		/*try {
			this.solo.finishOpenedActivities();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	@Test
	public void test() {
		
		solo.typeText(0, "10+10*(9+1)");
		solo.sleep(2000);
		solo.getButton("=").callOnClick();
		//solo.clickOnButton("=");
		solo.sleep(2000);
		solo.takeScreenshot("my_screen_shoot");
		assertTrue(solo.searchEditText("110.0"));
		//fail("Not yet implemented");
	}

}

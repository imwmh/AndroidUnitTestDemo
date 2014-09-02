package com.example.targetapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.res.Resources;
import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

@SuppressWarnings("rawtypes")
public class Apk_only_ActivityTest extends ActivityInstrumentationTestCase2
{
       //定义变量
		private Solo solo=null;
		private Instrumentation mInstrumemntation;
		private Activity activity=null;
		private static Class<?> launchActivityClass;
		private Resources mRes=null;
		//对应re-sign.jar生成出来的信息框里的两个值
		private static String mainActiviy = "com.example.targetapp.MainActivity";
		private static String packageName = "com.example.targetapp";
		static {
		try {
		launchActivityClass = Class.forName(mainActiviy);
		} catch (ClassNotFoundException e) {
		throw new RuntimeException(e);
		}
}


@SuppressWarnings({ "deprecation", "unchecked" })
public Apk_only_ActivityTest() 
{
	super(packageName, launchActivityClass);
}

@Override

protected void setUp() throws Exception 
{
	//super.setUp();
	this.activity = this.getActivity();
	
	//required the resources related to the activity
	mRes=this.activity.getResources();
	this.mInstrumemntation=this.getInstrumentation();
	this.solo = new Solo(this.mInstrumemntation,this.activity);
}

public void test() 
{
	solo.typeText(0, "10+10*(9+1)");
	solo.sleep(2000);
	solo.getButton("=").callOnClick();
	solo.sleep(2000);
	solo.takeScreenshot("my_screen_shoot");
	assertTrue(solo.searchEditText("110.0"));     
}

public void test1()
{	//we define view objects here
	int editTxt1ID,editTxt2ID;
	editTxt1ID=mRes.getIdentifier("editText1", "id", "com.example.targetapp");
	editTxt2ID=mRes.getIdentifier("editText2", "id", "com.example.targetapp");
	View editTxt1View=this.activity.findViewById(editTxt1ID);
	View editTxt2View=this.activity.findViewById(editTxt2ID);
	//Real test procedure starts here
	solo.typeText((EditText)editTxt1View, "10+10*(9+1)");
	//solo.typeText(0, "10+10*(9+1)");
	solo.sleep(2000);	
	solo.getButton("=").callOnClick();
	//solo.getButton(mButtonID).performClick();
	solo.sleep(2000);
	assertEquals(((EditText)editTxt2View).getText().toString(),"110.0");  
	solo.takeScreenshot("my_screen_shoot.jpg");
}

public void test2()
{	//we define view objects here
	int editTxt1ID,editTxt2ID;
	editTxt1ID=mRes.getIdentifier("editText1", "id", "com.example.targetapp");
	editTxt2ID=mRes.getIdentifier("editText2", "id", "com.example.targetapp");
	View editTxt1View=this.activity.findViewById(editTxt1ID);
	View editTxt2View=this.activity.findViewById(editTxt2ID);
	//Real case starts here
	solo.typeText((EditText)editTxt1View, "1+(12+13)*3");
	//solo.typeText(0, "10+(2+3)*3");
	solo.sleep(2000);	
	solo.getButton("=").callOnClick();
	//solo.getButton(mButtonID).performClick();
	solo.sleep(2000);
	assertEquals(((EditText)editTxt2View).getText().toString(),"76.0");  
	solo.takeScreenshot("my_screen_shoot2.jpg");
}
@Override

public void tearDown() throws Exception 
{

	try 
	{

		this.solo.finishOpenedActivities();

	} 
	catch (Throwable e) 
	{

		e.printStackTrace();

	}

	this.activity.finish();

	super.tearDown();

}

}
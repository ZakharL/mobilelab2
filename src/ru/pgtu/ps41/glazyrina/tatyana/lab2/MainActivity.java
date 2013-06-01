package ru.pgtu.ps41.glazyrina.tatyana.lab2;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
//import android.util.Log;
//import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
//import android.widget.TextView;
import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle; 
import android.view.View; 
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker; 



public class MainActivity extends Activity {
	
	
	
	private DatePicker date1;
	private DatePicker date2;
	
	int year1;  
	int month1;  
	int day1; 
	
	int year2;  
	int month2;  
	int day2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		date1 = (DatePicker) findViewById(R.id.datePicker1);
		date2 = (DatePicker) findViewById(R.id.datePicker2);
	}

	
	public final void commandListener(final View target) {
		switch (target.getId()) {
		case R.id.button1:
			Intent wayToFarFarAway = new Intent(this,
					SubActivity.class);

					
			Integer month1 = date1.getMonth();  
			Integer day1 = date1.getDayOfMonth(); 
			Integer year1 = date1.getYear();
			
			Integer month2 = date2.getMonth();  
			Integer day2 = date2.getDayOfMonth(); 
			Integer year2 = date2.getYear();
			
			DataInfo dateAll = new DataInfo(year1, month1, day1, year2, month2, day2);
			wayToFarFarAway.putExtra("dataInfo", dateAll);


			startActivity(wayToFarFarAway);
			break;

		default:
			finish();
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

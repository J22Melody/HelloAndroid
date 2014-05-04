package main;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import helper.URLConnectionHelper;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hello.R;

public class DisplayMessageActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
		String result = "";
		
    	String url = "https://api.douban.com/v2/book/search";
		String str = URLConnectionHelper.sendGet(url, "count=3&q=" + message);
		JSONObject json = null;
		try {
			json = new JSONObject(str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONArray books = null;
		try {
			books = (JSONArray) json.get("books");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<books.length();i++){
			JSONObject book = null;
			try {
				book = ((JSONObject) books.get(i));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				result += "《" + book.get("title") + "》" + "\n";
				result += "作者：" + book.get("author") + "\n";
				result += "出版社/出版日期：" + book.get("publisher") + "/" + book.get("pubdate") + "\n";
				result += "价格：" + book.get("price") + "\n";
				result += "页数：" + book.get("pages") + "\n";
				result += "ISBN：" + book.get("isbn13") + "\n";
				result += "评分：" + ((JSONObject) book.get("rating")).get("average") + "（" + ((JSONObject)book.get("rating")).get("numRaters") + "人评分）" + "\n";
				result += "豆瓣链接：" + book.get("alt") + "\n";
				result += "--------------------" + "\n";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		TextView textView = new TextView(this);
		textView.setText(result);
		
		setContentView(textView);
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
			View rootView = inflater.inflate(R.layout.fragment_display_message,
					container, false);
			return rootView;
		}
	}

}

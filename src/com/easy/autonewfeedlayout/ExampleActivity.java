package com.easy.autonewfeedlayout;

import android.app.Activity;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ExampleActivity extends Activity implements OnClickListener {

	private static float screen_density;

	private int padding;

	private String[] tags;

	private FlowLayout parent;

	private EditText editText;

	private Button addBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		tags = this.getResources().getStringArray(R.array.tags_1);
		parent = (FlowLayout) this.findViewById(R.id.tag_layout);
		editText = (EditText) this.findViewById(R.id.tag_edit);
		addBtn = (Button) this.findViewById(R.id.add_btn);
		addBtn.setOnClickListener(this);
		screen_density = getScreenDensity();
		for (String item : tags) {
			TextView tag = getTextView(item);
			parent.addView(tag);
			tag.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.add_btn) {
			String temp = editText.getText().toString();
			if (!TextUtils.isEmpty(temp)) {
				parent.addView(getTextView(temp));
				editText.setText("");
			}
		}
	}

	private TextView getTextView(String text) {
		TextView tv = new TextView(this);
		tv.setBackgroundResource(R.drawable.tag_bg);
		tv.setMaxEms(6);
		tv.setSingleLine(true);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
		tv.setPadding(padding, padding, padding, padding);
		tv.setGravity(Gravity.CENTER);
		tv.setEllipsize(TextUtils.TruncateAt.END);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				(int) (35 * screen_density));
		tv.setLayoutParams(lp);
		tv.setText(text);
		return tv;
	}

	private float getScreenDensity() {
		DisplayMetrics temp = this.getResources().getDisplayMetrics();
		return temp.density;
	}
}

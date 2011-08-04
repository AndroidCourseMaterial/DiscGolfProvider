package edu.rosehulman.discgolfprovider;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class DiscGolfRoundAdapter extends SimpleCursorAdapter {

	private Cursor mCursor;
	private Context mContext;
	
	public DiscGolfRoundAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
		super(context, layout, c, from, to);
		mCursor = c;
		mContext = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View roundScoreView = super.getView(position, convertView, parent);
		
		// Get the date for this round
		int iRoundDate = mCursor.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.ROUND_DATE);
		long dateMS = mCursor.getLong(iRoundDate);
		Date d = new Date(dateMS);
		
		// Format the date and set text view
		TextView dateTextView = (TextView) roundScoreView.findViewById(R.id.textViewDate);
		String date = (new SimpleDateFormat("M/d/yy")).format(d);
		dateTextView.setText(date);
		
		// Get the total
		int iTotal = mCursor.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.ROUND_TOTAL);
		TextView totalTextView = (TextView) roundScoreView.findViewById(R.id.textViewTotal);
		int coursePar = 72;  // TODO: Get real value
		int offPar = mCursor.getInt(iTotal) - coursePar;
		String offParString;
		if (offPar == 0) {
			offParString = "Even";
		} else if (offPar > 0) {
			offParString = "+" + offPar;
		} else {
			offParString = "" + offPar;
		}
		totalTextView.setText(offParString);
		
		return roundScoreView;
	}

}

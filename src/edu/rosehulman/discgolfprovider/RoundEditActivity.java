package edu.rosehulman.discgolfprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RoundEditActivity extends Activity {

	EditText mEditTextUsername;
	EditText mEditTextCoursename;
	EditText[] mEditTextHoles = new EditText[18];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_round_activity);

		mEditTextUsername = (EditText) findViewById(R.id.editTextUsername);
		mEditTextCoursename = (EditText) findViewById(R.id.editTextCourseName);
		mEditTextHoles[0] = (EditText) findViewById(R.id.editTextHole01);
		mEditTextHoles[1] = (EditText) findViewById(R.id.editTextHole02);
		mEditTextHoles[2] = (EditText) findViewById(R.id.editTextHole03);
		mEditTextHoles[3] = (EditText) findViewById(R.id.editTextHole04);
		mEditTextHoles[4] = (EditText) findViewById(R.id.editTextHole05);
		mEditTextHoles[5] = (EditText) findViewById(R.id.editTextHole06);
		mEditTextHoles[6] = (EditText) findViewById(R.id.editTextHole07);
		mEditTextHoles[7] = (EditText) findViewById(R.id.editTextHole08);
		mEditTextHoles[8] = (EditText) findViewById(R.id.editTextHole09);
		mEditTextHoles[9] = (EditText) findViewById(R.id.editTextHole10);
		mEditTextHoles[10] = (EditText) findViewById(R.id.editTextHole11);
		mEditTextHoles[11] = (EditText) findViewById(R.id.editTextHole12);
		mEditTextHoles[12] = (EditText) findViewById(R.id.editTextHole13);
		mEditTextHoles[13] = (EditText) findViewById(R.id.editTextHole14);
		mEditTextHoles[14] = (EditText) findViewById(R.id.editTextHole15);
		mEditTextHoles[15] = (EditText) findViewById(R.id.editTextHole16);
		mEditTextHoles[16] = (EditText) findViewById(R.id.editTextHole17);
		mEditTextHoles[17] = (EditText) findViewById(R.id.editTextHole18);

		final Button cancelButton = (Button) findViewById(R.id.buttonCancel);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(Activity.RESULT_CANCELED);
				finish();
			}
		});

		final Button confirmationButton = (Button) findViewById(R.id.buttonConfirm);
		confirmationButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				saveState();
				setResult(RESULT_OK);
				finish();
			}
		});
		
		if (getIntent().getData() != null) {
			confirmationButton.setText(R.string.update);
		} else {
			confirmationButton.setText(R.string.add);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		//saveState();
	}
	@Override
	protected void onResume() {
		super.onResume();
		// Determine if we should be populating the fields with data
		if (getIntent().getData() != null) {
			Log.d(DiscGolfProviderApplication.TAG, "RoundEditActivity.onResume populate fields with uri" + getIntent().getData() );
			populateFields();
		}
	}
	private void populateFields() {
		Uri singleRoundUri = getIntent().getData();
		Cursor c = managedQuery(singleRoundUri, null, null, null, null);
		if (c.moveToFirst()) {
			int iGolferName = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.GOLFER_USERNAME);
			int iCourseName = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.COURSE_NAME);
			int ihole01 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_01);
			int ihole02 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_02);
			int ihole03 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_03);
			int ihole04 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_04);
			int ihole05 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_05);
			int ihole06 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_06);
			int ihole07 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_07);
			int ihole08 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_08);
			int ihole09 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_09);
			int ihole10 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_10);
			int ihole11 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_11);
			int ihole12 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_12);
			int ihole13 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_13);
			int ihole14 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_14);
			int ihole15 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_15);
			int ihole16 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_16);
			int ihole17 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_17);
			int ihole18 = c.getColumnIndex(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_18);

			String golferName = c.getString(iGolferName);
			String courseName = c.getString(iCourseName);
			int[] holeScores = new int[18];
			holeScores[0] = c.getInt(ihole01);
			holeScores[1] = c.getInt(ihole02);
			holeScores[2] = c.getInt(ihole03);
			holeScores[3] = c.getInt(ihole04);
			holeScores[4] = c.getInt(ihole05);
			holeScores[5] = c.getInt(ihole06);
			holeScores[6] = c.getInt(ihole07);
			holeScores[7] = c.getInt(ihole08);
			holeScores[8] = c.getInt(ihole09);
			holeScores[9] = c.getInt(ihole10);
			holeScores[10] = c.getInt(ihole11);
			holeScores[11] = c.getInt(ihole12);
			holeScores[12] = c.getInt(ihole13);
			holeScores[13] = c.getInt(ihole14);
			holeScores[14] = c.getInt(ihole15);
			holeScores[15] = c.getInt(ihole16);
			holeScores[16] = c.getInt(ihole17);
			holeScores[17] = c.getInt(ihole18);

			mEditTextUsername.setText(golferName);
			mEditTextCoursename.setText(courseName);
			mEditTextHoles[0].setText(""+holeScores[0]);
			mEditTextHoles[1].setText(""+holeScores[1]);
			mEditTextHoles[2].setText(""+holeScores[2]);
			mEditTextHoles[3].setText(""+holeScores[3]);
			mEditTextHoles[4].setText(""+holeScores[4]);
			mEditTextHoles[5].setText(""+holeScores[5]);
			mEditTextHoles[6].setText(""+holeScores[6]);
			mEditTextHoles[7].setText(""+holeScores[7]);
			mEditTextHoles[8].setText(""+holeScores[8]);
			mEditTextHoles[9].setText(""+holeScores[9]);
			mEditTextHoles[10].setText(""+holeScores[10]);
			mEditTextHoles[11].setText(""+holeScores[11]);
			mEditTextHoles[12].setText(""+holeScores[12]);
			mEditTextHoles[13].setText(""+holeScores[13]);
			mEditTextHoles[14].setText(""+holeScores[14]);
			mEditTextHoles[15].setText(""+holeScores[15]);
			mEditTextHoles[16].setText(""+holeScores[16]);
			mEditTextHoles[17].setText(""+holeScores[17]);
		}
	}

	private void saveState() {
		ContentValues cv = new ContentValues();
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.GOLFER_USERNAME, mEditTextUsername.getText().toString());
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.COURSE_NAME, mEditTextCoursename.getText().toString());
		int[] holeScores = new int[18];

		for (int i=0 ; i<18 ; i++) {
			try {
				holeScores[i] = Integer.valueOf(mEditTextHoles[i].getText().toString());
			} catch (NumberFormatException e) {
				holeScores[i] = 0;	
			}
		}

		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_01, holeScores[0]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_02, holeScores[1]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_03, holeScores[2]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_04, holeScores[3]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_05, holeScores[4]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_06, holeScores[5]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_07, holeScores[6]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_08, holeScores[7]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_09, holeScores[8]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_10, holeScores[9]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_11, holeScores[10]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_12, holeScores[11]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_13, holeScores[12]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_14, holeScores[13]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_15, holeScores[14]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_16, holeScores[15]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_17, holeScores[16]);
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.HOLE_18, holeScores[17]);

		int total = 0;
		for(int i=0 ; i<18 ; i++) {
			total += holeScores[i];
		}
		cv.put(DiscGolfProviderMetaData.RoundScoresTableMetaData.ROUND_TOTAL, total);


		if (getIntent().getData() != null) {			
			// Update the existing URI
			Uri uri = getIntent().getData();
			ContentResolver cr = this.getContentResolver();
			int numRowsUpdated = cr.update(uri, cv, null, null);
			if (numRowsUpdated > 0 ) {
				Log.d(DiscGolfProviderApplication.TAG,"updated uri:" + uri);			
			} else {
				Log.e(DiscGolfProviderApplication.TAG,"failed to update uri");
			}
		} else {
			// Insert a new round score
			Uri uri = DiscGolfProviderMetaData.RoundScoresTableMetaData.CONTENT_URI;
			ContentResolver cr = RoundEditActivity.this.getContentResolver();
			Uri insertedUri = cr.insert(uri, cv);
			Log.d(DiscGolfProviderApplication.TAG,"inserted uri:" + insertedUri);	
		}	
	}
}

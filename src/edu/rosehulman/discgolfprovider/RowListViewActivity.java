package edu.rosehulman.discgolfprovider;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;



public class RowListViewActivity extends Activity {

	private SimpleCursorAdapter mRoundAdapter;
	private static final String TAG = DiscGolfProviderApplication.TAG;
	public static final int REQUEST_CODE_EDIT_ROUND = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.row_list_view_activity);
		ListView rowListView = (ListView) findViewById(R.id.listViewAllRounds);		
        registerForContextMenu(rowListView);
		
		Uri uri = DiscGolfProviderMetaData.RoundScoresTableMetaData.CONTENT_URI;
		Cursor c = this.managedQuery(uri, 
				null, //projection
				null, //selection string
				null, //selection args array of strings
				null); //sort order
		
		String[] from = new String[4];
		from[0] = DiscGolfProviderMetaData.RoundScoresTableMetaData.GOLFER_USERNAME;
		from[1] = DiscGolfProviderMetaData.RoundScoresTableMetaData.COURSE_NAME;
		from[2] = DiscGolfProviderMetaData.RoundScoresTableMetaData.ROUND_TOTAL;
		from[3] = DiscGolfProviderMetaData.RoundScoresTableMetaData.ROUND_DATE;
		int[] to = new int[4];
		to[0] = R.id.textViewName;
		to[1] = R.id.textViewCourse;
		to[2] = R.id.textViewTotal;
		to[3] = R.id.textViewDate;
		mRoundAdapter = new DiscGolfRoundAdapter(this, R.layout.round_view, c, from, to);
		rowListView.setAdapter(mRoundAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflator = getMenuInflater();
		inflator.inflate(R.menu.row_list_view_options_menu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		ContentResolver cr = this.getContentResolver();
		Uri uri = DiscGolfProviderMetaData.RoundScoresTableMetaData.CONTENT_URI;

		switch(item.getItemId()) {
		case R.id.menu_item_add_round_score:
			Toast.makeText(this, "Add item", Toast.LENGTH_SHORT).show();

			Intent editIntent = new Intent(this, RoundEditActivity.class);
			startActivity(editIntent);
			break;
		case R.id.menu_item_remove_last:
			Toast.makeText(this, "Remove last item", Toast.LENGTH_SHORT).show();

			int i = getCount();
			Uri delUri = Uri.withAppendedPath(uri, Integer.toString(i));
			cr.delete(delUri, null, null);
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	private int getCount()
	{
		Uri uri = DiscGolfProviderMetaData.RoundScoresTableMetaData.CONTENT_URI;
		Cursor c = this.managedQuery(uri, 
				null, //projection
				null, //selection string
				null, //selection args array of strings
				null); //sort order
		int numberOfRecords = c.getCount();
		c.close();
		return numberOfRecords;
	}

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflator = getMenuInflater();
		inflator.inflate(R.menu.row_list_view_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		ContentResolver cr = this.getContentResolver();
		Uri uri = DiscGolfProviderMetaData.RoundScoresTableMetaData.CONTENT_URI;
		Uri singleRoundUri = Uri.withAppendedPath(uri, ""+info.id);
		
    	switch(item.getItemId()) {
    	case R.id.menu_item_list_view_delete:
    		cr.delete(singleRoundUri, null, null);
    		return true;
    	case R.id.menu_item_list_view_edit:
    		Intent editIntent = new Intent(Intent.ACTION_EDIT);
    		editIntent.setData(singleRoundUri);
    		editIntent.setComponent(new ComponentName(this, RoundEditActivity.class));
    		startActivityForResult(editIntent, REQUEST_CODE_EDIT_ROUND);
    		return true;
    	}
    	return super.onContextItemSelected(item);
    }

   @Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	switch (requestCode) {
	case REQUEST_CODE_EDIT_ROUND:
		if (resultCode == RESULT_OK) {
			Toast.makeText(this, "Updated round info", Toast.LENGTH_SHORT).show();
			//mRoundAdapter.notifyDataSetChanged();
			Log.d(TAG, "RowListViewActivity.onActivityResult EDIT_ROUND RESULT_OK");
		} else {
			Log.d(TAG, "RowListViewActivity.onActivityResult EDIT_ROUND Result = " + resultCode);
		}
		break;
	}
}

}
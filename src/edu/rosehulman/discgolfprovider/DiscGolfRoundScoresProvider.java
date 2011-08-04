package edu.rosehulman.discgolfprovider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import edu.rosehulman.discgolfprovider.DiscGolfProviderMetaData.RoundScoresTableMetaData;

public class DiscGolfRoundScoresProvider extends ContentProvider
{
	public static HashMap<String, String> sDiscGolfRoundScoresProjectionMap;
	static 
	{
		sDiscGolfRoundScoresProjectionMap = new HashMap<String, String>();
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData._ID, RoundScoresTableMetaData._ID);

		//name, course
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.GOLFER_USERNAME, RoundScoresTableMetaData.GOLFER_USERNAME);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.COURSE_NAME, RoundScoresTableMetaData.COURSE_NAME);

		//holes
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_01, RoundScoresTableMetaData.HOLE_01);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_02, RoundScoresTableMetaData.HOLE_02);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_03, RoundScoresTableMetaData.HOLE_03);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_04, RoundScoresTableMetaData.HOLE_04);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_05, RoundScoresTableMetaData.HOLE_05);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_06, RoundScoresTableMetaData.HOLE_06);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_07, RoundScoresTableMetaData.HOLE_07);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_08, RoundScoresTableMetaData.HOLE_08);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_09, RoundScoresTableMetaData.HOLE_09);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_10, RoundScoresTableMetaData.HOLE_10);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_11, RoundScoresTableMetaData.HOLE_11);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_12, RoundScoresTableMetaData.HOLE_12);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_13, RoundScoresTableMetaData.HOLE_13);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_14, RoundScoresTableMetaData.HOLE_14);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_15, RoundScoresTableMetaData.HOLE_15);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_16, RoundScoresTableMetaData.HOLE_16);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_17, RoundScoresTableMetaData.HOLE_17);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.HOLE_18, RoundScoresTableMetaData.HOLE_18);
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.ROUND_TOTAL, RoundScoresTableMetaData.ROUND_TOTAL);

		// date
		sDiscGolfRoundScoresProjectionMap.put(RoundScoresTableMetaData.ROUND_DATE, RoundScoresTableMetaData.ROUND_DATE);
	}

	private static final UriMatcher sUriMatcher;
	private static final int INCOMING_URI_INDICATOR_ROUND_SCORES = 1;
	private static final int INCOMING_URI_INDICATOR_SINGLE_ROUND_SCORE = 2;
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(DiscGolfProviderMetaData.AUTHORITY, RoundScoresTableMetaData.TABLE_NAME, INCOMING_URI_INDICATOR_ROUND_SCORES);
		sUriMatcher.addURI(DiscGolfProviderMetaData.AUTHORITY, RoundScoresTableMetaData.TABLE_NAME+"/#", INCOMING_URI_INDICATOR_SINGLE_ROUND_SCORE);
	}

	/**
	 * This class helps open, create, and upgrade the database file.
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, 
					DiscGolfProviderMetaData.DATABASE_NAME, 
					null, 
					DiscGolfProviderMetaData.DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			Log.d(DiscGolfProviderApplication.TAG,"inner oncreate called");
			db.execSQL("CREATE TABLE " + RoundScoresTableMetaData.TABLE_NAME + " ("
					+ RoundScoresTableMetaData._ID + " INTEGER PRIMARY KEY,"
					+ RoundScoresTableMetaData.GOLFER_USERNAME + " TEXT,"
					+ RoundScoresTableMetaData.COURSE_NAME + " TEXT,"
					+ RoundScoresTableMetaData.HOLE_01 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_02 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_03 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_04 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_05 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_06 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_07 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_08 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_09 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_10 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_11 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_12 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_13 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_14 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_15 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_16 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_17 + " INTEGER,"
					+ RoundScoresTableMetaData.HOLE_18 + " INTEGER,"
					+ RoundScoresTableMetaData.ROUND_TOTAL + " INTEGER,"
					+ RoundScoresTableMetaData.ROUND_DATE + " INTEGER"
					+ ");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			Log.d(DiscGolfProviderApplication.TAG,"inner onupgrade called");
			Log.w(DiscGolfProviderApplication.TAG, "Upgrading database from version " 
					+ oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + RoundScoresTableMetaData.TABLE_NAME);
			onCreate(db);
		}
	}

	private DatabaseHelper mOpenHelper;

	@Override
	public boolean onCreate() 
	{
		Log.d(DiscGolfProviderApplication.TAG,"main onCreate called");
		mOpenHelper = new DatabaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, 
			String[] selectionArgs,  String sortOrder) 
	{
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		switch (sUriMatcher.match(uri)) {
		case INCOMING_URI_INDICATOR_ROUND_SCORES:
			qb.setTables(RoundScoresTableMetaData.TABLE_NAME);
			qb.setProjectionMap(sDiscGolfRoundScoresProjectionMap);
			break;

		case INCOMING_URI_INDICATOR_SINGLE_ROUND_SCORE:
			qb.setTables(RoundScoresTableMetaData.TABLE_NAME);
			qb.setProjectionMap(sDiscGolfRoundScoresProjectionMap);
			qb.appendWhere(RoundScoresTableMetaData._ID + "=" 
					+ uri.getPathSegments().get(1));
			break;

		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		// If no sort order is specified use the default
		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = RoundScoresTableMetaData.DEFAULT_SORT_ORDER;
		} else {
			orderBy = sortOrder;
		}

		// Get the database and run the query
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);

		// Tell the cursor what uri to watch, 
		// so it knows when its source data changes
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case INCOMING_URI_INDICATOR_ROUND_SCORES:
			return RoundScoresTableMetaData.CONTENT_TYPE;

		case INCOMING_URI_INDICATOR_SINGLE_ROUND_SCORE:
			return RoundScoresTableMetaData.CONTENT_ITEM_TYPE;

		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		// Validate the requested uri
		if (sUriMatcher.match(uri) != INCOMING_URI_INDICATOR_ROUND_SCORES) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		ContentValues values;
		if (initialValues != null) {
			values = new ContentValues(initialValues);
		} else {
			values = new ContentValues();
		}

		Long now = Long.valueOf(System.currentTimeMillis());

		// Make sure that the fields are all set
		if (values.containsKey(RoundScoresTableMetaData.GOLFER_USERNAME) == false) {
			throw new SQLException("Failed to insert row because Golfer Username is needed " + uri);
		}
		if (values.containsKey(RoundScoresTableMetaData.COURSE_NAME) == false) {
			throw new SQLException("Failed to insert row because Course Name is needed " + uri);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_01) == false) {
			values.put(RoundScoresTableMetaData.HOLE_01, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_02) == false) {
			values.put(RoundScoresTableMetaData.HOLE_02, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_03) == false) {
			values.put(RoundScoresTableMetaData.HOLE_03, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_04) == false) {
			values.put(RoundScoresTableMetaData.HOLE_04, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_05) == false) {
			values.put(RoundScoresTableMetaData.HOLE_05, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_06) == false) {
			values.put(RoundScoresTableMetaData.HOLE_06, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_07) == false) {
			values.put(RoundScoresTableMetaData.HOLE_07, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_08) == false) {
			values.put(RoundScoresTableMetaData.HOLE_08, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_09) == false) {
			values.put(RoundScoresTableMetaData.HOLE_09, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_10) == false) {
			values.put(RoundScoresTableMetaData.HOLE_10, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_11) == false) {
			values.put(RoundScoresTableMetaData.HOLE_11, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_12) == false) {
			values.put(RoundScoresTableMetaData.HOLE_12, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_13) == false) {
			values.put(RoundScoresTableMetaData.HOLE_13, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_14) == false) {
			values.put(RoundScoresTableMetaData.HOLE_14, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_15) == false) {
			values.put(RoundScoresTableMetaData.HOLE_15, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_16) == false) {
			values.put(RoundScoresTableMetaData.HOLE_16, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_17) == false) {
			values.put(RoundScoresTableMetaData.HOLE_17, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.HOLE_18) == false) {
			values.put(RoundScoresTableMetaData.HOLE_18, 0);
		}
		if (values.containsKey(RoundScoresTableMetaData.ROUND_DATE) == false) {
			values.put(RoundScoresTableMetaData.ROUND_DATE, now);
		}

		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long rowId = db.insert(RoundScoresTableMetaData.TABLE_NAME, null, values);
		if (rowId > 0) {
			Uri insertedBookUri = ContentUris.withAppendedId(RoundScoresTableMetaData.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(insertedBookUri, null);
			return insertedBookUri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count;
		switch (sUriMatcher.match(uri)) {
		case INCOMING_URI_INDICATOR_ROUND_SCORES:
			count = db.delete(RoundScoresTableMetaData.TABLE_NAME, where, whereArgs);
			break;
		case INCOMING_URI_INDICATOR_SINGLE_ROUND_SCORE:
			String rowId = uri.getPathSegments().get(1);
			count = db.delete(RoundScoresTableMetaData.TABLE_NAME, 
					RoundScoresTableMetaData._ID + "=" + rowId
					+ (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), 
					whereArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count;
		switch (sUriMatcher.match(uri)) {
		case INCOMING_URI_INDICATOR_ROUND_SCORES:
			count = db.update(RoundScoresTableMetaData.TABLE_NAME, 
					values, where, whereArgs);
			break;
		case INCOMING_URI_INDICATOR_SINGLE_ROUND_SCORE:
			String rowId = uri.getPathSegments().get(1);
			count = db.update(RoundScoresTableMetaData.TABLE_NAME, 
					values, RoundScoresTableMetaData._ID + "=" + rowId
					+ (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), 
					whereArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
}

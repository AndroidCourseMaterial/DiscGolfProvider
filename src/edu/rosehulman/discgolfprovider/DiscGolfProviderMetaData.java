package edu.rosehulman.discgolfprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class DiscGolfProviderMetaData 
{
	public static final String AUTHORITY = "edu.rosehulman.provider.DiscGolf";
	public static final String DATABASE_NAME = "discgolf.db";
	public static final int DATABASE_VERSION = 1;
	private DiscGolfProviderMetaData() {}

	//inner class describing columns and their types
	public static final class RoundScoresTableMetaData implements BaseColumns 
	{
		private RoundScoresTableMetaData() {}
		public static final String TABLE_NAME = "roundscores";

		//uri and mime type definitions
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/roundscores");
		public static final Uri CONTENT_ID_URI_BASE = Uri.parse("content://" + AUTHORITY + "/roundscores/");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.rosehulman.roundscore";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.rosehulman.roundscore";

		public static final String DEFAULT_SORT_ORDER = "rounddate DESC";

		//Additional Columns start here.
		public static final String GOLFER_USERNAME = "golferusername"; //string type
		public static final String COURSE_NAME = "coursename"; //string type
		public static final String HOLE_01 = "hole01"; //integer type
		public static final String HOLE_02 = "hole02"; //integer type
		public static final String HOLE_03 = "hole03"; //integer type
		public static final String HOLE_04 = "hole04"; //integer type
		public static final String HOLE_05 = "hole05"; //integer type
		public static final String HOLE_06 = "hole06"; //integer type
		public static final String HOLE_07 = "hole07"; //integer type
		public static final String HOLE_08 = "hole08"; //integer type
		public static final String HOLE_09 = "hole09"; //integer type
		public static final String HOLE_10 = "hole10"; //integer type
		public static final String HOLE_11 = "hole11"; //integer type
		public static final String HOLE_12 = "hole12"; //integer type
		public static final String HOLE_13 = "hole13"; //integer type
		public static final String HOLE_14 = "hole14"; //integer type
		public static final String HOLE_15 = "hole15"; //integer type
		public static final String HOLE_16 = "hole16"; //integer type
		public static final String HOLE_17 = "hole17"; //integer type
		public static final String HOLE_18 = "hole18"; //integer type
		public static final String ROUND_TOTAL = "roundtotal"; //integer type (used when no individual hole score data is available)
		public static final String ROUND_DATE = "rounddate"; //Integer from System.currentTimeMillis()
	}

	//inner class describing columns and their types
	public static final class CoursesTableMetaData implements BaseColumns 
	{
		private CoursesTableMetaData() {}
		public static final String TABLE_NAME = "courses";

		//uri and mime type definitions
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/courses");
		public static final Uri CONTENT_ID_URI_BASE = Uri.parse("content://" + AUTHORITY + "/courses/");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.rosehulman.course";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.rosehulman.course";

		public static final String DEFAULT_SORT_ORDER = "modified DESC";

		//Additional Columns start here.
		public static final String COURSE_NAME = "coursename"; //string type
		public static final String HOLE_PAR_01 = "hole01"; //integer type
		public static final String HOLE_PAR_02 = "hole02"; //integer type
		public static final String HOLE_PAR_03 = "hole03"; //integer type
		public static final String HOLE_PAR_04 = "hole04"; //integer type
		public static final String HOLE_PAR_05 = "hole05"; //integer type
		public static final String HOLE_PAR_06 = "hole06"; //integer type
		public static final String HOLE_PAR_07 = "hole07"; //integer type
		public static final String HOLE_PAR_08 = "hole08"; //integer type
		public static final String HOLE_PAR_09 = "hole09"; //integer type
		public static final String HOLE_PAR_10 = "hole10"; //integer type
		public static final String HOLE_PAR_11 = "hole11"; //integer type
		public static final String HOLE_PAR_12 = "hole12"; //integer type
		public static final String HOLE_PAR_13 = "hole13"; //integer type
		public static final String HOLE_PAR_14 = "hole14"; //integer type
		public static final String HOLE_PAR_15 = "hole15"; //integer type
		public static final String HOLE_PAR_16 = "hole16"; //integer type
		public static final String HOLE_PAR_17 = "hole17"; //integer type
		public static final String HOLE_PAR_18 = "hole18"; //integer type
		public static final String CREATED = "created"; //Integer from System.currentTimeMillis()
		public static final String MODIFIED = "modified"; //Integer from System.currentTimeMillis()
	}
	//inner class describing columns and their types
	public static final class GolfersTableMetaData implements BaseColumns 
	{
		private GolfersTableMetaData() {}
		public static final String TABLE_NAME = "golfers";

		//uri and mime type definitions
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/golfers");
		public static final Uri CONTENT_ID_URI_BASE = Uri.parse("content://" + AUTHORITY + "/golfers/");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.rosehulman.golfer";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.rosehulman.golfer";

		public static final String DEFAULT_SORT_ORDER = "namelast";

		public static final String GOLFER_USERNAME = "golferusername"; //string type
		public static final String NAME_FIRST = "namefirst"; //string type
		public static final String NAME_LAST = "namelast"; //string type
		public static final String NAME_DISPLAY = "namedisplay"; //string type
		public static final String EMAIL_ADDRESS = "emailaddress"; //string type
		public static final String COMMUNITY = "community"; //string type   TODO: This actually needs to be a string array
		public static final String CREATED = "created"; //Integer from System.currentTimeMillis()
		public static final String MODIFIED = "modified"; //Integer from System.currentTimeMillis()
	}
	//inner class describing columns and their types
	public static final class CommunitiesTableMetaData implements BaseColumns 
	{
		private CommunitiesTableMetaData() {}
		public static final String TABLE_NAME = "communities";

		//uri and mime type definitions
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/communities");
		public static final Uri CONTENT_ID_URI_BASE = Uri.parse("content://" + AUTHORITY + "/communities/");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.rosehulman.community";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.rosehulman.community";

		public static final String DEFAULT_SORT_ORDER = "name";

		public static final String NAME = "name"; //string type
		public static final String PASSWORD = "password"; //string type
		public static final String CREATED = "created"; //Integer from System.currentTimeMillis()
		public static final String MODIFIED = "modified"; //Integer from System.currentTimeMillis()
	}
}

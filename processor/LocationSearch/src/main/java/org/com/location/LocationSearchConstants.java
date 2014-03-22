package org.com.location;

/**
 * User: ravioactive
 * Date: 11/28/13
 * Time: 3:42 AM
 */


public class LocationSearchConstants {



    /*********************SOLR QUERY****************************
     * Query Params:
     * defType	        Selects the query parser to be used to process the query.
     * sort	            Sorts the response to a query in either ascending or descending order based on the response's score or another specified characteristic.
     * start	        Specifies an offset (by default, 0) into the responses at which Solr should begin displaying content.
     * rows	            Controls how many rows of responses are displayed at a time (default value: 10)
     * fq	            Applies a filter query to the search results.
     * fl	            With version 3.6, Solr limited the query's responses to a listed set of fields. With version 4.0, this parameter returns only the score.
     * wt	            Specifies the Response Writer to be used to format the query response.
     * debug	        Request additional debugging information in the response. Specifying the debug=timing parameter returns just the timing information; specifying the debug=results parameter returns "explain" information for each of the documents returned; specifying the debug=query parameter returns all of the debug information.
     * explainOther	    Allows clients to specify a Lucene query to identify a set of documents. If non-blank, the explain info of each document which matches this query, relative to the main query (specified by the q parameter) will be returned along with the rest of the debugging information.
     * timeAllowed	    Defines the time allowed for the query to be processed. If the time elapses before the query response is complete, partial information may be returned.
     * omitHeader	    Excludes the header from the returned results, if set to true. The header contains information about the request, such as the time the request took to complete. The default is false.
     * cache=false	 By default, Solr caches the results of all queries and filter queries. Set cache=false to disable caching of the results of a query.
     */
    public static final String _RAW_QUERY_STR = "q";
    public static final String _DEFAULT_FIELD = "df";
    public static final String _FIELD_LIST = "fl";
    public static final String _SORT = "sort";
    public static final String _RESULT_COUNT = "rows";
    public static final String _RESULT_COUNT_VALUE = "5";
    public static final int _RESULT_COUNT_VALUE_INT = 5;
    public static final String _RESULT_START = "start";
    public static final String _BOOST = "qf";
    public static final String _RESPONSE_TYPE = "wt";

    public static final String _QUERY_ALL = "*.*";

    /*********************CONTINENT****************************
     * <field name="id" type="long" indexed="true" required="true" stored="true"/>
     * <field name="cntcode" type="text" indexed="true" required="true" stored="true"/>
     * <field name="cntname" type="text" indexed="true" required="true" stored="true"/>
     */
    public static final String CONTINENT_CORE_NAME = "continent";
    public static final String CONTINENT_ID_FIELD = "id";
    public static final String CONTINENT_CODE_FIELD = "cntcode";
    public static final String CONTINENT_NAME_FIELD = "cntname";

    public static final String CONTINENT_DEFAULT_FIELD = CONTINENT_CODE_FIELD;
    public static final String CONTINENT_QUERY_FIELD_LIST = "cntcode";
    public static final String CONTINENT_BOOST = "";

    /*********************COUNTRY****************************
     * <field name="id" type="long" indexed="true" stored="true" required="true" multiValued="false"/>
     * <field name="cntryname" type="string" indexed="true" stored="true" required="true"/>
     * <field name="cntrycode" type="string" indexed="true" stored="true" required="true"/>
     * <field name="cntrycapital" type="string" indexed="true" stored="true" required="true"/>
     * <field name="cntrycontinent" type="string" indexed="true" stored="true" required="true"/>
     * <field name="cntrycurrency" type="string" indexed="true" stored="true" required="false" multiValued="true"/>
     * <field name="cntrycurrcode" type="string" indexed="true" stored="true" required="false" multiValued="true"/>
     * <field name="cntryneighbors" type="string" indexed="true" stored="true" required="false" multiValued="true"/>
     * <field name="cntryarea" type="double" indexed="true" stored="true" required="false"/>
     * <field name="cntrypopulation" type="long" indexed="true" stored="true" required="false"/>
     * <field name="cntrytld" type="string" indexed="true" stored="true" required="false"/>
     * <field name="lang" type="string" indexed="true" stored="true" required="false" multiValued="true"/>
     * <field name="zipregex" type="string" indexed="true" stored="true" required="false"/>
     */
    public static final String COUNTRY_CORE_NAME = "country";
    public static final String COUNTRY_ID_FIELD = "id";
    public static final String COUNTRY_NAME_FIELD = "cntryname";
    public static final String COUNTRY_CODE_FIELD = "cntrycode";
    public static final String COUNTRY_CAPITAL_FIELD = "cntrycapital";
    public static final String COUNTRY_CONTINENT_FIELD = "cntrycontinent";
    public static final String COUNTRY_CURRENCY_FIELD = "cntrycurrency";
    public static final String COUNTRY_CURRENCY_CODE_FIELD = "cntrycurrcode";
    public static final String COUNTRY_NEIGHBORS_FIELD = "cntryneighbors";
    public static final String COUNTRY_AREA_FIELD = "cntryarea";
    public static final String COUNTRY_POPULATION_FIELD = "cntrypopulation";
    public static final String COUNTRY_LANGUAGE_FIELD = "cntrytld";
    public static final String COUNTRY_ZIP_REGEX_FIELD = "lang";

    public static final String COUNTRY_DEFAULT_FIELD = COUNTRY_CODE_FIELD;
    public static final String COUNTRY_QUERY_FIELD_LIST = COUNTRY_CODE_FIELD + "," + COUNTRY_NAME_FIELD;
    public static final String COUNTRY_BOOST = "";

    /*********************STATE****************************
     * <field name="id" type="long" indexed="true" stored="true" required="true" multiValued="false"/>
     * <field name="statenameascii" type="text" indexed="true" stored="true" required="false"/>
     * <field name="statename" type="string" indexed="true" stored="true" required="true"/>
     * <field name="statecode" type="string" indexed="true" stored="true" required="true"/>
     * <field name="statecountry" type="string" indexed="true" stored="true" required="true"/>
     */
    public static final String STATE_CORE_NAME = "state";
    public static final String STATE_ID_FIELD = "id";
    public static final String STATE_CODE_FIELD = "statecode";
    public static final String STATE_NAME_FIELD = "statenameascii";
    public static final String STATE_NAME_UNICODE_FIELD = "statename";
    public static final String STATE_COUNTRY_FIELD = "statecountry";
    public static final String STATE_CODE_COMBINED_FIELD = "statecodecombined";

    public static final String STATE_DEFAULT_FIELD = STATE_CODE_FIELD;
    public static final String STATE_FIELD_LIST = STATE_CODE_FIELD + "," + STATE_NAME_FIELD;
    public static final String STATE_BOOST = "";

    /*********************DISTRICT****************************
     * <field name="id" type="long" indexed="true" stored="true" required="true" multiValued="false"/>
     * <field name="countynameascii" type="text" indexed="true" stored="true" required="false"/>
     * <field name="countyname" type="string" indexed="true" stored="true" required="true"/>
     * <field name="countycode" type="string" indexed="true" stored="true" required="true"/>
     * <field name="countystate" type="string" indexed="true" stored="true" required="true"/>
     * <field name="countycountry" type="string" indexed="true" stored="true" required="true"/>
     */
    public static final String DISTRICT_CORE_NAME = "county";
    public static final String DISTRICT_ID_FIELD = "id";
    public static final String DISTRICT_CODE_FIELD = "countycode";
    public static final String DISTRICT_NAME_FIELD = "countynameascii";
    public static final String DISTRICT_NAME_UNICODE_FIELD = "countyname";
    public static final String DISTRICT_STATE_FIELD = "countystate";
    public static final String DISTRICT_COUNTRY_FIELD = "countycountry";
    public static final String DISTRICT_CODE_COMBINED_FIELD = "countycodecombined";

    public static final String DISTRICT_DEFAULT_FIELD = DISTRICT_CODE_FIELD;
    public static final String DISTRICT_QUERY_FIELD_LIST = DISTRICT_CODE_FIELD + "," + DISTRICT_NAME_FIELD;
    public static final String DISTRICT_BOOST = "";

    /*********************PLACES****************************
     * <field name="id" type="long" indexed="true" stored="true" required="true" multiValued="false"/>

     * <field name="name" type="string" indexed="true" stored="true" required="true"/>
     * <field name="nameascii" type="text" indexed="true" stored="true" required="true"/>
     * <field name="namealternate" type="text_cf" indexed="true" stored="true" required="true" multiValued="true"/>

     * <field name="location" type="location_rpt" indexed="true" stored="true" required="true" multiValued="false"/>

     * <field name="featureclass" type="string" indexed="true" stored="true" required="true" multiValued="false"/>
     * <field name="featurecode" type="string" indexed="true" stored="true" required="true" multiValued="false"/>

     * <field name="countrycode" type="string" indexed="true" stored="true" required="true" multiValued="false"/>

     * <field name="admin1code" type="string" indexed="true" stored="true" required="false" multiValued="false"/>
     * <field name="admin2code" type="string" indexed="true" stored="true" required="false" multiValued="false"/>
     * <field name="admin3code" type="string" indexed="true" stored="true" required="false" multiValued="false"/>
     * <field name="admin4code" type="string" indexed="true" stored="true" required="false" multiValued="false"/>

     * <field name="population" type="long" indexed="true" stored="true" required="false" multiValued="false"/>

     * <field name="elevation" type="int" indexed="true" stored="true" required="false" multiValued="false"/>

     * <field name="timezone" type="text" indexed="true" stored="true" required="false" multiValued="false"/>
     */
    public static final String PLACES_CORE_NAME = "place";
    public static final String PLACE_ID_FIELD = "id";
    public static final String PLACE_NAME_FIELD = "nameascii";
    public static final String PLACE_NAME_UNICODE_FIELD = "name";
    public static final String PLACE_NAME_ALTERNATE_FIELD = "namealternate";
    public static final String PLACE_LOCATION_FIELD = "location";
    public static final String PLACE_FEATURE_CLASS_FIELD = "featureclass";
    public static final String PLACE_FEATURE_CODE_FIELD = "featurecode";
    public static final String PLACE_COUNTRY_CODE_FIELD = "countrycode";
    public static final String PLACE_ADMIN_1_CODE_FIELD = "admin1code";
    public static final String PLACE_ADMIN_2_CODE_FIELD = "admin2code";
    public static final String PLACE_ADMIN_3_CODE_FIELD = "admin3code";
    public static final String PLACE_ADMIN_4_CODE_FIELD = "admin4code";
    public static final String PLACE_POPULATION_FIELD = "population";
    public static final String PLACE_ELEVATION_FIELD = "elevation";
    public static final String PLACE_TIMEZONE_FIELD = "timezone";

    public static final String PLACE_ALLNAMES_FIELD = "allnames";

    public static final String PLACE_DEFAULT_FIELD = PLACE_NAME_FIELD;
    public static final String PLACE_QUERY_FIELD_LIST = PLACE_NAME_FIELD + "," + PLACE_NAME_ALTERNATE_FIELD;
    public static final String PLACE_BOOST = "";

    /*********************LANGUAGE****************************
     * <field name="langcode3" type="string" indexed="true" required="true" stored="true"/>
     * <field name="langcode2" type="string" indexed="true" required="true" stored="true"/>
     * <field name="langcode1" type="string" indexed="true" required="true" stored="true"/>
     * <field name="langname" type="text" indexed="true" required="true" stored="true"/>
     */
    public static final String LANGUAGE_CORE_NAME = "language";
    public static final String LANG_CODE_3_FIELD = "langcode3";
    public static final String LANG_CODE_2_FIELD = "langcode2";
    public static final String LANG_CODE_1_FIELD = "langcode1";
    public static final String LANG_NAME_FIELD = "langname";

    public static final String LANG_DEFAULT_FIELD = LANG_CODE_3_FIELD;
    public static final String LANG_QUERY_FIELD_LIST = LANG_CODE_3_FIELD + "," + LANG_NAME_FIELD;
    public static final String LANG_BOOST = "";

    /*********************TIMEZONE****************************
     * <field name="countrycode" type="text" indexed="true" stored="true" required="true" multiValued="false"/>
     * <field name="timezone" type="text" indexed="true" stored="true" required="true" multiValued="false"/>
     * <field name="gmtoffset" type="float" indexed="true" stored="true" required="false" multiValued="false"/>
     * <field name="dstoffset" type="float" indexed="true" stored="true" required="false" multiValued="false"/>
     * <field name="rawoffset" type="float" indexed="true" stored="true" required="false" multiValued="false"/>
     */
    public static final String TIMEZONE_CORE_NAME = "timezone";
    public static final String TIMEZONE_COUNTRY_FIELD = "countrycode";
    public static final String TIMEZONE_NAME_FIELD = "timezone";
    public static final String TIMEZONE_GMT_OFFSET_FIELD = "gmtoffset";
    public static final String TIMEZONE_DST_OFFSET_FIELD = "dstoffset";
    public static final String TIMEZONE_RAW_OFFSET_FIELD = "rawoffset";

    public static final String TIMEZONE_DEFAULT_FIELD = TIMEZONE_NAME_FIELD;
    public static final String TIMEZONE_QUERY_FIELD_LIST = TIMEZONE_NAME_FIELD + "," + TIMEZONE_GMT_OFFSET_FIELD + "," + TIMEZONE_DST_OFFSET_FIELD;
    public static final String TIMEZONE_BOOST = "";

    /*********************FEATURE****************************
     * <field name="id" type="string" indexed="true" stored="true" required="true" multiValued="false"/>
     * <field name="featuretype" type="string" indexed="true" stored="true" required="true" multiValued="false"/>
     * <field name="featurecode" type="string" indexed="true" stored="true" required="true" multiValued="false"/>
     * <field name="featurename" type="text" indexed="true" stored="true" required="true" multiValued="false"/>
     * <field name="featuredesc" type="text" indexed="true" stored="true" required="false" multiValued="false"/>
     */
    public static final String FEATURE_CORE_NAME = "feature";
    public static final String FEATURE_ID_FIELD = "id";
    public static final String FEATURE_TYPE_CODE_FIELD = "featuretype";
    public static final String FEATURE_CODE_FIELD = "featurecode";
    public static final String FEATURE_NAME_FIELD = "featurename";
    public static final String FEATURE_DESC_FIELD = "featuredesc";
    public static final String FEATURE_ALL_FIELD = "featureall";

    public static final String FEATURE_DEFAULT_FIELD = FEATURE_CODE_FIELD;
    public static final String FEATURE_LOC_FIELD_LIST = FEATURE_CODE_FIELD + "," + FEATURE_TYPE_CODE_FIELD + "," + FEATURE_NAME_FIELD;
    public static final String FEATURE_BOOST = "";

    /*********************WIKINEWS****************************
     * <field name="id"        type="string"  indexed="true" stored="true" required="true"/>
     * <field name="title"     type="string"  indexed="true" stored="false"/>
     * <field name="revision"  type="int"    indexed="true" stored="true"/>
     * <field name="user"      type="string"  indexed="true" stored="true"/>
     * <field name="userId"    type="int"     indexed="true" stored="true"/>
     * <field name="text"      type="text_en"    indexed="true" stored="false"/>
     * <field name="timestamp" type="date"    indexed="true" stored="true"/>
     * <field name="titleText" type="text_en"    indexed="true" stored="true"/>

     * <field name="text_f"    type="text_en"    indexed="true" stored="true" multiValued="true"/>
     */
    public static final String WIKINEWS_CORE_NAME = "wiki";
    public static final String WIKINEWS_ID_FIELD = "id";
    public static final String WIKINEWS_TITLE_FIELD = "title";
    public static final String WIKINEWS_REVISION_FIELD = "revision";
    public static final String WIKINEWS_USER_FIELD = "user";
    public static final String WIKINEWS_USERID_FIELD = "userId";
    public static final String WIKINEWS_TEXT_FIELD = "text";
    public static final String WIKINEWS_TIMESTAMP_FIELD = "timestamp";
    public static final String WIKINEWS_TITLETEXT_FIELD = "titleText";
    public static final String WIKINEWS_ALLTEXT_FIELD = "text_f";


    public static final String LOC_SORT = "asc";
    public static final String LOC_RESULT_COUNT = "100";
    public static final String LOC_RESPONSE_TYPE = "json";
    //public static final String LOC_BOOST = "qf";

    public static final String WIKI_DEFAULT_FIELD = "title";
    public static final String WIKI_FIELD_LIST = "title, content";
    public static final String WIKI_SORT = "sort asc";
    public static final String WIKI_RESULT_COUNT = "25";
    public static final String WIKI_BOOST = "title^0.8 content^0.2";
    public static final String WIKI_RESPONSE_TYPE = "json";

    public static final String USER_QUERY_ORIGINAL_QUERY_STR = "ORIGINAL";
    public static final String USER_QUERY_POS_RESULT_COMMONS = "POS_COMMONS";
    public static final String USER_QUERY_POS_RESULT_PROPERS = "POS_PROPERS";
    public static final String USER_QUERY_POS_RESULT_PREPS = "POS_PREPS";
    public static final String USER_QUERY_POS_RESULT_OTHERS = "POS_OTHERS";
    public static final String USER_QUERY_RAW_QUERY_STRING_KEY = "RAW";
    public static final String USER_QUERY_IS_FUNCTIONAL_KEY = "FUNC";
    public static final String USER_QUERY_IS_ADVANCED_KEY = "ADV";
    public static final String USER_QUERY_IS_HEIRARCHIAL_KEY = "HEIRARCHY";
    public static final String USER_QUERY_KEYWORDS_KEY = "KEYWORDS";
    public static final String USER_QUERY_LOCATION_KEY = "LOCATION";
    public static final String USER_QUERY_DISTANCE_KEY = "DISTANCE";
    public static final String USER_QUERY_TIME_SORT_KEY = "TIMESORT";
    public static final String USER_QUERY_TIME_SORT_VALUE_NEWEST_FIRST = "-1";
    public static final String USER_QUERY_TIME_SORT_VALUE_OLDEST_FIRST = "1";
    public static final String USER_QUERY_TIME_SORT_VALUE_NEUTRAL = "0";

    public static final String USER_QUERY_DISTANCE_SORT_KEY = "DISTANCESORT";
    public static final String USER_QUERY_DISTANCE_SORT_VALUE_ASC = "1";
    public static final String USER_QUERY_DISTANCE_SORT_VALUE_DESC = "-1";
    public static final String USER_QUERY_DISTANCE_SORT_VALUE_NEUTRAL = "0";

    public static final String LOCATION_QUERY_COORDINATES_KEY = "pt";
    public static final String LOCATION_QUERY_SPATIAL_FIELD_KEY = "sField";
    public static final String LOCATION_QUERY_SPATIAL_FIELD = PLACE_LOCATION_FIELD;
    public static final String LOCATION_QUERY_DISTANCE_KEY = "d";

    public static final int FEEDBACK_TYPE_IS_CONTINENT = 1;
    public static final int FEEDBACK_TYPE_IS_COUNTRY = 2;
    public static final int FEEDBACK_TYPE_IS_STATE = 3;
    public static final int FEEDBACK_TYPE_IS_DISTRICT = 4;
    public static final int FEEDBACK_TYPE_BEGIN_SPATIAL = 7;
    public static final int FEEDBACK_TYPE_INC_SPATIAL = 6;

    public static final int SPATIAL_SEARCH_DEFAULT_D = 100;

    public static final int SPATIAL_SEARCH_BEGIN_D = 100;
    public static final int SPATIAL_SEARCH_INCREMEMENT_D = 50;
    public static final int SPATIAL_SEARCH_LIMIT_D = 500;

    public static final int GLOBAL_TOP_K_LESS = 25;
    public static final int GLOBAL_TOP_K_MODERATE = 50;
    public static final int GLOBAL_TOP_K_HIGH = 100;

    public static final int HIERARCHY_CONTINENT_VALUE = 5;
    public static final int HIERARCHY_COUNTRY_VALUE = 4;
    public static final int HIERARCHY_STATE_VALUE = 3;
    public static final int HIERARCHY_DISTRICT_VALUE = 2;
    public static final int HIERARCHY_PLACE_VALUE = 1;

    public static final String SOLR_BASE_URL = "http://localhost:8983/solr/";
    public static final int DEFAULT_WIKINEWS_SUMMARY_LENGTH = 100;
    public static String USER_QUERY_CURRENT_PAGE_NUMBER_KEY = "CURR_PAGE";
}

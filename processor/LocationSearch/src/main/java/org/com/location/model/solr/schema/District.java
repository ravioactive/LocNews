package org.com.location.model.solr.schema;

import org.apache.solr.client.solrj.beans.Field;
import org.com.location.LocationSearchConstants;
import org.com.location.model.solr.SolrLocationType;

/**
 * Created with IntelliJ IDEA.
 * User: ravioactive
 * Date: 11/28/13
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * <field name="id" type="long" indexed="true" stored="true" required="true" multiValued="false"/>
 * <field name="countynameascii" type="text" indexed="true" stored="true" required="false"/>
 * <field name="countyname" type="string" indexed="true" stored="true" required="true"/>
 * <field name="countycode" type="string" indexed="true" stored="true" required="true"/>
 * <field name="countystate" type="string" indexed="true" stored="true" required="true"/>
 * <field name="countycountry" type="string" indexed="true" stored="true" required="true"/>
 */

public class District  implements SolrLocationType {

    
    long id;
    
    String countynameascii;
    
    String countyname;
    
    String countycode;
    
    String countystate;
    
    String countycountry;

    public String getCountycodecombined() {
        return countycodecombined;
    }

    @Field("countycodecombined")
    public void setCountycodecombined(String countycodecombined) {
        this.countycodecombined = countycodecombined;
    }

    String countycodecombined;

    public long getId() {
        return id;
    }
    @Field("id")
    public void setId(long id) {
        this.id = id;
    }

    public String getCountynameascii() {
        return countynameascii;
    }
     @Field("countynameascii")
    public void setCountynameascii(String countynameascii) {
        this.countynameascii = countynameascii;
    }

    public String getCountyname() {
        return countyname;
    }
@Field("countyname")
    public void setCountyname(String countyname) {
        this.countyname = countyname;
    }

    public String getCountycode() {
        return countycode;
    }
@Field("countycode")
    public void setCountycode(String countycode) {
        this.countycode = countycode;
    }

    public String getCountystate() {
        return countystate;
    }
@Field("countystate")
    public void setCountystate(String countystate) {
        this.countystate = countystate;
    }

    public String getCountycountry() {
        return countycountry;
    }
@Field("countycountry")
    public void setCountycountry(String countycountry) {
        this.countycountry = countycountry;
    }

    @Override
    public int getLocationType() {
        return districtHierarchyLevel;
    }

    private int districtHierarchyLevel = LocationSearchConstants.HIERARCHY_DISTRICT_VALUE;

    @Override
    public String getCode() {
        return getCountycodecombined();
    }
}

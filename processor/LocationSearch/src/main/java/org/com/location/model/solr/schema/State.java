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
 * <field name="statenameascii" type="text" indexed="true" stored="true" required="false"/>
 * <field name="statename" type="string" indexed="true" stored="true" required="true"/>
 * <field name="statecode" type="string" indexed="true" stored="true" required="true"/>
 * <field name="statecountry" type="string" indexed="true" stored="true" required="true"/>
 */

 public class State implements SolrLocationType {
    
    long id;
   
    String statenameascii;
    
    String statename;
    
    String statecode;
    
    String statecountry;

    public String getStatecodecombined() {
        return statecodecombined;
    }

    @Field("statecodecombined")
    public void setStatecodecombined(String statecodecombined) {
        this.statecodecombined = statecodecombined;
    }

    String statecodecombined;

    public long getId() {
        return id;
    }
@Field("id")
    public void setId(long id) {
        this.id = id;
    }

    public String getStatenameascii() {
        return statenameascii;
    }
@Field("statenameascii")
    public void setStatenameascii(String statenameascii) {
        this.statenameascii = statenameascii;
    }

    public String getStatename() {
        return statename;
    }
@Field("statename")
    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getStatecode() {
        return statecode;
    }
@Field("statecode")
    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public String getStatecountry() {
        return statecountry;
    }
@Field("statecountry")
    public void setStatecountry(String statecountry) {
        this.statecountry = statecountry;
    }

    @Override
    public int getLocationType() {
        return stateHierarchylevel;
    }

    private int stateHierarchylevel = LocationSearchConstants.HIERARCHY_STATE_VALUE;

    public String getCode() {
        return getStatecodecombined();
    }
}

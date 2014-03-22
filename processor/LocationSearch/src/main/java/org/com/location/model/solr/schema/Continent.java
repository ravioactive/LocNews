package org.com.location.model.solr.schema;

import org.apache.solr.client.solrj.beans.Field;
import org.com.location.LocationSearchConstants;
import org.com.location.model.solr.SolrLocationType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: ravioactive
 * Date: 11/28/13
 * Time: 3:40 PM
 */

/**
 * <field name="id" type="long" indexed="true" required="true" stored="true"/>
 * <field name="cntcode" type="text" indexed="true" required="true" stored="true"/>
 * <field name="cntname" type="text" indexed="true" required="true" stored="true"/>
 */

public class Continent implements SolrLocationType {

    
    long id;
   
    String cntcode;
    
    String cntname;

    public long getId() {
        return id;
    }
    @Field("id")
    public void setId(long id) {
        this.id = id;
    }

    public String getCntcode() {
        return cntcode;
    }
    @Field("cntcode")
    public void setCntcode(String cntcode) {
        this.cntcode = cntcode;
    }

    public String getCntname() {
        return cntname;
    }
    @Field("cntname")
    public void setCntname(String cntname) {
        this.cntname = cntname;
    }

    @Override
    public int getLocationType() {
        return continentHierarchyLevel;
    }

    private int continentHierarchyLevel = LocationSearchConstants.HIERARCHY_CONTINENT_VALUE;
    public String getCode() {
        return getCntcode();
    }
}

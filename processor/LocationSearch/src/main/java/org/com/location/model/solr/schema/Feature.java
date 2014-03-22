package org.com.location.model.solr.schema;

import org.apache.solr.client.solrj.beans.Field;
import org.com.location.model.solr.SolrLocationType;
import org.com.location.model.solr.SolrSchemaType;

/**
 * User: ravioactive
 * Date: 11/28/13
 * Time: 3:41 PM
 */

/**
 * <field name="id" type="string" indexed="true" stored="true" required="true" multiValued="false"/>
 * <field name="featuretype" type="string" indexed="true" stored="true" required="true" multiValued="false"/>
 * <field name="featurecode" type="string" indexed="true" stored="true" required="true" multiValued="false"/>
 * <field name="featurename" type="text" indexed="true" stored="true" required="true" multiValued="false"/>
 * <field name="featuredesc" type="text" indexed="true" stored="true" required="false" multiValued="false"/>
 */
public class Feature implements SolrSchemaType {
    
    private String id;
    
    private String featuretype;
    
    private String featurecode;
    
    private String featurename;
    
    private String featuredesc;

    public String getId() {
        return id;
    }
    @Field("id")
    public void setId(String id) {
        this.id = id;
    }

    public String getFeaturetype() {
        return featuretype;
    }
@Field("featuretype")
    public void setFeaturetype(String featuretype) {
        this.featuretype = featuretype;
    }

    public String getFeaturecode() {
        return featurecode;
    }
@Field("featurecode")
    public void setFeaturecode(String featurecode) {
        this.featurecode = featurecode;
    }

    public String getFeaturename() {
        return featurename;
    }
@Field("featurename")
    public void setFeaturename(String featurename) {
        this.featurename = featurename;
    }

    public String getFeaturedesc() {
        return featuredesc;
    }
@Field("featuredesc")
    public void setFeaturedesc(String featuredesc) {
        this.featuredesc = featuredesc;
    }
}

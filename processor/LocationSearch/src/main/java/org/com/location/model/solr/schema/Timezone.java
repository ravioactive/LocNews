package org.com.location.model.solr.schema; 
 
import org.apache.solr.client.solrj.beans.Field; 
import org.com.location.model.solr.SolrSchemaType; 
 
/** 
 * Created with IntelliJ IDEA. 
 * User: ravioactive 
 * Date: 11/28/13 
 * Time: 3:41 PM 
 * To change this template use File | Settings | File Templates. 
 */ 
 
/** 
 * <field name="countrycode" type="text" indexed="true" stored="true" required="true" multiValued="false"/> 
 * <field name="timezone" type="text" indexed="true" stored="true" required="true" multiValued="false"/> 
 * <field name="gmtoffset" type="float" indexed="true" stored="true" required="false" multiValued="false"/> 
 * <field name="dstoffset" type="float" indexed="true" stored="true" required="false" multiValued="false"/> 
 * <field name="rawoffset" type="float" indexed="true" stored="true" required="false" multiValued="false"/> 
 */ 
public class Timezone implements SolrSchemaType { 
     
    String countrycode; 
     
    String timezone; 
     
    float gmtoffset; 
     
    float dstoffset; 
     
    float rawoffset; 
 
    public String getCountrycode() { 
        return countrycode; 
    } 
    @Field("countrycode") 
    public void setCountrycode(String countrycode) { 
        this.countrycode = countrycode; 
    } 
 
    public String getTimezone() { 
        return timezone; 
    } 
    @Field("timezone") 
    public void setTimezone(String timezone) { 
        this.timezone = timezone; 
    } 
 
    public float getGmtoffset() { 
        return gmtoffset; 
    } 
    @Field("gmtoffset") 
    public void setGmtoffset(float gmtoffset) { 
        this.gmtoffset = gmtoffset; 
    } 
 
    public float getDstoffset() { 
        return dstoffset; 
    } 
    @Field("dstoffset") 
    public void setDstoffset(float dstoffset) { 
        this.dstoffset = dstoffset; 
    } 
 
    public float getRawoffset() { 
        return rawoffset; 
    } 
    @Field("rawoffset") 
    public void setRawoffset(float rawoffset) { 
        this.rawoffset = rawoffset; 
    } 
}
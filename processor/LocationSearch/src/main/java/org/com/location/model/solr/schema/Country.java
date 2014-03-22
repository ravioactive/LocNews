package org.com.location.model.solr.schema;

import org.apache.solr.client.solrj.beans.Field;
import org.com.location.LocationSearchConstants;
import org.com.location.model.solr.SolrLocationType;

import java.util.List;

/**
 * User: ravioactive
 * Date: 11/28/13
 * Time: 3:40 PM
 */

/**
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

public class Country implements SolrLocationType {

    private long id;
    
    private String cntryname;
    
    private String cntrycode;
    
    private String cntrycapital;
   
    private String cntrycontinent;
    
    private List<String> cntrycurrency;
    
    private List<String> cntrycurrcode;
    
    private List<String> cntryneighbors;
   
    private double cntryarea;
   
    private long cntrypopulation;
   
    private String cntrytld;
    
    private List<String> lang;
   
    private String zipregex;

    public long getId() {
        return id;
    }
    @Field("id")
    public void setId(long id) {
        this.id = id;
    }

    public String getCntryname() {
        return cntryname;
    }
    @Field("cntryname")
    public void setCntryname(String cntryname) {
        this.cntryname = cntryname;
    }

    public String getCntrycode() {
        return cntrycode;
    }
    @Field("cntrycode")
    public void setCntrycode(String cntrycode) {
        this.cntrycode = cntrycode;
    }

    public String getCntrycapital() {
        return cntrycapital;
    }
    @Field("cntrycapital")
    public void setCntrycapital(String cntrycapital) {
        this.cntrycapital = cntrycapital;
    }

    public String getCntrycontinent() {
        return cntrycontinent;
    }
    @Field("cntrycontinent")
    public void setCntrycontinent(String cntrycontinent) {
        this.cntrycontinent = cntrycontinent;
    }

    public List<String> getCntrycurrency() {
        return cntrycurrency;
    }
    @Field("cntrycurrency")
    public void setCntrycurrency(List<String> cntrycurrency) {
        this.cntrycurrency = cntrycurrency;
    }

    public List<String> getCntrycurrcode() {
        return cntrycurrcode;
    }
    @Field("cntrycurrcode")
    public void setCntrycurrcode(List<String> cntrycurrcode) {
        this.cntrycurrcode = cntrycurrcode;
    }

    public List<String> getCntryneighbors() {
        return cntryneighbors;
    }
    @Field("cntryneighbors")
    public void setCntryneighbors(List<String> cntryneighbors) {
        this.cntryneighbors = cntryneighbors;
    }

    public double getCntryarea() {
        return cntryarea;
    }
    @Field("cntryarea")
    public void setCntryarea(double cntryarea) {
        this.cntryarea = cntryarea;
    }

    public long getCntrypopulation() {
        return cntrypopulation;
    }
    @Field("cntrypopulation")
    public void setCntrypopulation(long cntrypopulation) {
        this.cntrypopulation = cntrypopulation;
    }

    public String getCntrytld() {
        return cntrytld;
    }
    @Field("cntrytld")
    public void setCntrytld(String cntrytld) {
        this.cntrytld = cntrytld;
    }

    public List<String> getLang() {
        return lang;
    }
    @Field("lang")
    public void setLang(List<String> lang) {
        this.lang = lang;
    }

    public String getZipregex() {
        return zipregex;
    }
    @Field("zipregex")
    public void setZipregex(String zipregex) {
        this.zipregex = zipregex;
    }

    @Override
    public String toString() {
        return super.toString();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int getLocationType() {
        return countryHierarchyLevel;
    }

    private int countryHierarchyLevel = LocationSearchConstants.HIERARCHY_COUNTRY_VALUE;

    public String getCode() {
        return getCntrycode();
    }
}

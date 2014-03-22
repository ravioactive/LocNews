package org.com.location.model.solr.schema;

import org.apache.solr.client.solrj.beans.Field;
import org.com.location.LocationSearchConstants;
import org.com.location.model.solr.SolrLocationType;

import java.util.List;

/**
 * User: ravioactive
 * Date: 11/28/13
 * Time: 3:41 PM
 */

/**
 * <field name="id" type="long" indexed="true" stored="true" required="true" multiValued="false"/>

 * <field name="name" type="string" indexed="true" stored="true" required="true"/>
 * <field name="nameascii" type="text" indexed="true" stored="true" required="true"/>
 * <field name="namealternate" type="text_cf" indexed="true" stored="true" required="true" multiValued="true"/>
 * <field name="allnames" type="text" indexed="true" stored="true" required="true" multiValued="true"/>

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
public class Place implements SolrLocationType {

    @Override
    public int getLocationType() {
        return placeHeirarchyLevel;
    }


    private int placeHeirarchyLevel = LocationSearchConstants.HIERARCHY_PLACE_VALUE;

   
    private long id;
    
    private String name;
    
    private String nameascii;
    
    private List<String> namealternate;
   
    private String location;
    
    private String featureclass;
    
    private String featurecode;
    
    private String countrycode;
    
    private String admin1code;
    
    private String admin2code;
    
    private String admin3code;
    
    private String admin4code;
    
    private long population;
    
    private int elevation;
    
    private String timezone;

    public String getAdmin2code() {
        return admin2code;
    }
@Field("admin2code")
    public void setAdmin2code(String admin2code) {
        this.admin2code = admin2code;
    }

    public String getAdmin4code() {
        return admin4code;
    }
@Field("admin4code")
    public void setAdmin4code(String admin4code) {
        this.admin4code = admin4code;
    }

    public List<String> getNamealternate() {
        return namealternate;
    }
@Field("namealternate")
    public void setNamealternate(List<String> namealternate) {
        this.namealternate = namealternate;
    }

    public String getNameascii() {
        return nameascii;
    }
@Field("nameascii")
    public void setNameascii(String nameascii) {
        this.nameascii = nameascii;
    }

    public String getCountrycode() {
        return countrycode;
    }
@Field("countrycode")
    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getLocation() {
        return location;
    }
@Field("location")
    public void setLocation(String location) {
        this.location = location;
    }

    public String getFeatureclass() {
        return featureclass;
    }
@Field("featureclass")
    public void setFeatureclass(String featureclass) {
        this.featureclass = featureclass;
    }

    public int getElevation() {
        return elevation;
    }
@Field("elevation")
    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public long getId() {
        return id;
    }
@Field("id")
    public void setId(long id) {
        this.id = id;
    }

    public String getTimezone() {
        return timezone;
    }
@Field("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getAdmin1code() {
        return admin1code;
    }
@Field("admin1code")
    public void setAdmin1code(String admin1code) {
        this.admin1code = admin1code;
    }

    public String getName() {
        return name;
    }
@Field("name")
    public void setName(String name) {
        this.name = name;
    }

    public String getAdmin3code() {
        return admin3code;
    }
@Field("admin3code")
    public void setAdmin3code(String admin3code) {
        this.admin3code = admin3code;
    }

    public String getFeaturecode() {
        return featurecode;
    }
@Field("featurecode")
    public void setFeaturecode(String featurecode) {
        this.featurecode = featurecode;
    }

    public long getPopulation() {
        return population;
    }
@Field("population")
    public void setPopulation(long population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeHeirarchyLevel=" + placeHeirarchyLevel +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", nameascii='" + nameascii + '\'' +
                ", namealternate=" + namealternate +
                ", location='" + location + '\'' +
                ", featureclass='" + featureclass + '\'' +
                ", featurecode='" + featurecode + '\'' +
                ", countrycode='" + countrycode + '\'' +
                ", admin1code='" + admin1code + '\'' +
                ", admin2code='" + admin2code + '\'' +
                ", admin3code='" + admin3code + '\'' +
                ", admin4code='" + admin4code + '\'' +
                ", population=" + population +
                ", elevation=" + elevation +
                ", timezone='" + timezone + '\'' +
                '}';
    }

    @Override
    public String getCode() {
        return String.valueOf(id);
    }
}

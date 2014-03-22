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
 * <field name="langcode3" type="string" indexed="true" required="true" stored="true"/>
 * <field name="langcode2" type="string" indexed="true" required="true" stored="true"/>
 * <field name="langcode1" type="string" indexed="true" required="true" stored="true"/>
 * <field name="langname" type="text" indexed="true" required="true" stored="true"/>
 */

public class Language implements SolrSchemaType {
    
    String langcode3;
    
    String langcode2;
    
    String langcode1;
    
    String langname;

    public String getLangcode3() {

        return langcode3;
    }
@Field("langcode3")
    public void setLangcode3(String langcode3) {
        this.langcode3 = langcode3;
    }

    public String getLangcode2() {
        return langcode2;
    }
@Field("langcode2")
    public void setLangcode2(String langcode2) {
        this.langcode2 = langcode2;
    }

    public String getLangcode1() {
        return langcode1;
    }
@Field("langcode1")
    public void setLangcode1(String langcode1) {
        this.langcode1 = langcode1;
    }

    public String getLangname() {
        return langname;
    }
@Field("langname")
    public void setLangname(String langname) {
        this.langname = langname;
    }
}

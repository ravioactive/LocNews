package org.com.location.model.solr.schema;

import org.apache.solr.client.solrj.beans.Field;
import org.com.location.model.solr.SolrLocationType;
import org.com.location.model.solr.SolrSchemaType;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ravioactive
 * Date: 11/28/13
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */

/**
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

public class WikiNews implements SolrSchemaType {
  
    private String id;
    
    private String title;
    
    private int revision;
    
    private String user;
    
    private int userId;
    
    private String text;
    
    private Date timestamp;

    private String titleText;

    String summary;

    public String getId() {
        return id;
    }
@Field("id")
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
@Field("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public int getRevision() {
        return revision;
    }
@Field("revision")
    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getUser() {
        return user;
    }
@Field("user")
    public void setUser(String user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }
@Field("userId")
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }
@Field("text")
    public void setText(String text) {
        this.text = text;
    }

    public Date getTimestamp() {
        return timestamp;
    }
@Field("timestamp")
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitleText() {
        return titleText;
    }
@Field("titleText")
    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }
}

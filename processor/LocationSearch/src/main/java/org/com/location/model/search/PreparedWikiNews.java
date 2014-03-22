package org.com.location.model.search;

import org.com.location.model.solr.schema.WikiNews;

/**
 * User: ravioactive
 * Date: 11/27/13
 * Time: 9:35 PM
 */

public class PreparedWikiNews {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    String author;
    String title;
    String content;
    String summary;

    public PreparedWikiNews(WikiNews solrWikiNews) {
        id = solrWikiNews.getId();
        author = solrWikiNews.getUser();
        title = solrWikiNews.getTitleText();
        content = solrWikiNews.getText();
        summary = solrWikiNews.getSummary();
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public PreparedWikiNews() {

    }

    @Override
    public String toString() {
        return "PreparedWikiNews{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}

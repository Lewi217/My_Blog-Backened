package DevLewi.My_Blog.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "articles") // Ensure this matches your database table name
public class Articles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId; // Unique identifier for the article

    private String title;
    private String content;
    private Long authorId;
    private String authorName; // Custom attribute not in the database
    private Date publicationDate;
    private String tag;
    private Long views;
    private Long likes;
    private Long comments;

    public Articles() {
    }

    public Articles(Long articleId, String title, String content, Long authorId, Date publicationDate, String tag, Long views, Long likes, Long comments) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.publicationDate = publicationDate;
        this.tag = tag;
        this.views = views;
        this.likes = likes;
        this.comments = comments;
    }

    // Constructor including authorName
    public Articles(Long articleId, String title, String content, Long authorId, String authorName, Date publicationDate, String tag, Long views, Long likes, Long comments) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.authorName = authorName;
        this.publicationDate = publicationDate;
        this.tag = tag;
        this.views = views;
        this.likes = likes;
        this.comments = comments;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", publicationDate=" + publicationDate +
                ", tag='" + tag + '\'' +
                ", views=" + views +
                ", likes=" + likes +
                ", comments=" + comments +
                '}';
    }
}

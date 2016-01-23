package course;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostBuilder {

    private String title;
    private String author;
    private String body;
    private List tags;

    private PostBuilder() {
    }

    public static PostBuilder builder() {
        return new PostBuilder();
    }

    public PostBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PostBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public PostBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public PostBuilder withTags(List tags) {
        this.tags = tags;
        return this;
    }

    public Document build() {
        return new Document()
                .append("title", title)
                .append("author", author)
                .append("body", body)
                .append("permalink", buildPermalink())
                .append("tags", tags)
                .append("comments", new ArrayList())
                .append("date", new Date());

    }

    private String buildPermalink(){
        String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
        permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
        permalink = permalink.toLowerCase();
        permalink = permalink+ (new Date()).getTime();
        return permalink;
    }

}

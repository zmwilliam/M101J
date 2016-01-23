package course;

import com.google.common.base.Strings;
import org.bson.Document;

import static com.google.common.base.Strings.isNullOrEmpty;

public class CommentBuilder {

    private String author;
    private String body;
    private String email;

    private CommentBuilder() {
    }

    public static CommentBuilder builder() {
        return new CommentBuilder();
    }


    public CommentBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public CommentBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public CommentBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public Document build() {
        Document comment = new Document()
                .append("author", author)
                .append("body", body);

        if (!isNullOrEmpty(email)) {
            comment.append("email", email);
        }

        return comment;
    }
}

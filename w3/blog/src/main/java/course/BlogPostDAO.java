package course;

import com.google.common.collect.Lists;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class BlogPostDAO {
    MongoCollection<Document> postsCollection;

    public BlogPostDAO(final MongoDatabase blogDatabase) {
        postsCollection = blogDatabase.getCollection("posts");
    }

    // Return a single post corresponding to a permalink
    public Document findByPermalink(String permalink) {

        // todo  XXX
        Document post = postsCollection.find(new Document("permalink", permalink)).first();
        return post;
    }

    // Return a list of posts in descending order. Limit determines
    // how many posts are returned.
    public List<Document> findByDateDescending(int limit) {

        // todo,  XXX
        // Return a list of Documents, each one a post from the posts collection

        FindIterable<Document> posts = postsCollection.find().sort(new Document("date", -1)).limit(limit);
        return Lists.newArrayList(posts);
    }


    public String addPost(String title, String body, List tags, String username) {

        System.out.println("inserting blog entry " + title + " " + body);

        // todo XXX
        // Remember that a valid post has the following keys:
        // author, body, permalink, tags, comments, date
        //
        // A few hints:
        // - Don't forget to create an empty list of comments
        // - for the value of the date key, today's datetime is fine.
        // - tags are already in list form that implements suitable interface.
        // - we created the permalink for you above.

        // Build the post object and insert it
        Document post = PostBuilder.builder()
                .withTitle(title)
                .withAuthor(username)
                .withBody(body)
                .withTags(tags)
                .build();

        postsCollection.insertOne(post);

        return post.getString("permalink");
    }


    //
    //
    //  White space to protect the innocent
    //
    //


    // Append a comment to a blog post
    public void addPostComment(final String name, final String email, final String body,
                               final String permalink) {

        // todo  XXX
        // Hints:
        // - email is optional and may come in NULL. Check for that.
        // - best solution uses an update command to the database and a suitable
        //   operator to append the comment on to any existing list of comments

        Document comment = CommentBuilder.builder()
                .withAuthor(name)
                .withBody(body)
                .withEmail(email)
                .build();

        Document postToUpdate = findByPermalink(permalink);
        Document addToComments = new Document("$addToSet", new Document("comments", comment));
        postsCollection.updateOne(postToUpdate, addToComments);

    }
}

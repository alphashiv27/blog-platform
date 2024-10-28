package club.shukla.blogplatform.repository.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogPostConstants {
    // Change this name according to the collection, database and atlas search index created
    public static final String BLOG_DATABASE= "blogplatform";
    public static final String BLOG_POST_COLLECTION = "blog";
    public static final String SEARCH_INDEX_NAME = "search_term";

    public static final String CREATED_AT = "createdAt";
    public static final String CONTENT = "content";
    public static final String TITLE = "title";
    public static final String CATEGORY = "category";
    public static final String TAGS = "tags";


    // -1 for descending and +1 for ascending the field is of createdAt
    public static final Integer SORT_ORDER = -1;
    public static final Integer BLOG_SEARCH_LIMIT = 5;
    public static final String SEARCH_AGGREGATION = "$search";
    public static final String SORT_AGGREGATION = "$sort";
    public static final String LIMIT_AGGREGATION = "$limit";
    public static final String INDEX = "index";
    public static final String TEXT = "text";
    public static final String QUERY = "query";
    public static final String PATH = "path";
}

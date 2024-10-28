# Personal Blogging Platform API

This project is a RESTful API for a personal blogging platform built with Spring Boot and MongoDB. The API allows users to perform basic CRUD (Create, Read, Update, Delete) operations for managing blog posts. Users can create, update, delete, retrieve, and search blog posts based on a term.

## Features

- **Create a Blog Post**: Add a new blog post with title, content, category, and tags.
- **Retrieve Blog Posts**: Retrieve all blog posts or filter posts by a search term.
- **Update a Blog Post**: Modify the details of an existing blog post.
- **Delete a Blog Post**: Remove a blog post by ID.
- **Search**: Use a search term to filter posts based on title, content, or category.

## Getting Started

### Prerequisites

- **Java 21**
- **MongoDB** (URI and database required)
- **Maven** (for building the project)

### Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/your-username/blog-platform.git
   cd blog-platform
   ```

2. **Install dependencies and build the project**:

   ```bash
   mvn clean install
   ```

### Configuration

Set up the following properties in `src/main/resources/application.properties`:

```properties
# Application name
spring.application.name=blog-platform

# MongoDB Configuration
spring.data.mongodb.uri=YOUR_MONGODB_URI
spring.data.mongodb.database=blogplatform
```

- **spring.application.name**: The name of your application.
- **spring.data.mongodb.uri**: The MongoDB connection URI.
- **spring.data.mongodb.database**: The name of the MongoDB database to use.

### Running the Application

After configuration, start the application with:

```bash
mvn spring-boot:run
```

The API should now be accessible at `http://localhost:8080`.

## API Endpoints

| Method | Endpoint             | Description                        |
|--------|----------------------|------------------------------------|
| -      | `/`                  | Swagger UI                         |
| GET    | `/health`            | Check the health of application    |
| POST   | `/posts`             | Create a new blog post             |
| GET    | `/posts`             | Get all blog posts                 |
| GET    | `/posts/{postId}`    | Get a single blog post by ID       |
| PUT    | `/posts/{postId}`    | Update a blog post by ID           |
| DELETE | `/posts/{postId}`    | Delete a blog post by ID           |
| GET    | `/posts?term={term}` | Filter blog posts by a search term |


### Example Requests

**Create Blog Post**

```http
POST /posts
Content-Type: application/json

{
  "title": "My First Blog Post",
  "content": "This is the content of my first blog post.",
  "category": "Technology",
  "tags": ["Tech", "Programming"]
}
```

**Get All Blog Posts**

```http
GET /posts
```

**Get a Single Blog Post**

```http
GET /posts/1
```

**Update a Blog Post**

```http
PUT /posts/1
Content-Type: application/json

{
  "title": "My Updated Blog Post",
  "content": "This is the updated content of my first blog post.",
  "category": "Technology",
  "tags": ["Tech", "Programming"]
}
```

**Delete a Blog Post**

```http
DELETE /posts/1
```

**Filter Blog Posts**

```http
GET /posts?term=tech
```

### Response Codes

- **201 Created**: Resource successfully created.
- **200 OK**: Request was successful.
- **204 No Content**: Resource successfully deleted.
- **400 Bad Request**: Validation failed.
- **404 Not Found**: Resource not found.

## Configuration Constants

You can tweak the following constants in `BlogPostConstants.java` to adjust database and search settings:

```java
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogPostConstants {
    // Change these according to your database, collection, and search index
    public static final String BLOG_DATABASE = "blogplatform";          // Database name
    public static final String BLOG_POST_COLLECTION = "blog";           // Collection name
    public static final String SEARCH_INDEX_NAME = "search_term";       // Index name for search

    public static final String CREATED_AT = "createdAt";
    public static final String CONTENT = "content";
    public static final String TITLE = "title";
    public static final String CATEGORY = "category";
    public static final String TAGS = "tags";

    // Sorting order: -1 for descending, 1 for ascending; field is 'createdAt'
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
```

### How to Tweak the Constants

- **Database and Collection Names**:
    - `BLOG_DATABASE`: Change this to match your MongoDB database name.
    - `BLOG_POST_COLLECTION`: Set this to your MongoDB collection name for blog posts.
    - Ensure these names match the ones you've set in your MongoDB setup.

- **Search Index Name**:
    - `SEARCH_INDEX_NAME`: Set this to the name of your MongoDB Atlas search index.
    - This is crucial for the search functionality to work correctly.
    - [Link](https://www.mongodb.com/docs/atlas/atlas-search/create-index/) to setup search index.

- **Sorting Order**:
    - `SORT_ORDER`: Adjust to `-1` for descending or `1` for ascending order based on the `createdAt` field.
    - This changes the order in which blog posts are returned when fetching multiple posts.

- **Search Limit**:
    - `BLOG_SEARCH_LIMIT`: Change this value to increase or decrease the number of search results returned.
    - Useful for pagination or limiting the amount of data returned in a single request.

## Technologies Used

- **Java 21**: Core programming language.
- **Spring Boot**: Framework for building the RESTful API.
- **MongoDB**: NoSQL database for storing blog posts.
- **Maven**: Dependency management and build tool.

### Links to setup Mongo Db 
1. [Create Database](https://www.mongodb.com/resources/products/fundamentals/create-database)
2. [Create Atlas Search Index](https://www.mongodb.com/docs/atlas/atlas-search/create-index/)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any questions or issues, please contact [Shivam](mailto:shivamshukla2712@gmail.com).


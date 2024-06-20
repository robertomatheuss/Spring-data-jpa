# JPA

## Diagrama de Classes

```mermaid
classDiagram
    class Book {
        +UUID id
        +String title
        +Publisher publisher
        +Set<Author> authors
        +Review review
    }
    
    class Author {
        +UUID id
        +String name
        +Set<Book> books
    }
    
    class Publisher {
        +UUID id
        +String name
        +Set<Book> books
    }
    
    class Review {
        +UUID id
        +String comment
        +Book book
    }
    
    Book --> Author : written by
    Book --> Publisher : published by
    Book --> Review : has
```

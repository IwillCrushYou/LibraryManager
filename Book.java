public class Book {
    public String author;
    public String title;
    public String publisher;
    public String isbn;
    public Integer uuid;

    Book(String author, String title, String publisher, String isbn, Integer uuid) {
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.isbn = isbn;
        this.uuid = uuid;
    }

    public String toString() {
        return this.author + "," + this.title + "," + this.publisher + "," + this.isbn+ "," + this.uuid;
    }

    public static Book toBook(String bookString) {
        String[] details = bookString.split(",");
        return new Book(details[0], details[1], details[2], details[3], Integer.parseInt(details[4]));
    }

}

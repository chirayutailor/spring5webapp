package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author a1 = new Author( "Chirayu", "Tailor");
        Book b1 = new Book( "Book1", " 12343");

        a1.getBooks().add( b1 );
        b1.getAuthors().add( a1 );

        Author a2 = new Author( "Jasmin", "Tailor" );
        Book b2 = new Book( "Book2", "12345" );

        a2.getBooks().add( b2 );
        b2.getAuthors().add( a2 );

        Publisher p1 = new Publisher( "Publisher1", "Address1", "City1", "State1", "12345" );
        publisherRepository.save( p1 );

        b1.setPublisher( p1 );
        b2.setPublisher( p1 );

        System.out.println( " #1 Total Book Size: " + p1.getBooks().size() );
        p1.getBooks().add( b1 );
        System.out.println( " #2 Total Book Size: " + p1.getBooks().size() );
        p1.getBooks().add( b2 );
        System.out.println( " #3 Total Book Size: " + p1.getBooks().size() );

        for ( Book book: p1.getBooks() ) {
            System.out.println( book );
        }

        authorRepository.save( a1 );
        authorRepository.save( a2 );

        bookRepository.save( b1 );
        bookRepository.save( b2 );

        publisherRepository.save( p1 );

        System.out.println( "Bootstrap Started" );
        System.out.println( "Author Count: " + authorRepository.count() );
        System.out.println( "Book Count: " + bookRepository.count() );
        System.out.println( "Publisher Count: "  + publisherRepository.count() );
        System.out.println( "Number of books with Publisher: " + p1.getBooks().size() );
    }
}

package csulb.cecs323.dataAccess.IRepositories;

import csulb.cecs323.dataAccess.IGenericRepository;
import csulb.cecs323.model.Book;
import csulb.cecs323.model.Publisher;

import java.util.List;

public interface IBookRepository {
    List<Book> GetAllBooks();

    Book GetBookByISBN(String ISBN);
}

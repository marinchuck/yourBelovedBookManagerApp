package com.progmatic.hazi3.view;

import static com.progmatic.hazi3.Main.AUTHOR_CONTROLLER;
import static com.progmatic.hazi3.Main.AUTHOR_VIEW;
import static com.progmatic.hazi3.Main.BOOK_CONTROLLER;
import static com.progmatic.hazi3.Main.SCANNER;

import com.progmatic.hazi3.dto.CreateBookDTO;
import com.progmatic.hazi3.dto.RetrieveAuthorDTO;
import com.progmatic.hazi3.dto.RetrieveBookDTO;
import java.util.List;

public class BookView implements View {

    public static BookView newInstance() {
        return new BookView();
    }

    private void renderMenu() {
        renderHeader();
        System.out.println("(1): List all books");
        System.out.println("(2): Register new book");
        System.out.println("(3): Search for a book");
        System.out.println("(b): Back\n");

    }

    public void render() {
        renderMenu();
        run();
    }

    void renderListAllBooksComponent() {
        System.out.println();
        BOOK_CONTROLLER.retrieveAllBooks().forEach(System.out::println);
    }

    void renderRegisterNewBookComponent() {
        System.out.println("\nEnter a title: ");
        String title = SCANNER.nextLine();
        RetrieveAuthorDTO author;
        while (true) {
            System.out.println("\nPlease give me the id of the author of the book!");
            AUTHOR_VIEW.renderListAllAuthorsComponent();
            System.out.println("(0) Register new author");

            String optionInString = SCANNER.nextLine();
            if ("0".equals(optionInString)) {
                AUTHOR_VIEW.renderRegisterNewAuthorComponent();
            } else {
                Long id;
                try {
                    id = Long.parseLong(optionInString);
                } catch (NumberFormatException e) {
                    System.err.println("Unable to parse option!\n");
                    return;
                }
                author = AUTHOR_CONTROLLER.retrieveAuthorById(id);
                break;
            }
        }
        System.out.println("\nEnter the isbn code of the book: ");
        String isbn = SCANNER.nextLine();
        System.out.println("\nEnter if book is orderAble or not (true/false): ");
        boolean isActive = Boolean.parseBoolean(SCANNER.nextLine());
        BOOK_CONTROLLER.createBook(new CreateBookDTO(title, author, isbn, isActive));
    }

    void renderSearchBookComponentSubmenu() {
        renderHeader();
        System.out.println("(1): Search book by id");
        System.out.println("(2): Search books by title");
        System.out.println("(3): Search books by author");
        System.out.println("(4): Search book by ISBN");
        System.out.println("(b): Back\n");
    }

    void renderSearchBookByIdComponent() {
        System.out.println("\nEnter the id of the book: ");
        Long id;
        try {
            id = Long.parseLong(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Unable to parse id!\n");
            return;
        }
        RetrieveBookDTO foundBook = BOOK_CONTROLLER.retrieveBookById(id);
        if (foundBook != null) {
            System.out.println(foundBook);
        }
    }

    void renderSearchBookByTitleComponent() {
        System.err.println("\nCAUTION!\nBooks with 'isActive=false' parameter will not be shown in the result list!");
        System.out.println("\nEnter the title of the book: ");

        String title = SCANNER.nextLine();

        List<RetrieveBookDTO> foundBooks = BOOK_CONTROLLER.retrieveBooksByTitleAndIsActive(title, true);

        if (foundBooks.isEmpty()) {
            System.out.println("No books with the title '" + title + "' found.\n");
        } else {
            foundBooks.forEach(System.out::println);
            System.out.println();
        }
    }

    void renderSearchBookByAuthorComponent() {
        System.err.println("\nCAUTION!\nBooks with 'isActive=false' parameter will not be shown in the result list!");

        List<RetrieveAuthorDTO> foundAuthors = AUTHOR_VIEW.renderSearchAuthorByNameComponent();
        if (foundAuthors == null || foundAuthors.isEmpty()) {
            return;
        }
        RetrieveAuthorDTO selectedAuthor = AUTHOR_VIEW.renderSearchAuthorByIdComponent();
        if (selectedAuthor == null) {
            return;
        }

        List<RetrieveBookDTO> foundBooks = BOOK_CONTROLLER.retrieveBooksByAuthorAndIsActive(selectedAuthor, true);

        if (foundBooks == null || foundBooks.isEmpty()) {
            System.out.println("No books with the author " + selectedAuthor.getName() + " found.\n");
        } else {
            foundBooks.forEach(System.out::println);
            System.out.println();
        }
    }

    void runSearchBookComponent() {
        String input;
        while (!(input = SCANNER.nextLine()).equalsIgnoreCase("b")) {
            switch (input.toLowerCase()) {
                case "1" -> renderSearchBookByIdComponent();
                case "2" -> renderSearchBookByTitleComponent();
                case "3" -> renderSearchBookByAuthorComponent();
                case "4" -> renderSearchBookByISBNComponent();

                default -> System.err.println("Invalid input!");
            }
            renderSearchBookComponentSubmenu();
        }
    }

    void renderSearchBookByISBNComponent() {
        System.err.println("\nCAUTION!\nBooks with 'isActive=false' parameter will not be shown in the result list!");
        System.out.println("\nEnter the ISBN of the book: ");

        String isbn = SCANNER.nextLine();

        RetrieveBookDTO foundBook = BOOK_CONTROLLER.retrieveBookByIsbnAndIsActive(isbn, false);

        if (foundBook != null) {
            System.out.println(foundBook);
        }
    }

    void renderSearchBookComponent() {
        renderSearchBookComponentSubmenu();
        runSearchBookComponent();
    }

    private void run() {
        String input;
        while (!(input = SCANNER.nextLine()).equalsIgnoreCase("b")) {
            switch (input.toLowerCase()) {
                case "1" -> renderListAllBooksComponent();
                case "2" -> renderRegisterNewBookComponent();
                case "3" -> renderSearchBookComponent();

                default -> System.err.println("Invalid input!");
            }
            renderMenu();
        }
    }

}

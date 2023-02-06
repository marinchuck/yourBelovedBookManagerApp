package com.progmatic.hazi3.view;

import static com.progmatic.hazi3.Main.AUTHOR_CONTROLLER;
import static com.progmatic.hazi3.Main.SCANNER;

import com.progmatic.hazi3.dto.CreateAuthorDTO;
import com.progmatic.hazi3.dto.RetrieveAuthorDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AuthorView implements View {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static AuthorView newInstance() {
        return new AuthorView();
    }

    void renderListAllAuthorsComponent() {
        System.out.println();
        AUTHOR_CONTROLLER.readAllAuthors().forEach(System.out::println);

    }

    void renderRegisterNewAuthorComponent() {
        System.out.println("\nEnter a name: ");
        String name = SCANNER.nextLine();
        System.out.println("\nEnter a birth date (YYYY-MM-DD): ");
        LocalDate birthDay;
        try {
            birthDay = LocalDate.parse(SCANNER.nextLine(), dtf);
        } catch (DateTimeParseException e) {
            System.err.println("Unable to parse date!");
            return;
        }
        AUTHOR_CONTROLLER.createAuthor(new CreateAuthorDTO(name, birthDay));
    }

    void renderDeleteAuthorComponent() {
        renderListAllAuthorsComponent();
        System.out.println("\nEnter an id: ");
        long id;
        try {
            id = Long.parseLong(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("\nUnable to parse id!");
            return;
        }
        AUTHOR_CONTROLLER.deleteAuthorById(id);
    }

    void renderModifyAuthorComponent() {
        renderListAllAuthorsComponent();
        System.out.println("\nEnter an id: ");
        long id;
        try {
            id = Long.parseLong(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("\nUnable to parse id!");
            return;
        }
        RetrieveAuthorDTO author = AUTHOR_CONTROLLER.retrieveAuthorById(id);
        if (author != null) {
            System.out.println("\nEnter a new name: ");
            String name = SCANNER.nextLine();
            AUTHOR_CONTROLLER.updateAuthor(author, new String[]{name});
        }
    }

    private void renderMenu() {
        renderHeader();
        System.out.println("(1): List all authors");
        System.out.println("(2): Search authors");
        System.out.println("(3): Register new author");
        System.out.println("(4): Delete author");
        System.out.println("(5): Modify author");
        System.out.println("(b): Back\n");
    }

    public void render() {
        renderMenu();
        run();
    }

    void renderSearchAuthorComponentSubmenu() {
        renderHeader();
        System.out.println("(1): Search author by id");
        System.out.println("(2): Search author by name");
        System.out.println("(b): Back\n");
    }

    void renderSearchAuthorComponent() {
        renderSearchAuthorComponentSubmenu();
        runSearchAuthorComponent();
    }

    RetrieveAuthorDTO renderSearchAuthorByIdComponent() {
        System.out.println("\nEnter the id of the author: ");
        Long id;
        try {
            id = Long.parseLong(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Unable to parse id!");
            return null;
        }
        RetrieveAuthorDTO foundAuthor = AUTHOR_CONTROLLER.retrieveAuthorById(id);
        if (foundAuthor != null) {
            System.out.println("\n" + foundAuthor);
        }
        return foundAuthor;
    }

    List<RetrieveAuthorDTO> renderSearchAuthorByNameComponent() {
        System.out.println("\nEnter the name of the author: ");

        String name = SCANNER.nextLine();

        List<RetrieveAuthorDTO> foundAuthors = AUTHOR_CONTROLLER.retrieveAuthorsByName(name);

        System.out.println();
        if (foundAuthors.isEmpty()) {
            System.out.println("No authors with the name '" + name + "' found.");
            return new ArrayList<>();
        } else {
            foundAuthors.forEach(System.out::println);
            return foundAuthors;
        }
    }

    void runSearchAuthorComponent() {
        String input;
        while (!(input = SCANNER.nextLine()).equalsIgnoreCase("b")) {
            switch (input.toLowerCase()) {
                case "1" -> renderSearchAuthorByIdComponent();
                case "2" -> renderSearchAuthorByNameComponent();

                default -> System.err.println("Invalid input!");
            }
            renderSearchAuthorComponentSubmenu();
        }
    }

    private void run() {
        String input;
        while (!(input = SCANNER.nextLine()).equalsIgnoreCase("b")) {
            switch (input.toLowerCase()) {
                case "1" -> renderListAllAuthorsComponent();

                case "2" -> renderSearchAuthorComponent();

                case "3" -> renderRegisterNewAuthorComponent();

                case "4" -> renderDeleteAuthorComponent();

                case "5" -> renderModifyAuthorComponent();

                default -> System.err.println("Invalid input!");
            }
            renderMenu();
        }
    }
}

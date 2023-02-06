package com.progmatic.hazi3.view;

import static com.progmatic.hazi3.Main.AUTHOR_VIEW;
import static com.progmatic.hazi3.Main.BOOK_VIEW;
import static com.progmatic.hazi3.Main.SCANNER;

import java.util.Scanner;
import lombok.Getter;

@Getter
public class MainView implements View{
    public static MainView newInstance() {
        return new MainView();
    }


    private void renderMenu() {
        renderHeader();
        System.out.println("(1): Manage authors");
        System.out.println("(2): Manage books");
        System.out.println("(e): Exit\n");
    }

    private void run() {
        String input;
        while (!(input = SCANNER.nextLine()).equalsIgnoreCase("e")) {
            switch (input.toLowerCase()) {
                case "1" -> AUTHOR_VIEW.render();

                case "2" -> BOOK_VIEW.render();

                case "e" -> {
                    return;
                }

                default -> System.err.println("Invalid input!");
            }
            renderMenu();
        }
    }

    public void render() {
        try (Scanner scanner = SCANNER) {
            renderMenu();
            run();
        }
    }


}

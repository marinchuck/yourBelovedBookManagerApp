package com.progmatic.hazi3;

import com.progmatic.hazi3.controller.AuthorController;
import com.progmatic.hazi3.controller.BookController;
import com.progmatic.hazi3.util.JPAUtil;
import com.progmatic.hazi3.view.AuthorView;
import com.progmatic.hazi3.view.BookView;
import com.progmatic.hazi3.view.MainView;
import java.util.Scanner;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final AuthorView AUTHOR_VIEW = AuthorView.newInstance();
    public static final BookView BOOK_VIEW = BookView.newInstance();
    public static final MainView MAIN_VIEW = MainView.newInstance();
    public static final AuthorController AUTHOR_CONTROLLER = AuthorController.newInstance();
    public static final BookController BOOK_CONTROLLER = BookController.newInstance();


    public static void main(String[] args) {

        try (JPAUtil entityManagerFactory = new JPAUtil()) {
            MAIN_VIEW.render();
        } catch (Exception e) {
            System.err.println("Factory is not closing today.");
            System.exit(1);
        }
        System.exit(0);
    }
}

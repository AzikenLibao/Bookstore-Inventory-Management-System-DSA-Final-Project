import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Inventory {


    private LinkedList<Book> bookInventory;

    private Queue<String> orderQueue;

    public Inventory() {
        this.bookInventory = new LinkedList<>();
        this.orderQueue = new LinkedList<>();
    }


    public void addBook(Scanner sc) {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter book author: ");
        String author = sc.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = sc.nextLine();

        double price = -1;
        while (price < 0) {
            try {
                System.out.print("Enter book price: ");
                // Use nextLine and parse to handle the entire line
                price = Double.parseDouble(sc.nextLine());
                if (price < 0) {
                    System.out.println("Price cannot be negative. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format. Please enter a valid number.");
                price = -1;
            }
        }

        Book newBook = new Book(title, author, isbn, price);
        bookInventory.add(newBook);
        System.out.println("Book added successfully!");
    }


    public void displayAllBooks() {
        if (bookInventory.isEmpty()) {
            System.out.println("The inventory is currently empty.");
            return;
        }

        System.out.println("--- All Books in Inventory ---");
        for (Book book : bookInventory) {
            System.out.println(book);
        }
        System.out.println("-----------------------------");
    }




    public void sortBooksByTitle() {
        System.out.println("Sorting books by title...");


        int n = bookInventory.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {

                Book book1 = bookInventory.get(j);
                Book book2 = bookInventory.get(j + 1);

                if (book1.getTitle().compareToIgnoreCase(book2.getTitle()) > 0) {
                    bookInventory.set(j, book2);
                    bookInventory.set(j + 1, book1);
                }
            }
        }
        System.out.println("Books sorted successfully!");
    }


    public void searchBookByTitle(Scanner sc) {
        System.out.print("Enter the title of the book to search for: ");
        String searchTitle = sc.nextLine().trim();

        // Linear search: Check every element in the list one by one
        for (Book book : bookInventory) {
            if (book.getTitle().equalsIgnoreCase(searchTitle)) {
                System.out.println("Book found:");
                System.out.println(book);
                return; // Exit the method once found
            }
        }
        // If the loop completes without finding the book
        System.out.println("Book with title \"" + searchTitle + "\" was not found in the inventory.");
    }


    public void addOrderToQueue(Scanner sc) {
        System.out.print("Enter the title of the book to order: ");
        String orderTitle = sc.nextLine().trim();


        orderQueue.add(orderTitle);
        System.out.println("Order for \"" + orderTitle + "\" has been added to the queue.");

    }


    public void processNextOrder() {
        System.out.println("Processing next order...");

        if (orderQueue.isEmpty()) {
            System.out.println("No customer orders currently in the queue.");
            return;
        }

        // The poll() method of Queue is used to retrieve and remove the head (FIFO)
        String processedTitle = orderQueue.poll();
        System.out.println("Processed order for: " + processedTitle);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory system = new Inventory();
        boolean running = true;

        System.out.println("Welcome to the Bookstore Inventory Management System!");

        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Add a new book");
            System.out.println("2. Display all books");
            System.out.println("3. Sort books by title");
            System.out.println("4. Search for a book by title");
            System.out.println("5. Add a customer order to the queue");
            System.out.println("6. Process the next customer order");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            // Input validation for the choice
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                // Consume the rest of the line to prevent issues with nextLine() later
                sc.nextLine();

                switch (choice) {
                    case 1:
                        system.addBook(sc);
                        break;
                    case 2:
                        system.displayAllBooks();
                        break;
                    case 3:
                        system.sortBooksByTitle();
                        break;
                    case 4:
                        system.searchBookByTitle(sc);
                        break;
                    case 5:
                        system.addOrderToQueue(sc);
                        break;
                    case 6:
                        system.processNextOrder();
                        break;
                    case 7:
                        running = false;
                        System.out.println("Thank you for using the Bookstore Inventory Management System!");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a number between 1 and 7.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                // Consume the invalid input to avoid an infinite loop
                sc.nextLine();
            }
        }
        sc.close();
    }
}
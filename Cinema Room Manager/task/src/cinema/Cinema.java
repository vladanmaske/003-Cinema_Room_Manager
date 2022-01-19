package cinema;

import java.util.Scanner;

public class Cinema {
    private static final int LIMIT = 60;
    private static final int FRONT_SEAT_PRICE = 10;
    private static final int BACK_SEAT_PRICE = 8;
    private static int rows;
    private static int columns;
    private static int currentIncome;
    private static int[] columnsArray;
    private static char[][] cinemaRoom;
    private static final Scanner scanner = new Scanner(System.in);
    private static int counter;

    public static void main(String[] args) {
        createCinemaRoom();
        showMenu();
    }

    public static void createCinemaRoom() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        columns = scanner.nextInt();

        columnsArray = new int[columns];
        for (int i = 0; i < columnsArray.length; i++) {
            columnsArray[i] = i + 1;
        }
        cinemaRoom = new char[rows][columns];
        for (int i = 0; i < cinemaRoom.length; i++) {
            for (int j = 0; j < cinemaRoom[i].length; j++) {
                cinemaRoom[i][j] = 'S';
            }
        }
    }

    public static void editCinemaRoom() {
        System.out.println();
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();
        if (rowNumber > rows || seatNumber > columns) {
            System.out.println("Wrong input!");
            editCinemaRoom();
        } else if (cinemaRoom[rowNumber - 1][seatNumber - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            editCinemaRoom();
        } else {
            System.out.println();
            if (rows * columns <= LIMIT || rowNumber <= rows / 2) {
                System.out.println("Ticket price: $" + FRONT_SEAT_PRICE);
                currentIncome += FRONT_SEAT_PRICE;
            } else {
                System.out.println("Ticket price: $" + BACK_SEAT_PRICE);
                currentIncome += BACK_SEAT_PRICE;
            }
            cinemaRoom[rowNumber - 1][seatNumber - 1] = 'B';
            counter++;
        }


    }

    public static void printCinemaRoomGrid(int[] columnsArray, char[][] cinemaRoom) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i : columnsArray) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < cinemaRoom.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < cinemaRoom[i].length; j++) {
                System.out.print(cinemaRoom[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void calculateTotalIncome() {
        int income;
        if (rows * columns <= LIMIT) {
            income = rows * columns * FRONT_SEAT_PRICE;
        } else if (rows % 2 == 0){
            income = rows / 2 * columns * FRONT_SEAT_PRICE + rows / 2 * columns * BACK_SEAT_PRICE;
        } else {
            income = rows / 2 * columns * FRONT_SEAT_PRICE + (rows + 1) / 2 * columns * BACK_SEAT_PRICE;
        }
        System.out.println("Total income: $" + income);
    }

    public static void showMenu() {
        while (true) {
            System.out.println();
            System.out.println("1. Show the seats\n" + "2. Buy a ticket\n" + "3. Statistics\n" + "0. Exit");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    printCinemaRoomGrid(columnsArray, cinemaRoom);
                    break;
                case 2:
                    editCinemaRoom();
                    break;
                case 3:
                    showStats();
                    break;
                case 0:
                    return;
            }
        }
    }

    public static void showStats() {
        System.out.println("Number of purchased tickets: " + counter);
        double percentage = (double) counter / (double) (rows * columns) * (double) 100;
        System.out.println(String.format("Percentage: %.2f", percentage) + "%");
        System.out.println("Current income: $" + currentIncome);
        calculateTotalIncome();
    }
}
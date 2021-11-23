package Main;

import Entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Index {
    private static double notes = 0;
    private static List<Product> product = new ArrayList<>();
    private static double budget = 50000;
    private static double minRate = 0.1;

    public static void main(String[] args) {
        String options = null;
        do {
            chooseNotes();
            System.out.print("\n");
            choosePro();
            System.out.print("\n");
            options = checkOut(options);
        }
        while (!options.equals("N"));
    }


    private static void choosePro() {
        String n;
        Scanner sc = new Scanner(System.in);
        Choices choice = new Choices();
        do {
            //Check if the notes is run out or not
            if (notes == 0) {
                System.out.print("You ran out of money. Do you want to top up? (Y/N): ");
                n = checkYesNo(sc.nextLine().toUpperCase());
                if (n.equals("Y")) {
                    chooseNotes();
                } else {
                    n = "N";
                }
            } else {
                System.out.println("\t** Choose products **");
                System.out.println(" \t\tA - Coke \n\t\tB - Pepsi \n\t\tC - Soda");
                System.out.println("\t\tYou have " + notes + " left");
                System.out.print("Your choice: ");
                n = sc.nextLine().toUpperCase();
                if (!n.equals("A") && !n.equals("B") && !n.equals("C")) {
                    do {
                        System.out.print("Please choose the correct letter: ");
                        n = sc.nextLine().toUpperCase();
                    }
                    while (!n.equals("A") && !n.equals("B") && !n.equals("C"));
                }
                //choose the product by calling method Products in class Main.Choices
                Product p = choice.Products(n);
                if (p.getPrice() > notes) {
                    System.out.print("You don't have enough money. Do you want to top up? (Y/N): ");
                    n = checkYesNo(sc.nextLine().toUpperCase());
                    if (n.equals("Y")) {
                        chooseNotes();
                    } else {
                        n = "N";
                    }
                } else {
                    product.add(p);
                    notes -= p.getPrice();
                    System.out.print("Do you want to continue to buy (Y/N): ");
                    n = sc.nextLine().toUpperCase();
                    n = checkYesNo(n);
                }
            }
        }
        while (!n.equals("N"));
    }


    private static void chooseNotes() {
        Scanner sc = new Scanner(System.in);
        Choices choice = new Choices();
        String n;
        do {
            System.out.println("\t** Choose the note **");
            System.out.println(" \t\tA - 10.000  \n\t\tB - 20.000  \n\t\tC - 50.000 \n\t\tD - 100.000 \n\t\tE - 200.000");
            System.out.print("Your choice: ");
            n = sc.nextLine().toUpperCase();
            if (!n.equals("A") && !n.equals("B") && !n.equals("C") && !n.equals("D") && !n.equals("E")) {
                do {
                    System.out.print("Please choose the correct letter: ");
                    n = sc.nextLine().toUpperCase();
                }
                while (!n.equals("A") && !n.equals("B") && !n.equals("C") && !n.equals("D") && !n.equals("E"));
            }
            notes += choice.Notes(n);
            System.out.print("Do you want to continue to top up (Y/N): ");
            n = checkYesNo(sc.nextLine().toUpperCase());
        }
        while (!n.equals("N"));
    }


    private static String checkPromotion(List<String> proName) {
        int count = 0;
        String[] name = proName.toArray(new String[0]);
        for (int i = 1; i <= name.length - 1; i++) {
            if (name[i - 1].equalsIgnoreCase(name[i])) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 2) {
                return name[i];
            }
        }
        return "none";
    }

    private static String checkYesNo(String input) {
        Scanner sc = new Scanner(System.in);
        if (!input.equals("Y") && !input.equals("N")) {
            do {
                System.out.print("Please choose the correct letter: ");
                input = sc.nextLine().toUpperCase();
            }
            while (!input.equals("Y") && !input.equals("N"));
        }
        return input;
    }

    private static String checkOut(String options) {
        Scanner sc = new Scanner(System.in);
        List<String> proName = new ArrayList<>();
        int n;
        boolean isOkay = true;

        System.out.println("\t** Check out or cancel? ** ");
        System.out.println(" \t\t1 - Check out \n\t\t2 - Cancel");
        System.out.print("Your choice: ");
        do {
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n != 1 && n != 2) {
                    do {
                        System.out.print("Please choose the correct number: ");
                        n = Integer.parseInt(sc.nextLine());

                    } while (n != 1 && n != 2);
                }

                if (n == 2) {
                    for (Product p : product) {
                        notes += p.getPrice();
                    }
                    System.out.print("Your refund: " + notes);
                    options = "N";
                } else {

                    System.out.println("\nYour change: " + notes);
                    for (Product prod : product) {
                        proName.add(prod.getName());
                    }
                    String win = checkPromotion(proName);
                    if (budget != 0 && !win.equals("none")) {
                        double rate = Math.random() * (1 - 0) + 0;
                        //check the rate
                        getGacha(rate, win);
                        reportProduct();

                        //reset for another day
                        product.clear();
                        notes = 0;
                        proName.clear();
                        if (budget != 0) {
                            System.out.print("Do you want to move to next day? (Y/N): ");
                            options = checkYesNo(sc.nextLine().toUpperCase());
                            minRate += minRate * 0.5;
                        } else options = "N";

                    } else {
                        options = "N";
                        reportProduct();
                    }
                }

            } catch (Exception e) {
                isOkay = false;
                System.out.print("Please enter the corect format: ");
            }
        } while (isOkay != true);
        return options;
    }

    private static void reportProduct() {
        int amountCoke = 0;
        int amountPepsi = 0;
        int amountSoda = 0;
        for (Product prod : product) {
            switch (prod.getName()) {
                case "Coke":
                    amountCoke += 1;
                    break;
                case "Pepsi":
                    amountPepsi += 1;
                    break;
                case "Soda":
                    amountSoda += 1;
                    break;
            }
        }
        System.out.println("Your products include: ");
        System.out.println("\tCoke: " + amountCoke + " Pepsi: " + amountPepsi + " Soda: " + amountSoda);
    }


    private static void getGacha(double rate, String win) {
        if (0 <= rate && rate <= minRate) {
            System.out.println("Congratulation, you won one more " + win);
            for (Product prod : product) {
                if (win.equalsIgnoreCase(prod.getName())) {
                    if (prod.getPrice() > budget) {
                        prod.setName("Coke");
                        prod.setPrice(10000);
                        budget -= prod.getPrice();
                        break;
                    } else {
                        budget -= prod.getPrice();
                        product.add(prod);
                        break;
                    }
                }
            }
        }
    }
}


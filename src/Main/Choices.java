package Main;

import Entity.Money;
import Entity.Product;

public class Choices {

    public double Notes(String choice) {
        Money money = new Money();
        switch (choice) {
            case "A":
                money.setMoneyIn(10000);
                break;
            case "B":
                money.setMoneyIn(20000);
                break;
            case "C":
                money.setMoneyIn(50000);
                break;
            case "D":
                money.setMoneyIn(100000);
                break;
            case "E":
                money.setMoneyIn(200000);
                break;
            default:
        }
        return money.getMoneyIn();
    }

    public Product Products(String option) {
        Product pro = new Product();
        switch (option) {
            case "A":
                pro.setName("Coke");
                pro.setPrice(10000);
                break;
            case "B":
                pro.setName("Pepsi");
                pro.setPrice(10000);
                break;
            case "C":
                pro.setName("Soda");
                pro.setPrice(20000);
                break;
            default:
        }
        return pro;
    }


}

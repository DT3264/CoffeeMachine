package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String action;
        Machine coffeeMachine = new Machine();
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = scanner.nextLine();
            if (action.equals("")) action = scanner.nextLine();
            if (action.equals("buy")) {
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                String coffeeOption = scanner.nextLine();
                if (coffeeOption.equals("back")) continue;
                String response = coffeeMachine.buyCoffee(coffeeOption);
                System.out.println(response);
            } else if (action.equals("fill")) {
                int water, milk, coffee, cups;
                System.out.println("Write how many ml of water do you want to add:");
                water = scanner.nextInt();
                System.out.println("Write how many ml of milk do you want to add:");
                milk = scanner.nextInt();
                System.out.println("Write how many grams of coffee beans do you want to add");
                coffee = scanner.nextInt();
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                cups = scanner.nextInt();
                coffeeMachine.fillMachine(water, milk, coffee, cups);
            } else if (action.equals("take")) {
                int moneyTaken = coffeeMachine.takeMoney();
                System.out.println("I gave you $" + moneyTaken);
            } else if (action.equals("remaining")) System.out.println(coffeeMachine);
            else if (action.equals("exit")) break;
            System.out.println();
        }
    }
}

class Machine {
    int availableWater;
    int availableMilk;
    int availableCoffee;
    int availableCups;
    int availableMoney;

    Machine() {
        availableWater = 400;
        availableMilk = 540;
        availableCoffee = 120;
        availableCups = 9;
        availableMoney = 550;
    }

    /**
     * Method to check if the machine can serve a coffee given the water, milk and coffee needed
     * @param water The watter needed
     * @param milk The milk needed
     * @param coffee The coffee needed
     * @return An Object[] where obj[0] is a boolean if the machine can serve the coffee
     * and obj[1] is the message given by the machine
     */
    Object[] canServeCoffee(int water, int milk, int coffee) {
        if (availableWater < water) return new Object[]{false, "Sorry, not enough water!"};
        else if (availableMilk < milk) return new Object[]{false, "Sorry, not enough milk!"};
        else if (availableCoffee < coffee) return new Object[]{false, "Sorry, not enough coffee!"};
        else if (availableCups == 0) return new Object[]{false, "Sorry, not enough cups!"};
        return new Object[]{true, "I have enough resources, making you a coffee!"};
    }

    void serveCoffee(int water, int milk, int coffee, int money) {
        availableWater -= water;
        availableMilk -= milk;
        availableCoffee -= coffee;
        availableMoney += money;
        availableCups--;
    }

    String buyCoffee(String coffeeType) {
        int water = 0, milk = 0, coffee = 0, money = 0;
        if (coffeeType.equals("1")) {
            water = 250;
            milk = 0;
            coffee = 16;
            money = 4;
        } else if (coffeeType.equals("2")) {
            water = 350;
            milk = 75;
            coffee = 20;
            money = 7;
        } else if (coffeeType.equals("3")) {
            water = 200;
            milk = 100;
            coffee = 12;
            money = 6;
        }
        Object[] message = canServeCoffee(water, milk, coffee);
        if ((boolean) message[0]) {
            serveCoffee(water, milk, coffee, money);
        }
        return (String) message[1];
    }

    void fillMachine(int water, int milk, int coffee, int cups) {
        availableWater += water;
        availableMilk += milk;
        availableCoffee += coffee;
        availableCups += cups;
    }

    int takeMoney() {
        int previousMoney = availableMoney;
        availableMoney = 0;
        return previousMoney;
    }

    @Override
    public String toString() {
        String str = "";
        str += "The coffee machine has:\n";
        str += availableWater + " of water\n";
        str += availableMilk + " of milk\n";
        str += availableCoffee + " of coffee beans\n";
        str += availableCups + " of disposable cups\n";
        str += "$" + availableMoney + " of money";
        return str;
    }
}

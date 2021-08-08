package app.game;

import java.util.*;

public class Slots {
    public static void main(String [] args){
        Random generator = new Random();
        Scanner console = new Scanner(System.in);

        int input;
        int credits = 100;
        int slot1, slot2, slot3;
        do{
            System.out.println("Slot Machine v1");
            System.out.println("Your credits: " + credits);
            System.out.println("Press 1 to play (5 credits), 2 to exit");

            input = console.nextInt();
            slot1 = generator.nextInt(9) + 1;
            slot2 = generator.nextInt(9) + 1;
            slot3 = generator.nextInt(9) + 1;

            System.out.println(slot1 + " " + slot2 + " " + slot3);

            if(slot1 == slot2 && slot1 == slot3) {
                credits += 10;
                System.out.println("You win 10 credits");
            }
            else if(slot1 == slot2 || slot1 == slot3 || slot2 == slot3){
                credits += 5;
                System.out.println("You win 5 credits");
            }
            else{
                credits -= 5;
                System.out.println("You lose");
            }
        } while (input == 1);
    }
}

package app.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class JumpingCards {
    static HashSet<String> playBoard = new HashSet<>();
    static HashSet<String> playerCard= new HashSet<>();
    static ArrayList<Integer> d1= new ArrayList<>();
    static ArrayList<Integer> d2= new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        System.out.println("- JUMPING CARDS -\n1. This is a luck based game where 6 cards will be drawn at random from a deck of 52.\n2. The cards will be anonymous at first and will be numbered from 1-6.\n3. The user will throw a die 3 times.");
        System.out.println("4. The card with the number of the die will be given to user.\n5. If die value is repeated, take another turn.\n6. When both computer and user have 3 cards, the cards will be revealed.");
        System.out.println("7. Only the number part of all the cards will be counted, type of cards carry no value.\n8. The player with the higher value cards will win.\nP.S.: C=Clubs, D=Diamonds, S=Spades, H=Hearts, 11=Jack, 12=Queen, 13=King.\n\nGood luck!\n");
        int s1=0, s2=0;
        String cards[]= {"C", "H", "D", "S"};
        while(playBoard.size()<6) {
            String c1=cards[(int) Math.floor(Math.random()*4)];
            int c = (int) Math.floor(Math.random()*13)+1;
            String c2=Integer.toString(c);
            playBoard.add(c1+c2);
        }
        Object[] a=playBoard.toArray();
        for(int i=0;i<6;i++) {
            d1.add(i+1);
            String x=a[i].toString();
            s1=s1+ Integer.parseInt(x.substring(1));
        }
        display();
        while(playerCard.size()<3) {
            System.out.print("Press A to spin the die: ");
            scanner.next();
            int die = (int) Math.floor(Math.random()*6);
            System.out.println("Die Value: "+(die+1));
            String x=(String)a[die];
            if(playBoard.contains(x)) {
                s2=s2+Integer.parseInt(x.substring(1));
                d1.remove(Integer.valueOf(die+1));
                d2.add(die+1);
            }
            playerCard.add((String) a[die]);
            playBoard.remove((String) a[die]);
            display();
        }
        System.out.print("This is where the cards will be revealed. Press A to continue: ");
        scanner.next();
        displayFinal();
        int s=s1-s2;
        System.out.println("-FINAL SCORE- \nComputer: "+s+"\nPlayer: "+s2+"\n");
        if(s>s2)
            System.out.println("YOU LOSE!");
        else if(s<s2)
            System.out.println("YOU WIN!");
        else
            System.out.println("DRAW!");
    }
    public static void display() {
        System.out.println("\nComputer: "+d1);
        System.out.println("Player: "+d2+"\n");
    }
    public static void displayFinal() {
        System.out.println("\nComputer: "+playBoard);
        System.out.println("Player: "+playerCard+"\n");
    }
}

import java.util.Random;
import java.util.Scanner;

public class LastPencil{

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}

class Game {
    private int remainingPencils;
    private int removingPencils;

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many pencils would you like to use:");

        boolean numOfPencilsValid = false;
        do {
            try{
                String input = scanner.nextLine();
                if(input.equals(" ")) {
                    System.out.println("The number of pencils should be numeric");
                    continue;
                }
                remainingPencils = Integer.parseInt(input);
                if(remainingPencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                    continue;
                }
                numOfPencilsValid = true;
            }
            catch(NumberFormatException ex) {
                System.out.println("The number of pencils should be numeric");
            }
        }while(!numOfPencilsValid);

        System.out.println("Who will be the first (John, Jack):");

        boolean playerNameValid = false;


        String firstPlayer;
        do {
            firstPlayer = scanner.next();
            if(!firstPlayer.equals("John") && !firstPlayer.equals("Jack")){
                System.out.println("Choose between 'John' and 'Jack'");
                continue;
            }
            playerNameValid = true;
        }while(!playerNameValid);


        int playerNum;
        if(firstPlayer.equals("John"))
            playerNum = 1;
        else
            playerNum = 2;

        while (remainingPencils > 0) {
            printPencils(remainingPencils);
            System.out.printf("%s's turn!%n", getPlayer(playerNum));

            if (playerNum == 1) {
                boolean validNumOfRemovingPencils = false;
                do {
                    try {
                        removingPencils = Integer.parseInt(scanner.next());
                        if (removingPencils > 3 || removingPencils <= 0) {
                            System.out.println("Possible values: '1', '2' or '3'");
                            continue;
                        }
                        if (removingPencils > remainingPencils) {
                            System.out.println("Too many pencils were taken");
                            continue;
                        }
                        validNumOfRemovingPencils = true;
                    } catch (NumberFormatException ex) {
                        System.out.println("Possible values: '1', '2' or '3'");
                    }

                } while (!validNumOfRemovingPencils);
                playerNum = 2;
            }
            else if(playerNum == 2){
                System.out.println(removingPencils = getBotTake(remainingPencils));
                playerNum = 1;
            }

            remainingPencils -= removingPencils;
        }

        if(playerNum == 1)
            System.out.println("John won!");
        else if(playerNum == 2)
            System.out.println("Jack won!");
    }

    public int getBotTake(int remainingPencils) {
        int botTake;
        Random random = new Random();
        if((remainingPencils >= 4) && ((remainingPencils - 4) % 4 == 0))
            botTake = 3;
        else if((remainingPencils >= 3) && ((remainingPencils - 3) % 4 == 0))
            botTake = 2;
        else if((remainingPencils >= 2) && ((remainingPencils - 2) % 4 == 0))
            botTake = 1;
        else if(remainingPencils == 1)
            botTake = 1;
        else
            botTake = random.nextInt(3)+1;
        return botTake;
    }

    public void printPencils(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("|");
        }
        System.out.println();
    }

    public String getPlayer(int num) {
        String player = "";
        switch(num) {
            case 1 :
                player = "John";
                break;
            case 2 :
                player = "Jack";
                break;
        }
        return player;
    }
}

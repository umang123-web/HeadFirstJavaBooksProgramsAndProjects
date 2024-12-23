package Chapter5.ExtraStrengthMethod;

public class SimpleStartupTestDrive {

    public static void main(String[] args) {


        //simple method
        /*
        SimpleStartup dot = new SimpleStartup();

        int[] locations={2 , 3 , 4};

        dot.setLocationcells(locations);
        int userGuess =2;
        String result = dot.checkYourself(userGuess);

        String testresult = "failed";

        if(testresult.equals("hit")){
            testresult="passed";
        }

        System.out.println(testresult);


         */




        //Make a variable to track how many guesses the user makes

        int numofguesses=0;


        //This is the special class that we wrote  that has the method for getting  user input  for more pretend its part of java
        GameHelper game =new GameHelper();

        SimpleStartup startup = new SimpleStartup();


        //Make the random number for the first cell and use it to make the cell location array

        int randomnumber = (int) (Math.random() *5);

        int []locations={randomnumber , randomnumber + 1 , randomnumber +2};

        //give the startup its location(the array)
        startup.setLocationcells(locations);



        //make the boolean variable to track whether the game is alive to use
        //in the while loop test repeat while game is still alive
        boolean isAlive = true;



        while (isAlive){


            //get the user guess

            int guess = Integer.parseInt(game.getUserInput("enter a number"));


            //Ask the startup to check the guess save the returned result
            String result = startup.checkYourself(guess);
            numofguesses++;


            //Was it a “kill”? if so, set isAlive to false (so we won’t
            //re-enter the loop) and print user guess count
            if(result.equals("kills")){
                isAlive = false;

                System.out.println("you took" + numofguesses + "   guesses");
            }
        }






    }
}

package Chapter6.JavaLibraries;

import Chapter5.ExtraStrengthMethod.GameHelper;

import java.util.ArrayList;

public class StartupBust {

    private GameHelper gameHelper = new GameHelper();
    private ArrayList<Startup> startups = new ArrayList<>();
    private int numofguesses =0;

    private void setupGame(){
        //first make some startup and give them locations

        Startup one = new Startup();
        one.setName("poniez");
        Startup two = new Startup();
        two.setName("hacqi");

        Startup three = new Startup();
        three.setName("cabista");
        startups.add(one);
        startups.add(two);
        startups.add(three);


        System.out.println("Your goal is to sink three startup");
        System.out.println("poniez , hacqi , cabista");
        System.out.println("try to sink them all in the fewest number of guesses");


        for(Startup startup : startups){
            ArrayList<String> newLocation =gameHelper.placeStartup(3);
            startup.setLocationcells(newLocation);

            //close for loop
            //close setUpGame method
        }
    }

    private void startPlaying(){
        while (!startups.isEmpty()){
          String userGuess = String.valueOf(gameHelper.getUserInput("Enter a guess"));
          checkUserGuess(userGuess);

        }

        finishGame();
    }


    private void checkUserGuess(String userGuess) {

     numofguesses++;
     String result ="miss";
     for(Startup startupToTest : startups){
         result = startupToTest.checkYourself(userGuess);

         if(result.equals("hit")){
             break;
         }
         if(result.equals("kill")){
             startups.remove(startupToTest);
             break;
         }
     }

        System.out.println(result);
    }


    private void finishGame() {

        System.out.println("All startups are dead! Your stock is now worthless");
        if(numofguesses <=18){
            System.out.println("It only took you"  + numofguesses + " guesses.");
        }else {
            System.out.println("Took you long enough."  + numofguesses + " guesses");
            System.out.println("Fish are dancing with your options");
        }
    }

    public static void main(String[] args) {
        StartupBust game = new StartupBust();
        game.setupGame();
        game.startPlaying();
    }

}

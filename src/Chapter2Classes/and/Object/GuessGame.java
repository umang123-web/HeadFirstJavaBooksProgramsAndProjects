package Chapter2Classes.and.Object;

class   Player{

    public int number;

    public void guess() {

    }
}

public class GuessGame {

    Player p1;
    Player p2;
    Player p3;

     void Startgame(){
        p1 = new Player();
        p2= new Player();
        p3= new Player();


        int guessp1=0;
        int gueesp2=0;                    //Declare three to hold the three guesses the player make
        int guessp3=0;

        boolean p1isRight=false;
        boolean p2isRight=false;            //Declare three variable to hold a true  or false based on the player s ' answer
        boolean p3isRight=false;

        int targetNumber =(int) (Math.random() *10); //make a target number that the player have to guess
        System.out.println("I am thinking of a number  between 0 and 9....");

        while (true){
            System.out.println("Number guesses:"  + targetNumber);

            p1.guess();
            p2.guess();             //call each player guess method
            p3.guess();

            guessp1 =p1.number;
            System.out.println("player one guessed" +   guessp1);               /** check each player s' guess
                                                                           matches the target number. If a player is
                                                                           right then set that player variable to be
                                                                            true(remember , we set it false by default)


                                                                                  **/
            gueesp2 = p2.number;
            System.out.println("player two guessed" +  gueesp2);
            guessp3 = p3.number;
           System.out.println("player three guessed" +  guessp3);


           if(guessp1 == targetNumber){

               p1isRight = true;
           }

           if(gueesp2 == targetNumber){
               p2isRight = true;
           }

           if(guessp3 == targetNumber){
               p3isRight = true;
           }

           if(p1isRight || p2isRight || p3isRight){              //If the plyer one or two or three is right
               System.out.println("we have a winner");
               System.out.println("Player one got right ?"  + p1isRight);
               System.out.println("player two got right?" + p2isRight);
               System.out.println("player three got right?" + p3isRight);

               break;

           }else {
               //we must keep going because nobody got it right   otherwise stay in the loop and ask the player for another guess
               System.out.println("Player will have try again");

           }


        }




    }



}

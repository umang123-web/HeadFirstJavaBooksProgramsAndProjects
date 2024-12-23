package Chapter5.ExtraStrengthMethod;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameHelper {
private static final String ALPHABET="abcdefg";
private static final int GRID_LENGTH=7;
private static final int GRID_SIZE =49;
private static final int MAX_ATTEMPTS = 200;
static final int HORIZONTAL_INCREMENT = 1;
static final int VERTICAL_INCREMENT = GRID_LENGTH;

private final int[]grid =new int[GRID_SIZE];
private final Random random = new Random();
private int startupcount =0;

public String getUserInput(String prompt){

        System.out.print(prompt + " :");

        Scanner sc = new Scanner(System.in);

        return sc.nextLine().toLowerCase();


    }

    public ArrayList<String>placeStartup(int startupsize){
    //hold index to grid (0-48)
        int[] startupcoords = new int[startupsize];
        int attempts =0;
        boolean success = false;

        startupcount++;
        int increment = getIncrement();

        while(!success & attempts++ <MAX_ATTEMPTS){
            int location = random.nextInt(GRID_SIZE);

            for(int i=0; i<startupcoords.length; i++){
                startupcoords[i] = location;
                location+=increment;
            }

            if(startupFits(startupcoords , increment)){
                success = coordsAvailable(startupcoords);
            }
        }



        savePositionToGrid(startupcoords);
        ArrayList<String> alphacells = convertCoordsToAlphaFormat(startupcoords);

        return alphacells;
        
    }






    private ArrayList<String> convertCoordsToAlphaFormat(int[] startupcoords) {

    //for each grid coordinate
        //turn it into an "a0" style
        //add to list



              ArrayList<String> alphacells = new ArrayList<String>();
              for(int index : startupcoords){
                  String alphaCoords = getAlphaCoordsFromIndex(index);
                  alphacells.add(alphaCoords);
              }


              //return the "a0-style coords"
              return alphacells;

}

    private String getAlphaCoordsFromIndex(int index) {


    //get row value
        //get numeric column value
        //convert to letter

        int row = calcRowfromIndex(index);
        int column  = index % GRID_LENGTH;
        String letter = ALPHABET.substring(column , column +1);
        return letter + row;

        //end getAlphaCoordsFromIndex




    }

    private void savePositionToGrid(int[] startupcoords) {

          for(int index : startupcoords){
              grid[index] = -1;
          }
    }

    private boolean coordsAvailable(int[] startupcoords) {

    //Check all the potential positions
        //this position already taken

        for (int coords : startupcoords) {
            if (grid[coords] != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean startupFits(int[] startupcoords, int increment) {

    //check end isn't off the bottom

    int finalLocation = startupcoords[startupcoords.length -1];
    if(increment ==HORIZONTAL_INCREMENT){
        //check end is on same row as start
        return calcRowfromIndex(startupcoords[0]) == calcRowfromIndex(finalLocation);


    }else{
        return finalLocation <GRID_SIZE;
    }
    }

    private int calcRowfromIndex(int index) {

    return index/ GRID_LENGTH;

    //end calcRowFromIndex
    }


    private int getIncrement() {

    if(startupcount %2==0){
        return HORIZONTAL_INCREMENT;

    }else {
        return VERTICAL_INCREMENT;
    }
    }

}

package Chapter5.ExtraStrengthMethod;


import java.util.ArrayList;
import java.util.List;

public class SimpleStartup{




    //This is the wrong way because if number has been already hit then why it has been hit again


    private int[] locationcells;
    private int numHits=0;


    public void setLocationcells(int []locs){
        locationcells = locs;
    }


    public String checkYourself(int guess){
        String result ="miss";

        for(int cell : locationcells){
            if(guess ==cell){
                result ="hit" ;

                 numHits++;
                 break;
            }


        }
        if(numHits == locationcells.length){
            result ="kills";
        }

        System.out.println(result);

        return result;


    }


}

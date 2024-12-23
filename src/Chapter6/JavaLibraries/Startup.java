package Chapter6.JavaLibraries;

import java.util.ArrayList;

public class Startup {
//Change the int array to an ArrayList that holds String
    private ArrayList<String> locationcells;

    //private int numofhits
    //don't need to track this now

    public void setLocationcells(ArrayList<String>locs){
        locationcells = locs;
    }
/*
   This is now a String - it needs to accept a value like A3

   New and improved argument name

 */
    public String checkYourself(String userInput){
        String result ="miss";

        //Find out if the user guess is in the Arraylist by asking for its index if it
        //not in the list then indexOf() return a-1
        int index = locationcells.indexOf(userInput);

        if(index >=0){
            locationcells.remove(index);

            if(locationcells.isEmpty()){
                result ="kills";

            }else {
                result ="hit";
            }
        }

        return result;
    }

    public void setName(String name) {
        System.out.println(name);
    }
}

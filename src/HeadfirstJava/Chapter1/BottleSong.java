package HeadfirstJava.Chapter1;

public class BottleSong {

    public static void main(String[] args) {

        int bottlenum =10;
        String word ="bottle";


        while(bottlenum > 0){
            if(bottlenum == 1){
                word ="bottle";
            }

            System.out.println(bottlenum + " green" +  word  + "hanging on the wall");
            System.out.println("And if one green bottle should accidently fall");
            bottlenum = bottlenum -1;

            if(bottlenum > 0){
                System.out.println("There will be " + bottlenum + " green " +  word +  " ,. hanging on the wall");
            }else{
                System.out.println("There will be no green bottle hanging on the wall");
            }
        }
    }
}

package HeadfirstJava.Chapter1;

public class poolPuzzleOne{


    public static void main(String[] args) {
        int x =0;

        while (x <4){
            System.out.println("a");

            if(x > 1){
                System.out.println("");
            }
            System.out.println("n");

            if( x > 1){
                System.out.println("Oyster");
                x = x+2;

            }

            if(x == 1){
                System.out.println("noys");
            }

            if(x < 1){
                System.out.println("oise");
            }

            System.out.println();

            x = x+1;
        }
    }
}

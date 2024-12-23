package HeadfirstJava.Chapter1;

public class CodeManget {

    public static void main(String[] args) {

        int x =3;

        while (x > 0){
            if(x >2){
                System.out.println("a");
            }

            x = x-1;
            System.out.println("-");
            if(x == 2){
                System.out.println("b c");
            }

            if(x ==1){
                System.out.println("c d");
                x = x-1;
            }

        }
    }
}

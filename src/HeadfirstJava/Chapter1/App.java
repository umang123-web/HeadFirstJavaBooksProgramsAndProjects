package HeadfirstJava.Chapter1;

public class App {


    public static void main(String[] args) {

        int x =3;
        String name ="Dark";

        x = x*17;  //51 value

        System.out.println("The value of x is:" + x);

        double d = Math.random();


        while(x > 12){
            x=x-1;


        }

        for(int i=0; i<10; i++){
            System.out.println("the value of i is:" + i);
        }


        if(x == 10){
            System.out.println("x must be 10");
        }else{
            System.out.println("x is not 10");
        }

        if(x > 3 && name.equals("Dark")){
            System.out.println("Gently");
        }else{
            System.out.println("This line run what matter what");
        }
    }
}

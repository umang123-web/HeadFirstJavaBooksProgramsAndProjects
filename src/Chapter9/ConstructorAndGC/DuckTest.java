package Chapter9.ConstructorAndGC;
class Duck{
    private int kilos =6;
    private float floatability = 2.1f;
    private String name ="Generic";
    private long [] feather={1 , 2 , 3 , 4 , 5 , 6 , 7};

    private boolean canFly=true;
    private int maxSpeed =25;



    public Duck(){
        System.out.println("type 1 duck");
    }

    public Duck(boolean fly){
        canFly = fly;
        System.out.println("type 2 duck");
    }

    public Duck(String n , long []f){
        name =n;
        feather = f;
        System.out.println("type 3 duck");
    }


    public Duck(int w , float f){
        kilos = w;
        floatability = f;
        System.out.println("type 4 duck");
    }

    public Duck(float Density , int max){
        floatability = Density;
        maxSpeed = max;
        System.out.println("type 5 duck");
    }
}
public class DuckTest {

    public static void main(String[] args) {
        int weight =8;
        float Density = 2.3f;
         String name = "Donald";
         long[] feather = {1 , 2, 3 , 4, 5, 6};
         boolean canFly = true;
         int airspeed = 22;


         Duck[]d = new Duck[7];
         d[0] = new Duck();
         d[1] = new Duck(Density , weight);
         d[2] = new Duck(name , feather);
         d[3] = new Duck(canFly);
         d[4] = new Duck(3.3F , airspeed);
         d[5] = new Duck(false);
         d[6] = new Duck(airspeed , Density);

    }


}

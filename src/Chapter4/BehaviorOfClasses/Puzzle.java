package Chapter4.BehaviorOfClasses;


class Value{
    int intvalue;

    public int dostuff(int factor){
        if(intvalue > 100){
            return intvalue * factor;
        }else {
            return intvalue *(5-factor);
        }
    }
}



public class Puzzle {

    public static void main(String[] args) {


        Value[]values = new Value[6];


        int number=1;
        int i=0;
        while (i <6){
            values[i] = new Value();
            values[i].intvalue=number;
            i=i+1;

        }

        int result=0;
        i=6;
        while (i >0){
            i=i-1;
            result = result + values[i].dostuff(i);
        }

        System.out.println("result:" + result);

    }
}

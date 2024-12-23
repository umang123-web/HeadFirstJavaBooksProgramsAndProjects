package Chapter9.ConstructorAndGC;

import java.util.ArrayList;

class V2Radiator{
    V2Radiator(ArrayList<SimUnit> test){
        for(int x=0; x<5; x++){
            test.add(new SimUnit("V2Radiator"));
        }
    }
}


class V3Radiator extends  V2Radiator{

    V3Radiator(ArrayList<SimUnit> lglist) {
        super(lglist);
        for(int g=0; g<10; g++){
            lglist.add(new SimUnit("V3 Radiator"));
        }
    }
}


class RetentionBot{
    RetentionBot(ArrayList<SimUnit> rlist){
        rlist.add(new SimUnit("Retention"));
    }
}


class SimUnit{

    String botType;
    SimUnit(String type){
        botType = type;
    }

    int poweruse(){
        if("Retention".equals(botType)){
            return 2;
        }else {
            return 4;
        }
    }
}
public class TestLifeSupportSim {
    public static void main(String[] args) {

        ArrayList<SimUnit> alist = new ArrayList<>();
        V2Radiator v2 = new V2Radiator(alist);
        V3Radiator v3 = new V3Radiator(alist);
        for(int z =0; z<20; z++){
            RetentionBot bot = new RetentionBot(alist);
        }

    }


}

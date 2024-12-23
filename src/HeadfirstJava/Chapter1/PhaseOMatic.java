package HeadfirstJava.Chapter1;

import java.util.Random;

public class PhaseOMatic {

    public static void main(String[] args) {

        String[] wordListOne = {"agnostic", "opinionated",
                "voice activated", "haptically driven", "extensible",
                "reactive", "agent based", "functional", "AI enabled",
                "strongly typed"};
        String[] wordListTwo = {"loosely coupled", "six sigma",
                "asynchronous", "event driven", "pub-sub", "IoT", "cloud native"};
        String[] wordListThree = {"framework", "library",
                "DSL", "REST API", "repository", "pipeline", "service mesh", "architecture", "perspective", "design",
                "orientation"};



        int onelength = wordListOne.length;
        int twolength = wordListTwo.length;
        int threelength = wordListThree.length;

        System.out.println(onelength + ',' + twolength + ','  + threelength);



        java.util.Random randomGenerator = new java.util.Random();

        int rand1 = randomGenerator.nextInt(onelength);
        int rand2 = randomGenerator.nextInt(twolength);
        int rand3 = randomGenerator.nextInt(threelength);

        String phase = wordListOne[rand1]  + " " + wordListTwo[rand2] + " " + wordListThree[rand3];


        System.out.println("what we need is:" + phase);
    }
}

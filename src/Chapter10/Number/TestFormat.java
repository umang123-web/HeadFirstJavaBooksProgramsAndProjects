package Chapter10.Number;

public class TestFormat {


    /*
    Take the second argument to this method, and format it
as a decimal integer and insert commas.
     */
    public static void main(String[] args) {
        long mybillion = 1_000_000_000;

        String s = String.format("% ,d " ,  mybillion);
        System.out.println(s);
    }
}

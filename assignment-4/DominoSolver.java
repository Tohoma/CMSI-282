import java.util.Hashtable;
import java.util.Arrays;
public class DominoSolver {

    static Hashtable <String,Integer> lookupTable;
    static int value;

    public static void main(String args[]) {
        int input [] = new int[args.length];
        String name = "";
        lookupTable = new Hashtable<String, Integer>();
        Domino dominoList [] = new Domino[args.length/2];
        value = 0;
        if (args.length == 0 || args.length % 2 != 0) {
            throw new IllegalArgumentException();
        }

        int counter = 0;
        for(int i = 0; i<args.length; i+=2) {
            dominoList[counter] = new Domino(Integer.valueOf(args[i]),Integer.valueOf(args[i+1]));

            if(i>0 && i!= args.length) {
                if(dominoList[counter-1].getSecond() != dominoList[counter].getFirst()) {
                    throw new IllegalArgumentException();
                }
            }
            counter++;
        }

        System.out.println(maxValue(dominoList));
    }

    static int maxValue (Domino input []) {
        String key = "";
        for(int i=0; i< input.length; i++) {
            key+= input[i].toString();
        }
        if (lookupTable.get(key) != null) {
            return lookupTable.get(key);
        }
        if(input.length == 2) {
            return input[0].getFirst() * input[0].getSecond() * input[1].getSecond();
        } else {
            value = max(maxValue(Arrays.copyOfRange(input,0,input.length-1))
                            + input[0].getFirst() * input[input.length - 1].getSecond() * input[input.length - 1].getFirst(),
                    maxValue(Arrays.copyOfRange(input,1,input.length))
                            + input[0].getFirst() * input[0].getSecond() *
                            input[input.length-1].getSecond());
            lookupTable.put(key,value);
        }
        return value;
    }

    static int max (int x, int y){
        if(x> y){
            return x;
        } else {
            return y;
        }
    }
}

class Domino {
    private int first;
    private int second;
    public Domino(int first, int second) {
        this.first = first;
        this.second = second;
    }
    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }


    @Override
    public String toString() {
        return Integer.toString(first) + Integer.toString(second);
    }
}
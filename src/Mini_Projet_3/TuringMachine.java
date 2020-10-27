package Mini_Projet_3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TuringMachine {
    public static int MAX_STATE_NUMBER ;
    private ArrayList<State> states ;
    private char[] inputAlphabet ;
    private char[] outputAlphabet ;
    private char[] entrance ;
    private int headLectureIsAt = 1;
    private int currentState = 0 ;
    private boolean done = false ;



    public TuringMachine(String path,String entrance) throws IOException {




        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

        this.entrance = ("B"+entrance+"B").toCharArray();

        MAX_STATE_NUMBER = Integer.valueOf(br.readLine());
        this.states = new ArrayList<>();

        for(int index = 0 ; index < MAX_STATE_NUMBER; index++){
            if(index == MAX_STATE_NUMBER-1)
                this.states.add(new State(index,true));
            else
                this.states.add(new State(index,false));
        }
        this.states.add(new State(MAX_STATE_NUMBER,true));

        String[] alphabet = br.readLine().split(" ");
        inputAlphabet = new char[alphabet.length];

        for(int index = 0 ; index < alphabet.length; index++){
            inputAlphabet[index] = alphabet[index].charAt(0);
        }

        alphabet = br.readLine().split(" ");
        outputAlphabet = new char[alphabet.length];

        for(int index = 0 ; index < alphabet.length; index++){
            outputAlphabet[index] = alphabet[index].charAt(0);
        }

        String readLine ;
        while((readLine = br.readLine())!= null){

            String[] line = readLine.split(" ");
            int inputState = Integer.valueOf(line[0]);
            int outputState = Integer.valueOf(line[1]);
            char intputChar = line[2].charAt(0);
            char outputChar = line[3].charAt(0);
            char direction = line[4].charAt(0);

            this.states.get(inputState).addTransition(inputState,outputState,intputChar,outputChar,direction);
        }
        states.add(new State(-1,true));
    }
    public void print(){
        for(State state : states)
            state.print();
    }

    public void printRuban() {
        for (char character : entrance)
            System.out.print(character + "|");

        System.out.println(" current state : "+ states.get(currentState).number);

        for (int index = 0; index < entrance.length; index++) {
            if (headLectureIsAt == index) System.out.print("^");
            else System.out.print("  ");
        }
        System.out.println(" ");
    }
    public boolean startMachine(){

        while(!done || currentState == -1){
            printRuban();
            Transition transition = states.get(currentState).switchState(entrance[headLectureIsAt]);
            currentState = transition.getOutputState() ;
            done = states.get(currentState).done ;
            entrance[headLectureIsAt] = transition.getOutputChar();
            headLectureIsAt = transition.getDirection() == Transition.Direction.RIGHT ? headLectureIsAt+1 : headLectureIsAt-1 ;


        }
        printRuban();
        return currentState == MAX_STATE_NUMBER ;

    }

    public static void main(String arg[]) throws IOException {
        TuringMachine t = new TuringMachine(arg[0],arg[1]);
        System.out.println(t.inputAlphabet);
        System.out.println(t.outputAlphabet);
        t.print();
        System.out.println(t.entrance);
        System.out.println(t.startMachine());
    }
}

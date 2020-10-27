package Mini_Projet_3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TuringMachine {
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

        int states = Integer.valueOf(br.readLine());
        this.states = new ArrayList<>();

        for(int index = 0 ; index < states; index++){
            if(index == states-1)
                this.states.add(new State(index,true));
            else
                this.states.add(new State(index,false));
        }

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
        this.states.add(new State(states,true)); // A voir

    }
    public void print(){
        for(State state : states)
            state.print();
    }

    public void startMachine(){

        while(!done){

        }


    }

    public static void main(String arg[]) throws IOException {
        TuringMachine t = new TuringMachine(arg[0],arg[1]);
        System.out.println(t.inputAlphabet);
        System.out.println(t.outputAlphabet);
        t.print();
        System.out.println(t.entrance);

    }
}

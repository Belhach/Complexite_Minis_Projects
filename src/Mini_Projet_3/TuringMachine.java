package Mini_Projet_3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TuringMachine {
    public static int MAX_STATE_NUMBER;
    private final ArrayList<State> states;
    private final char[] inputAlphabet;
    private final char[] outputAlphabet;
    private final char[] band;
    private int headLectureIsAt = 1;
    private int currentState = 0;
    private boolean done = false;

    public TuringMachine(String path, String word) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        this.band = constructBand(word);
        MAX_STATE_NUMBER = Integer.parseInt(br.readLine());


        initStates();

        String[] alphabet = br.readLine().split(" ");
        inputAlphabet = initAlphabet(alphabet);

        alphabet = br.readLine().split(" ");
        outputAlphabet = initAlphabet(alphabet);

        this.states = new ArrayList<>();
        createAndAddStates(br);
    }

    private void createAndAddStates(BufferedReader br) throws IOException {
        String readLine;
        while ((readLine = br.readLine()) != null) {
            String[] line = readLine.split(" ");

            int inputState = Integer.parseInt(line[0]);
            int outputState = Integer.parseInt(line[1]);

            char inputChar = line[2].charAt(0);
            char outputChar = line[3].charAt(0);
            char direction = line[4].charAt(0);

            this.states.get(inputState).addTransition(inputState, outputState, inputChar, outputChar, direction);
        }
        states.add(new State(-1, true));
    }

    private char[] initAlphabet(String[] alphabet) {
        final char[] inputAlphabet;
        inputAlphabet = new char[alphabet.length];

        for (int index = 0; index < alphabet.length; index++) {
            inputAlphabet[index] = alphabet[index].charAt(0);
        }
        return inputAlphabet;
    }

    private void initStates() {
        for (int index = 0; index < MAX_STATE_NUMBER; index++) {
            if (index == MAX_STATE_NUMBER - 1)
                this.states.add(new State(index, true));
            else
                this.states.add(new State(index, false));
        }

        this.states.add(new State(MAX_STATE_NUMBER, true));
    }


    private char[] constructBand(String word) {
        return ("B" + word + "B").toCharArray();
    }

    public void print() {
        for (State state : states)
            state.print();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(State state : states){
            s.append(state);
        }
        return new String(s);
    }

    public void printRuban() {
        for (char character : band) {
            System.out.print(character + "|");
        }

        System.out.println(" current state : " + states.get(currentState).number);

        for (int index = 0; index < band.length; index++) {
            if (headLectureIsAt == index) System.out.print("^");
            else System.out.print("  ");
        }
        System.out.println(" ");
    }

    public boolean startMachine() {
        while (!done || currentState == -1) {
            printRuban();
            Transition transition = states.get(currentState).switchState(band[headLectureIsAt]);
            currentState = transition.getOutputState();
            done = states.get(currentState).isFinalState;
            band[headLectureIsAt] = transition.getOutputChar();
            headLectureIsAt = transition.getDirection() == Direction.RIGHT ? headLectureIsAt + 1 : headLectureIsAt - 1;
        }

        printRuban();
        return currentState == MAX_STATE_NUMBER;
    }

    public static void main(String[] args) throws IOException {
        TuringMachine t = new TuringMachine(args[0], args[1]);
        System.out.println(t.inputAlphabet);
        System.out.println(t.outputAlphabet);
        System.out.println(t);
        System.out.println(t.band);
        System.out.println(t.startMachine());
    }
}

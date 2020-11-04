package Mini_Projet_3;

/*****************************************************************************************************************************************************************************
*    ____            ____     _     __          _     _         ______     _________       ________              _     _____________    _______________     _____________    *
*   | |\ \          / /| |   | |   | \ \       | |   | |       |  ___ \    |  _____ |     / ______ \            | |   |  ___________|  |______   ______|   |___________  |   *
*   | | \ \        / / | |   | |   | |\ \      | |   | |       | |   \ \   | |    / /    / /      \ \           | |   | |                     | |                     |  |   *
*   | |  \ \      / /  | |   | |   | | \ \     | |   | |       | |   / /   | |   / /    / /        \ \          | |   | |                     | |                     |  |   *
*   | |   \ \    / /   | |   | |   | |  \ \    | |   | |       | |  / /    | |__/ /    / /          \ \         | |   | |___________          | |           __________|  |   *
*   | |    \ \  / /    | |   | |   | |   \ \   | |   | |       | |_/ /     |  _  /    / /            \ \   __   | |   |  ___________|         | |          |___________  |   *
*   | |     \ \/ /     | |   | |   | |    \ \  | |   | |       |  __/      | | \ \    \ \            / /   \ \  | |   | |                     | |                     |  |   *
*   | |      \__/      | |   | |   | |     \ \ | |   | |       | |         | |  \ \    \ \          / /     \ \ | |   | |                     | |                     |  |   *
*   | |                | |   | |   | |      \ \| |   | |       | |         | |   \ \    \ \________/ /       \ \| |   | |___________          | |           __________|  |   *
*   |_|                |_|   |_|   |_|       \_ _|   |_|       |_|         |_|    \_\    \__________/         \___|   |_____________|         |_|          |_____________|   *
*                                                                                                                                                                            *
*****************************************************************************************************************************************************************************/



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TuringMachine {
    public static int MAX_STATE_NUMBER;
    private final ArrayList<NodeState> nodeStates;
    private final char[] inputAlphabet;
    private final char[] outputAlphabet;
    private final char[] band;
    private TypeState typeState;

    public TuringMachine(String path, String word) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        this.band = constructBand(word);
        MAX_STATE_NUMBER = Integer.parseInt(br.readLine());


        String[] alphabet = br.readLine().split(" ");
        inputAlphabet = initAlphabet(alphabet);

        alphabet = br.readLine().split(" ");
        outputAlphabet = initAlphabet(alphabet);

        String[] qOuiAndQNonIndexes = br.readLine().split(" ");
        int qOuiIndex = Integer.parseInt(qOuiAndQNonIndexes[0]);
        int qNonIndex = Integer.parseInt(qOuiAndQNonIndexes[1]);
        this.nodeStates = new ArrayList<>();
        initStates(qOuiIndex, qNonIndex);
        createAndAddStates(br);
        this.typeState = this.nodeStates.get(0).typeState;
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

            this.nodeStates.get(inputState).addTransition(inputState, outputState, inputChar, outputChar, direction);
        }
    }

    private char[] initAlphabet(String[] alphabet) {
        final char[] inputAlphabet;
        inputAlphabet = new char[alphabet.length];

        for (int index = 0; index < alphabet.length; index++) {
            inputAlphabet[index] = alphabet[index].charAt(0);
        }
        return inputAlphabet;
    }

    private void initStates(int qOuiIndex, int qNonIndex) {
        for (int index = 0; index < MAX_STATE_NUMBER; index++) {
            if (index == qOuiIndex) {
                this.nodeStates.add(new NodeState(index, TypeState.Q_OUI));
            } else if (index == qNonIndex) {
                this.nodeStates.add(new NodeState(index, TypeState.Q_NON));
            } else {
                this.nodeStates.add(new NodeState(index, TypeState.NORMAL));
            }
        }
    }


    private char[] constructBand(String word) {
        return ("B" + word + "B").toCharArray();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (NodeState nodeState : nodeStates) {
            s.append(nodeState);
        }
        return new String(s);
    }

    public void printRuban() {
        for (char character : band) {
            System.out.print(character + "|");
        }

        System.out.println(" current state : " + nodeStates.get(currentState).number);

        for (int index = 0; index < band.length; index++) {
            if (headLectureIsAt == index) System.out.print("^");
            else System.out.print("  ");
        }
        System.out.println(" ");
    }

    private int headLectureIsAt = 1;
    private int currentState = 0;

    public boolean startMachine() {
        while (typeState == TypeState.NORMAL) {
            printRuban();
            Transition transition = nodeStates.get(currentState).switchState(band[headLectureIsAt]);
            currentState = transition.getOutputState();
            typeState = nodeStates.get(currentState).typeState;
            band[headLectureIsAt] = transition.getOutputChar();
            headLectureIsAt = transition.getDirection() == Direction.RIGHT ? headLectureIsAt + 1 : headLectureIsAt - 1;
        }

        return typeState == TypeState.Q_OUI;
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

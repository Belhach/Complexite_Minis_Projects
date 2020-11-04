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
    private ArrayList<State> states ;
    private char[] inputAlphabet ;
    private char[] outputAlphabet ;

    // ceci est un com
    public TuringMachine() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/mhamedbelhachemi/Desktop/Home/amu/M1/S1/Complexité/Complexite_Minis_Projects/src/Mini_Projet_3/machine1")));

        int states = Integer.valueOf(br.readLine());
        this.states = new ArrayList<>();

        for(int index = 0 ; index < states; index++){
            this.states.add(new State(index));
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
            String[] cell = readLine.split(" ");
            int inputState = Integer.valueOf(cell[0]);
            int outputState = Integer.valueOf(cell[1]);
            char intputChar = cell[2].charAt(0);
            char outputChar = cell[3].charAt(0);
            char direction = cell[4].charAt(0);

            this.states.get(inputState).addTransition(inputState,outputState,intputChar,outputChar,direction);
        }

    }
    public void print(){
        for(State state : states)
            state.print();
    }

    public static void main(String arg[]) throws IOException {
        TuringMachine t = new TuringMachine();
        System.out.println(t.inputAlphabet);
        System.out.println(t.outputAlphabet);
        t.print();
        System.out.println("a");

    }
}

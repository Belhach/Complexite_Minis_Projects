package Mini_Projet_3;

import java.util.HashMap;

public class State {
    HashMap<Character,Transition> transitions  ;
    int number ;
    boolean isFinalState;

    public State(int number , boolean isFinalState){
        transitions = new HashMap<>();
        this.number = number ;
        this.isFinalState = isFinalState;
    }

    public void addTransition(int inputState, int outputState, char inputChar, char outputChar, char direction){
        transitions.put(inputChar,new Transition(inputState,outputState,inputChar,outputChar,direction));
    }

    public void print(){
        for(Character c : transitions.keySet())
            transitions.get(c).print();
    }

    public Transition switchState(char character){
        return transitions.containsKey(character) ?
                transitions.get(character) :
                // Créer une transition vers Qnon
                new Transition(number, TuringMachine.MAX_STATE_NUMBER, character,character,'R');
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("finale = ")
                .append(isFinalState)
                .append("(Q")
                .append(number)
                .append(")").append("\n");

        for(Character c: transitions.keySet()){
            s.append(transitions.get(c));
            s.append("\n");
        }
        return new String(s);
    }
}

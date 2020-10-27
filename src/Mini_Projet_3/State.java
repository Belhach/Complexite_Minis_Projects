package Mini_Projet_3;

import java.util.ArrayList;
import java.util.HashMap;

public class State {

    HashMap<Character,Transition> transitions  ;
    int number ;
    boolean done ;

    public State(int number , boolean done ){
        transitions = new HashMap<>();
        this.number = number ;
        this.done = done ;
    }

    public void addTransition(int inputState, int outputState, char inputChar, char outputChar, char direction){
        transitions.put(inputChar,new Transition(inputState,outputState,inputChar,outputChar,direction));
    }

    public void print(){
        for(Character c : transitions.keySet())
            transitions.get(c).print();
    }
    public Transition switchState(char character){

        return transitions.containsKey(character) ? transitions.get(character) : new Transition(number, TuringMachine.MAX_STATE_NUMBER, character,character,'R');
    }
}

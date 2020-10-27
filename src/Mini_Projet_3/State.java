package Mini_Projet_3;

import java.util.ArrayList;

public class State {

    ArrayList<Transition> transitions ;
    int number ;
    boolean done ;
    public State(int number , boolean done ){
        transitions = new ArrayList<>();
        this.number = number ;
        this.done = done ;
    }

    public void addTransition(int inputState, int outputState, char inputChar, char outputChar, char direction){
        transitions.add(new Transition(inputState,outputState,inputChar,outputChar,direction));
    }

    public void print(){
        for(Transition transition : transitions)
            transition.print();
    }
}

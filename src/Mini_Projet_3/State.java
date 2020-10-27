package Mini_Projet_3;

import java.util.ArrayList;

public class State {

    ArrayList<Transition> transitions ;
    int number ;
    public State(int number ){
        transitions = new ArrayList<>();
        this.number = number ;
    }

    public void addTransition(int inputState, int outputState, char inputChar, char outputChar, char direction){
        transitions.add(new Transition(inputState,outputState,inputChar,outputChar,direction));
    }

    public void print(){
        for(Transition transition : transitions)
            transition.print();
    }
}

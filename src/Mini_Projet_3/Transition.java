package Mini_Projet_3;

public class Transition {

    private int inputState , outputState ;
    private char inputChar, outputChar ;
    private Direction direction ;
    enum Direction {RIGHT , LEFT}

    public Transition(int inputState, int outputState, char inputChar, char outputChar, char direction) {
        this.inputState = inputState;
        this.outputState = outputState;
        this.inputChar = inputChar;
        this.outputChar = outputChar;
        this.direction = direction == 'R' ? Direction.RIGHT : Direction.LEFT ;
    }

    public void print(){

        System.out.println("( "+inputState+" , "+inputChar+") -> ( "+outputState+" , "+outputChar+" , "+ direction+ " )");
    }
}

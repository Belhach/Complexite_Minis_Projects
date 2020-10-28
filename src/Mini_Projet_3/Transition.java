package Mini_Projet_3;

enum Direction {
    RIGHT,
    LEFT

}

public class Transition {

    private final int inputState , outputState ;
    private final char inputChar, outputChar ;
    private final Direction direction ;


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

    public int getInputState() {
        return inputState;
    }

    public int getOutputState() {
        return outputState;
    }

    public char getInputChar() {
        return inputChar;
    }

    public char getOutputChar() {
        return outputChar;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "( "+inputState+" , "+inputChar+") -> ( "+outputState+" , "+outputChar+" , "+ direction+ " )";
    }
}

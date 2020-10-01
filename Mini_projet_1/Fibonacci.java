/*******************************************************************************************************************************************************************
*    ____            ____     _     __          _     _         ______     _________       ________              _     _____________    _______________     ___    *
*   | |\ \          / /| |   | |   | \ \       | |   | |       |  ___ \    |  _____ |     / ______ \            | |   |  ___________|  |______   ______|   /   |   *
*   | | \ \        / / | |   | |   | |\ \      | |   | |       | |   \ \   | |    / /    / /      \ \           | |   | |                     | |         / /| |   *
*   | |  \ \      / /  | |   | |   | | \ \     | |   | |       | |   / /   | |   / /    / /        \ \          | |   | |                     | |        /_/ | |   *
*   | |   \ \    / /   | |   | |   | |  \ \    | |   | |       | |  / /    | |__/ /    / /          \ \         | |   | |___________          | |            | |   *
*   | |    \ \  / /    | |   | |   | |   \ \   | |   | |       | |_/ /     |  _  /    / /            \ \   __   | |   |  ___________|         | |            | |   *
*   | |     \ \/ /     | |   | |   | |    \ \  | |   | |       |  __/      | | \ \    \ \            / /   \ \  | |   | |                     | |            | |   *
*   | |      \__/      | |   | |   | |     \ \ | |   | |       | |         | |  \ \    \ \          / /     \ \ | |   | |                     | |            | |   *
*   | |                | |   | |   | |      \ \| |   | |       | |         | |   \ \    \ \________/ /       \ \| |   | |___________          | |            | |   *
*   |_|                |_|   |_|   |_|       \_ _|   |_|       |_|         |_|    \_\    \__________/         \___|   |_____________|         |_|            |_|   *
*                                                                                                                                                                  *
*******************************************************************************************************************************************************************/

class Fibonacci{

    //iterative version of fibonacci
    public int fibonacciIterative(int n){
        if(n <= 1) return n;
        int fib = 1;
        int previousFib = 1;

        for(int index=2;index<n;index++){
            int temp = fib;
            fib+=previousFib;
            previousFib = temp;
        }
        return fib;
    }

    //recursive version of fibonacci
    public int fibonacciRecursive(int n){
        if(n <= 1) return n;
        return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
    }

    public void fibonacci(int n, String version){
        if(version == "iterative"){
            System.out.println("la version iterative pour la valeur "+n+" retourne le resultat : "+fibonacciIterative(n));
        }
        else if(version == "recursive"){
            System.out.println("la version iterative pour la valeur "+n+" retourne le resultat : "+fibonacciRecursive(n));
        }           
    }
}

class Main{
    public static void main(String[] args) {

        Fibonacci fibonacci = new Fibonacci();
        fibonacci.fibonacci(10,"iterative");

    }
}
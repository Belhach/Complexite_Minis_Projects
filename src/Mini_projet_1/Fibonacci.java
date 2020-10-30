package Mini_projet_1;

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

public class Fibonacci{

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

    public void fibonacci(int n){
              
            // Calcule de temps pour la version iterative
            long tempsDebut = System.nanoTime();
            System.out.println("la version iterative pour la valeur "+n+" retourne le resultat : "+fibonacciIterative(n));
            long tempsFin = System.nanoTime();
            float seconds = (tempsFin - tempsDebut) / 1000F;
            System.out.println("Temps d'execution pout la version iteeative: "+ Float.toString(seconds) + " nano secondes.");

            // Calcule de temps pour la version recursive
            long tempsDebut1 = System.nanoTime();
            System.out.println("la version iterative pour la valeur "+n+" retourne le resultat : "+fibonacciRecursive(n));
            long tempsFin1 = System.nanoTime();
            float seconds1 = (tempsFin1 - tempsDebut1) / 1000F;
            System.out.println("Temps d'execution pout la version recursive: "+ Float.toString(seconds1) + " nano secondes.");
    }

    public static void main(String[] args) {

        Fibonacci fibonacci = new Fibonacci();
        if(args.length == 1){
            fibonacci.fibonacci(Integer.parseInt(args[0]));
        }
        else{
            System.out.println("Pour lancer le programme il faut fournir un argument \n arg[0] = nombre qu'on veut calculer");
            System.out.println("l'execution doit etre sous la forme \"java Fibonacci.java arg[0]\"");
        }

    }
}
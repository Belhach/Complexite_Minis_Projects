package Mini_Projet_2;

/*****************************************************************************************************************************************************************************
*    ____            ____     _     __          _     _         ______     _________       ________              _     _____________    _______________       _______        * 
*   | |\ \          / /| |   | |   | \ \       | |   | |       |  ___ \    |  _____ |     / ______ \            | |   |  ___________|  |______   ______|     / ____  \       *
*   | | \ \        / / | |   | |   | |\ \      | |   | |       | |   \ \   | |    / /    / /      \ \           | |   | |                     | |           / /     \ \      *
*   | |  \ \      / /  | |   | |   | | \ \     | |   | |       | |   / /   | |   / /    / /        \ \          | |   | |                     | |           \ \____ / /      *
*   | |   \ \    / /   | |   | |   | |  \ \    | |   | |       | |  / /    | |__/ /    / /          \ \         | |   | |___________          | |            \_____  /       *
*   | |    \ \  / /    | |   | |   | |   \ \   | |   | |       | |_/ /     |  _  /    / /            \ \   __   | |   |  ___________|         | |                 / /        *
*   | |     \ \/ /     | |   | |   | |    \ \  | |   | |       |  __/      | | \ \    \ \            / /   \ \  | |   | |                     | |                / /         *
*   | |      \__/      | |   | |   | |     \ \ | |   | |       | |         | |  \ \    \ \          / /     \ \ | |   | |                     | |               / /          *
*   | |                | |   | |   | |      \ \| |   | |       | |         | |   \ \    \ \________/ /       \ \| |   | |___________          | |              / /_______    *
*   |_|                |_|   |_|   |_|       \_ _|   |_|       |_|         |_|    \_\    \__________/         \___|   |_____________|         |_|             /__________|   * 
*                                                                                                                                                                            *
*****************************************************************************************************************************************************************************/



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Graph {
    private int[][] matrix ;
    private ArrayList<ArrayList<Integer>> listAdj;
    private int n ;
    private ArrayList<Integer> removedVertexes = new ArrayList<>();




    private Graph() throws IOException { // Le constructeur consiste à lire le fichier puis implémente les données du graph sous
                                        // forme matrices ainsi que sous forme d'adjascence
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\hak72\\IdeaProjects\\Complexite_Minis_Projects\\src\\Mini_Projet_2\\matrix")));

        n =Integer.valueOf( br.readLine());
        matrix = new int[n][n];
        listAdj = new ArrayList<>(n);

        int counter = 0 ;
        while(br.ready()){
            String[] line = br.readLine().split(" ");
            listAdj.add(new ArrayList<>());
            for(int index= 0 ; index < n ; index++){
                int s = Integer.valueOf(line[index]);
                if( s == 1) listAdj.get(counter).add(index);

                matrix[counter][index] = s;
            }
            counter++;
        }

    }
    public void printMatrix(){
        for(int i = 0 ; i < matrix.length ; i++){
            for(int j = 0 ; j < matrix[i].length; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println("\n");
    }
    public void printList(){
        for(int i = 0 ; i < listAdj.size() ; i++){
            System.out.print(i+" : ");
            for(int s : listAdj.get(i)){
                System.out.print(s + " ");
            }
            System.out.println(" ");
        }
        System.out.println("\n");
    }

    public void removeVertex(Integer s){// Retire le sommet s du graph avec une compléxité θ(1)
        if(removedVertexes.contains(s)) return ;
        removedVertexes.add(s);

    }

    public boolean isEmptyZone(ArrayList<Integer> x){ ///////QUESTION 1
        if(x.size() == 1) return true ;
        for(int i : x){  ///// |X| itérations
            for(int j : x ){ ////// |X| itérations

                if(j != i && matrix[i][j] == 1)
                    return false ;
            }
        }
        return true ;  ////// on en déduit que cet algorithme appartient à  θ( |X|*|X| ) = θ( |X|^2 )
    }

    public ArrayList<Integer> maximalEmptyZone(){ ////////////QUESTION 2 dans cet algorithme, le pire des cas serait
                                                  ////////////lorsque le graph ne contient aucune arête

        ArrayList<Integer> maximalEmptyZone = new ArrayList<>();

        for(Integer index= 0 ; index < listAdj.size() ; index++){ ////// N itérations

            if( !removedVertexes.contains(index)){

                maximalEmptyZone.add(index);
                if(!isEmptyZone(maximalEmptyZone))// Le pire cas serait si maximalEmptyZone = "tous les sommets du graph" donc => θ(N^2)
                    maximalEmptyZone.remove(index);

            }
        }
        return maximalEmptyZone ;////Donc au cours des N itérations, nous obtient comme compléxité θ( 1^2 + 2^2 + ... + N^2)
                                 ////On en déduit que cet algorithme appartient à θ( N * N^2 ) = θ(N^3)
    }

    public ArrayList<Integer> maximumEmptyZone(){//Cet algorithme consiste à énumérer toutes les zones vides maximales du graphe en utilisant
                                                // l'algorithme précédent, puis comparer leurs taille et
                                                //renvoyer la zone vide maximale la plus grande.


        //Le pire des cas dans cet algorithme serait que tous les sommets soient reliés entre eux,
        // ce qui ferait qu'il y a autant de zone vide maximale que de sommet dans le graph
        ArrayList<Integer> maximumEmptyZone = new ArrayList<>();
        while(removedVertexes.size() != n){// on aura donc déja, N Itérations dans ce 'while' => θ(N)

            ArrayList<Integer> maximalEmptyZone = this.maximalEmptyZone(); // θ(N^2)


            if(maximalEmptyZone.size() > maximumEmptyZone.size())
                maximumEmptyZone = maximalEmptyZone;

            for(int index = 0 ; index < maximalEmptyZone.size(); index++){// N Itérations => θ(N)

                this.removeVertex(maximalEmptyZone.get(index));

            }

        }

        removedVertexes = new ArrayList<>();

        return maximumEmptyZone ;   // On obtient comme compléxité : θ(N*N*N^2) = θ(N^4)

    }


    public ArrayList<Integer> optimized_maximal_zone(){// La différence entre cette algorithme ainsi que celui de la question 2 est que celui ci commence
                                                        // d'abord par ajouter dans notre ensemble de zone vide le sommet avec le moins d'aretes possible,
        //                                              ce qui augmenterai nos chances de pouvoir rajouter encore plus de sommets dans notre ensemble.
        ArrayList<Integer> maximalEmptyZone = new ArrayList<>();
        int vertexWithMinimumEdges = 0 ;
        for(int index = 1 ; index < n ; index++){ // N Itérations, donc => θ(N)
            int numberVertexes = listAdj.get(index).size();

            if(listAdj.get(vertexWithMinimumEdges).size() > numberVertexes)
                vertexWithMinimumEdges = index ;
        }

        maximalEmptyZone.add(vertexWithMinimumEdges);

        for(Integer index= 0 ; index < listAdj.size() ; index++){ ////// N itérations

            if(listAdj.get(index).size() != n && index != vertexWithMinimumEdges){

                maximalEmptyZone.add(index);
                if(!isEmptyZone(maximalEmptyZone)) // Le pire cas serait si maximalEmptyZone = "tous les sommets du graph" donc => θ(N^2)
                    maximalEmptyZone.remove(index);

            }
        }
        //On en déduit la complexité suivante : θ(N * N^2 + N) = θ(N^3)  ( Soit la même compléxité que l'algorithme numero 2, mais en potentiellement plus efficace)
        return maximalEmptyZone ;
    }


    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        graph.printList();
        graph.printMatrix();
        System.out.println(graph.maximumEmptyZone());
        System.out.println(graph.optimized_maximal_zone());
        System.out.println(graph.maximalEmptyZone());


    }
}


package Mini_Projet_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Graph {
    private int[][] matrix ;
    private ArrayList<ArrayList<Integer>> listAdj;
    private int n ;

    public Graph(int n){
        matrix= new int[n][n] ;
    }


    private Graph() throws IOException {
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

    public boolean isEmptyZone(ArrayList<Integer> x){ ///////QUESTION 1
        if(x.size() == 1) return true ;
        for(int i : x){  ///// |X| itérations
            for(int j : x ){ ////// |X| itérations

                if(j != i && matrix[i][j] == 1)
                    return false ;
            }
        }
        return true ;  ////// on en déduit que cet algorithme appartient à  θ( |X|^2 )
    }

    public ArrayList<Integer> maximalEmptyZone(){ ////////////QUESTION 2 dans cet algorithme, le pire des cas serait
                                                  ////////////lorsque le graph ne contient aucune arête

        ArrayList<Integer> maximalEmptyZone = new ArrayList<>();
        for(Integer index= 0 ; index < listAdj.size() ; index++){ ////// |S| itérations

            if(listAdj.get(index).size() != n){
                System.out.println(index);
                maximalEmptyZone.add(index);
                if(!isEmptyZone(maximalEmptyZone))
                    maximalEmptyZone.remove(index);

            }
        }
        return maximalEmptyZone ;////Donc au cours des |S| itérations, nous obtient comme compléxité θ( 1^2 + 2^2 + ... + |S|^2)
                                 ////On en déduit que cet algorithme appartient à θ( |S|^2 )
    }





    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        graph.printList();
        graph.printMatrix();
        ArrayList<Integer> vide = new ArrayList<>();
        vide.add(1);
        vide.add(2);
        System.out.println(graph.isEmptyZone(vide));
        System.out.println(graph.maximalEmptyZone());

    }
}


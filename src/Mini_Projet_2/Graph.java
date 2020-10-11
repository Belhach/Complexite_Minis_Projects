package Mini_Projet_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Graph {
    private int[][] matrix ;
    private ArrayList<ArrayList<Integer>> listMatrix;
    private int n ;

    public Graph(int n){
        matrix= new int[n][n] ;
    }


    private Graph() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\hak72\\IdeaProjects\\Complexite_Minis_Projects\\src\\Mini_Projet_2\\matrix")));





        n =Integer.valueOf( br.readLine());
        matrix = new int[n][n];
        listMatrix = new ArrayList<>(n);

        int counter = 0 ;
        while(br.ready()){
            String[] line = br.readLine().split(" ");
            listMatrix.add(new ArrayList<>());
            for(int index= 0 ; index < n ; index++){
                int s = Integer.valueOf(line[index]);
                if( s == 1)listMatrix.get(counter).add(index);

                matrix[counter][index] = s;
            }
            counter++;
        }

    }
    public static boolean isEmptyZone(int[][] matrix,ArrayList<Integer> x){ ///////QUESTION 1
        for(int i : x){
            for(int j : x ){

                if(j != i && matrix[i][j] == 1)
                    return false ;
            }
        }
        return true ;
    }

    public static ArrayList<Integer> maximalEmptyZone(){
        return null ;
    }
    public static void printMatrix(int[][] matrix){
        for(int i = 0 ; i < matrix.length ; i++){
            for(int j = 0 ; j < matrix[i].length; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println("\n");
    }
    public static void printList(ArrayList<ArrayList<Integer>> listMatrix){
        for(int i = 0 ; i < listMatrix.size() ; i++){
            System.out.print(i+" : ");
            for(int s : listMatrix.get(i)){
                System.out.print(s + " ");
            }
            System.out.println(" ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        printList(graph.listMatrix);
        printMatrix(graph.matrix);
        ArrayList<Integer> vide = new ArrayList<>();
        vide.add(1);
        vide.add(2);
        System.out.println(isEmptyZone(graph.matrix,vide));


    }
}


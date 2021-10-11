package movieSelector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

class BinarySearchTree { 
  
   
    class Node { 
        String[] key; 
        Node left, right; 
  
        public Node(String[] item) { 
            key = item; 
            left = right = null; 
        } 
    } 

    Node root; 
 
    BinarySearchTree() {  
        root = null;  
    } 
  
    void insert(String[] movie) { 
       root = insertRec(root, movie); 
    } 
    
    Node insertRec(Node root, String[] movie) {  
        if (root == null) { 
            root = new Node(movie); 
            return root; 
        }   
        if (Integer.parseInt(movie[1]) <= Integer.parseInt(root.key[1])) 
            root.left = insertRec(root.left, movie); 
        else if (Integer.parseInt(movie[1]) > Integer.parseInt(root.key[1])) 
            root.right = insertRec(root.right, movie);   
        return root; 
    } 
  
    void inorder()  { 
       inorderRec(root); 
    } 
    void inorderRec(Node root) { 
        if (root != null) { 
            inorderRec(root.left); 
            System.out.println(root.key[0]); 
            inorderRec(root.right); 
        } 
    }
    
}     
public class movieSelector {
    
    LinkedList<String[]> database = new LinkedList<>();
    Stack<String[]> watchList = new Stack<>();
    Queue<String[]> searchedMovies = new LinkedList<>(); 
    BinarySearchTree tree = new BinarySearchTree();
    
    void insertMovie(){
        
        int mchoice = 1;
        
            
        while(true){
            if(mchoice == 1){
                
                Scanner scan = new Scanner(System.in);

                String arr[] = new String[3];                
                System.out.printf("Enter the Name of Movie you want to insert: ");
                arr[0] = scan.nextLine();
                while(true){
                    
                    try{                        
                        Scanner sc = new Scanner(System.in);                        
                        System.out.printf("Enter the year of release of movie: ");                        
                        int temp = sc.nextInt();                        
                        arr[1] = Integer.toString(temp);
                        break;
                    }
                    catch(Exception e){
                        System.out.println("Please Enter an integer for choice: ");
                    }
                }
                System.out.printf("Enter the genre of movie: ");
                arr[2] = scan.nextLine();
                  database.add(arr);
                
                System.out.println("Do you want to add another movie: ");
                System.out.println("1) Yes: ");
                System.out.println("0) No: ");
                
                while(true){
                    try{
                        
                        Scanner sc = new Scanner(System.in);
                        mchoice = sc.nextInt();
                        if((mchoice > -1) && (mchoice < 2)){
                            break;
                        }
                        System.out.println("Please Enter integer between (0-1): ");
                    }
                    catch(Exception e){
                        System.out.println("Please Enter an integer for choice: ");
                    }
                }

            }else{
                break;
            }
        }                
    }       
    void printInitials(){
        
        System.out.println("---------- Welcome To Movie Selector ----------\n");
        System.out.println("1) Add a movie to Database");
        System.out.println("2) Pick me a movie from database");
        System.out.println("3) Search a movie from database");
        System.out.println("4) Delete a movie from database");
        System.out.println("5) Exit");
        System.out.printf("What do you want to do: ");
        
    }
    int getChoice(){
        
        int choice = 0;
        
        while(true){
            try{
                Scanner scan = new Scanner(System.in);
                
                choice = scan.nextInt();
                
                if((choice > 0) && (choice < 6)){
            
                    break;                
                }            
                System.out.println("Please Enter integer between (1-5): ");                
            }
            catch(Exception e){
                System.out.println("Please Enter an integer for choice: ");
            }
        }      
        return choice;        
    }

    void bubbleSort(){
        
        int n = database.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (Integer.parseInt(database.get(j)[1]) > Integer.parseInt(database.get(j+1)[1]))
                {
                    
                    String[] temp = database.get(j);
                    database.set(j, database.get(j+1));
                    database.set(j+1, temp);
                }
        
    }    
    String pickRandomMovie(){
        
        int rchoice = 1;
        String rmovie = "None";

        while(true){
            
            System.out.println("What do you want to do: ");
            System.out.println("1) Pick a random Movie: ");
            System.out.println("2) Pick a random Movie by Genre: ");           
            
            while(true){
                try{
                        
                    Scanner sc = new Scanner(System.in);

                    rchoice = sc.nextInt();

                    if((rchoice > 0) && (rchoice < 3)){

                        break;

                    }
                    System.out.println("Please Enter integer  (1 or 2): ");
                }
                catch(Exception e){
                    System.out.println("Please Enter an integer for choice: ");
                    }
                }                       
            Random rand = new Random();
            int rand_int = rand.nextInt(database.size());
            
            
            switch (rchoice) {
                case 1:
                    rmovie = database.get(rand_int)[0];
                    break;
                case 2:
                    {
                        printCategories();
                        LinkedList<String[]> list = new LinkedList<>();
                        System.out.println("Enter the genre you want to pick from: ");
                        Scanner scan = new Scanner(System.in);
                        String category =  scan.nextLine();
                        for(int i = 0; i < database.size(); i++){
                            if(category.equals(database.get(i)[2])){
                                list.add(database.get(i));
                            }
                        }       rand_int = rand.nextInt(list.size());
                        rmovie = list.get(rand_int)[0];
                        break;
                    }
                default:
                    break;
            }        
            return rmovie;        
        }        
    }    
    void searchMovie()
    {        
        int schoice = 1;
        while(true){
            
            System.out.println("What do you want to do: ");
            System.out.println("1) Search By Name: ");
            System.out.println("2) Search by Genre: ");
            System.out.println("3) Search by Year Of Release: ");            
            
            while(true){
                try{
                        
                    Scanner sc = new Scanner(System.in);

                    schoice = sc.nextInt();

                    if((schoice > 0) && (schoice < 4)){

                        break;

                    }

                    System.out.println("Please Enter integer between (1-3): ");

                }
                catch(Exception e){
                    System.out.println("Please Enter an integer for choice: ");
                    }
                }
            
                switch (schoice) {
                case 1:
                    {
                        System.out.println("Enter the name of movie you want to pick: ");
                        Scanner scan = new Scanner(System.in);
                        String name =  scan.nextLine();
                        
                        for(int i = 0; i < database.size(); i++){
                            if(name.equals(database.get(i)[0])){
                                searchedMovies.add(database.get(i));
                            }
                        }                        
                    }
                    break;
                case 2:
                    {
                        printCategories();
                        System.out.println("Enter the genre you want to search movies from: ");
                        Scanner scan = new Scanner(System.in);
                        String category =  scan.nextLine();

                        for(int i = 0; i < database.size(); i++){
                            if(category.equals(database.get(i)[2])){
                                searchedMovies.add(database.get(i));
                            }
                        }                               
                        break;
                    }
                case 3:
                    {

                        System.out.println("Enter the year you want to search movies from: ");
                        Scanner scan = new Scanner(System.in);
                        String year =  scan.nextLine();
                        for(int i = 0; i < database.size(); i++){
                            if(year.equals(database.get(i)[1])){
                                tree.insert(database.get(i));
                            }
                        }       
                        break;
                    }
                default:
                    break;                    
            }            
            return;               
        }
    }
        
    void deleteMovie(String movie){
        
        for(int i = 0; i < database.size(); i++){
            if(movie.equals(database.get(i)[0])){
                database.remove(i);
            }
        }        
    }
    
    void addMovie(String movie){
        
        for(int i = 0; i < database.size(); i++){
            
            if(movie.equals(database.get(i)[0])){
                watchList.push(database.get(i));
            }
        }        
    }
    
    String whatToDo(String movie){

        String message = "None";
        
        int schoice = 1;

        while(true){
            
            System.out.println("What do you want to do: ");
            System.out.println("1) Add to your watchList: ");
            System.out.println("2) Delete from the database: ");
            
            while(true){
                try{
                        
                    Scanner sc = new Scanner(System.in);

                    schoice = sc.nextInt();

                    if((schoice > 0) && (schoice < 3)){

                        break;

                    }

                    System.out.println("Please Enter integer between (1-2): ");

                }
                catch(Exception e){
                    System.out.println("Please Enter an integer for choice: ");
                    }
                }
            
            switch (schoice) {
                case 1:
                    {
                        addMovie(movie);
                        message = ("The movie (" + movie + ") is added to watchlist.");
                    }
                    break;
                case 2:
                    {
                        
                        deleteMovie(movie);
                        message = ("The movie (" + movie + ") is deleted from database.");
                    }
                    break;
                default:
                    break;
                    
            }
            
            return message;
        
        }
        
    }
    
    
    void readData(String filename){
        
        String line = "";  
        String splitBy = ",";  
        try   
        {  
           
            BufferedReader br = new BufferedReader(new FileReader(filename));  
            while ((line = br.readLine()) != null)   
                {  
                String[] movie = line.split(splitBy);     
                    database.add(movie);
                }  
        }   
        catch (IOException e)   
        {  
        e.printStackTrace();  
        }  
        
    }
    void writeData(String filename){
        
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(filename);
            for(int i = 0; i < database.size(); i++){
                csvWriter.append(database.get(i)[0]);
                csvWriter.append(",");
                csvWriter.append(database.get(i)[1]);
                csvWriter.append(",");
                csvWriter.append(database.get(i)[2]);
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(movieSelector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                csvWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(movieSelector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    void printCategories(){
        
        
        Set<String> categories = new HashSet<String>();
        
        for(int i = 0; i < database.size(); i++){
            categories.add(database.get(i)[2]);
        }
        
        System.out.println("\nGenre to search are: ");
        System.out.println(categories+"\n");
        
    }
       
    public static void main(String[] args) {

        movieSelector mp = new movieSelector();
        
        mp.readData("database.csv");
        
        String moviePicked;
        int choice = 0;       
        
        while(choice != 5){

            mp.printInitials();
            choice = mp.getChoice();        
            
            switch (choice) {
                case 1:
                    mp.insertMovie();
                    mp.bubbleSort();
                    break;
                case 2:
                    moviePicked = mp.pickRandomMovie();
                    System.out.println("Movie Picked: " + moviePicked);
                    String message = mp.whatToDo(moviePicked);
                    System.out.println(message);
                    break;
                case 3:
                    mp.searchMovie();
                    System.out.println("Movies found from the Search are: ");
                    if(!mp.searchedMovies.isEmpty()){
                        
                        for(int i = 0; i <= mp.searchedMovies.size(); i++){
                        
                        String[] amovie = mp.searchedMovies.remove();
                        
                        System.out.println(i + ") Name: " + amovie[0]);
                        System.out.println(i + ") Year: " + amovie[1]);
                        System.out.println(i + ") Genre: " + amovie[2]);
                        System.out.println();
                        
                        }
                    }
                    mp.tree.inorder();
                    break;
                case 4:
                    System.out.println("\nMovies In Database:- \n");
                    for(int i = 0; i < mp.database.size(); i++){
                        System.out.println(i + ") " + mp.database.get(i)[0]);
                    }
                    System.out.println("\nEnter the name of Movie to delete: ");
                    Scanner scan = new Scanner(System.in);
                    String movieToDelete = scan.nextLine();
                    mp.deleteMovie(movieToDelete);
                    System.out.println("The movie (" + movieToDelete + ") is deleted from database.");
                    break;
                default:
                    break;
            }
            System.out.println("\nMovies In Database:- ");
            System.out.println();
            for(int i = 0; i < mp.database.size(); i++){
                System.out.println(i + ") " + mp.database.get(i)[0]);
            }
        }
        
         System.out.println();
            mp.writeData("database.csv");
            if(mp.watchList.empty()){
                System.out.println("Watch list is empty");
            }else
{
                System.out.println("Watch list has following movies ordered from first to watch to last to watch");
                while(!mp.watchList.empty()){
                    System.out.println("-> "+Arrays.toString(mp.watchList.pop()));
                }
                
            }                  
    }
    
}

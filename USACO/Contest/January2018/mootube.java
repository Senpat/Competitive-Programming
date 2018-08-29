import java.io.*;
import java.util.*;

class mootube{
   
   public static int num;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("mootube.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int num = Integer.parseInt(st.nextToken());
      int numq = Integer.parseInt(st.nextToken());
      
      int[][] matrix = new int[num-1][3];
      
      for(int k = 0; k < num-1; k++){
         st = new StringTokenizer(f.readLine());
         matrix[k][0] = Integer.parseInt(st.nextToken());
         matrix[k][1] = Integer.parseInt(st.nextToken());
         matrix[k][2] = Integer.parseInt(st.nextToken());
      }
      
      for(int k = 0; k < numq; k++){
         st = new StringTokenizer(f.readLine());
         out.println(calc(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),matrix));
      }
      
      out.close();
      
   }
   public static int[] array;
   public static int calc(int x, int y, int[][] matrix){
      int count = 0;
      array = new int[matrix.length+1];
      for(int k = 0; k < num; k++){
         PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
         if(matrix[k][0] == y){
            helper(matrix[0][matrix[1].indexOf(y)],y,matrix);
         }
      }
      
      return count;
   }
   
   public static void helper(int x, int y, int[][] matrix){
            
      if(array[y]==null){
         array[y] = matrix[matrix[1].indexOf(y)][2];
         for(int k= 0; k < matrix.length; k++){
            if(matrix[k][0] == matrix[1].indexOf(y)
            
   
   
}
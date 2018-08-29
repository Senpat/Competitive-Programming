import java.io.*;
import java.util.*;

class lifeguards{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("lifeguards.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
      
      int num = Integer.parseInt(f.readLine());
      
      int[][] matrix = new int[num][2];
      
      int max_num = 0;
      for(int k = 0; k < num; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         matrix[k][0] = Integer.parseInt(st.nextToken());
         matrix[k][1] = Integer.parseInt(st.nextToken());
         max_num = Math.max(matrix[k][1],max_num);
      }
      
      int[] array = new int[max_num];
      
      Arrays.fill(array,0);
      int newcount = 0;
      
      for(int k = 0; k < num; k++){
         for(int x = matrix[k][0]-1; x < matrix[k][1]-1; x++){
            if(array[x] == 0)
               newcount++;
            array[x]++;
         }
      }
      
      
      int min = Integer.MAX_VALUE;
      
      for(int k = 0; k < num; k++){
         int count = 0;
         for(int x = matrix[k][0]-1; x < matrix[k][1]-1; x++){
            if(array[x] == 1){
               count++;
            }
         }
         min = Math.min(min,count);
      }
      
      out.println(newcount-min);
      out.close();
   }
}
      
      
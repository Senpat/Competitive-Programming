//make sure to make new file!
import java.io.*;
import java.util.*;

public class B598{
   
   public static int[] array;
   public static int[] indexof;
   public static HashSet<Integer> used;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         array = new int[n];
         indexof = new int[n+1];
         
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            indexof[array[k]] = k;
         }
         
         int moves = n-1;
         int mover = 1;
         int min = 0;
         
         used = new HashSet<Integer>();
         
         while(moves > 0 && mover < n){
            //int nummove = Math.min(indexof[mover]-min,moves);
            //int newmin = indexof[mover];
            
            int moved = move(indexof[mover],moves);
            
            moves -= moved;
            mover++;
            //min = Math.max(newmin,indexof[mover-1]+1);
         }
         
         
            
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + array[k]);
         }
         
         out.println(sj);
      
      
      }
      
      
      
      
      out.close();
   }
   
   //move index i c times
   public static int move(int i, int limit){
      
      int moved = 0;
      for(int k = i; k > 0; k--){
         //switch k with k-1
         
         if(array[k-1] < array[k] || used.contains(k-1) || moved == limit) return moved;
         moved++;
         used.add(k-1);
         
         indexof[array[k]]--;
         indexof[array[k-1]]++;
         
         int temp = array[k];
         array[k] = array[k-1];
         array[k-1] = temp;
      }
      
      return moved;
   }
      
}
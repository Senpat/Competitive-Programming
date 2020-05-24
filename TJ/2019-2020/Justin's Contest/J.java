//make sure to make new file!
import java.io.*;
import java.util.*;

public class J{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());

      Integer[] array = new Integer[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
      }
      Arrays.sort(array);
      
      
      Pair[] pairs = new Pair[m];
      for(int k = 0; k < m; k++){
         pairs[k] = (new Pair(Integer.parseInt(f.readLine()),k));
      }
      Arrays.sort(pairs);
      
      int[] answer = new int[m];
      
      
      int sum = n;
      int i = 0;
      for(int k = 0; k < m; k++){
         //
         while(i < n && array[i] < pairs[k].v){
            i++;
            sum--;
         }
         
         answer[pairs[k].i] = sum;
      
      }
      
      for(int k = 0; k < m; k++){
         out.println(answer[k]);
      }
         
         
      
      
      
      
      
      out.close();
   }
   
   public static class Pair implements Comparable<Pair>{
      int v;
      int i;
      
      public Pair(int a, int b){
         v = a;
         i = b;
      }
      public int compareTo(Pair p){
         return v-p.v;
      }
   }
      
}
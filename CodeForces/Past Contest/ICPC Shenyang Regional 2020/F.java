//make sure to make new file!
import java.io.*;
import java.util.*;

public class F{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      Pair[] sort = new Pair[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         sort[k] = new Pair(array[k],k);
      }
      
      Arrays.sort(sort);
      
      int[] a = new int[n];
      for(int k = 0; k < n; k++){
         a[sort[k].i] = k;
      }
      
      int max = 0;
      int answer = 0;
      
      for(int k = 0; k < n; k++){
         max = Math.max(max,a[k]);
         if(max == k){
            answer++;
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Pair implements Comparable<Pair>{
      int x;
      int i;
      public Pair(int a, int b){
         x = a;
         i = b;
      }
      public int compareTo(Pair p){
         if(x == p.x) return i-p.i;
         return x-p.x;
      }
   }
      
}
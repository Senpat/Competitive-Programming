//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1642{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long x = Long.parseLong(st.nextToken());
      
      long[] array = new long[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      //stores sum of pairs
      HashMap<Long,Pair> hmap = new HashMap<Long,Pair>();
      
      int[] a = new int[4];
      for(int l = 0; l < n; l++){
         for(int r = l+1; r < n; r++){
            if(hmap.containsKey(x-array[l]-array[r])){
               Pair p = hmap.get(x-array[l]-array[r]);
               a[0] = p.a+1;
               a[1] = p.b+1;
               a[2] = l+1;
               a[3] = r+1;
               break;
            }
         }
         
         if(a[0] != 0) break;
         //add sums of pairs including l
         for(int k = 0; k < l; k++){
            hmap.put(array[k]+array[l],new Pair(k,l));
         }
      }
      
      if(a[0] == 0){
         out.println("IMPOSSIBLE");
      } else {
         out.println(a[0] + " " + a[1] + " " + a[2] + " " + a[3]);
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Pair{
      int a;
      int b;
      public Pair(int c, int d){
         a = c;
         b = d;
      }
   }
      
}
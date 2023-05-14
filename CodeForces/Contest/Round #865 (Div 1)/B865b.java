//make sure to make new file!
import java.io.*;
import java.util.*;

public class B865b{
   
   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         if(n == 2){
            out.println("! 1 2 2 1");
            out.flush();
            f.readLine();
            continue;
         }
      
         //query n+1 and n+2 to make a bamboo -> 2 queries
         out.println("+ " + (n+1));
         out.flush();
         f.readLine();           //1 or -2
         out.println("+ " + (n+2));
         out.flush();
         f.readLine();
         
         int[] array = new int[n];
         array[0] = 1;
         
         int add = n-1;
         int sign = 1;
         for(int k = 1; k < n; k++){
            array[k] = array[k-1] + (add*sign);
            add--;
            sign *= -1;
         }
         
         //query 1 and everything else, get node that's farthest -> (n-1) queries
         int maxval = 1;
         int maxdist = 0;
         for(int k = 2; k <= n; k++){
            int i = query2(1,k);
            if(i > maxdist){
               maxval = k;
               maxdist = i;
            }
         }
         
         //query maxval with all other nodes to make the path -> (n-1) queries
         int[] path1 = new int[n+1];
         int[] path2 = new int[n+1];
         path1[maxval] = array[0];
         path2[maxval] = array[n-1];
         for(int k = 1; k <= n; k++){
            if(k == maxval) continue;
            int i = query2(maxval,k);
            path1[k] = array[i];
            path2[k] = array[n-i-1];
         }
         
         StringJoiner sj = new StringJoiner(" ");
         sj.add("!");
         for(int k = 1; k <= n; k++) sj.add("" + path1[k]);
         for(int k = 1; k <= n; k++) sj.add("" + path2[k]);
         
         out.println(sj.toString());
         out.flush();
         
         f.readLine();
      }
      
      
      
      
      out.close();
   }
   
   public static int query2(int a, int b) throws IOException{
      out.println("? " + a + " " + b);
      out.flush();
      
      return Integer.parseInt(f.readLine());
   }
      
}
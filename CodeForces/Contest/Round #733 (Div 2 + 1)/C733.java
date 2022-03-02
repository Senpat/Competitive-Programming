//make sure to make new file!
import java.io.*;
import java.util.*;

public class C733{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int[] count = new int[200005];
      for(int k = 1; k < 200005; k++){
         count[k] = k-(k/4);
      }
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         
         Integer[] a = new Integer[n+1];
         Integer[] b = new Integer[n+1];
         
         a[0] = -1;
         b[0] = -1;
         
         for(int k = 1; k <= n; k++){
            a[k] = Integer.parseInt(st1.nextToken());
            b[k] = Integer.parseInt(st2.nextToken());
         }
         
         Arrays.sort(a);
         Arrays.sort(b);
         
         int asum = 0;
         int bsum = 0;
         
         for(int k = n-count[n]+1; k <= n; k++){
            asum += a[k];
            bsum += b[k];
         }
         
         int atail = n-count[n]+1;
         int btail = n-count[n]+1;
         
         int i = n+1;
         int reps = 0;
         while(asum < bsum){
            //add 100 to a and 0 to b
            
            asum += 100;
            
            if(count[i] == count[i-1]){
               //adjust a
               asum -= a[atail];
               atail++;
            } else {
               //adjust b
               if(btail > 1){
                  bsum += b[btail-1];
                  btail--;
               }
            }
            
            i++;
            reps++;
         }
         
         out.println(reps);
         

      }
      
      
      
      
      out.close();
   }
   
      
}
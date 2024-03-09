//make sure to make new file!
import java.io.*;
import java.util.*;

public class A901{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         
         long asum = 0L;
         Long[] a = new Long[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            a[k] = Long.parseLong(st.nextToken());
            asum += a[k];
         }
         Arrays.sort(a);
         
         st = new StringTokenizer(f.readLine());
         long bsum = 0L;
         Long[] b = new Long[m];
         for(int k = 0; k < m; k++){
            b[k] = Long.parseLong(st.nextToken());
            bsum += b[k];
         }
         Arrays.sort(b);
         
         if(x % 2 == 1){
            //a does maximum improving move, then undos whatever b does
            asum += Math.max(0,b[m-1]-a[0]);
            out.println(asum);
         } else {
            //a does move, b undo, a does move, b undo, until last 2 moves
            //then a does maximum improving move, then b does maximum improving move
            
            if(b[m-1] > a[0]){
               long temp = a[0];
               a[0] = b[m-1];
               b[m-1] = temp;
               
               Arrays.sort(a);
               Arrays.sort(b);
            }
            
            if(a[n-1] > b[0]){
               long temp = b[0];
               b[0] = a[n-1];
               a[n-1] = temp;
            }
            
            asum = 0L;
            for(int k = 0; k < n; k++){
               asum += a[k];
            }
            
            out.println(asum);
            
         }
         
         

      }
      
      
      
      
      out.close();
   }
   
      
}
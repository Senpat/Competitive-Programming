//make sure to make new file!
import java.io.*;
import java.util.*;

public class C620{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int tt = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= tt; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         Customer[] array = new Customer[n+1];
         array[0] = new Customer(0,m,m);
         for(int k = 1; k <= n; k++){
            st = new StringTokenizer(f.readLine());
      
            int t = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            
            array[k] = new Customer(t,l,h);
         }
         
         if(check(array)){
            out.println("YES");
         } else {
            out.println("NO");
         }
         
         
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(Customer[] array){
      int n = array.length;
      
      for(int k = 0; k < n; k++){
         for(int j = k+1; j < n; j++){
            if(array[k].l-array[j].h > Math.abs(array[k].t-array[j].t)){
               return false;
            }
            if(array[j].l-array[k].h > Math.abs(array[k].t-array[j].t)){
               return false;
            }
         }
      }
      
      return true;
   }
   
   public static class Customer{
      int t;
      int l;
      int h;
      public Customer(int a, int b, int c){
         t = a;
         l = b;
         h = c;
      }
   }
   
      
}
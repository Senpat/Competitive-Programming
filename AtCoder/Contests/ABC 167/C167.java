//make sure to make new file!
import java.io.*;
import java.util.*;

public class C167{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      
      Book[] books = new Book[n];
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         
         int cost = Integer.parseInt(st.nextToken());
         int[] algs = new int[m];
         for(int j = 0; j < m; j++){
            algs[j] = Integer.parseInt(st.nextToken());
         }
         
         books[k] = new Book(cost,algs);
      }
      
      int mincost = Integer.MAX_VALUE;
      for(int k = 0; k < 1<<n; k++){
         int index = 0;
         int i = k;
         
         int cost = 0;
         int[] values = new int[m];
         while(i > 0){
            if(i%2 == 1){
               cost += books[index].cost;
               for(int j = 0; j < m; j++){
                  values[j] += books[index].algs[j];
               }
            }
            i >>= 1;
            index++;
         }
         
         if(check(values,x)){
            mincost = Math.min(cost,mincost);
         }
      }
         
      if(mincost == Integer.MAX_VALUE){
         out.println("-1");
      } else {
         out.println(mincost);
      }
      
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] array, int x){
      for(int k = 0; k < array.length; k++){
         if(array[k] < x) 
            return false;
      }
      return true;
   }
   
   public static class Book{
      int cost;
      int[] algs;
      
      public Book(int a, int[] b){
         cost = a;
         algs = b;
      }
   }
      
}
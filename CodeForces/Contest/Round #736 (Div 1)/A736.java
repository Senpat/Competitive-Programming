//make sure to make new file!
import java.io.*;
import java.util.*;

public class A736{

   public static int[] bits;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      ArrayList<HashSet<Integer>> above = new ArrayList<HashSet<Integer>>(n+1);
      for(int k = 0; k <= n; k++){
         above.add(new HashSet<Integer>());
      }
      
      bits = new int[n+1];
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         if(b < a){
            int temp = a;
            a = b;
            b = temp;
         }
         
         if(above.get(a).isEmpty()){
            update(a,1);
         }
         above.get(a).add(b);
      }
      
      
      int t = Integer.parseInt(f.readLine());
      for(int q = 0; q < t; q++){
         st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken());
         
         if(i == 3){
            int answer = n-psum(n);
            out.println(answer);
         } else {
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if(b < a){
               int temp = a;
               a = b;
               b = temp;
            }
            
            if(i == 1){
               //add
               
               if(above.get(a).isEmpty()){
                  update(a,1);
               }
               above.get(a).add(b);
            } else if (i == 2){
               //remove
               
               if(above.get(a).size() == 1){
                  update(a,-1);
               }
               above.get(a).remove(b);
            }
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static void update(int i, int x){
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }

}
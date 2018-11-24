//make sure to make new file!
import java.io.*;
import java.util.*;

public class B519{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] x = new int[n+1];
      
      ArrayList<Integer> alist = new ArrayList<Integer>();
      
      x[0] = array[0];
      
      for(int k = 1; k < n; k++){
         //check if x works at that length
         boolean yes = true;
         for(int j = 1; j < n; j++){
            if(array[j]-array[j-1] != x[j%k]){
               yes = false;
               j = n;
            }
         }
         if(yes){
            alist.add(k);
         }
         x[k] = array[k]-array[k-1];
      }
      
      out.println(alist.size()+1);
      for(int i : alist){
         out.print(i + " ");
      }
      out.println(n);
      
      
      
      
      out.close();
   }
   
}
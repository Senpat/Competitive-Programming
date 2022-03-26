//make sure to make new file!
import java.io.*;
import java.util.*;

public class C550{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      Integer[] array = new Integer[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(array);
      
      ArrayList<Integer> inc = new ArrayList<Integer>();
      ArrayList<Integer> dec = new ArrayList<Integer>();
      
      boolean fail = false;
      for(int k = 0; k < n; k++){
         if(k >= 2 && (int)array[k] == (int)array[k-1] && (int)array[k] == (int)array[k-2]){
            fail = true;
            break;
         }
         if(k >= 1 && (int)array[k] == (int)array[k-1]){
            dec.add(array[k]);
         } else {
            inc.add(array[k]);
         }
      }
      
      if(fail){
         out.println("NO");
      } else {
         out.println("YES");
         //print inc
         out.println(inc.size());
         for(int k = 0; k < inc.size(); k++){
            out.print(inc.get(k) + " ");
         }
         out.println();
         //print dec
         out.println(dec.size());
         for(int k = dec.size()-1; k >= 0; k--){
            out.print(dec.get(k) + " ");
         }  
         out.println();
      }
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
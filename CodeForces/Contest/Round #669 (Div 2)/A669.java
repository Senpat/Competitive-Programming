//make sure to make new file!
import java.io.*;
import java.util.*;

public class A669{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         ArrayList<Integer> alist = new ArrayList<Integer>();
         
         for(int k = 0; k < n; k+=2){
            if(array[k] != array[k+1]) alist.add(0);
            else{
               alist.add(array[k]);
               alist.add(array[k+1]);
            }
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int i : alist){
            sj.add("" + i);
         }
         out.println(alist.size());
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class expansion{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n1 = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= n1; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int o = Integer.parseInt(st.nextToken());
         int e = Integer.parseInt(st.nextToken());
         
         Integer[] array = new Integer[o];
         st = new StringTokenizer(f.readLine());
         TreeSet<Integer> pq = new TreeSet<Integer>();
         ArrayList<Integer> alist = new ArrayList<Integer>();
         for(int k = 0; k < o; k++){
            int i = Integer.parseInt(st.nextToken());
            alist.add(Math.abs(Collections.binarySearch(alist,i)+1),i);
         }
         
         
         int thresh = alist.get(o/2);
         
         int count = 0;
         
         st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= e; k++){
            int ex = Integer.parseInt(st.nextToken());
            
            if(ex >= thresh){
               count++;
            }
            
         }
         
         out.println("Expansion #" + q + ": " + count);
         
         
         
         
      
      }
      
      
      
      out.close();
   }
   
}
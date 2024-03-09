//make sure to make new file!
import java.io.*;
import java.util.*;

public class Aapivotalquestion{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      Integer[] sort = new Integer[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         sort[k] = array[k];
      }
      
      Arrays.sort(sort);
      
      ArrayList<Integer> alist = new ArrayList<Integer>();
      int pmax = 0;
      for(int k = 0; k < n; k++){
         if(array[k] == (int)sort[k] && pmax < array[k]){
            alist.add(array[k]);
         }
         pmax = Math.max(pmax,array[k]);
      }
      
      StringJoiner sj = new StringJoiner(" ");
      sj.add("" + alist.size());
      for(int k = 0; k < Math.min(100,alist.size()); k++){
         sj.add("" + alist.get(k));
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
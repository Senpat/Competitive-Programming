//make sure to make new file!
import java.io.*;
import java.util.*;

public class C148{
   
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
         for(int k = 0; k < n; k++){
            if(k > 0 && array[k] == array[k-1]) continue;
            alist.add(array[k]);
         }
         
         if(alist.size() == 1){
            out.println(1);
            continue;
         }
         
         int answer = 2;
         for(int k = 1; k < alist.size()-1; k++){
            if(alist.get(k) > alist.get(k-1) && alist.get(k) > alist.get(k+1)) answer++;
            if(alist.get(k) < alist.get(k-1) && alist.get(k) < alist.get(k+1)) answer++;
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}
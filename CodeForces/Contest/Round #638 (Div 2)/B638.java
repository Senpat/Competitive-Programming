//make sure to make new file!
import java.io.*;
import java.util.*;

public class B638{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         HashSet<Integer> hset = new HashSet<Integer>();
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            hset.add(array[k]);
         }
         
         
         if(hset.size() > m){
            out.println("-1");
         } else {
            ArrayList<Integer> alist = new ArrayList<Integer>();
            for(int i : hset){
               alist.add(i);
            }
            
            while(alist.size() < m){
               alist.add(alist.get(alist.size()-1));
            }
            
            StringJoiner sj = new StringJoiner(" ");
            for(int k = 0; k < n; k++){
               for(int j = 0; j < m; j++){
                  sj.add(""+alist.get(j));
               }
            }
            
            out.println(n*m);
            out.println(sj.toString());
         }

      }
      
      
      
      
      out.close();
   }
   
      
}
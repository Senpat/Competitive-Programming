//make sure to make new file!
import java.io.*;
import java.util.*;

public class B84{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>(n);
         
         for(int k = 0; k < n; k++){
            alist.add(new ArrayList<Integer>());
         
            StringTokenizer st = new StringTokenizer(f.readLine());
            
            int i = Integer.parseInt(st.nextToken());
         
            for(int j = 0; j < i; j++){
               alist.get(k).add(Integer.parseInt(st.nextToken()));
            }
         }
         
         
         HashSet<Integer> added = new HashSet<Integer>();
         int emptydaughter = -1;
         
         for(int k = 0; k < n; k++){
            int i = 0;
            while(i < alist.get(k).size() && added.contains(alist.get(k).get(i))){
               i++;
            }
            
            if(i >= alist.get(k).size()){
               emptydaughter = k+1;
            } else {
               added.add(alist.get(k).get(i));
            }
         }
         
         if(added.size() == n){
            out.println("OPTIMAL");
         } else {
            out.println("IMPROVE");
            
            int answer = -1;
            for(int k = 1; k <= n; k++){
               if(!added.contains(k)){
                  answer = k;
                  break;
               }
            }
            
            out.println(emptydaughter + " " + answer);
         }
            
         
         
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}
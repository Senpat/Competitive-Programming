//make sure to make new file!
import java.io.*;
import java.util.*;

public class B860{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>(n);
         for(int k = 0; k < n; k++) alist.add(new ArrayList<Integer>());
         
         for(int k = 0; k < n; k++){
            int ni = Integer.parseInt(f.readLine());
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            for(int j = 0; j < ni; j++){
               alist.get(k).add(Integer.parseInt(st.nextToken()));
            }
         
         }
         
         HashSet<Integer> seen = new HashSet<Integer>();
         
         boolean fail = false;
         int[] answer = new int[n];
         Arrays.fill(answer,-1);
         for(int k = n-1; k >= 0; k--){
            //find one
            for(int i : alist.get(k)){
               if(!seen.contains(i)){
                  answer[k] = i;
               }
               seen.add(i);
            }
            
            if(answer[k] == -1){
               fail = true;
               break;
            }
         }
         
         if(fail){
            out.println(-1);
         } else {
            StringJoiner sj = new StringJoiner(" ");
            for(int k = 0; k < n; k++){
               sj.add("" + answer[k]);
            }
            out.println(sj.toString());
         }
      }
      
      
      
      
      out.close();
   }
   
      
}
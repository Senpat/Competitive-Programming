//Color the Fence
import java.io.*;
import java.util.*;
//slow
public class B349{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
      
      int min = Integer.MAX_VALUE;
      for(int k = 1; k <= 9; k++){
         int i = Integer.parseInt(st.nextToken());
         map.put(i,k);
         min = Math.min(min,i);
      }
      
      if(n<min){
         out.println(-1);
         out.close();
         System.exit(0);
      }
      
      String[] dp = new String[n+1];
      Arrays.fill(dp,"");
      
      dp[min] = map.get(min).toString();
      
      for(int k = min; k < n+1; k++){
         if(dp[k] != ""){
            for(int i : map.keySet()){
               if(i+k<dp.length){
                  dp[i+k] = max(dp[i+k],""+map.get(i)+dp[k]);
               }
            }
         }
      }
      
      out.println(dp[n]);
      
      
      out.close();
   }
   
   public static String max(String a, String b){
      if(a.length() > b.length()) 
         return a;
      if(b.length() > a.length()) 
         return b;
      for(int k = a.length()-1; k >= 0; k--){
         if(a.charAt(k) > b.charAt(k)) 
            return a;
         if(b.charAt(k) > a.charAt(k)) 
            return b;
      }
      
      //equal
      return a;
   }
   
}
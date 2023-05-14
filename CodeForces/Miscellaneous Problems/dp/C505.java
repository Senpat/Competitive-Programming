//Mr. Kitayuta, the Treasure Hunter
import java.io.*;
import java.util.*;

public class C505{

   public static ArrayList<HashMap<Integer,Integer>> dp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 30000;
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      
      int[] array = new int[N+1];
      for(int k = 1; k <= n; k++){
         int i = Integer.parseInt(f.readLine());
         array[i]++;
      }
      
      long start = System.currentTimeMillis();
      
      dp = new ArrayList<>(N+1);
      
      for(int k = 0; k <= N; k++) dp.add(new HashMap<Integer,Integer>());
      
      int answer = 0;
      max(d,d,array[d]);
      
      for(int k = d; k <= N; k++){
         for(int i : dp.get(k).keySet()){
            int v = dp.get(k).get(i);
            answer = Math.max(answer,v);
            //jump to k+i-1, k+i, k+i+1
            for(int di = -1; di <= 1; di++){
               if(k+i+di <= N && i+di >= 1){
                  max(k+i+di,i+di,v+array[k+i+di]);
               }
            }
         }
      }
      
      out.println(answer);
      
      long end = System.currentTimeMillis();
      out.println(end-start);
      
      
      
      
      
      out.close();
   }
   
   public static void max(int i, int x, int y){
      if(!(dp.get(i).containsKey(x) && dp.get(i).get(x) >= y)) dp.get(i).put(x,y);
   }
   
      
}
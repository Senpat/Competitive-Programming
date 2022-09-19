//make sure to make new file!
import java.io.*;
import java.util.*;

public class A{
   
   public static int n;
   public static int d;
   
   public static ArrayList<ArrayList<ArrayList<Integer>>> adj;
   public static int[] last;                       //last day that person appears
   
   public static HashSet<Integer> hset;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      d = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<ArrayList<Integer>>>(d+1);
      for(int k = 0; k <= d; k++){
         ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
         for(int j = 0; j <= n; j++){
            temp.add(new ArrayList<Integer>());
         }
         adj.add(temp);
      }
      
      last = new int[n+1];
      
      for(int k = 0; k < c; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int day = Integer.parseInt(st.nextToken());
         
         adj.get(day).get(a).add(b);
         adj.get(day).get(b).add(a);
         
         last[a] = Math.max(last[a],day);
         last[b] = Math.max(last[b],day); 
      }
      
      hset = new HashSet<Integer>();
      for(int zero = 1; zero <= n; zero++){
         test(zero);
      }
      
      ArrayList<Integer> answer = new ArrayList<Integer>();
      for(int i : hset){
         answer.add(i);
      }
      Collections.sort(answer);
      
      out.println(answer.size());
      for(int k = 0; k < answer.size(); k++){
         out.println("" + answer.get(k));
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void test(int zero){
      if(last[zero] >= 2) return;
      HashSet<Integer> infected = new HashSet<Integer>();
      infected.add(zero);
      
      for(int day = 1; day <= d; day++){
         HashSet<Integer> next = new HashSet<Integer>();
         for(int i : infected){
            //consider infecting everyone
            for(int nei : adj.get(day).get(i)){
               if(last[nei] <= day+1){
                  next.add(nei);
               }
            }
         }
         
         infected = next;
      }
      
      for(int i : infected){
         hset.add(i);
      }
   
   }
   
      
}
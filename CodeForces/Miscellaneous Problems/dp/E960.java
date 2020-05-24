//make sure to make new file!
import java.io.*;
import java.util.*;

public class E960{
   
   public static int n;
   
   public static long MOD = 1000000007L;
   //public static ArrayList<Integer>[] adj;
   public static ArrayList<ArrayList<Integer>> adj;
    
   public static int[] p;
   
   public static long[] oddc;
   public static long[] evenc;
   public static long[] oddp;
   public static long[] evenp;
   public static long[] add;
   public static long[] sub;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n+1];
      long sumarray = 0L;
      for(int k = 1; k <= n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sumarray = (sumarray+array[k] + MOD)%MOD;
      }
      
      //adj = new ArrayList[n+1];
      //for(int k = 0; k <= n; k++) adj[k] = new ArrayList<Integer>();
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         //adj[a].add(b);
         //adj[b].add(a);
         adj.get(a).add(b);
         adj.get(b).add(a); 
      }
      
      p = new int[n+1];
      
      oddc = new long[n+1];
      evenc = new long[n+1];
      oddp = new long[n+1];
      evenp = new long[n+1];
      add = new long[n+1];
      sub = new long[n+1];
      
      /*
      dfsc1();
      dfsp1();
      dfsanswer1();
      */
      
      
      dfsc(1,-1);
      dfsp(1,-1);
      dfsanswer(1,-1);
      
      long answer = 0L;
      
      for(int k = 1; k <= n; k++){
         long prodadd = array[k]*(add[k]-sub[k]);
         prodadd = ((prodadd % MOD) + MOD) % MOD;
         answer = (answer + prodadd + MOD)%MOD;
      }
      
      answer = (answer*2 + MOD)%MOD;
      answer = (answer + sumarray + MOD)%MOD;
      out.println(answer);
         
      
      out.close();
   }
   /*
   public static void dfsc1(){
      
      
      Stack<Integer> stk = new Stack<Integer>();
      stk.add(-1);
      

      while(!stk.isEmpty()){
         int v = stk.pop();
         if(v < 0){
            stk.add(v*-1);
            v*=-1;
            for(int nei : adj[v]){
               if(nei == p[v]) 
                  continue;
               nei *= -1;
               
               stk.add(nei);
               p[nei*-1] = v;
            }
         } else {
            for(int nei : adj[v]){
               if(nei == p[v]) continue;
               oddc[v] += 1+evenc[nei];
               evenc[v] += oddc[nei];
            }
         }
      }   
      
   }
   
   
   public static void dfsp1(){
   
      Stack<Integer> stk = new Stack<Integer>();
      stk.add(1);
      
      
      while(!stk.isEmpty()){
         int v = stk.pop();
         if(p[v] > 0){
            oddp[v] = 1+evenp[p[v]]+evenc[p[v]]-oddc[v];
            evenp[v] = oddp[p[v]]+oddc[p[v]]-evenc[v]-1;
         }
      
         for(int nei : adj[v]){
            if(nei == p[v]) 
               continue;
            stk.add(nei);
            
         }
      }
   }
   
   public static void dfsanswer1(){
   
      
      Stack<Integer> stk = new Stack<Integer>();
      stk.add(1);
      
      while(!stk.isEmpty()){
         int v = stk.pop();
         add[v] = ((evenc[v]+1)*(evenp[v]+1)-1+MOD)%MOD;
         sub[v] = ((oddc[v])*(oddp[v])+MOD)%MOD;
         
         long sumoddc = 0L;
         long sumevenc = 0L;
         for(int nei : adj[v]){
            if(nei == p[v]) 
               continue;
            add[v] = (add[v] + sumoddc*oddc[nei]+MOD)%MOD;
            sub[v] = (sub[v] + sumevenc*(1+evenc[nei])+MOD)%MOD;
            sumoddc += oddc[nei];
            sumevenc += 1+evenc[nei];
            stk.add(nei);
         }
      }
   }
   */
   
   //recursive versions
   public static void dfsc(int v, int p){
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         dfsc(nei,v);
         oddc[v] += 1+evenc[nei];
         evenc[v] += oddc[nei];
      }
   }
   
   
   public static void dfsp(int v, int p){
      if(p != -1){
         oddp[v] = 1+evenp[p]+evenc[p]-oddc[v];
         evenp[v] = oddp[p]+oddc[p]-evenc[v]-1;
      }
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         dfsp(nei,v);
      }
   }
   
   public static void dfsanswer(int v, int p){
      add[v] = ((evenc[v]+1)*(evenp[v]+1)-1+MOD)%MOD;
      sub[v] = ((oddc[v])*(oddp[v])+MOD)%MOD;
      
      long sumoddc = 0L;
      long sumevenc = 0L;
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         add[v] = (add[v] + sumoddc*oddc[nei]+MOD)%MOD;
         sub[v] = (sub[v] + sumevenc*(1+evenc[nei])+MOD)%MOD;
         sumoddc += oddc[nei];
         sumevenc += 1+evenc[nei];
         dfsanswer(nei,v);
      }
   }
   
      
}
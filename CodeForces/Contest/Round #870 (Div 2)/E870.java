//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class E870{

   public static ArrayList<BitSet> adj;
   
   public static int n;
   public static long[] p;
   public static long[] dp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int m = Integer.parseInt(st.nextToken());
      n = Integer.parseInt(st.nextToken());
      
      p = new long[n];
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         p[k] = Long.parseLong(st.nextToken());
      }
      
      Value[][] matrix = new Value[m][n];
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            int i = Integer.parseInt(st.nextToken());
            matrix[k][j] = new Value(j,i);
         }
         Arrays.sort(matrix[k]);
      }
      
      //edge between a and b if adj.get(a).get(b) is true
      adj = new ArrayList<>(n);
      for(int k = 0; k < n; k++){
         adj.add(new BitSet(n));
      }
      
      for(int k = 0; k < m; k++){
         BitSet curbs = new BitSet(n);
         int i = 0;
         for(int j = 0; j < n; j++){
            while(i < n && matrix[k][i].v <= matrix[k][j].v){
               curbs.sub(matrix[k][i].i);
               i++;
            }
            
            adj.get(matrix[k][j].i).and(curbs);
         }
      }
      
      
      dp = new long[n];
      Arrays.fill(dp,-1);
      
      for(int k = 0; k < n; k++){
         if(dp[k] == -1){
            dfs(k);
         }
      }
      
      long answer = 0L;
      for(int k = 0; k < n; k++){
         answer = Math.max(answer,dp[k]);
      }
      out.println(answer);      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      if(dp[v] != -1) return;
      dp[v] = p[v];
      for(int nei = 0; nei < n; nei++){
         if(nei == v) continue;
         if(adj.get(v).get(nei)){
            dfs(nei);
            dp[v] = Math.max(dp[v],p[v]+dp[nei]);
         }
      }
   }

   public static class Value implements Comparable<Value>{
      int i;
      int v;
      public Value(int a, int b){
         i = a;
         v = b;
      }
      
      public int compareTo(Value val){
         return v-val.v;
      }
   }
   
      
}

class BitSet
{
    private int CONS = 62; //safe
    public long[] sets;
    public int size;
 
    public BitSet(int N)
    {
        size = N;
        if(N%CONS == 0)
            sets = new long[N/CONS];
        else
            sets = new long[N/CONS+1];
        
        //set all to true
        Arrays.fill(sets, Long.MAX_VALUE);
    }
    public void add(int i)
    {
        int dex = i/CONS;
        int thing = i%CONS;
        sets[dex] |= (1L << thing);
    }
    
    public void sub(int i)
    {
        int dex = i/CONS;
        int thing = i%CONS;
        sets[dex] &= (Long.MAX_VALUE - (1L << thing));
    }
    public void and(BitSet oth)
    {
        int boof = Math.min(sets.length, oth.sets.length);
        for(int i=0; i < boof; i++)
            sets[i] &= oth.sets[i];
    }
    public int xor(BitSet oth)
    {
        int boof = Math.min(sets.length, oth.sets.length);
        int res = 0;
        for(int i=0; i < boof; i++)
            res += Long.bitCount(sets[i] ^ oth.sets[i]);
        return res;
    }
    
    public boolean get(int x){
      long con = sets[x / CONS];
      return ((con & (1L << (x % CONS))) > 0);
    }
}
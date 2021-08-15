//Place
import java.io.*;
import java.util.*;
//uses range bit
public class Place2{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int[] order;
   public static int i;
   
   public static int[] subsize;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      long[] val = new long[n+1];
      
      //get 1
      val[1] = Long.parseLong(f.readLine());
      
      for(int k = 2; k <= n; k++){
         st = new StringTokenizer(f.readLine());
      
         long b = Long.parseLong(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(k);
         adj.get(k).add(a);
         
         val[k] = b;
         
      }
      
      order = new int[n+1];
      subsize = new int[n+1];
      
      i = 1;
      dfs(1,-1);
      
      /*
      int[] oindex = new int[n+1];
      for(int k = 1; k <= n; k++){
         oindex[order[k]] = k;
      }*/
      
      RangeBIT rbit = new RangeBIT(n);
      
      for(int k = 1; k <= n; k++){
         rbit.update(order[k],order[k],val[k]);
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         char c = st.nextToken().charAt(0);
         
         if(c == 'p'){
            int i = Integer.parseInt(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            
            rbit.update(order[i]+1,order[i]+subsize[i]-1,x);
         }
         
         if(c == 'u'){
            int i = Integer.parseInt(st.nextToken());
            long answer = rbit.query(order[i],order[i]);
            out.println(answer);
         }
      }
            
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      order[v] = i;
      i++;
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         dfs(nei,v);
         subsize[v] += subsize[nei];
      }
      
      subsize[v] += 1;
   }

}

class RangeBIT {
    final long[] value;
    final long[] weightedVal;

    RangeBIT(int treeTo) {
        value = new long[treeTo + 2];
        weightedVal = new long[treeTo + 2];
    }

    void updateHelper(int index, long delta) {
        long weightedDelta = ((long) index) * delta;
        for (int j = index; j < value.length; j += j & -j) {
            value[j] += delta;
            weightedVal[j] += weightedDelta;
        }
    }

    void update(int from, int to, long delta) {
        updateHelper(from, delta);
        updateHelper(to + 1, -delta);
    }

    long query(int to) {
        long res = 0;
        long weightedRes = 0;
        for (int j = to; j > 0; j -= j & -j) {
            res += value[j];
            weightedRes += weightedVal[j];
        }
        return (((long) (to + 1)) * res) - weightedRes;
    }

    long query(int from, int to) {
        if (to < from) {
            return 0;
        }
        return query(to) - query(from - 1);
    }
}

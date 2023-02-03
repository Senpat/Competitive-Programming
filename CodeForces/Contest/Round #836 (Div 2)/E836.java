//make sure to make new file!
import java.io.*;
import java.util.*;
//in contest attempt
public class E836{

   public static long MOD = 1000000007L;
   
   public static int ih;
   public static boolean fail;
   public static int[] offset;
   
   public static int[][] rangenums;
   public static ArrayList<HashSet<Integer>> adj;
   public static ArrayList<Integer> cur;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         ih = Integer.parseInt(st.nextToken());
         long h = (long)ih;
         
         int N = n+m+5;
         long[] powh = new long[N];
         powh[0] = 1L;
         for(int k = 1; k < N; k++){
            powh[k] = (h*powh[k-1] + MOD)%MOD;
         }
         
         boolean flip = n > m;
         
         int[][] board = new int[Math.min(n,m)][Math.max(n,m)];
         
         for(int k = 0; k < n; k++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0; j < m; j++){
               if(flip) board[j][k] = Integer.parseInt(st.nextToken());
               else board[k][j] = Integer.parseInt(st.nextToken());
            }
         }
         
         if(flip){
            int temp = n;
            n = m;
            m = temp;
         }
         
         fail = false;
         
         adj = new ArrayList<HashSet<Integer>>();
         rangenums = new int[n][n];
         for(int k = 0; k < n; k++){
            Arrays.fill(rangenums[k],Integer.MAX_VALUE);
            adj.add(new HashSet<Integer>());
         }
         
         HashSet<Integer> start = new HashSet<Integer>();
         
         for(int j = 0; j < m; j++){
            for(int l = 0; l < n; l++){
               if(board[l][j] != -1) start.add(l);
               
               for(int r = l+1; r < n; r++){
                  if(board[l][j] == -1 || board[r][j] == -1) continue;
                  int val = board[r][j] - board[l][j];
                  if(val < 0) val += ih;
                  
                  if(rangenums[l][r] != Integer.MAX_VALUE && rangenums[l][r] != val){
                     fail = true;
                     break;
                  }
                  
                  rangenums[l][r] = val;
                  
                  adj.get(l).add(r);
                  adj.get(r).add(l);
               }
            }
         }
        
         if(fail){
            out.println(0);
            continue;
         }
         
         offset = new int[n];
         Arrays.fill(offset,Integer.MAX_VALUE);
         
         int numdfs = 0;
         
         cur = new ArrayList<Integer>();
         for(int k : start){
            if(offset[k] != Integer.MAX_VALUE) continue;
            offset[k] = 0;
            dfs(k);
            
            numdfs++;
            
         }
         
         if(fail){
            out.println(0);
            continue;
         }
         
         Collections.sort(cur);
         int sumgap = 0;
         for(int k = 1; k < cur.size(); k++){
            sumgap += cur.get(k)-cur.get(k-1)-1;
         }
         if(cur.size() > 0) sumgap += n-(cur.get(cur.size()-1) - cur.get(0))-1;
         else sumgap += n-1;
         //count empty columns
         
         int empcol = 0;
         for(int j = 0; j < m; j++){
            boolean empty = true;
            for(int k = 0; k < n; k++){
               if(board[k][j] != -1) empty = false;
            }
            
            if(empty) empcol++;
         }
         
         int sum = sumgap + Math.max(0,numdfs-1) + empcol;
         
         long answer = powh[sum];
         out.println(answer);
         
      

      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      
      cur.add(v);
      
      for(int nei : adj.get(v)){
         int newoffset;
         if(nei > v){
            newoffset = offset[v] + rangenums[v][nei];
            if(newoffset >= ih) newoffset-=ih;
         } else {
            newoffset = offset[v] - rangenums[nei][v];
            if(newoffset < 0) newoffset+=ih;
         }
         
         if(offset[nei] == Integer.MAX_VALUE){
            offset[nei] = newoffset;
            dfs(nei);
         } else {
            if(offset[nei] != newoffset){
               fail = true;
            }
         }
      }
      
   }
      
}
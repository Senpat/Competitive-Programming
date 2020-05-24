//make sure to make new file!
import java.io.*;
import java.util.*;
//MLE
public class C641{

   public static int n;
   public static int m;
   public static int[][] matrix;
   public static int[][] set;
   public static int size;
   public static int curset;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());
      
      matrix = new int[n][m];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            matrix[k][j] = Character.getNumericValue(s.charAt(j));
         }
      }
      
      set = new int[n][m];
      
      curset = 1;
      
      ArrayList<Integer> sizes = new ArrayList<Integer>();
      sizes.add(-1);
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(set[k][j] != -1) 
               continue;
            size = 0;
            ff(k,j);
            sizes.add(size);
            curset++;
         }
      }
      
      int nodes = sizes.size()-1;
      ArrayList<HashSet<Integer>> adj = new ArrayList<HashSet<Integer>>(nodes+1);
      for(int k = 0; k <= nodes; k++) adj.add(new HashSet<Integer>());
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(in(k+1,j) && set[k][j] != set[k+1][j]){
               adj.get(set[k][j]).add(set[k+1][j]);
               adj.get(set[k+1][j]).add(set[k][j]);
            }
            if(in(k,j+1) && set[k][j] != set[k][j+1]){
               adj.get(set[k][j]).add(set[k][j+1]);
               adj.get(set[k][j+1]).add(set[k][j]);
            }
         }
      }
      
      long[] flippoint = new long[nodes+1];
      
      Arrays.fill(flippoint,Long.MAX_VALUE);
      Queue<State> q = new LinkedList<State>();
      
      for(int k = 1; k <= nodes; k++){
         if(sizes.get(k) > 1){
            q.add(new State(k,0));
            flippoint[k] = 0;
         }
      }
      
      while(!q.isEmpty()){
         State s = q.poll();
         
         for(int nei : adj.get(s.v)){
            if(flippoint[nei] > s.d+1){
               flippoint[nei] = s.d+1;
               q.add(new State(nei,s.d+1));
            }
         }
      }
      
      for(int k = 0; k < t; k++){
         st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken())-1;
         int j = Integer.parseInt(st.nextToken())-1;
         long p = Long.parseLong(st.nextToken());
         
         if(p <= (long)flippoint[set[i][j]] || flippoint[set[i][j]] == Long.MAX_VALUE){
            out.println(matrix[i][j]);
         } else {
            out.println(((long)Math.abs(matrix[i][j]-flippoint[set[i][j]]) + p)%2);
         }
      }
         
      
      
      out.close();
   }
   
   public static class Statef{
      int x;
      int y;
      public Statef(int a, int b){
         x = a;
         y = b;
      }
   }
   
   public static class State{
      int v;
      int d;
      public State(int a, int b){
         v = a;
         d = b;
      }
   }
   
   public static void ff(int x, int y){
      
      Queue<Statef> q = new LinkedList<Statef>();
      q.add(new Statef(x,y));
      
      while(!q.isEmpty()){
         Statef sf = q.poll();
         x = sf.x;
         y = sf.y;
         size++;
         set[x][y] = curset;
         if(in(x+1,y) && set[x+1][y] == -1 && matrix[x+1][y] == matrix[x][y]){
            ff(x+1,y);
         }
         if(in(x,y+1) && set[x][y+1] == -1 && matrix[x][y+1] == matrix[x][y]){
            ff(x,y+1);
         }
         if(in(x-1,y) && set[x-1][y] == -1 && matrix[x-1][y] == matrix[x][y]){
            ff(x-1,y);
         }
         if(in(x,y-1) && set[x][y-1] == -1 && matrix[x][y-1] == matrix[x][y]){
            ff(x,y-1);
         }
      }
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && x < n && y >= 0 && y < m;
   }
      
}
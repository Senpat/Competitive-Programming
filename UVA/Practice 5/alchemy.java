//make sure to make new file!
import java.io.*;
import java.util.*;
//should be right but doesn't output in lexicographical order
public class alchemy{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Circle[] circles = new Circle[n];
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         circles[k] = new Circle(x,y,r,a,b,k+1);
      }
      
      Arrays.sort(circles);
      
      //stores edges of bigger to smaller circles
      ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
      
      for(int k = 0; k < n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n; k++){
         for(int j = k+1; j < n; j++){
            if(circles[k].ischild(circles[j])){
               adj.get(j).add(k);
               break;
            }
         }
      }
      
      ArrayList<Integer> order = new ArrayList<Integer>();
      
      HashSet<Integer> seen = new HashSet<Integer>();
      
      int answer = 0;
      for(int k = n-1; k >= 0; k--){
         if(seen.contains(k)) continue;
         
         Queue<State> q = new LinkedList<State>();
         q.add(new State(k,0));
         
         while(!q.isEmpty()){
            State s = q.poll();
            seen.add(s.v);
            
            int[] gm = circles[s.v].getmax(s.depth);
            int max = gm[0];
            int num = gm[1];
            
            answer += max;
            
            if(num == 0) order.add(s.v);
            else{
               int i = order.size()-1;
               int numfound = 0;
               while(numfound < num){
                  if(circles[s.v].ischild(circles[order.get(i)])){
                     numfound++;
                  }
                  i--;
               }
               
               order.add(i+1,s.v);
            }
            
            for(int nei : adj.get(s.v)){
               q.add(new State(nei,s.depth+1));
            }
         }
      }
      
      out.println(answer);
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + circles[order.get(k)].i);
      }
      
      out.println(sj.toString());
            
         
      
      
      
      
      out.close();
   }
   
   public static class State{
      int v;
      int depth;
      
      public State(int a, int b){
         v = a;
         depth = b;
      }
   }
   
   public static class Circle implements Comparable<Circle>{
      int x;
      int y;
      int r;
      int a;
      int b;
      int i;
      public Circle(int q, int w, int e, int s, int d, int index){
         x = q;
         y = w;
         r = e;
         a = s;
         b = d;
         i = index;
      }
      
      public int[] getmax(int upper){
         int max = 0;
         int num = 0;
         int sum = 0;
         for(int k = 0; k < upper; k++){
            if(k%2 == 0) sum += a;
            else sum += b;
            if(max < sum){
               max = sum;
               num = k+1;
            }
         }
         int[] ret = new int[]{max,num};
         return ret;
      }
      
      public boolean ischild(Circle c){
         return Math.abs(x-c.x) <= c.r && Math.abs(y-c.y) <= c.r;
      }
      
      public int compareTo(Circle c){
         return r-c.r;
      }
      
      public String toString(){
         return "(" + x + ", " + y + ") " + r;
      }
   }
      
}
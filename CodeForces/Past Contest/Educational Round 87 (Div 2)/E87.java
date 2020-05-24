//make sure to make new file!
import java.io.*;
import java.util.*;
//wrong, assumes there is only 1 component
public class E87{

   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] parity;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      StringTokenizer st = new StringTokenizer(f.readLine());
   
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int n3 = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      parity = new int[n+1];
      Arrays.fill(parity,-1);
      int i = calc();
      
      if(i == -1){
         out.println("NO");
      } else if(i == n2){
         out.println("YES");
         StringJoiner sj = new StringJoiner("");
         
         int ones = 0;
         for(int k = 1; k <= n; k++){
            if(parity[k] == 0){
               sj.add("2");
            } else {
               if(ones < n1){
                  sj.add("1");
                  ones++;
               } else {
                  sj.add("3");
               }
            }
         }
         out.println(sj.toString());
      } else if(i == n-n2){
         out.println("YES");
         StringJoiner sj = new StringJoiner("");
         
         int ones = 0;
         for(int k = 1; k <= n; k++){
            if(parity[k] == 1){
               sj.add("2");
            } else {
               if(ones < n1){
                  sj.add("1");
                  ones++;
               } else {
                  sj.add("3");
               }
            }
         }
         out.println(sj.toString());
      } else {
         out.println("NO");
      }
         
      
      
      
      
      
      
      out.close();
   }
   
   
   public static int calc(){
      
      //bfs, fill parity
      
      parity[1] = 0;
      
      Queue<Integer> q = new LinkedList<Integer>();
      q.add(1);
      
      while(!q.isEmpty()){
         int i = q.poll();
         
         for(int nei : adj.get(i)){
            if(parity[nei] == parity[i]) return -1;
            if(parity[nei] != -1) continue;
            parity[nei] = 1-parity[i];
            q.add(nei);
         }
      }
      
      int num0 = 0;
      for(int k = 1; k < adj.size(); k++){
         if(parity[k] == 0) num0++;
      }
      return num0;
   }  
      
   
      
}
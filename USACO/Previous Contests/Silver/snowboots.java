import java.io.*;
import java.util.*;

class snowboots{
   
   public static int n,b;
   public static int best = Integer.MAX_VALUE;
   public static int[] f,s,d;
   public static boolean[][] been;
   
   public static void main(String[] args)throws IOException{
      BufferedReader br = new BufferedReader(new FileReader("snowboots.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      been = new boolean[n][b];
      
      f = new int[n];
      StringTokenizer st1 = new StringTokenizer(br.readLine());
      for(int k = 0; k < n; k++){
         f[k] = Integer.parseInt(st1.nextToken());
      }
      
      s = new int[b];
      d = new int[b];
      
      for(int k = 0; k < b; k++){
         StringTokenizer st2 = new StringTokenizer(br.readLine());
         s[k] = Integer.parseInt(st2.nextToken());
         
         d[k] = Integer.parseInt(st2.nextToken());
      }
      
      visit(0,0);
      
      System.out.println(best);
      out.println(best);
      out.close();
   }
   
   public static void visit(int x, int y){
      //System.out.println(x + " " + y);

      if(been[x][y]) return;
      
      been[x][y] = true;
      
      if(x == n-1){
         best = Math.min(best,y);
         return;
      }
      for(int k = 1+x; k-x <= d[y] && k < n; k++){
         
         if(s[y] >= f[k]){
            visit(k,y);
         }
      }
      
      for(int k = 1+y; k < b; k++){
         //System.out.println(s[k] + " " + f[x]);

         if(s[k] >= f[x]){
            visit(x,k);
         }
      }
   }
   
   
   
}
      
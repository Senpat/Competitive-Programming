//GIft Stacking
//time out on test 5
import java.io.*;
import java.util.*;

public class Jc{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Gift[] gifts = new Gift[n*3];
      
      int maxx = 0;
      int maxy = 0;
      
      for(int k = 0; k < n; k++){  
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         
         //dp[Math.max(a,b)][Math.min(a,b)] = Math.max(dp[Math.max(a,b)][Math.min(a,b)],c);
         //dp[Math.max(a,c)][Math.min(a,c)] = Math.max(dp[Math.max(a,c)][Math.min(a,c)],b);
         //dp[Math.max(c,b)][Math.min(c,b)] = Math.max(dp[Math.max(c,b)][Math.min(b,c)],a);         
         
         gifts[k*3] = new Gift(Math.max(a,b),Math.min(a,b),c);
         gifts[k*3+1] = new Gift(Math.max(a,c),Math.min(a,c),b);
         gifts[k*3+2] = new Gift(Math.max(b,c),Math.min(b,c),a);
         
         
      }
      
      Arrays.sort(gifts);
      
      int[] dp = new int[
      
      
      int max = 0;
      
      for(int k = 0; k < gifts.length; k++){
         int curx = gifts[k].x;
         int cury = gifts[k].y;
         int curz = gifts[k].z;
         
         int index = alist.binarySearch(
         
      
      out.println(max);
               
               
      
      out.close();
   }
   
   public static class Gift implements Comparable<Gift>{
      int x;
      int y;
      int z;
      public Gift(int a, int b, int c){
         x=a;
         y=b;
         z=c;
      }
      public int compareTo(Gift g){
         return x*y-g.x*g.y;
      }
      public String toString(){
         return x + " " + y + " " + z;
      }
      
      
   }
   
}
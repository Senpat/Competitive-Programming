//make sure to make new file!
import java.io.*;
import java.util.*;

public class laptop{

   public static int min = Integer.MAX_VALUE;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n1 = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= n1; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         n = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         char[] array = f.readLine().toCharArray();
         
         TreeSet<Integer> rs = new TreeSet<Integer>();
         TreeSet<Integer> gs = new TreeSet<Integer>();
         TreeSet<Integer> bs = new TreeSet<Integer>();
         
         for(int k = 0; k < n; k++){
            if(array[k] == 'R') rs.add(k);
            if(array[k] == 'G') gs.add(k);
            if(array[k] == 'B') bs.add(k);
         }
         
         for(int i : rs){
            TreeSet<Integer> temp = new TreeSet<Integer>(rs);
            temp.remove(i);
            dfs(i,temp,gs,bs,0);
         }
         
         out.println(min * 2 * r * Math.sin(180/n));
         
         
         
                  
         
         
         
      
      }
      
      out.close();
   }
   
   public static void dfs(int x, TreeSet<Integer> rs,TreeSet<Integer> gs,TreeSet<Integer> bs,int d){
      if(!rs.isEmpty()){
         TreeSet<Integer> tempr = new TreeSet<Integer>(rs);
         int ceil = rs.ceiling(x);
         if(ceil == null){
            int f = tempr.first();
            tempr.remove(f);
            dfs(f,tempr,gs,bs,d+n-x+f);
         } else {
            tempr.remove(ceil);
            dfs(ceil,tempr,gs,bs,d+ceil-x);
         }
         TreeSet<Integer> tempr2 = new TreeSet<Integer>(rs);
         int floor = rs.floor(x);
         if(floor == null){
            int f = tempr2.last();
            tempr2.remove(f);
            dfs(f,tempr2,gs,bs,d+n-f+x);
         } else {
            tempr2.remove(floor);
            dfs(floor,tempr2,gs,bs,d+x-floor);
         }

            
      } else if(!gs.isEmpty()){
         TreeSet<Integer> tempr = new TreeSet<Integer>(gs);
         int ceil = gs.ceiling(x);
         if(ceil == null){
            int f = tempr.first();
            tempr.remove(f);
            dfs(f,rs,tempr,bs,d+n-x+f);
         } else {
            tempr.remove(ceil);
            dfs(ceil,rs,tempr,bs,d+ceil-x);
         }
         TreeSet<Integer> tempr2 = new TreeSet<Integer>(rs);
         int floor = gs.floor(x);
         if(floor == null){
            int f = tempr2.last();
            tempr2.remove(f);
            dfs(f,rs,tempr2,bs,d+n-f+x);
         } else {
            tempr2.remove(floor);
            dfs(floor,rs,tempr2,bs,d+x-floor);
         }
      } else if(!bs.isEmpty()){
         TreeSet<Integer> tempr = new TreeSet<Integer>(rs);
         int ceil = bs.ceiling(x);
         if(ceil == null){
            int f = tempr.first();
            tempr.remove(f);
            dfs(f,rs,gs,tempr,d+n-x+f);
         } else {
            tempr.remove(ceil);
            dfs(ceil,rs,gs,tempr,d+ceil-x);
         }
         TreeSet<Integer> tempr2 = new TreeSet<Integer>(rs);
         int floor = bs.floor(x);
         if(floor == null){
            int f = tempr2.last();
            tempr2.remove(f);
            dfs(f,rs,gs,tempr2,d+n-f+x);
         } else {
            tempr2.remove(floor);
            dfs(floor,rs,gs,tempr2,d+x-floor);
         }
      } else {
         min = Math.min(min,d);
      }
   }
         
   
}
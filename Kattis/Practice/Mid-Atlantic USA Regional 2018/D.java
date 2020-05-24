/*
If you want to aim high, aim high
Don't let that studying and grades consume you
Just live life young
****************************
If I'm the sun, you're the moon
Because when I go up, you go down
*****************************
I'm working for the day I will surpass you
https://www.a2oj.com/Ladder16.html
*/
import java.util.*;
import java.io.*;
import java.math.*;

public class D
{
   static int CON = 100;
   public static void main(String omkar[]) throws Exception
   {
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));  
      
      ArrayList<Integer> nums = new ArrayList<Integer>();
      
      //String s = f.readLine();
      
      String s;
   
      
      while((s=f.readLine()) != null && !s.equals("")){
         
         s = s.replace(" ","");
         s = s.replace("\t","");
         s = s.replace("(","");
         s = s.replace(")","");
         
         
         String[] s1 = s.split(";");
         for(int k = 0; k < s1.length; k++){
            String[] s2 = s1[k].split(",");
            for(int j = 0; j < s2.length; j++){
               nums.add(Integer.parseInt(s2[j]));
            }
         }
         
         
         //s = f.readLine();
      }
      
      
      edges = new LinkedList[20000];
      for(int i=0; i < 20000; i++)
         edges[i] = new LinkedList<Integer>();
      HashSet<Integer> exist = new HashSet<Integer>();
      for(int i=0; i < nums.size(); i += 4)
      {
         int a = nums.get(i)*CON;
         a += nums.get(i+1);
         int b = nums.get(i+2)*CON;
         b += nums.get(i+3);
         edges[a].add(b);
         edges[b].add(a);
         exist.add(a);  exist.add(b);
      }
      int res1 = 0;
      int res2 = 0;
      seen = new HashSet<Integer>();
      for(int v: exist)
         if(!seen.contains(v))
         {
            path = new ArrayList<Integer>();
            dfs(v);
            res1++;
            boolean works = true;
            for(int x: path)
               if(edges[x].size() != 2)
                  works = false;
            if(works && path.size() > 2)
               res2++;
         }
      System.out.println(res1+" "+res2);
   }
   static ArrayList<Integer> path;
   static HashSet<Integer> seen;
   static LinkedList<Integer>[] edges;
   public static void dfs(int curr)
   {
      path.add(curr);
      for(int next: edges[curr]){
         if(!seen.contains(next)){
            seen.add(next);
            dfs(next);
         }
      }
   }
}
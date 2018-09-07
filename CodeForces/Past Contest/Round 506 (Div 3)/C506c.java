//Maximal Intersection
//tutorial
import java.io.*;
import java.util.*;

public class C506c{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] l = new int[n];
      int[] r = new int[n];
      
      TreeSet<Integer> lt = new TreeSet<Integer>();
      TreeSet<Integer> rt = new TreeSet<Integer>();
      HashSet<Integer> dupl = new HashSet<Integer>();
      HashSet<Integer> dupr = new HashSet<Integer>();
      
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         l[k] = Integer.parseInt(st.nextToken());
         r[k] = Integer.parseInt(st.nextToken());
         
         if(lt.contains(l[k])) dupl.add(l[k]);
         else lt.add(l[k]);
         if(rt.contains(r[k])) dupr.add(r[k]);
         else rt.add(r[k]);
         
      }
      
      int answer = 0;
      
      for(int k = 0; k < n; k++){
         if(!dupl.contains(l[k])) lt.remove(l[k]);
         if(!dupr.contains(r[k])) rt.remove(r[k]);
         
         answer = Math.max(answer,rt.first()-lt.last());
         
         lt.add(l[k]);
         rt.add(r[k]);
      }
      
      out.println(answer);      
      
      out.close();
   }
   
/*
4
1 3
2 6
0 4
3 3

*/
   
}
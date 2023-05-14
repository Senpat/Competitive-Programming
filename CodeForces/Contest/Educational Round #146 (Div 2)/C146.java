//make sure to make new file!
import java.io.*;
import java.util.*;

public class C146{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int s1 = Integer.parseInt(st.nextToken());
         int s2 = Integer.parseInt(st.nextToken());
         
         Box[] boxes = new Box[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            int w = Integer.parseInt(st.nextToken());
            boxes[k] = new Box(k+1,w);
         }
         
         Arrays.sort(boxes);
         
         ArrayList<Integer> a = new ArrayList<Integer>();
         ArrayList<Integer> b = new ArrayList<Integer>();
         
         int atime = s1;
         int btime = s2;
         for(int k = n-1; k >= 0; k--){
            if(atime <= btime){
               a.add(boxes[k].i);
               atime += s1;
            } else {
               b.add(boxes[k].i);
               btime += s2;
            }
         }
         
         StringJoiner sja = new StringJoiner(" ");
         StringJoiner sjb = new StringJoiner(" ");
         sja.add("" + a.size());
         for(int i : a){
            sja.add("" + i);
         }
         sjb.add("" + b.size());
         for(int i : b){
            sjb.add("" + i);
         }
         
         out.println(sja.toString());
         out.println(sjb.toString());
         
         

      }
      
      
      
      
      out.close();
   }
   
   public static class Box implements Comparable<Box>{
      int i;
      int w;
      public Box(int a, int b){
         i = a;
         w = b;
      }
      public int compareTo(Box b){
         return w-b.w;
      }
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class A683{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long m = Long.parseLong(st.nextToken());
         long lower = (m+1L)/2L;
         
         Num[] array = new Num[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            long i = Long.parseLong(st.nextToken());
            array[k] = new Num(i,k+1);
         }
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         
         Arrays.sort(array);
         
         //find biggest number <= m
         int i = -1;
         for(int k = 0; k < n; k++){
            if(array[k].x <= m) i = k;
         }
         
         if(i == -1){
            out.println("-1");
            continue;
         } else if(array[i].x >= lower){
            out.println("1");
            out.println(array[i].i);
            continue;
         }
         
         long sum = 0L;
         
         while(sum < lower && i >= 0){
            sum += array[i].x;
            answer.add(array[i].i);
            i--;
         }
         
         if(sum >= lower){
            out.println(answer.size());
            StringJoiner sj = new StringJoiner(" ");
            for(int a : answer){
               sj.add("" + a);
            }
            out.println(sj);
         } else {
            out.println(-1);
         }
      
      }
      
      
      
      
      out.close();
   }
   
   public static class Num implements Comparable<Num>{
      long x;
      int i;
      public Num(long a, int b){
         x = a;
         i = b;
      }
      public int compareTo(Num u){
         if(x > u.x) 
            return 1;
         if(x < u.x) 
            return -1;
         return 0;
      }
   }
      
}
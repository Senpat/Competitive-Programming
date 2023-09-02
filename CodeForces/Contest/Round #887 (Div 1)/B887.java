//make sure to make new file!
import java.io.*;
import java.util.*;

public class B887{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         
         Val[] array = new Val[n];
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = new Val(Integer.parseInt(st.nextToken()),k);
         }
         
         Arrays.sort(array);
         
         //check if it's possible
         int[] marker = new int[n+1];
         for(int k = 0; k < n; k++){
            marker[n-array[k].x]++;
         }
         boolean found = false;
         int psum = 0;
         for(int k = 0; k < n; k++){
            psum += marker[k];
            if(psum != array[k].x){
               found = true;
               break;
            }
         }
         
         if(found){
            out.println("NO");
            continue;
         }
         
         int[] answer = new int[n];
         int d = 0;
         int l = 0;
         int r = n-1;
         //initial
         boolean added = false;
         while(l < n && array[l].x == 0){
            answer[array[l].i] = -n;
            added = true;
            l++;
         }
         if(added) d++;
         
         //alternate l and r
         boolean dor = true;
         while(l <= r){
            if(dor){
               int start = array[r].x;
               while(r >= 0 && array[r].x == start){
                  answer[array[r].i] = n-d;
                  r--;
               }
            } else {
               int start = array[l].x;
               while(l < n && array[l].x == start){
                  answer[array[l].i] = -n+d;
                  l++;
               }
            }
            d++;
            dor = !dor;
         }
         
         out.println("YES");
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
   public static class Val implements Comparable<Val>{
      int x;
      int i;
      public Val(int a, int b){
         x = a;
         i = b;
      }
      public int compareTo(Val v){
         if(x == v.x) return i-v.i;
         return x-v.x;
      }
   }
   
      
}
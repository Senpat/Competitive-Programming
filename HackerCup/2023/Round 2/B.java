//make sure to make new file!
import java.io.*;
import java.util.*;

public class B{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("b_full_in.txt"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("b_full_out.txt")));
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer sta = new StringTokenizer(f.readLine());
         StringTokenizer stb = new StringTokenizer(f.readLine());
         
         int[] a = new int[n];
         int[] b = new int[n];
         
         int eq = -1;
         boolean muleq = false;   
         
         for(int k = 0; k < n; k++){
            a[k] = Integer.parseInt(sta.nextToken());
            b[k] = Integer.parseInt(stb.nextToken());
            
            if(a[k] == b[k]){
               if(eq != -1) muleq = true;
               eq = k;
            }
         }
         
         int shift = -1;
         if(n%2 == 1){
            if(eq == -1 || muleq){
               out.println(answerstring(q,-1)); 
               continue;  
            }
            
            //shift eq to middle (n/2)
            shift = eq-(n/2);
            if(eq < n/2) shift += n;
         } else {
            if(eq != -1){
               out.println(answerstring(q,-1));
               continue;
            }
            //check first
            int first = -1;
            if((a[0] > b[0]) == (a[n-1] > b[n-1])) first = 0;
            boolean fail = false;
            for(int k = 1; k < n; k++){
               if((a[k] > b[k]) != (a[k-1] > b[k-1])){
                  if(first == -1) first = k;
                  else fail = true;
               }
            }
            
            if(fail){
               out.println(answerstring(q,-1));
               continue;
            }
            
            
            //shift eq to middle (n/2)
            shift = first-(n/2);
            if(first < n/2) shift += n;
         }
         
         //System.out.println(q + " " + shift);
         
         int[] na = new int[n];
         int[] nb = new int[n];
            
         for(int k = 0; k < n; k++){
            if(k+shift < n){
               na[k] = a[k+shift];
               nb[k] = b[k+shift];
            } else {
               na[k] = b[k+shift-n];
               nb[k] = a[k+shift-n];
            }
         }
            
         if(check(na,nb)){
            out.println(answerstring(q,shift));
            continue;
         }
            
         //swap
         for(int k = 0; k < n; k++){
            int temp = na[k];
            na[k] = nb[k];
            nb[k] = temp;
         }
            
         if(check(na,nb)){
            out.println(answerstring(q,shift+n));
         } else {
            out.println(answerstring(q,-1));
         }
            
            
         
         
         
      
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] a, int [] b){
      int n = a.length;
      for(int k = 0; k < n; k++){
         if(a[k] != b[n-k-1]) 
            return false;
      }
      
      for(int k = 0; k < n/2; k++){
         if(a[k] >= b[k]) 
            return false;
      }
      int start = n/2;
      if(n%2 == 1) start++;
      for(int k = start; k < n; k++){
         if(a[k] <= b[k]) 
            return false;
      }
      
      return true;
   }
   
   public static String answerstring(int q, int ans){
      return "Case #" + q + ": " + ans;
   }
   
      
}
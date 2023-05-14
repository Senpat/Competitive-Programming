import java.io.*;
import java.util.*;
 
public class G {

   public static BufferedReader f;
   public static PrintWriter out;

   public static int n;
   public static int[] answer;
   
   public static void main(String[] args) throws IOException {
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
   
      int q = Integer.parseInt(f.readLine());
      for(int t = 0; t < q; t++){
         n = Integer.parseInt(f.readLine());
         answer = new int[n];
      
         answer[0] = 0;
         
         get1(1,Math.random());
         if(n > 2){
            get1(2,Math.random());
         }
      
         for(int k = 4; k < n; k+=2){
            get2(k);
         }
      
         if(n%2 == 0){
            get1(n-1,Math.random());
         }
      
         StringJoiner sj = new StringJoiner("");
         for(int k = 0; k < n; k++){
            sj.add("" + answer[k]);
         }
         out.println("0 " + sj.toString());
         out.flush();
      
         int one = Integer.parseInt(f.readLine());
      }
   
      out.close();
   }

   public static boolean isprefix(int i0, int i1, int x0, int x1){
      return i0 == x0 && i1 == x1;
   }

   public static boolean isantiprefix(int i0, int i1, int x0, int x1){
      return i0 != x0 && i1 != x1;
   }
   public static int query(int a, int b) throws IOException{
      out.println(a + " " + (b+1));
      out.flush();
      int i = Integer.parseInt(f.readLine());
      return i;
   }

   public static int get1(int x, double r) throws IOException {
      if(r < 0.5){
         //query same (prefix)
         int i = query(1,x);
         if(i == 0){
            answer[x] = 1-answer[0];
         } else {
            answer[x] = answer[i-1];
         }
         return i;
      } else {
         //different (antiprefix)
         int i = query(2, x);
         if (i == 0) {
            answer[x] = answer[0];
         } else {
            answer[x] = 1 - answer[i - 1];
         }
         return i;
      }
   }

   public static void get2(int x) throws IOException{
      double r = Math.random();
      if(r < 0.5){
         //same (prefix)
         int i = get1(x,r);
         
         if(i == x){
            answer[x-1] = answer[x-2];
            answer[x] = answer[x-1];
         } else if (i >= 2){
            answer[x-1] = answer[i-2];
         } else if (x <= 2){
            get1(x-1,Math.random());
         } else if(isprefix(answer[0],answer[1],0,answer[x])){
            answer[x-1] = 1;
         } else if(isprefix(answer[0],answer[1],1,answer[x])){
            answer[x-1] = 0;
         } else {
            get1(x-1,Math.random());
         }
      
      } else {
         //different (antiprefix)
         int i = get1(x,r);
         
         if(i == x){
            answer[x-1] = 1-answer[x-2];
            answer[x] = 1-answer[x-1];
         } else if (i >= 2){
            answer[x-1] = 1-answer[i-2];
         } else if (x <= 2){
            get1(x-1,Math.random());
         } else if(isantiprefix(answer[0],answer[1],0,answer[x])){
            answer[x-1] = 1;
         } else if(isantiprefix(answer[0],answer[1],1,answer[x])){
            answer[x-1] = 0;
         } else {
            get1(x-1,Math.random());
         }
         
      }
   }
}
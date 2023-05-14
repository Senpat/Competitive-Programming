//make sure to make new file!
import java.io.*;
import java.util.*;

public class D853{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         String s1 = f.readLine();
         String s2 = f.readLine();
         
         if(s1.equals(s2)){
            out.println(0);
            continue;
         }
         
         char[] ach = s1.toCharArray();
         char[] bch = s2.toCharArray();
         
         boolean all0a = true;
         boolean all0b = true;
         
         int[] a = new int[n];
         int[] b = new int[n];
         
         for(int k = 0; k < n; k++){
            if(ach[k] == '1') all0a = false;
            if(bch[k] == '1') all0b = false;
            
            a[k] = Character.getNumericValue(ach[k]);
            b[k] = Character.getNumericValue(bch[k]);
         }
         
         if(all0a || all0b){
            out.println(-1);
            continue;
         }
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         
         int blsb = getlsb(b);
         //solve from lsb to 0 first
         for(int k = blsb; k >= 0; k--){
            if(b[k] != a[k]){
               int alsb = getlsb(a);
               int curshift = alsb - k;
               answer.add(curshift);
               shift(a,curshift);
            }
         }
         
         int amsb = getmsb(a);
         for(int k = blsb+1; k < n; k++){
            if(b[k] != a[k]){
               int curshift = amsb - k;
               answer.add(curshift);
               shift(a,curshift);  
            }
         }
         
         
         out.println(answer.size());
         StringJoiner sj = new StringJoiner(" ");
         for(int i : answer){
            sj.add("" + i);
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
   public static void shift(int[] array, int x){
      int n = array.length;
      int[] shifted = new int[n];
      
      for(int k = 0; k < n; k++){
         if(k+x >= 0 && k+x < n){
            shifted[k] = array[k+x];
         }
      }
      
      for(int k = 0; k < n; k++){
         array[k] ^= shifted[k];
      }
      
   }
   
   public static int getlsb(int[] a){
      for(int k = a.length-1; k >= 0; k--){
         if(a[k] == 1) return k;
      }
      return -1;
   }
   
   public static int getmsb(int[] a){
      for(int k = 0; k < a.length; k++){
         if(a[k] == 1) return k;
      }
      return -1;
   }
      
   
      
}
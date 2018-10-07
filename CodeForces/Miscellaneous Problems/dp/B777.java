//Game of Credit Cards
import java.io.*;
import java.util.*;

public class B777{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s1 = f.readLine();
      String s2 = f.readLine();
      
      int[] a = new int[n];
      int[] b = new int[n];
      
      for(int k = 0; k < n; k++){
         a[k] = s1.charAt(k);
         b[k] = s2.charAt(k);
      }
      
      Arrays.sort(a);
      Arrays.sort(b);
      
      int aind = 0;
      int bind = 0;
      
      int count1 = 0;
      
      while(aind<n && bind<n){
         if(a[aind]<=b[bind]){
            aind++;
            bind++;
         } else {
            bind++;
            count1++;
         }
      }
      
      out.println(count1);
      
      aind = 0;
      bind = 0;
      
      int count2 = 0;
      
      while(aind<n && bind<n){
         if(a[aind]<b[bind]){
            aind++;
            bind++;
            count2++;
         } else {
            bind++;
         }
      }
      
      out.println(count2);
      
      out.close();
   }
   
}
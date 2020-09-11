//make sure to make new file!
import java.io.*;
import java.util.*;

public class B662{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      int[] freq = new int[100005];
      
      int n2 = 0;
      int n4 = 0;
      int n6 = 0;
      int n8 = 0;
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         freq[array[k]]++;
         if(freq[array[k]] == 2) n2++;
         if(freq[array[k]] == 4) n4++;
         if(freq[array[k]] == 6) n6++;
         if(freq[array[k]] == 8) n8++;
      }
      
      int q = Integer.parseInt(f.readLine());
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         String sign = st.nextToken();
         int x = Integer.parseInt(st.nextToken());
         
         if(sign.equals("+")){
            freq[x]++;
            if(freq[x] == 2) n2++;
            if(freq[x] == 4) n4++;
            if(freq[x] == 6) n6++;
            if(freq[x] == 8) n8++;
         } else {
            if(freq[x] == 2) n2--;
            if(freq[x] == 4) n4--;
            if(freq[x] == 6) n6--;
            if(freq[x] == 8) n8--;
            freq[x]--;
         }
         
         if(check(n2,n4,n6,n8)){
            out.println("YES");
         } else {
            out.println("NO");
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean check(int n2, int n4, int n6, int n8){
      if(n8 >= 1) return true;
      if(n6 >= 1 && n2 >= 2) return true;
      if(n4 >= 2) return true;
      if(n4 >= 1 && n2 >= 3) return true;
      return false;
   }
      
}
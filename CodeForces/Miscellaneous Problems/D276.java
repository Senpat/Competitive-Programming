//Little Girl and Maximum XOR
import java.io.*;
import java.util.*;
import java.lang.*;

public class D276{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());                //n is lower
      long m = Long.parseLong(st.nextToken());                //m is upper
      long lower = n;
      long upper = m;
      
      String nb = Long.toBinaryString(n);
      String mb = Long.toBinaryString(m);
      int len = mb.length();
      
      String add0 = "";
      for(int k = nb.length(); k < len; k++) add0+="0";
      
      nb = add0 + nb;
      
      long answer = 0L;
      // int index = 0;
//       while(index<len && nb.charAt(index) == '1' && mb.charAt(index) == '1'){
//          index++;
//          answer+=(long)Math.pow(2,len-index-1);
//       }
      
      for(int k = 0; k < len; k++){
         char a = mb.charAt(k);                       //a is upper
         char b = nb.charAt(k);                       //b is lower
         
         long i = (long)Math.pow(2,len-k-1);
         if(a!=b) answer+=i;
         
         if(a=='0' && b=='0'){
            if(lower+i<=m){
               lower+=i;                              //change 0 to 1 on lower
               answer+=i;
            }
         }
         if(a=='1' && b=='1'){
            if(upper-i>=n){
               upper-=i;                              //change 1 to 0 on upper
               answer+=i;
            }
         }
      }
      
      out.println(answer);
      
      out.close();
   }
   
}
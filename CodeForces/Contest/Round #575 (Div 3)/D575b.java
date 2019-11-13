//make sure to make new file!
import java.io.*;
import java.util.*;

public class D575b{

   public static HashMap<Character,Character> next;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      next = new HashMap<Character,Character>();
      next.put('R','G');
      next.put('G','B');
      next.put('B','R');
      
      for(int t = 0; t < q; t++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         String s = f.readLine();
         
         
         int r = count(s,m,'R');
         int g = count(s,m,'G');
         int b = count(s,m,'B');
         
         int answer = Math.min(m,Math.min(r,Math.min(g,b)));
         
         
         out.println(answer);
         
      
      }
      
      
      
      
      out.close();
   }
   
   public static int count(String s, int i, char c){
      int count = 0;
      int n = s.length();
      char[] array = new char[n];
      for(int k = 0; k < i; k++){
         if(s.charAt(k) != c) count++;
         array[k] = c;
         c = next.get(c);
      }
      for(int k = i; k < n; k++){
         array[k] = c;
         c = next.get(c);
      }
      
      int min = count;
      for(int k = 0; k < n-i; k++){
         if(s.charAt(k) != array[k]) count--;
         if(s.charAt(k+i) != array[k+i]) count++;
         min = Math.min(min,count);
      }
      
      
      return min;
   }
      
   
}
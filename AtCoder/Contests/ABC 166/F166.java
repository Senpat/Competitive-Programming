//make sure to make new file!
import java.io.*;
import java.util.*;

public class F166{
   
   public static ArrayList<Character> answer;
   public static long a;
   public static long b;
   public static long c;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      a = Long.parseLong(st.nextToken());
      b = Long.parseLong(st.nextToken());
      c = Long.parseLong(st.nextToken());
      
      answer = new ArrayList<Character>();
      
      String[] array = new String[n];
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      
      for(int k = 0; k < n; k++){
         String s = array[k];
         
         if(s.equals("AB")){
            if(a == 0 && b == 0){
               out.println("No");
               out.close();
               return;
            }
            
            if(a > b){
               calc('B','A');
            } else if(a < b){
               calc('A','B');
            } else {
               if(a==1 && k < n-1 && (array[k+1].equals("AC") || array[k+1].equals("BC"))){
                  if(array[k+1].equals("AC")){
                     calc('A','B');
                  } else {
                     calc('B','A');
                  }
               } else {
                  calc('B','A');//random
               }
            }
         } else if(s.equals("AC")){
            if(a == 0 && c == 0){
               out.println("No");
               out.close();
               return;
            }
            
            if(a > c){
               calc('C','A');
            } else if(a < c){
               calc('A','C');
            } else {
               if(a==1 && k < n-1 && (array[k+1].equals("AB") || array[k+1].equals("BC"))){
                  if(array[k+1].equals("AB")){
                     calc('A','C');
                  } else {
                     calc('C','A');
                  }
               } else {
                  calc('C','A');//random
               }
            }
         } else {
            if(b == 0 && c == 0){
               out.println("No");
               out.close();
               return;
            }
            
            if(b > c){
               calc('C','B');
            } else if(b < c){
               calc('B','C');
            } else {
               if(b==1 && k < n-1 && (array[k+1].equals("AB") || array[k+1].equals("AC"))){
                  if(array[k+1].equals("AB")){
                     calc('B','C');
                  } else {
                     calc('C','B');
                  }
               } else {
                  calc('C','B');//random
               }
            }
         }
      }
      
      out.println("Yes");
      for(char ch : answer){
         out.println(ch);
      }
      
      
      
      
      
      
      out.close();
   }
   
   public static void calc(char c1, char c2){
      answer.add(c1);
      if(c1 == 'A') a++;
      if(c1 == 'B') b++;
      if(c1 == 'C') c++;
      if(c2 == 'A') a--;
      if(c2 == 'B') b--;
      if(c2 == 'C') c--;
      
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class E603{

   public static int[] bits;
   public static int n;
   public static int BUFFER = 5;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      char[] array = f.readLine().toCharArray();
      bits = new int[n+BUFFER+1];
      
      HashSet<Integer> openinds = new HashSet<Integer>();
      char[] board = new char[n+BUFFER];
      Arrays.fill(board,' ');
      int index = 1;
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         char c = array[k];
         
         if(c == 'L'){
            index = Math.max(1,index-1);
         } else if(c == 'R'){
            index++;
         } else if(c == '('){
            openinds.add(index);
            
            if(board[index] == ')'){
               update(index,2);
            } else if(board[index] != '('){
               update(index,1);
            }
            
            board[index] = c;
            
         } else if(c == ')'){
            if(openinds.contains(index)) openinds.remove(index);
            
            if(board[index] == '('){
               update(index,-2);
            } else if(board[index] != ')'){
               update(index,-1);
            }
            
            board[index] = c;
         } else {
            if(openinds.contains(index)) openinds.remove(index);
            
            if(board[index] == '('){
               update(index,-1);
            } else if(board[index] == ')'){
               update(index,1);
            }
            
            board[index] = c;
         }
         
         if(psum(n+BUFFER) == 0){
            int max = 0;
            
            for(int i : openinds){
               max = Math.max(max,psum(i));
            }
            
            sj.add("" + max);
            
         } else {
            sj.add("" + (-1));
         }
         
      }
      
      out.println(sj);
      
      
      
      out.close();
   }
   
   public static void update(int i, int x){
      for(; i <= n+BUFFER; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }

   
      
}
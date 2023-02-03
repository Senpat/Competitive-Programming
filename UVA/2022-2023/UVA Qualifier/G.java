//make sure to make new file!
import java.io.*;
import java.util.*;

public class G{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      HashSet<String> ops = new HashSet<String>();
      ops.add("*");
      ops.add("+");
      ops.add("-");
      
      int q = 1;
      while(true){
         String sin = f.readLine();
         
         if(sin == null) 
            break;
         
         StringTokenizer st = new StringTokenizer(sin);
         
         Stack<String> stack = new Stack<String>();
         
         while(st.hasMoreTokens()){
            String s = st.nextToken();
            if(!isdigit(s)){
               stack.push(s);
            } else {
               while(true){
                  if(stack.size() >= 2){
                     String p1 = stack.pop();
                     String op = stack.pop();
                  
                     if(isdigit(p1) && ops.contains(op)){
                        long i1 = Long.parseLong(p1);
                        long i2 = Long.parseLong(s);
                        if(op.equals("*")){
                           s = "" + (i1*i2);
                        } else if(op.equals("+")){
                           s = "" + (i1+i2);
                        } else if(op.equals("-")){
                           s = "" + (i1-i2);
                        }
                     } else {
                        stack.push(op);
                        stack.push(p1);
                        stack.push(s);
                        break;
                     }
                  } else {
                     stack.push(s);
                     break;
                  }
               }
            }
         }
         
         ArrayList<String> alist = new ArrayList<String>();
         while(!stack.isEmpty()){
            alist.add(stack.pop());
         }
         
         StringJoiner sj = new StringJoiner(" ");
         sj.add("Case");
         sj.add("" + q + ":");
         for(int k = alist.size()-1; k >= 0; k--){
            sj.add(alist.get(k));
         }
         out.println(sj.toString());
         q++;
         
      }
      
      
      
      
      
      
      
      out.close();
   }
    
   
   public static boolean isdigit(String s){
      if(s.length() >= 2) 
         return true;
      return Character.isDigit(s.charAt(0));
   }
   
      
}
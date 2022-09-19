//make sure to make new file!
import java.io.*;
import java.util.*;

public class M{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      //PrintWriter out = new PrintWriter(new FileWriter("Mout.txt"));
      
      //generate answer
      
      ArrayList<String> a5 = new ArrayList<String>();
      for(char ch = 'a'; ch < 'z'; ch++){
         char nx = (char)((int)ch+1);            //next char
         a5.add("" + ch + ch + ch + ch + ch);
         a5.add("" + nx + ch + ch + ch + ch);
         a5.add("" + nx + nx + ch + ch + ch);
         a5.add("" + nx + nx + nx + ch + ch);
         a5.add("" + nx + nx + nx + nx + ch);
         
         
      
      }
      a5.add("zzzzz");
      int len5 = a5.size();
      //out.println(len5);
      
      ArrayList<String> answer = new ArrayList<String>();
      
      int i1 = 0;
      int i2 = 0;
      
      String tr = "b";                   //transition
      
      while(i2 < len5-2){
         //out.println(i1 + " " + i2);
         if(i1 == len5-1){
            answer.add("zzzzz" + a5.get(i2));
            answer.add("zz" + tr + "zz" + a5.get(i2));
            answer.add("z" + tr + tr + "zz" + a5.get(i2));
            answer.add(tr + tr + tr + "zz" + a5.get(i2));
            answer.add(tr + tr + tr + "zz" + a5.get(i2+1));
            answer.add(tr + tr + tr + "zz" + a5.get(i2+2));
            answer.add("a" + tr + tr + "zz" + a5.get(i2+2));
            answer.add("aa" + tr + "zz" + a5.get(i2+2));
            answer.add("aaazz" + a5.get(i2+2));
            answer.add("aaaaz" + a5.get(i2+2));
            i1 = 0;
            i2 += 2;
            if(tr.equals("b")) tr = "c";
            else tr = "b";
         } else {
            answer.add(a5.get(i1) + a5.get(i2));
            i1++;
         }
      }
         
      
      
      
      
      int n = Integer.parseInt(f.readLine());
      
      for(int k = 0; k < n; k++){
         out.println(answer.get(k));
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
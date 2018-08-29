import java.io.*;
import java.util.*;

class cowcode{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("cowcode.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      String word = st.nextToken();
      long num = Long.parseLong(st.nextToken());
      
      out.println(parse(word,num-1));
      out.close();
      
      
      
   }

   public static char parse(String word, long index){
      if(index<word.length()){
         return word.charAt((int)index);
      }
      
      long length = word.length();
      while(length*2 <= index){
         length*=2;
      }
      if(length == index){
         return parse(word,length-1);
      }
      return parse(word,index-length-1);
   }





}
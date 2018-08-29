/*
USER: pgz11901
TASK: lamps
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class lamps{

   public static ArrayList<Integer> on, off;
   public static ArrayList<String> result;
   
   public static int c;

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      c = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      on = new ArrayList<Integer>();
      while(st.hasMoreTokens()){
         int i = Integer.parseInt(st.nextToken());
         if(i!=-1) on.add(i);
      }
      
      st = new StringTokenizer(f.readLine());
      off = new ArrayList<Integer>();
      while(st.hasMoreTokens()){
         int i = Integer.parseInt(st.nextToken());
         if(i!=-1) off.add(i);
      }
      
      result = new ArrayList<String>();
      
      String lamp = "";
      for(int k = 0; k < n; k++){
         lamp+="1";
      }
      
      if(c%2==0){
         if(c>=4){
            add(change(lamp,true,true,true,true));
         } if(c>=2){
            add(change(lamp,false,false,true,true));
            add(change(lamp,false,true,false,true));
            add(change(lamp,false,true,true,false));
            add(change(lamp,true,false,false,true));
            add(change(lamp,true,false,true,false));
            add(change(lamp,true,true,false,false));
         }
         
         add(lamp);
      
      
      } else {
         if(c>=3){
            add(change(lamp,false,true,true,true));
            add(change(lamp,true,false,true,true));
            add(change(lamp,true,true,false,true));
            add(change(lamp,true,true,true,false));
         }
         if(c>=1){
            add(change(lamp,true,false,false,false));
            add(change(lamp,false,true,false,false));
            add(change(lamp,false,false,true,false));
            add(change(lamp,false,false,false,true));
         }
      
      
      
      }
      
      if(result.size() == 0){
         System.out.println("IMPOSSIBLE");
         out.println("IMPOSSIBLE");
      } else {
         String[] array = new String[result.size()];
         result.toArray(array);
         Arrays.sort(array);
         for(int k = 0; k < array.length; k++){
            System.out.println(array[k]);
            out.println(array[k]);
         }
      }
      
      out.close();
      
   }
   
   public static String change(String s, boolean b1, boolean b2, boolean b3, boolean b4){
      String str = s;
      if(b1) str = do1(str);
      if(b2) str = do2(str);
      if(b3) str = do3(str);
      if(b4) str = do4(str);
      return str;
   }
   
   public static void add(String s){
      if(!result.contains(s)){
         for(int i : on) if(s.charAt(i-1) == '0') return;
         for(int i : off) if(s.charAt(i-1) == '1') return;
      }
      result.add(s);
   }

   
   public static String do1(String s){
      String str = "";
      for(int k = 0; k < s.length(); k++){
         if(s.charAt(k) == '0') str+="1";
         else str+="0";
      }
      return str;
   }
   public static String do2(String s){
      String str = "";
      for(int k = 0; k < s.length(); k++){
         if(k%2==0){
            if(s.charAt(k) == '0') str+="1";
            else str+="0";
         } else {
            str+=s.charAt(k);
         }
      }
      return str;
   }
   public static String do3(String s){
      String str = "";
      for(int k = 0; k < s.length(); k++){
         if(k%2==1){
            if(s.charAt(k) == '0') str+="1";
            else str+="0";
         } else {
            str+=s.charAt(k);
         }
      }
      return str;
   }
   public static String do4(String s){
      String str = "";
      for(int k = 0; k < s.length(); k++){
         if(k%3==0){
            if(s.charAt(k) == '0') str+="1";
            else str+="0";
         } else {
            str+=s.charAt(k);
         }
      }
      //System.out.println(s + " " + str);
      return str;
   }
   
   
   
}
      
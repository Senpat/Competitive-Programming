import java.util.Scanner;
import java.io.*;
import java.util.*;
public class Barcode {
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
      String barcode = s.nextLine();
      
      System.out.println(check(barcode)); 
        

	}
   
   public static boolean check(String s){
      String[] array = s.split(" ");
      
      int sum = 0;
      
      HashMap<String,Integer> hmap = new HashMap<String,Integer>();
      hmap.put(":::||",1);
      hmap.put("::|:|",2);
      hmap.put("::||:",3);
      hmap.put(":|::|",4);
      hmap.put(":|:|:",5);
      hmap.put(":||::",6);
      hmap.put("|:::|",7);
      hmap.put("|::|:",8);
      hmap.put("|:|::",9);
      hmap.put("||:::",0);
      
      for(int k = 0; k < array.length; k++){
         if(!hmap.containsKey(array[k])) return false;
         sum += hmap.get(array[k]);
      }
      
      return sum%10 == 0;
   }


}

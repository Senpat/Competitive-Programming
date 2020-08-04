import java.io.*
import java.util.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val s = f.readLine()

		if(s.length <= 3){
			println(s)
		} else if(s.length <= 6){
			var thousands = s.substring(0,s.length-3).toInt()
			if(Character.getNumericValue(s[s.length-3]) >= 5) thousands++

			if(thousands >= 1000){
				println("1M")
			} else {
				println("${thousands}K")
			}
		} else {
			var millions = s.substring(0,s.length-6).toInt()
			if(Character.getNumericValue(s[s.length-6]) >= 5) millions++

			println("${millions}M")
		}
	}
}

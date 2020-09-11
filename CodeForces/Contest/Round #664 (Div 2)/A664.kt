import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		var array = f.readLine().split(" ").map{it.toInt()}
		//r,g,b,w

		var numeven = 0
		var numodd = 0
		var first30 = false
		for(k in 0 until 4){
			if(array[k] % 2 == 0) numeven++
			else numodd++
			if(k < 3 && array[k] == 0) first30 = true
		}

		if(numodd <= 1){
			println("Yes")
		} else if(numeven <= 1 && !first30){
			println("Yes")
		} else {
			println("No")
		}

	}
}

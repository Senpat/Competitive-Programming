import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val a = f.readLine().toCharArray().sorted().toCharArray()
		val b = f.readLine().toCharArray().sorted().toCharArray()

		val count = IntArray(26){0}
		var fail = false
		for(k in 0 until n){
			if(a[k] > b[k]){
				fail = true
				break
			} else {
				for(j in a[k].toInt()-97 until b[k].toInt()-97){
					count[j]++
				}
			}

		}

		fun check() : Boolean{
			for(k in 0 until 26){
				if(count[k]%m != 0) return false
			}
			return true
		}

		if(check() && !fail){
			println("Yes")
		} else {
			println("No")
		}
	}
}

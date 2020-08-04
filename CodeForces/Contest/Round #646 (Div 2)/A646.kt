import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val array = f.readLine().split(" ").map{it.toInt()}

		if(n==m){
			var sum = 0
			for(i in array) sum += i
			if(sum % 2 == 1){
				println("Yes")
			} else {
				println("No")
			}
		} else {
			var odd = false
			var even = false
			for(i in array){
				if(i%2 == 1){
					odd = true
				} else {
					even = true
				}
			}
			if((m%2 == 0 && odd && even) || (m%2 == 1 && odd)){
				println("Yes")
			} else {
				println("No")
			}
		}
	}
}

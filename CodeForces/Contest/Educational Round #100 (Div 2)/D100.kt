import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}

		//get lower bound

		var mins = 0
		var seenunused = 0
		var ai = 0
		for(k in 1..(2*n)){
			if(ai < n && array[ai] == k){
				ai++
				if(seenunused > 0) seenunused--
				else mins++
			} else {
				seenunused++
			}
		}

		//get upper bound

		var maxs = 0
		seenunused = 0
		ai = n-1
		for(k in (2*n) downTo 1){
			if(ai >= 0 && array[ai] == k){
				ai--
				if(seenunused > 0){
					maxs++
					seenunused--
				}
			} else {
				seenunused++
			}
		}

		println(maxs-mins+1)
	}
}

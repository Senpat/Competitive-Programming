import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}

		var xor = 0
		for(k in 0 until n){
			xor = xor xor array[k]
		}

		if(xor == 0){
			println("YES")
		} else {
			var gotfirst = false
			var gotsecond = false

			var curxor = 0
			for(k in 0 until n){
				curxor = curxor xor array[k]
				if(curxor == xor){
					if(!gotfirst) gotfirst = true
					else if(!gotsecond) gotsecond = true
					else continue
					curxor = 0
				}
			}

			if(gotfirst && gotsecond && curxor == xor){
				println("YES")
			} else {
				println("NO")
			}
		}
	}
}

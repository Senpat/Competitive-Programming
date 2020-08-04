import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val array = f.readLine().split(" ").map{it.toInt()}

		val bits = IntArray(35){0}

		for(k in 0 until n){
			var i = array[k]
			var num = 0
			while(i > 0){
				if(i and 1 == 1) bits[num]++
				i = i shr 1
				num++
			}
		}

		//get most significant odd bit
		var sigbit = -1
		for(k in 34 downTo 0){
			if(bits[k]%2 == 1){
				sigbit = k
				break
			}
		}

		if(sigbit == -1){
			println("Draw")
			continue
		}

		var thresh = 1 shl sigbit
		var count = 0
		var numexact = 0
		for(k in 0 until n){
			if(array[k] and thresh == thresh) count++
		}

		if(count == 1 || (n-count)%2 == 1 || (count-1)%4 == 0) println("Win")
		else println("Lose")

	}
}

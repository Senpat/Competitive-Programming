import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}.sorted()

		fun check(x : Int) : Boolean{

			val pq = PriorityQueue<Int>()

			for(k in 0 until n){
				pq.add((array[k] xor x))
				//println("${array[k]} $x ${(array[k] xor x)}")
			}

			for(k in 0 until n){
				if(array[k] != pq.poll()) return false
			}
			return true
		}

		var found = false
		for(k in 1 until 1024){
			if(check(k)){
				println(k)
				found = true
				break
			}
		}

		if(!found){
			println(-1)
		}

	}
}

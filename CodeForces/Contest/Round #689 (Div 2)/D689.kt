import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val array = f.readLine().split(" ").map{it.toLong()}.toMutableList()

		val hset = HashSet<Long>()

		fun divide(a : MutableList<Long>){
			var min = 10000000L
			var max = 0L
			var sum = 0L
			for(k in 0 until a.size){
				min = min(min,a[k])
				max = max(max,a[k])
				sum += a[k]
			}
			hset.add(sum)

			if(max == min) return

			var mid = (min+max)/2L
			val left = mutableListOf<Long>()
			val right = mutableListOf<Long>()

			for(k in 0 until a.size){
				if(a[k] <= mid) left.add(a[k])
				else right.add(a[k])
			}

			divide(left)
			divide(right)
		}

		divide(array)

		for(k in 0 until m){
			val i = f.readLine().toLong()
			if(hset.contains(i)) println("Yes")
			else println("No")
		}
	}
}

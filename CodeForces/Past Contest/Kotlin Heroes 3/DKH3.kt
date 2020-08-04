import java.io.*
import java.util.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		var (n,m) = f.readLine().split(" ").map{it.toInt()}

		val array = f.readLine().split(" ").map{it.toInt()}.toMutableList()			//toMutableList() instead of IntArray() to avoid quicksort hack
		val hmap = HashMap<Int,Int>()
		for(k in 0 until n){
			hmap.put(array[k],k)
		}
		array.sort()
		val stoa = Array<Int>(n){j -> hmap.get(array[j])!!}					//convert index from sorted to array

		val answer = IntArray(n)
		for(k in n-2 downTo 0){
			val i = array[n-1]-(n-k-1) - array[k]

			if(m <= i){
				answer[stoa[k]] = m
				m = 0
				break
			} else {
				answer[stoa[k]] = i
				m-=i
			}
		}

		for(k in 0 until n){
			answer[k] += m/n
		}

		for(k in n-m%n until n){
			answer[stoa[k]] ++
		}

		println(answer.joinToString(" "))

	}
}

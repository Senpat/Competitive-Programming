import java.io.*
import java.util.*
import kotlin.math.*
//COCI '14 Contest 2 #3 Studentsko
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val array = f.readLine().split(" ").map{it.toInt()}.toIntArray()
	val tosort = Array<Pair<Int,Int>>(n){j -> Pair(array[j],j)}

	tosort.sortWith(compareBy({it.first},{it.second}))

	val order = IntArray(n){0}
	for(k in 0 until n){
		order[tosort[k].second] = k/m+1
	}

	//get LIS of order, answer is n-LIS (longest non-decreasing sequence for this problem)
	//maintain lis increasing order
	val lis = mutableListOf<Int>()
	lis.add(-1)
	lis.add(order[0])

	for(k in 1 until n){
		var l = 1
		var r = lis.size-1
		var ans = lis.size

		while(l <= r){
			val mid = l + (r-l)/2
			if(lis[mid] > order[k]){
				ans = mid
				r = mid-1
			} else {
				l = mid+1
			}
		}

		if(ans == lis.size) lis.add(order[k])
		else lis[ans] = order[k]

		//println(lis.joinToString(" "))
	}

	val answer = n-lis.size+1
	println(answer)
}

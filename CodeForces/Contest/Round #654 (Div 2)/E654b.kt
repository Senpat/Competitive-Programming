import java.io.*
import java.util.*
import kotlin.math.*
//solves E2 version
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,p) = f.readLine().split(" ").map{it.toInt()}
	//val p : Long = pi.toLong()
	val array = f.readLine().split(" ").map{it.toInt()}.toIntArray().sorted()

	val answer = mutableListOf<Int>()

	var minanswer = array[0]

	for(k in 0 until n){
		minanswer = max(minanswer,array[k]-k)
	}

	fun check(x : Int) : Boolean{
		//number of numbers < x+k
		var i = 0

		while(i < n && array[i] <= x){
			i++
		}

		if(i % p == 0) return false
		//prod*=i

		for(k in 1 until n){
			while (i < n && array[i] <= x+k){
				i++
			}

			val mul = i-k
			if(mul % p == 0) return false
			//prod *= mul

		}

		return true
	}

	var l = minanswer
	var r = max(n,array[n-1])
	var ans = -1
	while(l <= r){
		val mid = l + (r-l)/2

		if(check(mid)){
			ans = mid
			l = mid+1
		} else {
			r = mid-1
		}
	}

	if(ans != -1){
		for(k in minanswer..ans){
			answer.add(k)
		}
	}

	println(answer.size)
	println(answer.joinToString(" "))
}

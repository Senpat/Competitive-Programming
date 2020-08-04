import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD : Long = 998244353L

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val a = f.readLine().split(" ").map{it.toLong()}
	val b = f.readLine().split(" ").map{it.toLong()}

	val sufmax = LongArray(n)
	sufmax[n-1] = a[n-1]
	for(k in n-2 downTo 0){
		sufmax[k] = min(sufmax[k+1],a[k])
	}

	if(sufmax[0] != b[0]){
		println(0)
		return
	}

	var l = 0
	var r = 0

	var answer = 1L

	for(k in 1 until m){
		while(l < n && sufmax[l] < b[k]){
			l++
		}

		if(l == n || sufmax[l] != b[k]){
			answer = 0L
			break
		}

		r = l
		while(r < n-1 && sufmax[r+1] == b[k]){
			r++
		}

		/*
		if((l == n || r == n-1)){
			answer = 0L
			break
		}*/

		answer = (answer * (r-l+1).toLong() + MOD)%MOD

		l = r


	}

	println(answer)
}

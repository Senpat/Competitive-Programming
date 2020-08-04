import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val a = f.readLine().split(" ").map{it.toInt()}
	val b = f.readLine().split(" ").map{it.toInt()}

	val indexofb = IntArray(n+1)

	for(k in 0 until n){
		indexofb[b[k]] = k
	}

	val freq = IntArray(n)

	for(k in 0 until n){
		val i = indexofb[a[k]]
		if(i >= k){
			freq[i-k]++
		} else {
			freq[n-(k-i)]++
		}
	}

	var max = 0
	for(k in 0 until n){
		max = max(max,freq[k])
	}

	println(max)
}

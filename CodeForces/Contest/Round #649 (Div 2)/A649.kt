import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,x) = f.readLine().split(" ").map{it.toInt()}
		val array = f.readLine().split(" ").map{it.toInt()}

		var sum = 0
		var hasnox = false
		for(k in 0 until n){
			sum += array[k]
			if(array[k]%x != 0) hasnox = true
		}

		if(sum % x != 0){
			println(n)
		} else if(hasnox){
			//closest to left edge
			var i1 = -1
			for(k in 0 until n){
				if(array[k]%x != 0){
					i1 = k
					break
				}
			}

			var i2 = -1
			for(k in n-1 downTo 0){
				if(array[k]%x != 0){
					i2 = k
					break
				}
			}

			var sub = min(i1,n-i2-1)
			println(n-sub-1)
		} else{
			println(-1)
		}
	}
}

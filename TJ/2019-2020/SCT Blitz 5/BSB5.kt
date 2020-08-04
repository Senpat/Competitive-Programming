import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	var (n,m) = f.readLine().split(" ").map{it.toInt()}

	val array = f.readLine().split(" ").map{it.toInt()}

	var i = 1

	val mbits = HashSet<Int>()

	while(i >= 0 && i <= m){
		if(m and i == i) mbits.add(i)
		i = i shl 1
	}

	var excessbits = 0
	var repeats = 0
	val arrayhas = HashSet<Int>()
	for(k in 0 until n){
		i = 1
		while(i >= 0 && i <= array[k]){
			if(array[k] and i == i){
				if(mbits.contains(i)){
					if(arrayhas.contains(i)){
						repeats++
					} else {
						arrayhas.add(i)
					}
				}
				else {
					excessbits++
				}
			}
			i = i shl 1
		}
	}

	//println("${mbits.size} ${arrayhas.size} $excessbits $repeats")
	if(repeats + excessbits + arrayhas.size < mbits.size){
		println(-1)
	} else {
		if(mbits.size-arrayhas.size <= excessbits){
			println(excessbits)
		} else {
			println(mbits.size-arrayhas.size)
		}
	}
}

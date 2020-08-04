import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toLong()}

	var zero = false
	for(i in array){
		if(i == 0L) zero = true
	}

	if(zero){
		println(0)
	} else {
		var prod = 1L
		for(i in array){
			prod *= i
			if(prod > 1000000000000000000 || prod <= 0){
				prod = -1
				break
			}
		}
		println(prod)
	}
}

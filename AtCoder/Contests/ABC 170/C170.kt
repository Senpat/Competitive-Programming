import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (x,n) = f.readLine().split(" ").map{it.toInt()}
	if(n == 0){
		println(x)
		return
	}
	val array = f.readLine().split(" ").map{it.toInt()}.toIntArray().toHashSet()


	var ans = -2
	var min = 105

	for(k in -1..101){
		if(!array.contains(k)){
			if(abs(k-x) < min){
				ans = k
				min = abs(k-x)
			}
		}
	}

	println(ans)
}

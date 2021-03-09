import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toLong()}.toLongArray()

		var add = 0L
		var fail = false
		for(k in 0 until n){
			if(array[k]+add < k){
				fail = true
				break
			}

			add += array[k]-k
		}

		if(fail){
			println("NO")
		} else {
			println("YES")
		}
	}
}

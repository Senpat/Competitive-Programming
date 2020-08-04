import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))


	val n = f.readLine().toInt()

	if(n%2 == 0){
		println(-1)
	} else {
		var i = (7+n)%n
		var answer = 1
		var found = false
		for(k in 1 until 1000005){
			if(i%n == 0){
				println(k)
				found = true
				break
			}

			i = (i*10+7+n)%n
		}

		if(!found) println(-1)
	}


}

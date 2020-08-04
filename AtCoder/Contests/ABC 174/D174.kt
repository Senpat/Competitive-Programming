import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().toCharArray()

	var totalr = 0
	for(k in 0 until n) if(array[k] == 'R') totalr++

	var rafter = totalr
	var wbefore = 0
	var min = n+5
	for(k in 0 until n+1){
		min = min(min,max(rafter,wbefore))
		if(k < n){
			if(array[k] == 'W') wbefore++
			else rafter--
		}
	}

	println(min)
}

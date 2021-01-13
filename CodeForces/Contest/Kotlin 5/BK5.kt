import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val s = f.readLine()
		val n = s.length
		//get indices of w
		val ws = mutableListOf<Int>()
		for(k in 0 until n){
			if(s[k] == 'w'){
				ws.add(k)
			}
		}

		var answer = ws.size
		for(k in 0 until ws.size){
			if(k == 0){
				answer += ws[k]/2
			} else {
				answer += (ws[k]-ws[k-1]-1)/2
			}
		}

		if(ws.size >= 1){
			answer += (n-ws[ws.size-1]-1)/2
		} else {
			answer += n/2
		}

		println(answer)
	}
}

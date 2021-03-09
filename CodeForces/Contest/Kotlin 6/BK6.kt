import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val s = f.readLine().toCharArray()

		val stk = Stack<Char>()
		var op2used = 0

		var answer = 0
		for(k in 0 until n){
			if(s[k] == '('){
				stk.push(s[k])
			} else {
				stk.pop()
				if(stk.isEmpty()){
					answer++
				} else {
					if(op2used < m){
						answer++
						op2used++
					}
				}
			}
		}

		println(answer)

	}
}

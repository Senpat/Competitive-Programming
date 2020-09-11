import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().map{Character.getNumericValue(it)}.toIntArray()

		val answer = IntArray(n)
		var curindex = 1

		val need0 : Queue<Int> = LinkedList<Int>()
		val need1 : Queue<Int> = LinkedList<Int>()

		for(k in 0 until n){
			if(array[k] == 0){
				if(need0.isEmpty()){
					answer[k] = curindex
					need1.add(curindex)
					curindex++
				} else {
					val i = need0.poll()
					answer[k] = i
					need1.add(i)
				}
			} else {
				if(need1.isEmpty()){
					answer[k] = curindex
					need0.add(curindex)
					curindex++
				} else {
					val i = need1.poll()
					answer[k] = i
					need0.add(i)
				}
			}
		}

		println(curindex-1)
		println(answer.joinToString(" "))
	}
}

import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (a,b,c,d) = f.readLine().split(" ").map{it.toInt()}

	val hset = HashSet<Int>()
	hset.add(a)
	hset.add(b)
	hset.add(c)
	hset.add(d)

	if(hset.size == 1) println(1)
	if(hset.size == 3) println(3)
	if(hset.size == 4) println(6)
	if(hset.size == 2){
		var count = 0
		if(b == a) count++
		if(c == a) count++
		if(d == a) count++
		if(count == 2 || count == 0) println(1)
		else println(2)
	}
}

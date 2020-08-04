import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val array = f.readLine().split(" ").map{it.toInt()}.toIntArray().sorted()

		if(array[2] == array[1]){
			println("YES")
			println("${array[2]} ${array[0]} ${array[0]}")
		} else {
			println("NO")
		}
	}
}

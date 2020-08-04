import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val 进 = BufferedReader(InputStreamReader(System.`in`))

	for(测试 in 1..进.readLine().toInt()){
		val 数组 = 进.readLine().split(" ").map{it.toInt()}.toIntArray().sorted()

		if(数组[1] == 数组[2]){
			println("YES")
			println("${数组[2]} ${数组[0]} ${数组[0]}")
		} else {
			println("NO")
		}
	}
}

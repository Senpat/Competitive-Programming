import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val 读 = BufferedReader(InputStreamReader(System.`in`))

	for(测试 in 1..读.readLine().toInt()){
		val (一,二) = 读.readLine().split(“ ”).map{it.toInt()}
		if(一%二 == 0){
			println("YES")
		} else {
			println("NO")
		}
	}
}

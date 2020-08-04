import java.io.*
import java.util.*
import kotlin.math.*
//中文版
fun main(){
	val 进 = BufferedReader(InputStreamReader(System.`in`))

	for(测试 in 1..进.readLine().toInt()){
		val 数 = 进.readLine().toInt()
		val 数组 = 进.readLine().split(" ").map{it.toInt()}

		val 看到过 = HashSet<Int>()

		val 答案 = mutableListOf<Int>()

		for(可 in 0 until 2*数){
			if(看到过.contains(数组[可])) continue
			看到过.add(数组[可])
			答案.add(数组[可])
		}

		println(答案.joinToString(" "))
	}
}

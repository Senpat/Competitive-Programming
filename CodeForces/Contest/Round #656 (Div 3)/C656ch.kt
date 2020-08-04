import java.io.*
import java.util.*
import kotlin.math.*
//中文版
fun main(){
	val 进 = BufferedReader(InputStreamReader(System.`in`))

	for(测试 in 1..进.readLine().toInt()){
		val 数 = 进.readLine().toInt()
		val 数组 = 进.readLine().split(" ").map{it.toInt()}

		fun 检查(点 : Int) : Boolean{
			var 开始 = 点
			var 结束 = 数-1

			var 以前 = -1
			while(开始 <= 结束){
				if(数组[开始] < 数组[结束]){
					if(以前 > 数组[开始]) return false
					以前 = 数组[开始]
					开始++
				} else {
					if(以前 > 数组[结束]) return false
					以前 = 数组[结束]
					结束--
				}
			}

			return true
		}

		var 左 = 0
		var 右 = 数-1
		var 答案 = 数-1

		while(左 <= 右){
			var 中 = 左 + (右-左)/2

			if(检查(中)){
				答案 = 中
				右 = 中-1
			} else {
				左 = 中+1
			}
		}

		println(答案)
	}
}

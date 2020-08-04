import java.io.*
import java.util.*
import kotlin.math.*
//stupid stuff
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD : Long = 1000000007L

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()



		val rdb = Array(n+1){LongArray(3){0L}}

		rdb[1][0] = 1L

		for(k in 2 until n){
			if(k == 2){
				rdb[k][0] = rdb[k-1][0]
				rdb[k-1][1] = (rdb[k-1][1] + rdb[k-1][0] + MOD)%MOD
				rdb[k-1][0] = 0L
			} else if(k == 3){
				rdb[k][0] = rdb[k-1][0]
				rdb[k-1][1] = (rdb[k-1][1] + rdb[k-1][0] + MOD)%MOD
				rdb[k-1][0] = 0L

				rdb[k-1][0] = (rdb[k-2][1]*2 + MOD)%MOD
				rdb[k-2][2] = (rdb[k-2][2] + rdb[k-2][1] + MOD)%MOD
				rdb[k-2][1] = 0L

			} else {
				rdb[k][0] = rdb[k-1][0]
				rdb[k-1][1] = rdb[k-1][0]
				rdb[k-1][0] = (rdb[k-2][0] + rdb[k-2][1]*2 + MOD)%MOD
				rdb[k-2][2] = (rdb[k-2][2] + rdb[k-2][1] + MOD)%MOD
				rdb[k-2][1] = rdb[k-2][0]
				rdb[k-2][0] = (rdb[k-3][0] + rdb[k-3][1]*2 + MOD)%MOD
				rdb[k-3][2] = (rdb[k-3][2] + rdb[k-3][1] + MOD)%MOD
				rdb[k-3][1] = 0L
			}
		}

	}
}

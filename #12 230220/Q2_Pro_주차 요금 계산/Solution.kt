data class Car(val id: String) {
    var inTime: String = "00:00"  // 입차 시간
    var outTime: String = "23:59" // 출차 시간
    var totalMinutesOfUse = 0     // 누적 주차 시간

    // 출차 시 주차한 시간
    val minutesOfUse: Int
        get() = timeToMinute(outTime) - timeToMinute(inTime)

    // hh:mm 꼴의 문자열을 분 단위로 치환
    fun timeToMinute(timeStamp: String): Int {
        val (hour, minute) = timeStamp.split(":").map { it.toInt() }
        return hour * 60 + minute
    }

    // 요금 계산
    fun getFee(defaultTime: Int, defaultFee: Int, unitTime: Int, unitFee: Int): Int {
        return defaultFee + if (totalMinutesOfUse > defaultTime) {
            val overTime = totalMinutesOfUse - defaultTime
            val flag = if (overTime % unitTime == 0) 0 else 1

            (flag + overTime / unitTime) * unitFee
        } else 0
    }
}

class Solution {

    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val (defaultTime, defaultFee, unitTime, unitFee) = fees
        val cars = hashMapOf<String, Car>() // 한 번이라도 주차장을 이용한 자동차를 기록할 map
        val parkingLot = hashSetOf<Car>()   // 주차장을 표현할 set

        records.forEach { record ->
            val (timeStamp, id, action) = record.split(" ")
            val car = cars[id] ?: Car(id).also { cars[id] = it } // map에서 car를 꺼냄. 만약 없으면 생성

            // 입차 기록
            if (action == "IN") parkingLot.add(car.apply {
                inTime = timeStamp
                outTime = "23:59"
            })
            // 출차 기록 (출차 시에는 이용 시간을 누적한다)
            else parkingLot.remove(car.apply {
                outTime = timeStamp
                totalMinutesOfUse += minutesOfUse
            })
        }

        // 주차장에 남아있는 차들의 이용 시간을 누적한다
        parkingLot.forEach { it.totalMinutesOfUse += it.minutesOfUse }

        return cars.values
            .sortedBy { it.id }
            .map {it.getFee(defaultTime, defaultFee, unitTime, unitFee)}
            .toIntArray()
    }
}
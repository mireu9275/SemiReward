package kr.eme.semiReward.managers

import kr.eme.semiReward.objects.Mission

object MissionManager {
    private val missionMap = HashMap<Int, Mission>()

    init {
        missionMap[1] = Mission("Mission 1 - Killed Pig","돼지 5마리를 사냥하세요!")
        missionMap[2] = Mission("Mission 2 - Killed Cow","소 5마리를 사냥하세요!")
    }

    fun getMission(id: Int): Mission? = missionMap[id]
    fun getMissionList(): List<Mission> = missionMap.values.toList()
}
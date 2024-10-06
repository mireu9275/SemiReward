package kr.eme.semiReward.managers

import kr.eme.semiReward.objects.Mission

object MissionManager {
    private val missionMap = HashMap<Int, Mission>()

    init {
        missionMap[1] = Mission("Mission 1","거래 모듈에서 분쇄기 모듈을 구매 후 설치하세요!")
        missionMap[2] = Mission("Mission 2","디바이스에서 농사 모듈 1개를 증설하세요!")
        missionMap[3] = Mission("Mission 3","농사 모듈에 감자 씨앗을 5개 이상 심으세요!")
        missionMap[4] = Mission("Mission 4","디바이스에서 십자 모듈을 원하는 곳에 증설하세요!")
        missionMap[5] = Mission("Mission 5","십자 모듈과 이어지는 광산 모듈을 증설하세요!")
        missionMap[6] = Mission("Mission 6","광산으로 내려가 아무 광물을 5개 이상 채굴하세요!")
        missionMap[7] = Mission("Mission 7","분쇄기에 아무 광물이나 분해하세요!")
    }

    fun getMission(id: Int): Mission? = missionMap[id]
    fun getMissionList(): List<Mission> = missionMap.values.toList()
}
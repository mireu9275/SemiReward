package kr.eme.semiReward.managers

import kr.eme.semiReward.objects.PlayerMissionData
import org.bukkit.entity.Player

object MissionManager {
    private val missionMap = mutableMapOf<Player, PlayerMissionData>()

    /**
     * 플레이어의 미션 데이터를 불러옴.
     */
    fun loadPlayerMission(player: Player, missionId: String, progress: Int) {

    }

    /**
     * 미션 상태를 업데이트함.
     */
    fun updateMissionProgress(player: Player, missionId: String, progress: Int) {
        val missionData = missionMap[player] ?: return
        missionData.updateProgress(missionId, progress)
    }

    /**
     * 미션 완료 처리.
     */
    fun completeMission(player: Player, missionId: String) {
        val missionData = missionMap[player] ?: return
        missionData.completedMission(missionId)
    }
}
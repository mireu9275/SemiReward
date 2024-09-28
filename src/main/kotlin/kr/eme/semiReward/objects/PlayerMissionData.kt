package kr.eme.semiReward.objects

data class PlayerMissionData(
    val completedMission: MutableList<String> = mutableListOf(),
    val inProgressMissions: MutableMap<String, Int> = mutableMapOf()
) {
    fun updateProgress(missionId: String, progress: Int) {
        inProgressMissions[missionId] = progress
    }
    fun completedMission(missionId: String) {
        completedMission.add(missionId)
        inProgressMissions.remove(missionId)
    }
}

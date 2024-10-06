package kr.eme.semiReward.objects

import java.util.UUID

data class PlayerData(
    val uuid: UUID,
    val completedMissions: MutableSet<Int> = mutableSetOf(),
    var currentPage: Int = 0
)

package com.example.repositorys;

import com.example.MoonMission;

import java.util.List;

public interface MoonMissionRepository {
    List<String> listMissions();
    MoonMission getMissionById(int missionId);
    int countMissionsByYear(int year);
}

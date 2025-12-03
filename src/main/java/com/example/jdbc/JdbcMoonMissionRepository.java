package com.example.jdbc;

import com.example.MoonMission;
import com.example.repositorys.MoonMissionRepository;

import java.util.List;

public class JdbcMoonMissionRepository implements MoonMissionRepository {
    @Override
    public List<String> listMissions() {
        return List.of();
    }

    @Override
    public MoonMission getMissionById(int missionId) {
        return null;
    }

    @Override
    public int countMissionsByYear(int year) {
        return 0;
    }
}

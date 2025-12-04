package com.example.jdbc;

import com.example.MoonMission;
import com.example.SimpleDriverManagerDataSource;
import com.example.repositorys.MoonMissionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcMoonMissionRepository implements MoonMissionRepository {

    private final SimpleDriverManagerDataSource ds;

    public JdbcMoonMissionRepository(SimpleDriverManagerDataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<String> listMissions() {

        try (Connection connection = ds.getConnection()) {

            String sql = "select spacecraft from moon_mission;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<String> result = new ArrayList<>();

            while (rs.next()){
                result.add(rs.getString("spacecraft"));
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MoonMission getMissionById(int missionId) {
        String sql = "select * from moon_mission where mission_id = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);){

            ps.setInt(1, missionId);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    MoonMission mission = new MoonMission();
                    mission.setMissionId(rs.getInt("mission_id"));
                    mission.setSpacecraft(rs.getString("spacecraft"));
                    mission.setLaunchDate(rs.getDate("launch_date").toLocalDate());
                    mission.setCarrierRocket(rs.getString("carrier_rocket"));
                    mission.setOutcome(rs.getString("outcome"));
                    mission.setMissionType(rs.getString("mission_type"));
                    mission.setOperator(rs.getString("operator"));
                    return mission;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int countMissionsByYear(int year) {

        try (Connection connection = ds.getConnection()){

            String sql = "select count(*) from moon_mission as mission_count where year(launch_date) = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, year);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return rs.getInt("mission_count");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}

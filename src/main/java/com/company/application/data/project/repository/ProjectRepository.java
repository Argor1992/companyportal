package com.company.application.data.project.repository;

import com.company.application.data.project.entity.ProjectEntity;
import com.company.application.data.project.entity.ProjectState;
import com.company.application.domain.projectlist.data.ProjectOverview;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "projectManagers",
                    "projectClients",
            }
    )
    @Query(
            "select c from ProjectEntity c where c.id = :id"
    )
    Optional<ProjectEntity> findProjectByIdAndFetchEagerly(int id);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "projectManagers",
                    "projectClients"
            }
    )
    @Query(
            "select e from ProjectEntity e"
    )
    List<ProjectEntity> findAllEntitiesAndFetchProjects();

    List<ProjectOverview> findAllProjectOverviewsBy();

    Optional<ProjectOverview> findProjectOverviewById(int id);

    @Modifying
    @Query(
            "update ProjectEntity set name = :name, " +
                    "description = :description, " +
                    "amount = :amount, " +
                    "date = :date, " +
                    "projectState = :projectState, " +
                    "priority = :priority where id = :id"
    )
    int updateProjectOverview(@Param("id") int id,
                              @Param("name") String name,
                              @Param("description") String description,
                              @Param("amount") double amount,
                              @Param("date") LocalDate date,
                              @Param("projectState") ProjectState projectState,
                              @Param("priority") int priority);
}

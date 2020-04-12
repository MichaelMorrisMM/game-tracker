package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.GameProfile;
import com.michaelmorris.gametracker.model.Platform;
import com.michaelmorris.gametracker.model.Priority;
import com.michaelmorris.gametracker.model.Profile;
import com.michaelmorris.gametracker.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameProfileRepository extends PagingAndSortingRepository<GameProfile, Long> {

    void deleteAllByProfile(Profile profile);

    Page<GameProfile> findByProfile(Profile profile, Pageable pageable);

    @Query("select gp from GameProfile gp where gp.profile = :profile and gp.priority in :priorities and gp.status in :statuses")
    Page<GameProfile> findByCustomFilter(@Param("profile") Profile profile, @Param("priorities") List<Priority> priorities,
                                         @Param("statuses") List<Status> statuses, Pageable pageable);

    @Query("select gp from GameProfile gp where gp.profile = :profile and gp.priority in :priorities and gp.status in :statuses and :platform member of gp.platforms")
    Page<GameProfile> findByCustomFilterWithPlatform(@Param("profile") Profile profile, @Param("priorities") List<Priority> priorities,
                                                     @Param("statuses") List<Status> statuses, @Param("platform") Platform platform,
                                                     Pageable pageable);

}

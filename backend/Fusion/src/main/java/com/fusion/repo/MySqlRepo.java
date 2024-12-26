package com.fusion.repo;

import com.fusion.model.MySqlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySqlRepo extends JpaRepository<MySqlEntity, String> {
}

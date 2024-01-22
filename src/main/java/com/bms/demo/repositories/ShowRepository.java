package com.bms.demo.repositories;

import com.bms.demo.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Long> {

}

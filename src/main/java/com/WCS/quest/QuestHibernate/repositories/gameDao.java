package com.WCS.quest.QuestHibernate.repositories;


import com.WCS.quest.QuestHibernate.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface gameDao extends JpaRepository<Game, Long> {
}

package com.WCS.quest.QuestHibernate.utils;

import com.WCS.quest.QuestHibernate.entities.Game;
import com.WCS.quest.QuestHibernate.repositories.gameDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Outputter implements CommandLineRunner {

    private Logger LOG = LoggerFactory.getLogger("Game");
    @Autowired
    private gameDao gameDao;


    @Override
    public void run(String... args) throws Exception {
        LOG.info("Objects in DB : " + gameDao.count());

        // Crée un nouveau jeu et l'enregistre dans la BDD
        Game game1 = new Game("BroForce", 20, "Best multiplayer game");
        LOG.info("******************");
        LOG.info(game1 + " has been created !");
        gameDao.save(game1);
        LOG.info(game1 + " has been saved !");


        // Crée un second jeu et l'enregistre dans la BDD
        Game game2 = new Game("Assassin's Creed", 40, "Best solo game");
        LOG.info("******************");
        LOG.info(game2 + " has been created !");
        gameDao.save(game2);
        LOG.info(game2 + " has been saved !");

        // Updater les infos du premier jeu
        game1.setName("BroForce2");
        game1.setDescription("Suite of the bestgame ever");
        game1.setPrice(30);
        LOG.info("******************");
        LOG.info(game1 + " has been updated !");
        gameDao.save(game1);
        LOG.info(game1 + "has been saved");




        // Lit les informations correspondant au second jeu
        Game tempGame = gameDao.findById(2L).get(); /* On écrit "2L" car
                                                       le type de l'id est Long */
        LOG.info("******************");
        LOG.info("Second game's Name is " + tempGame.getName());
        LOG.info("Second game's description is " + tempGame.getDescription());
        LOG.info("Second game's price is " + tempGame.getPrice());

        // Liste les jeux enregistrés dans la BDD
        LOG.info("******************");
        LOG.info("Games in list are ");
        for(Game myGame : gameDao.findAll()) {
            LOG.info(myGame.toString());
        };

        // Supprime le second jeu de la BDD
        gameDao.deleteById(2L); /* risque de provoquer une erreur si
                                tu n'as pas vidé ta table avant de relancer
                                ton application ! */

        /*     Liste les utilisateurs enregistrés dans la BDD
             (permet de vérifier que le second utilisateur
             a bien été supprimé de la BDD) */
        LOG.info("******************");
        LOG.info("Users in list are ");
        for(Game myGame : gameDao.findAll()) {
            LOG.info(myGame.toString());
        };
    }
}
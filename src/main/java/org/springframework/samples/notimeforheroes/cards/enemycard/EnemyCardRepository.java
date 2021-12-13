package org.springframework.samples.notimeforheroes.cards.enemycard;


	
import java.util.Collection;

import org.springframework.data.repository.CrudRepository;



		
public interface EnemyCardRepository extends CrudRepository<EnemyCard, Integer>{

			
		Collection<EnemyCard> findAll();
			
			
}




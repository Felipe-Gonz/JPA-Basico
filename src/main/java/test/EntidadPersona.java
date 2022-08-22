
package test;

import com.mycompany.create.jpa.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.*;

public class EntidadPersona {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String [] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        //Iniciamos la Transaccion
        tx.begin();
        //Se manda los datos sin utilizar Id porque es autoincrementable
        Persona persona = new Persona("Luis", "Gonzalez", "lg.email.com", "431141234");
        log.debug("Objeto a persistir" + persona);
        //Persistimos el objeto
        em.persist(persona);
        //Se termina la transacion
        //Hacemos un commit para guardar los cambios
        tx.commit();
        log.debug("Objeto persistido" + persona);
        em.close();
    }
}

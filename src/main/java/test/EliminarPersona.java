package test;

import com.mycompany.create.jpa.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.*;

public class EliminarPersona {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Iniciamos la Transaccion
        tx.begin();
        //Se ejecuta SQL select
        Persona persona = em.find(Persona.class, 5);
        //Se termina la transacion
        tx.commit();

        //Objeto en estado de datached
        log.debug("Objeto recuperado" + persona);
                
        //Iniciar segunda Transaccion
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        
        //Eliminamos el objeto
        em.remove(em.merge(persona));
        
        //Termina la Transaccion 
        tx2.commit();
        
        //Objeto en estado datached ya eliminado
        log.debug("Objeto eliminado: " + persona);
        
               
        em.close();
    }
}


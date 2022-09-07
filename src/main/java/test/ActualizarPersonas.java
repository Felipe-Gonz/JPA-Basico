package test;

import com.mycompany.create.jpa.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.*;

public class ActualizarPersonas {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Iniciamos la Transaccion
        tx.begin();
        //Se ejecuta SQL select
        Persona persona = em.find(Persona.class, 3);
        //Se termina la transacion
        tx.commit();

        //Objeto en estado de datached
        log.debug("Objeto recuperado" + persona);
        
        //SetValue agregar nuevo valor
        persona.setNombre("Escobar");
        
        //Iniciar segunda Transaccion
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        
        //Modificamos el objeto
        em.merge(persona);
        
        //Termina la Transaccion 
        tx2.commit();
        
        //Objeto en estado datached ya modificado
        log.debug("Objeto recuperado: " + persona);
        
               
        em.close();
    }
}

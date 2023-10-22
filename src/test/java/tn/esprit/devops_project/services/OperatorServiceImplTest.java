package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class OperatorServiceImplTest {
    @Autowired
    private OperatorServiceImpl operatorService;
    @Autowired
    private OperatorRepository operatorRepository;
    //-------------------------------withmock

    @Autowired
    @InjectMocks
    private OperatorServiceImpl OperatorService;
    @Autowired
    @Mock
    private OperatorRepository OperatorRepository;

    public OperatorServiceImplTest() {
    }

    @BeforeEach
    void setUp() {
        this.OperatorRepository = (OperatorRepository)Mockito.mock(OperatorRepository.class);
        this.OperatorService = new OperatorServiceImpl(this.OperatorRepository);
    }

    @Test
    void addOperatormock() {
        OperatorRepository operatorRepository = (OperatorRepository)Mockito.mock(OperatorRepository.class);
        Operator operator = new Operator();
        operator.setFname("nada");
        operator.setLname("bencheikh");
        Mockito.when((Operator)operatorRepository.save((Operator)Mockito.any(Operator.class))).thenReturn(operator);
        OperatorServiceImpl operatorService = new OperatorServiceImpl(operatorRepository);
        Operator savedOperator = operatorService.addOperator(operator);
        ((OperatorRepository)Mockito.verify(operatorRepository, Mockito.times(1))).save((Operator)Mockito.any(Operator.class));
        Assertions.assertNotNull(savedOperator);
        Assertions.assertEquals("nada", savedOperator.getFname());
        Assertions.assertEquals("bencheikh", savedOperator.getLname());
    }

    @Test
    void RetrieveOperatorByIdmock() {
        OperatorRepository operatorRepository = (OperatorRepository)Mockito.mock(OperatorRepository.class);
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("nada");
        operator.setLname("bencheikh");
        Mockito.when(operatorRepository.findById(1L)).thenReturn(Optional.of(operator));
        OperatorServiceImpl operatorService = new OperatorServiceImpl(operatorRepository);
        Optional<Operator> retrievedOperator = Optional.ofNullable(operatorService.retrieveOperator(1L));
        ((OperatorRepository)Mockito.verify(operatorRepository, Mockito.times(1))).findById(1L);
        Assertions.assertTrue(retrievedOperator.isPresent());
        Assertions.assertEquals("nada", ((Operator)retrievedOperator.get()).getFname());
        Assertions.assertEquals("bencheikh", ((Operator)retrievedOperator.get()).getLname());
    }
//----------------------------------------without mock
    @Test
    @Order(0)
    void addOperator() {
        Operator o = new Operator();
        o.setFname("nadaa");
        o.setPassword("12344");
        o.setLname("bencheikhsalah");
        Operator save = this.operatorService.addOperator(o);
        Assertions.assertNotNull(save);
    }

    @Test
    @Order(1)
    void retrieveAllOperators() {
        Operator o = new Operator();
        o.setFname("nada");
        o.setLname("nada");
        o.setPassword("password123");
        this.operatorService.addOperator(o);
        Logger logger = LoggerFactory.getLogger(OperatorServiceImplTest.class);
        List<Operator> lo = this.operatorService.retrieveAllOperators();
        if (lo.isEmpty()) {
            logger.info("Database is empty.");
        } else {
            logger.info("List of Operators:");
            Iterator var5 = lo.iterator();

            while(var5.hasNext()) {
                Operator operator = (Operator)var5.next();
                logger.info(operator.toString());
            }
        }

        Assertions.assertNotNull(lo);
    }

    @Test
    @Order(2)
    void updateOperator() {
        Operator operator = new Operator();
        operator.setFname("John");
        operator.setLname("Doe");
        operator.setPassword("password123");
        Operator savedOperator = this.operatorService.addOperator(operator);
        savedOperator.setFname("nada");
        savedOperator.setLname("nada");
        this.operatorService.updateOperator(savedOperator);
        Optional<Operator> updatedOperator = this.operatorRepository.findById(savedOperator.getIdOperateur());
        Assertions.assertTrue(updatedOperator.isPresent());
        Assertions.assertEquals("nada", ((Operator)updatedOperator.get()).getFname());
    }

    @Test
    @Order(3)
    void retrieveOperator() {
        Operator operator = new Operator();
        operator.setFname("nada");
        operator.setLname("nada");
        operator.setPassword("password123");
        Operator savedOperator = this.operatorService.addOperator(operator);
        Optional<Operator> retrievedOperator = this.operatorRepository.findById(savedOperator.getIdOperateur());
        Assertions.assertTrue(retrievedOperator.isPresent());
        Assertions.assertEquals("nada", ((Operator)retrievedOperator.get()).getFname());
    }

    @Test
    @Order(4)
    void deleteOperatorwithid() {
        Operator operator = new Operator();
        operator.setFname("nada");
        operator.setLname("nada");
        operator.setPassword("password123");
        Operator savedOperator = this.operatorService.addOperator(operator);
        this.operatorRepository.deleteById(savedOperator.getIdOperateur());
        Optional<Operator> deletedOperator = this.operatorRepository.findById(savedOperator.getIdOperateur());
        Assertions.assertFalse(deletedOperator.isPresent());
    }

    @Test
    @Order(5)
    void deleteAllOperators() {
        this.operatorRepository.deleteAll();
        List<Operator> lo = this.operatorService.retrieveAllOperators();
        Assertions.assertEquals(0, lo.size());
    }
//--------------------------------
    @AfterAll
    public void cleanUp() {
        this.operatorRepository.deleteAll();
    }
}

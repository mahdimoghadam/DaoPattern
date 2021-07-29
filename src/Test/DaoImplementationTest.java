import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class DaoImplementationTest {

    @InjectMocks
    private DaoImplementation panelDao;

    @Mock
    private Connection connection;

    @Mock
    private ResultSet result;

    @Mock
    private Connection1 cc;

    @Mock
    private PreparedStatement stmt;

    Category cat;


    @BeforeEach
    public void setup() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.openMocks(this);

        when(connection.prepareStatement(any(String.class))).thenReturn(stmt);
        when(cc.Connection()).thenReturn(connection);

        cat= new Category("101","Food");


    }



    @Test
    void add() throws SQLException, ClassNotFoundException {

        Mockito.when(connection.prepareStatement(("INSERT INTO Category VALUES ( ?, ? )"))).thenReturn((PreparedStatement) stmt);

        panelDao.add(cat);

        Mockito.verify(stmt).executeUpdate();


    }

    @Test
    void edit() throws SQLException, ClassNotFoundException {
        Mockito.when(result.next()).thenReturn(true).thenReturn(false);
        Mockito.when(result.getString("catcode")).thenReturn("101");
        Mockito.when(result.getString("catdesc")).thenReturn("Food");
        Mockito.when(result.first()).thenReturn(true);
        Mockito.when(stmt.executeQuery()).thenReturn(result);



        // Category panel = panelDao.search("101");

        cat = new Category("101","Food and Bev");
        Category pp =  panelDao.edit(cat,"101");




        assertEquals("Food and Bev",pp.getCatDesc());

    }

    @Test
    void delete() {
    }

    @Test
    void display() {
    }

    @Test
    void search() {
    }
}
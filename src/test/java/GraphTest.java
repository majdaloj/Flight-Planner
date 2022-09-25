
import entities.Graph;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class GraphTest {
    private Graph g0;
    private Graph g1;

    @Before
    public void setUp() {
        this.g0 = new Graph(0);
        this.g1 = new Graph(5);
    }

    @Test(timeout = 50)
    public void TestGetNodeCount() {
        assertEquals(g0.getNodeCount(), 0);
        assertEquals(g1.getNodeCount(), 5);
    }

    @Test(timeout = 50)
    public void TestGetMatrixElement() {
        assertEquals(g1.getMatrixElement(4, 4), 0);
        assertEquals(g1.getMatrixElement(0, 0), 0);
        assertEquals(g1.getMatrixElement(2, 3), 0);

    }

    @Test(timeout = 50)
    public void TestAddEdge() {
        g1.addEdge(0, 1);
        g1.addEdge(1, 3);

        assertEquals(g1.getMatrixElement(0, 1), 1);
        assertEquals(g1.getMatrixElement(1, 3), 1);

        assertEquals(g1.getMatrixElement(1, 2), 0);
        assertEquals(g1.getMatrixElement(0, 0), 0);
    }

    @Test(timeout = 50)
    public void TestEquals() {
        Graph h0 = new Graph(0);
        Graph h1 = new Graph(5);
        Graph j0 = new Graph(0);
        Graph j1 = new Graph(5);

        // Test Reflexive Property (a=a)
        assertEquals(g0.equals(g0), true);
        assertEquals(g1.equals(g1), true);

        // Test Symmetric Property (a=b -> b=a)
        assertEquals(g0.equals(h0), h0.equals(g0));
        assertEquals(g1.equals(h1), h1.equals(g1));
        assertEquals(g0.equals(g1), g1.equals(g0));

        // Test Transitive Property (a=b, b=c -> a=c)
        if (g0.equals(h0) && h0.equals(j0)) {
            assertEquals(g0.equals(j0), true);
        } else {
            assertEquals(g0.equals(j0), false);
        }
        if (g0.equals(h1) && h1.equals(j1)) {
            assertEquals(g0.equals(j1), true);
        } else {
            assertEquals(g0.equals(j1), false);
        }

        // Test Consistency (a=a -> a=a)
        assertEquals(g0.equals(h0), g0.equals(h0));
        assertEquals(g0.equals(h1), g0.equals(h1));
        assertEquals(g0.equals(g1), g0.equals(g1));

        // Test Null (a=/= null)
        assertEquals(g0.equals(null), false);
        assertEquals(g1.equals(null), false);
    }

    @Test(timeout = 50)
    public void TestAllPaths() {
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(0, 4);

        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);

        ArrayList<ArrayList<Integer>> expected = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path1 = new ArrayList<Integer>();
        ArrayList<Integer> path2 = new ArrayList<Integer>();
        ArrayList<Integer> path3 = new ArrayList<Integer>();

        path1.add(0);
        path1.add(1);
        path1.add(2);
        path1.add(3);
        path1.add(4);

        path2.add(0);
        path2.add(2);
        path2.add(3);
        path2.add(4);

        path3.add(0);
        path3.add(4);

        expected.add(path1);
        expected.add(path2);
        expected.add(path3);

        assertEquals(g1.allPaths(0, 4), expected);
    }

}

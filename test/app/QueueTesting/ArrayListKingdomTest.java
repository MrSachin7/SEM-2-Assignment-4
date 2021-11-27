package app.QueueTesting;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListKingdomTest {

    private ListADT<String> listElements;

    @BeforeEach
    public void setup()
    {
        listElements = new ArrayListKingdom<>();
    }

    @Test
    public void testIsEmptyReturnsTrueWhenEmpty()
    {
        //No elements are added in the list currently
        assertTrue(listElements.isEmpty());
    }

    @Test
    public void testIsEmptyReturnsFalseWhenListIsNotEmpty()
    {
        listElements.add("TEST");
        assertFalse(listElements.isEmpty());
    }

    @Test
    public void testAddElementAtNegativeIndex()
    {
        assertThrows(IndexOutOfBoundsException.class, ()-> listElements.add(-1,"Test"));
    }

    @Test
    public void testAddElementAtIndex0()
    {
        //List size =0 and legal values are 0 to list size
        assertDoesNotThrow(()-> listElements.add(0, "TEST"));
    }

    @Test
    public void testAddElementAtIndex1()
    {

        assertThrows(IndexOutOfBoundsException.class,()-> listElements.add(1, "TEST"));
    }

    @Test
    public void testAddElement()
    {
        listElements.add("TEST1");
        assertEquals(1, listElements.size());
    }

    @Test
    public void testAddElementsAddsToRear()
    {
        listElements.add("TEST1");
        listElements.add("TEST2");

        assertEquals(1, listElements.indexOf("TEST2"));
    }

    @Test
    public void testSetElementSet()
    {
        //Adds an element such that list size = 1
        //legal values = 0 to list size -1
        listElements.add("TEST1");
        listElements.set(0,"TEST2");
        assertEquals("TEST2", listElements.get(0));
    }

    @Test
    public void testSetElementInAnEmptyListThrowsIndexOutOfBound()
    {
        assertThrows(IndexOutOfBoundsException.class,() -> listElements.set(0, "Test1"));
    }

    @Test
    public void testSetElementAtIndex1ThrowsIndexOutOfBound()
    {
        //Adds one element such that list size = 1
        //Legal values for index are 0 to 0
        listElements.add("Test1");
        assertThrows(IndexOutOfBoundsException.class, ()-> listElements.set(1,"Test2"));
    }

    @Test
    public void testSetElementAtIndexMinus1ThrowsIndexOutOfBound()
    {
        //Adds one element such that list size = 1
        //Legal values for index are 0 to 0
        listElements.add("Test1");
        assertThrows(IndexOutOfBoundsException.class, ()-> listElements.set(-1,"Test2"));
    }

    @Test
    public void testGetElement()
    {
        listElements.add("Test1");
        listElements.add("Test2");
        assertEquals("Test2", listElements.get(1));
    }

    @Test
    public void testGetElementAtIndex2ThrowsOutOfBoundException()
    {
        //Legal index are 0 and 1
        listElements.add("Test1");
        listElements.add("Test2");
        assertThrows(IndexOutOfBoundsException.class, ()-> listElements.get(2));
        //Fails because it throws an undocumented exception

    }

    @Test
    public void testGetElementAtIndexMinus1ThrowsOutOfBoundException()
    {
        //Test right under boundary value
        listElements.add("Test1");
        listElements.add("Test2");
        assertThrows(IndexOutOfBoundsException.class, ()-> listElements.get(-1));
        //Fails because it throws an undocumented exception
    }

    @Test
    public void testRemoveElementRemove()
    {
        //Adds an element such that list size = 1
        //legal values = 0 to list size -1
        listElements.add("TEST1");
        assertEquals("TEST1", listElements.remove(0));
        assertEquals(0, listElements.size());
    }

    @Test
    public void testRemoveElementInAnEmptyListThrowsIndexOutOfBound()
    {
        assertThrows(IndexOutOfBoundsException.class,() -> listElements.remove(0));
    }

    @Test
    public void testRemoveElementAtIndex1ThrowsIndexOutOfBound()
    {
        //Adds one element such that list size = 1
        //Legal values for index are 0 to 0
        listElements.add("Test1");
        assertThrows(IndexOutOfBoundsException.class, ()-> listElements.remove(1));
    }

    @Test
    public void testRemoveElementAtIndexMinus1ThrowsIndexOutOfBound()
    {
        //Adds one element such that list size = 1
        //Legal values for index are 0 to 0, boundary values = 0
        listElements.add("Test1");
        assertThrows(IndexOutOfBoundsException.class, ()-> listElements.remove(-1));
    }


    @Test
    public void removeElement()
    {
        listElements.add("TEST1");
        assertEquals("TEST1", listElements.remove("TEST1"));
    }

    @Test
    public void testRemoveAnElementIsNotInTheList()
    {
        listElements.add("Test1");
        listElements.add("Test2");
        assertThrows(IllegalStateException.class, ()-> listElements.remove("Test3"));
    }

    @Test
    public void testIndexOf()
    {
        //We could also test indexOf of element of an element that does not exist in list
        //But it is not specified in the documentation what behaviour should occur.
        listElements.add("Test1");
        assertEquals(0, listElements.indexOf("Test1"));
    }

    @Test
    public void testContainsTrue()
    {
        listElements.add("Test1");
        assertTrue(listElements.contains("Test1"));
    }

    @Test
    public void testContainsFalse()
    {
        listElements.add("Test1");
        assertFalse(listElements.contains("Test2"));
    }

    @Test
    public void testIsFull()
    {
        //List is unbounded and will always return true
        for (int i = 0; i < 10000; i++)
        {
            listElements.add( "Test" + i);
        }
        // the ListADT in the kingdom project has the isFull set to be true
        //thus the tested class does not follow the documentation.
        assertFalse(listElements.isFull());
    }

    @Test
    public void testListSize()
    {
        listElements.add("Test1");
        listElements.add("Test2");
        listElements.add("Test3");

        assertEquals(3, listElements.size());
    }

    @Test
    public void testToString()
    {
        listElements.add("Test1");
        listElements.add("Test2");
        listElements.add("Test3");

        assertEquals("{Test1, Test2, Test3}", listElements.toString());
    }

}
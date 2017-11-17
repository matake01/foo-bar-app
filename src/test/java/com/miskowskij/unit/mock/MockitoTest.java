package com.miskowskij.unit.mock;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Iterator;

public class MockitoTest  {

	@Test
	public void test1()  {
        //  create mock
        MyClass test = mock(MyClass.class);

        // define return value for method getUniqueId()
        when(test.getUniqueId()).thenReturn(43);

        // use mock in test....
        assertEquals(test.getUniqueId(), 43);
	}


	@Test
	public void testMoreThanOneReturnValue()  {
        Iterator<String> i= mock(Iterator.class);
        when(i.next()).thenReturn("Mockito").thenReturn("rocks");
        String result= i.next() + " " + i.next();
        //assert
        assertEquals("Mockito rocks", result);
	}

	@Test
	public void testReturnValueDependentOnMethodParameter()  {
        Comparable<String> c= mock(Comparable.class);
        when(c.compareTo("Mockito")).thenReturn(1);
        when(c.compareTo("Eclipse")).thenReturn(2);
        //assert
        assertEquals(1, c.compareTo("Mockito"));
	}

	@Test
	public void testReturnValueInDependentOnMethodParameter()  {
	        Comparable<Integer> c= mock(Comparable.class);
	        when(c.compareTo(anyInt())).thenReturn(-1);
	        //assert
	        assertEquals(-1, c.compareTo(9));
	}

}

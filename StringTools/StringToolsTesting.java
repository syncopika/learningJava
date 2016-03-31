//JUnit testing for the StringTools methods

import static org.junit.Assert.*;
import org.junit.Test;

public class StringToolsTesting {

	@Test
	public void testCount() {
		assertTrue(StringTools.count("caaaat", 'a') == 4);
	}
	
	@Test
	public void testCountSubstring() {
		assertTrue(StringTools.countSubstring("Mississippi", "issi") == 2);
		System.out.println(StringTools.countSubstring("Miinniicooiiiii", "ii"));
		assertTrue(StringTools.countSubstring("Miinniicooiiiii", "ii") == 6);
	}
	
	@Test
	public void testReverse(){
		System.out.println(StringTools.reverse("helloWorld"));
		assertTrue(StringTools.reverse("helloWorld").equals("dlroWolleh"));
	}
	
	@Test
	public void testExpand(){
		System.out.println(StringTools.expand("Fred"));
		assertTrue(StringTools.expand("Fred").equals("Frreeedddd"));
		assertTrue(StringTools.expand("cat").equals("caattt"));
	}

	@Test
	public void testAlternating(){
		System.out.println(StringTools.alternating("cow", "buffalo"));
	}
	
	@Test
	public void testLinkExtractor(){
		System.out.println(StringTools.linkExtractor("a wdbhwb $%^ 78href=\"asdhjh j%$^^ASBhjbjwdh\"adjbh ajs653%^&"));
	}
}

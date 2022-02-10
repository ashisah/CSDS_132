import static org.junit.Assert.*;

import java.util.InputMismatchException;

import org.junit.Test;

/**
 * 
 * @author Ashley Sah
 * Testing class of DNA
 */
public class DNATest {

	@Test
	public void testSplice() {
		
		//Test many
		DNA dna1 = DNA.string2DNA("GATACA");
		dna1.splice(DNA.string2DNA("ATT"), 2);
		
		assertEquals("GATACAT", dna1.toString());
		
		//Test 0
		DNA dna2 = DNA.string2DNA("CAT");
		dna2.splice(DNA.string2DNA(""), 0);
		assertEquals("CAT", dna2.toString());
		
		//Test when intput number is greater than length of input dna sequence
		dna1 = DNA.string2DNA("GATACA");
		dna1.splice(DNA.string2DNA("ATT"), 4);
		
		assertEquals("GATACA", dna1.toString());
		
	}
	
	@Test
	public void testString2DNA() {
		DNA dna1 = DNA.string2DNA("");
		DNA dna2 = DNA.string2DNA("AGTC");
		
		assertEquals(true, dna1.isEmpty());
		assertEquals("AGTC", dna2.toString());
		assertEquals("", dna1.toString());
		
		try {
			dna1 = DNA.string2DNA("FJDSJKDLSJAL");
		}
		
		catch(InputMismatchException e){
			System.out.println("congrats string2DNA throws the right exception");
		}
		catch(Exception e) {
			System.out.println("I'm sorry, but string2DNA does not throw the right exception");
		}
		
	}
	@Test
	public void testToString() {
		DNA dna1 = DNA.string2DNA("TGTC");
		DNA dna2 = DNA.string2DNA("AGTC");
		
		assertEquals("TGTC", dna1.toString());
		assertEquals("AGTC", dna2.toString());
	}
	
	@Test
	public void testOverlaps() {
		
		DNA dna1 = DNA.string2DNA("TTCG");
		DNA dna2 = DNA.string2DNA("CGAA");
		
		assertEquals(true,  DNA.overlaps(dna1, dna2, 2));
		
		dna1  = DNA.string2DNA("TTTCG");
		dna2  = DNA.string2DNA("ACTT");
		
		//last base of dna2 is same as first base of dna1
		assertEquals(true,  DNA.overlaps(dna2, dna1, 1));
		
		//last 2 bases of dna2 are equal to first 2 bases of dna2
		assertEquals(true, DNA.overlaps(dna2, dna1, 2));
		
		assertEquals(false, DNA.overlaps(dna2, dna1, 3));
		
		dna1  = DNA.string2DNA("ACTT");
		dna2  = DNA.string2DNA("TTTCG");
		assertEquals(true,  DNA.overlaps(dna1, dna2, 1));
		assertEquals(true,  DNA.overlaps(dna1, dna2, 2));
		assertEquals(false,  DNA.overlaps(dna1, dna2, 3));
		assertEquals(false,  DNA.overlaps(dna1, dna2, 4));
		assertEquals(false,  DNA.overlaps(dna1, dna2, 4));
		
	}
	
	
	
	

}

import java.util.ListIterator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.System;
import java.util.Scanner;
/**
 * This class represents a DNA sequence. It is a double linked list of bases
 * @author Ashley Sah
 *
 */

public class DNA extends DoubleLinkedList<DNA.Base> implements Comparable<DNA>{
	/**
	 * Represents the 5 bases: Adenine, Cytosine, Guanine, Thymine (only in DNA), and Uracil (only in RNA)
	 * @author Ashley Sah
	 *
	 */
	protected enum Base{
		//please don't dock points for the uracil,
		//I included it in case someone wanted to make an RNA sequence
		Adenine("A"), Cytosine("C"), Guanine("G"), Thymine("T"), Uracil("U");
		
		/**
		 * @field name stores the letter representation of the base
		 */
		private String name = "";
		
		
		/**
		 * constructor
		 * @param stores the letter representation of the base
		 */
		private Base(String name) {
			this.name = name;
		}
		
		/**
		 * returns the letter representation of the base
		 * @return returns the letter representation of the base
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * checks if two bases are equal
		 * @param b the base we are checking is equal
		 * @return true if the names of the bases are equal, false if not
		 */
		public boolean equals(Base b) {
			if(getName().equals(b.getName())) {
				return true;
			}
			return false;
		}
	}

	/**
	 * Takes an input string converts it into a DNA sequence
	 * Throws an InputMismatchException if string contains characters other than the 4 bases (A,G,C,T)
	 * @param DNAString the string to be converted into a DNA object
	 * @return a DNA object with the order of bases stored in DNAString
	 */
	public static DNA string2DNA(String DNAString){
		DNA dna1 = new DNA();
		for(int i = 0; i<DNAString.length(); i++) {
			if(DNAString.charAt(i) == 'A') {
				dna1.addToBack(Base.Adenine);
			}
			else if(DNAString.charAt(i) == 'T') {
				dna1.addToBack(Base.Thymine);
			}
			else if(DNAString.charAt(i) == 'C') {
				dna1.addToBack(Base.Cytosine);
			}
			else if(DNAString.charAt(i) == 'G') {
				dna1.addToBack(Base.Guanine);
			}
			else {
				throw new InputMismatchException();
			}
		}
		return dna1;
	}
	
	/**
	 * Adds the first n(numbases) of the input dna to the end of this dna
	 * @param dna a dna sequence
	 * @param numbases the number of bases we are cutting off from the input dna
	 */
	public void splice(DNA dna, int numbases) {

		//if dna is not empty
		if (!dna.isEmpty()) {
			for(int i = 0; i<numbases; i++) {
				if(!dna.isEmpty())
					dna.removeFromFront();
			}

			//add the input dna to this dna
			append(dna);

		}
	}
	
	/**
	 * returns the string form of the DNA strand, overrides Object's toString() method
	 * @returns the string form of the DNA strand
	 */
	public String toString() {
		//stores sting version of DNA
		StringBuilder DNAString = new StringBuilder();
		//iterator for this DNA sequence
		Iterator<Base> it = iterator();
		//while there is another base in the sequence
		while(it.hasNext()) {
			//add the base on to the sequence
			DNAString.append(it.next().getName());
		}
		
		//return the string
		return  DNAString.toString();	
	}
	
	/**
	 * Checks if the last n(numbases) bases of DNA1 are the same as the first n(numbases) bases of DNA2
	 * @param dna1 the first DNA sequence
	 * @param dna2 the second DNA sequence
	 * @param numbases the number of bases we are checking
	 * @return true if the last n(numbases) bases of DNA1 are the same the first n(numbases) bases of DNA2
	 */
	public static boolean overlaps(DNA dna1, DNA dna2, int numbases) {
		
		//stores the WHOLE dna1 sequence
		String dna1String = dna1.toString();
		//stores the WHOLE dna2 sequence
		String dna2String = dna2.toString();
		
		//will store the portion of dna1 we are interested in
		StringBuilder sequence1 = new StringBuilder();
		//will store the portion of dna2 we are interested in
		StringBuilder sequence2 = new StringBuilder();
		
		//add the bases of interest from dna1 and dna2 to sequence1 and sequence2
		for(int i = 0, i2 = (dna1String.length()-numbases) ; i<numbases; i++, i2++) {
			sequence2.append(dna2String.charAt(i));
			sequence1.append(dna1String.charAt(i2));
		}
		
		//return true if sequences of interest are equal, false if not
		return sequence1.toString().equals(sequence2.toString());
	}
	
	
	/**
	 * main method–takes 2 DNA sequences and checks for the greatest overlap then returns the spliced DNA
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			// stores the first dna sequence
			DNA dna1 = DNA.string2DNA(scanner.nextLine());
			// stores the second dna sequence
			DNA dna2 = DNA.string2DNA(scanner.nextLine());
			
			// step one: find the max amount of overlap from the back of dna1 to front of
			// dna2

			// increment values
			DLNode<Base> nodeptr1 = dna1.getBack();
			int numbases = 1;

			// represents maximum number of bases that overlap between the back of dna1 to
			// the front of dna2
			int overlaps1to2 = 0;

			// while dna1 has a previous node,
			while (nodeptr1.getPrevious() != null) {
				if (DNA.overlaps(dna1, dna2, numbases)) {
					overlaps1to2 = numbases;
				}
				numbases++;
				nodeptr1 = nodeptr1.getPrevious();
			}

			// step two: find the max amount of overlap from the front of dna2 to the back
			// of dna1
			
			// increment values
			DLNode<Base> nodeptr2 = dna2.getBack();
			numbases = 1;
			
			//represents maximum number of bases that overlap between the back of dna2 to
			// the front of dna1
			int overlaps2to1 = 0;

			while (nodeptr2.getPrevious() != null) {
				if (DNA.overlaps(dna2, dna1, numbases)) {
					overlaps2to1 = numbases;
				}
				nodeptr2 = nodeptr2.getPrevious();
				numbases++;
			}
			
			
			// Step three: splice or return appropriate values
			if(overlaps1to2 == 0 && overlaps2to1==0) {
				System.out.println("There is no overlap");
				System.out.println(dna1);
				System.out.println(dna2);
			}
			else if(dna1.equals(dna2)) {
				System.out.println(dna1);
			}
			else if (overlaps1to2 >= overlaps2to1) {
				dna1.splice(dna2, overlaps1to2);
				System.out.println(dna1);
			} else {
				dna2.splice(dna1, overlaps2to1);
				System.out.println(dna2);
			}
		}
		catch(InputMismatchException e) {
			System.out.println("Either one or both of your DNA sequences"
					+ "are not valid");
		}
		catch(StringIndexOutOfBoundsException e) {
			System.out.println("Please enter 2 DNA sequences");
		}

		
	}

	@Override
	/**
	 * returns a negative number if this dna is shorter than the longer dna, 
	 * returns a postitive number if dna2 is longer than this dna
	 * If dna2 and this dna are the same length, 
	 * returns a negative number if this dna comes before dna2 alphabetically 
	 * (positive if dna2 comes before dna1 alphabetically)
	 * @param dna2 the DNA sequence we are comparing to
	 */
	public int compareTo(DNA dna2) {
		String dna1String = toString();
		String dna2String = dna2.toString();
		
		if(dna1String.length()!=dna2String.length())
			return dna1String.length()-dna2String.length();
		else
			return dna1String.compareTo(dna2String);
	}
	

}
package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import fasterQuicksort.*;
import quicksort.PivotType;
import quicksort.Quicksort;
import quicksort.QuicksortI;

public class QuantitativeTest {
    public static void main(String... args) {
        FasterQuicksort fqs = new FasterQuicksort();
        QuicksortI qs = new Quicksort();
        
    	try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {
	  	    StringBuilder sb = new StringBuilder();
	  	    sb.append("N");
	  	    sb.append(',');
	  	    sb.append("Counter fqs");
	  	    sb.append(',');
	  	    sb.append("T in ms fqs");
	  	    sb.append(',');
	  	    sb.append("Counter qs");
	  	    sb.append(',');
	  	    sb.append("T in ms qs");
	  	    sb.append('\n');
	  	    
	  	    for (int x = 0; x < 10; x++) {
		        for (int i = 10; i <= 1_000_000; i *= 10) {
		        	// CSV [
		        	sb.append(i);
		        	sb.append(',');
		        	// ] CSV
		            System.out.println("List size: " + i + "-------------");
		            Node[] unsortedListOne = new Node[i];
		            Node[] unsortedListTwo = new Node[i];
		            for (int j = 0; j < i; j++) {
		                double rnd = Math.random() * 100;
		                int num = (int) rnd + 700;
		                int key = num * i;
		                unsortedListOne[j] = new Node(key, key);
		                unsortedListTwo[j] = new Node(key, key);
		            }
		            long startTime = System.currentTimeMillis();
		            fqs.sort(unsortedListOne);
		            for (int p=0; p<i; p++) {
		            	System.out.println(unsortedListOne[p].getKey());
		            }
		            long endTime = System.currentTimeMillis();
		            long time = endTime - startTime;
		            System.out.println("FastQuicksort: " + fqs.getCounter() + "\n" + time + " ms");
		        	// CSV [
		        	sb.append(fqs.getCounter());
		        	sb.append(',');
		        	sb.append(time);
		        	sb.append(',');
		        	// ] CSV
		            fqs.resetCounter();
	
		        	
		            startTime = System.currentTimeMillis();
		            qs.sort(unsortedListTwo, PivotType.MEDIANOFTHREE);
		            endTime = System.currentTimeMillis();
		            time = endTime - startTime;
		            System.out.println("Quicksort:     " + qs.getCounter() + "\n" + time + " ms");
		        	// CSV [
		        	sb.append(qs.getCounter());
		        	sb.append(',');
		        	sb.append(time);
		        	sb.append(',');
			  	    sb.append('\n');
		        	// ] CSV
		            qs.resetCounter();
		        }
	  	    }
        	// CSV [
	        writer.write(sb.toString());
        	// ] CSV
	    } catch (FileNotFoundException e) {
  	      System.out.println(e.getMessage());
  	    }
    }
}

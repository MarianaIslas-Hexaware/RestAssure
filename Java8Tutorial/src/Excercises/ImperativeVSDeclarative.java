package Excercises;

import java.util.stream.IntStream;

public class ImperativeVSDeclarative {
	
	//Imperative 
	int sum = 0;
	
	for(int i=0; i<=100; i++) {
		sum+=i;
	}
	System.out.println("Sum using Imperative Approach: " + sum);
	
	//Declarative
	int sum1= IntStream.rangeClosed(0,100).sum();
	System.out.println("Sum using declarative approach: "+sum1);
}
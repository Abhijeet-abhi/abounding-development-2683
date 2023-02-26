package com.masai.exception;

public class SomeThingWrongException extends Exception {
	@Override
	public String toString() {
		return "Some thing went wrong, try again later";
	}
	public SomeThingWrongException() {
	System.out.println("Something went wrong");
	}
}

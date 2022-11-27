package com.jesusnut.exception;

/**
 * Custom built BaseException for the framework.<br>
 * Extends Runtime Exception and can be thrown anywhere to terminate a program.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@SuppressWarnings("serial")
public class FrameworkException extends RuntimeException {

	public FrameworkException(String messg) {
		super(messg);
		printStackTrace();
	}

	public FrameworkException(String messg, Throwable e) {
		super(messg, e);
		printStackTrace();
	}

	public FrameworkException(Throwable e) {
		super(e);
		printStackTrace();
	}

	@Override
	public final void printStackTrace() {
		super.printStackTrace();
	}

}

package com.model.goodss.util;

public class Line {
	public static int getNumber() {
		return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}
}

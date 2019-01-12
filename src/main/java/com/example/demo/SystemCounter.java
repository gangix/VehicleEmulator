package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class SystemCounter {
	private long index = 0;

	public long getIndex() {
		return index;
	}
	
	public long increase() {
		return ++index;
	}

}

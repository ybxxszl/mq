package com.wjy.mq.pc;

public class Listener {

	public static void main(String[] args) {

		while (true) {

			String message = Poper.pop(0, "TestQueue");

			System.out.println(message);

		}

	}

}

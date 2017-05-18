package com.kyle.ie.property;

import java.util.List;

import org.springframework.boot.CommandLineRunner;

public interface IDBMSSeeder<T> extends CommandLineRunner {

	public List<T> generateData();
}

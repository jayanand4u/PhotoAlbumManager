package com.teradata.exercise.service;

import java.util.concurrent.ExecutionException;

public interface InitLoadService {
	public void loadData() throws InterruptedException, ExecutionException;
}

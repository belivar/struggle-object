package com.YinglishZhi.thread.manager;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Callable;

/**
 * @author LDZ
 * @date 2019-11-04 14:57
 */
@Data
@AllArgsConstructor
public abstract class BaseAsyncTask<T> implements Callable<T> {

    private String threadPoolName;

    public abstract String getId();

}

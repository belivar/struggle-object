package com.yinglishzhi.agent;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author LDZ
 * @date 2019-10-25 17:12
 */
public class AgentClassLoader extends URLClassLoader {
    public AgentClassLoader(URL[] urls) {
        super(urls, ClassLoader.getSystemClassLoader().getParent());
    }
}

package com.ttn.blog;

import org.osgi.framework.BundleActivator;

public class BlogActivator implements BundleActivator {
    public void start(org.osgi.framework.BundleContext bundleContext) throws java.lang.Exception {
        System.out.println("Bundle started..");
    }

    public void stop(org.osgi.framework.BundleContext bundleContext) throws java.lang.Exception {
        System.out.println("Bundle stopped..");
    }
}

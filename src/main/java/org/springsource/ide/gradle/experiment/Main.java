package org.springsource.ide.gradle.experiment;

import java.io.File;

public class Main {
	
	public static final File spring_xd = new File("/home/kdvolder/git/spring-xd");
	
	public static final File simple = new File("/home/kdvolder/commandline-dev/open-sts/eclipse-integration-gradle/org.springsource.ide.eclipse.gradle.core.test/resources/projects/sts2834/my-lib");
	
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		System.out.println("==== building publication models ====");
		PublicationModels publications = new PublicationModels(simple);
		long end = System.currentTimeMillis();
		
		System.out.println("==== building publication models ====");
		System.out.println("duratation = "+(end-start)/1000+" seconds");
		System.out.println();
		System.out.println();
		System.out.println();
		publications.dump();
	}

}

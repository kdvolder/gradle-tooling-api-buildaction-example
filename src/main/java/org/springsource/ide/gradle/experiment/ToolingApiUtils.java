package org.springsource.ide.gradle.experiment;

import java.io.File;
import java.net.URI;

import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

public class ToolingApiUtils {

	public static ProjectConnection getGradleConnector(File projectLoc, URI distributionPref, File gradleUserHomePref) throws Exception {
		GradleConnector connector = GradleConnector.newConnector();
		if (gradleUserHomePref!=null) {
			connector.useGradleUserHomeDir(gradleUserHomePref);
		}
		// Configure the connector and create the connection
		if (distributionPref!=null) {
			boolean distroSet = false;
			if ("file".equals(distributionPref.getScheme())) {
				File maybeFolder = new File(distributionPref);
				if (maybeFolder.isDirectory()) {
					connector.useInstallation(maybeFolder);
					distroSet = true;
				}
			}
			if (!distroSet) {
				connector.useDistribution(distributionPref);
			}
		}
		connector.forProjectDirectory(projectLoc);
		return connector.connect();
	}
	
	private static File getGradleUserHomePref() {
		return null;
	}


	private static URI getDistributionPref() {
		return null;
	}
	
}

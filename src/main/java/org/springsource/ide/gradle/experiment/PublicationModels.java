package org.springsource.ide.gradle.experiment;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.gradle.tooling.BuildActionExecuter;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleModuleVersion;
import org.gradle.tooling.model.gradle.GradlePublication;
import org.gradle.tooling.model.gradle.ProjectPublications;

public class PublicationModels {

	private ProjectConnection conn;
	private Map<File, ProjectPublications> map;

	public PublicationModels(File rootProjectLocation) throws Exception {
		try {
			conn = ToolingApiUtils.getGradleConnector(rootProjectLocation, null, null);
			BuildActionExecuter<Map<File, ProjectPublications>> action = conn.action(new PublicationModelFetcher());
			action.setStandardOutput(System.out);
			action.setStandardError(System.err);
			this.map = action.run();
		} finally {
			if (conn!=null) {
				conn.close();
			}
		}
	}
		
	public void dump() {
		System.out.println("=== pubs models ===");
		System.out.println("map size = "+this.map.size());
		for (Entry<File, ProjectPublications> e : map.entrySet()) {
			System.out.println(e.getKey());
			for (GradlePublication pub : e.getValue().getPublications()) {
				GradleModuleVersion id = pub.getId();
				System.out.println("   "+id.getGroup()+":"+id.getName()+":"+id.getVersion());
			}
		}
	}

}

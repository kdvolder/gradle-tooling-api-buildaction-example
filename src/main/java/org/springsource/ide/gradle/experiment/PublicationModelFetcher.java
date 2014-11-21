package org.springsource.ide.gradle.experiment;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.gradle.tooling.BuildAction;
import org.gradle.tooling.BuildController;
import org.gradle.tooling.model.eclipse.HierarchicalEclipseProject;
import org.gradle.tooling.model.gradle.ProjectPublications;

public class PublicationModelFetcher implements BuildAction<Map<File, ProjectPublications>> {

	public PublicationModelFetcher() {
	}

	public Map<File, ProjectPublications> execute(BuildController controller) {
		Map<File, ProjectPublications> map = new HashMap<File, ProjectPublications>();
		HierarchicalEclipseProject root = controller.getModel(HierarchicalEclipseProject.class);
		walk(controller, root, map);
		return map;
	}

	private void walk(BuildController controller, HierarchicalEclipseProject project, Map<File, ProjectPublications> map) {
		ProjectPublications pubs = controller.getModel(project, ProjectPublications.class);
		map.put(project.getProjectDirectory(), pubs);
		for (HierarchicalEclipseProject child : project.getChildren()) {
			walk(controller, child, map);
		}
	}

}

/*******************************************************************************
 * Copyright (c) 2011 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.m2e.core.ui.internal.editing;

import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.DEPENDENCIES;
import static org.eclipse.m2e.core.ui.internal.editing.PomEdits.getChild;

import org.apache.maven.model.Dependency;
import org.eclipse.m2e.core.ui.internal.editing.PomEdits.Operation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AddDependencyOperation implements Operation {

  private Dependency dependency;

  public AddDependencyOperation(Dependency dependency) {
    this.dependency = dependency;
  }

  /* (non-Javadoc)
   * @see org.eclipse.m2e.core.ui.internal.editing.PomEdits.Operation#process(org.w3c.dom.Document)
   */
  public void process(Document document) {
    Element dependencies = getChild(document.getDocumentElement(), DEPENDENCIES);

    PomHelper.addOrUpdateDependency(dependencies, dependency.getGroupId(), dependency.getArtifactId(),
        (dependency.getVersion() == null || dependency.getVersion().length() == 0) ? null : dependency.getVersion(),
        null, null, null);
  }
}

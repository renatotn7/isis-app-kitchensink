/*
 *  Copyright 2014 Dan Haywood
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.isisaddons.app.kitchensink.dom.hierarchy.child;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;

import org.isisaddons.app.kitchensink.dom.RepositoryAbstract;
import org.isisaddons.app.kitchensink.dom.hierarchy.parent.ParentObject;

@DomainService(
        repositoryFor = ChildObject.class
)
public class ChildObjects extends RepositoryAbstract<ChildObject> {

    public ChildObjects() {
        super(ChildObject.class, Visibility.NOT_VISIBLE);
    }

    public ChildObject create(
            final String name,
            final ParentObject parentObject) {
        return repositoryService.persist(ChildObject.create(name, parentObject));
    }

    public List<ChildObject> listAll() {
        return repositoryService.allInstances(ChildObject.class);
    }

}

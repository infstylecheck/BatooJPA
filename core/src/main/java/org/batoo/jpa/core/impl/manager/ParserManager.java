/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.batoo.jpa.core.impl.manager;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.batoo.jpa.core.BLogger;
import org.batoo.jpa.core.BatooException;
import org.batoo.jpa.core.impl.mapping.MetamodelImpl;
import org.batoo.jpa.core.impl.metamodel.ManagedTypeImpl;
import org.batoo.jpa.core.impl.reflect.ReflectHelper;

import com.google.common.collect.Sets;

/**
 * A Manager that parses the metadata of the persistent classes
 * 
 * @author hceylan
 * @since $version
 */
public class ParserManager extends DeploymentManager<ManagedTypeImpl<?>> {

	private static final BLogger LOG = BLogger.getLogger(ParserManager.class);

	public static void parse(MetamodelImpl metamodel) throws BatooException {
		new ParserManager(metamodel).perform();
	}

	private ParserManager(MetamodelImpl metamodel) {
		super(LOG, "Parser", metamodel, Context.MANAGED_TYPES);
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Void perform(ManagedTypeImpl<?> type) throws BatooException {
		final Set<Class<? extends Annotation>> parsed = Sets.newHashSet();

		type.parse(parsed);

		ReflectHelper.checkAnnotations(type.getJavaType(), parsed);

		return null;
	}

}

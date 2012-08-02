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
package org.batoo.jpa.core.impl.criteria;

import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang.StringUtils;
import org.batoo.jpa.core.impl.model.MetamodelImpl;

/**
 * Implementation of {@link CriteriaUpdate}.
 * 
 * @param <T>
 *            the entity type that is the target of the update
 * 
 * @author hceylan
 * @since $version
 */
public class CriteriaUpdateImpl<T> extends CriteriaModify<T> implements CriteriaUpdate<T> {

	/**
	 * @param metamodel
	 *            the metamodel
	 * 
	 * @since $version
	 * @author hceylan
	 */
	public CriteriaUpdateImpl(MetamodelImpl metamodel) {
		super(metamodel);
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public String generateJpql() {
		final StringBuilder builder = new StringBuilder();

		builder.append("update " + this.getRoot().getEntity().getName());

		if (this.getRestriction() != null) {
			builder.append("\nwhere\n\t").append(this.getRestriction().generateJpqlRestriction(this));
		}

		return builder.toString();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public String generateSql() {
		final String sqlRestriction = this.generateSqlRestriction();
		final String updates = "";

		return "UPDATE " + this.getRoot().generateSqlFrom(this) + "\nSET " + updates
			+ (StringUtils.isNotBlank(sqlRestriction) ? "\nWHERE " + sqlRestriction : "");
	}

	/**
	 * Returns the restriction for the query.
	 * 
	 * @return the restriction
	 * 
	 * @since $version
	 * @author hceylan
	 */
	private String generateSqlRestriction() {
		if ((this.getRestriction() == null) && (this.getRoot().getEntity().getRootType().getInheritanceType() == null)) {
			return null;
		}

		final String sqlRestriction = this.getRestriction().generateSqlRestriction(this);

		if (this.getRoot().getEntity().getRootType().getInheritanceType() == null) {
			return sqlRestriction;
		}

		return "(" + sqlRestriction + ") AND (" + this.getRoot().generateDiscrimination() + ")";
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public <Y> CriteriaUpdate<T> set(Path<Y> attribute, Expression<? extends Y> value) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public <Y, X extends Y> CriteriaUpdate<T> set(Path<Y> attribute, X value) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public <Y> CriteriaUpdate<T> set(SingularAttribute<? super T, Y> attribute, Expression<? extends Y> value) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public <Y, X extends Y> CriteriaUpdate<T> set(SingularAttribute<? super T, Y> attribute, X value) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public CriteriaUpdate<T> set(String attributeName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public CriteriaUpdateImpl<T> where(Expression<Boolean> restriction) {
		return (CriteriaUpdateImpl<T>) super.where(restriction);
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public CriteriaUpdateImpl<T> where(Predicate... restrictions) {
		return (CriteriaUpdateImpl<T>) super.where(restrictions);
	}
}
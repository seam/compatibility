/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.seam.compat.cdi.validation;

import javax.enterprise.inject.spi.BeanManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Validates that injection points on observer methods are validated.
 *
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
@RunWith(Arquillian.class)
@Ignore
public class ObserverMethodParameterInjectionValidationTest {
    @Deployment
    public static JavaArchive getDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addClass(Observer.class);
        return jar;
    }

    @Test
    public void testDeployment() {
        Assert.fail("Deployment should have failed with 'Unsatisfied dependencies for type [File] with qualifiers [@Default] on observer method injection point'");
    }

    /**
     * This test should not run, but if it does, it shows Weld reporting this error:
     * WELD-001324 Argument bean must not be null
     */
    @Test
    public void testNullInjectionOnObserverMethod(BeanManager beanManager) {
        beanManager.fireEvent("Message");
    }
}

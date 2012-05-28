/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.integration.aws;

import org.springframework.beans.DirectFieldAccessor;
import org.springframework.util.Assert;

/**
 * The Subset of the methods taken from spring-integration-test.
 * TODO: Get rid of this class and instead have the dependency on spring-integration-test project
 *
 *	@author Mark Fisher
 * 	@author Iwein Fuld
 * 	@author Oleg Zhurakousky
 */
public class TestUtils {

	public static Object getPropertyValue(Object root, String propertyPath) {
        Object value = null;
        DirectFieldAccessor accessor = new DirectFieldAccessor(root);
        String[] tokens = propertyPath.split("\\.");
        for (int i = 0; i < tokens.length; i++) {
            value = accessor.getPropertyValue(tokens[i]);
            if (value != null) {
                accessor = new DirectFieldAccessor(value);
            } else if (i == tokens.length - 1) {
                return null;
            } else {
                throw new IllegalArgumentException(
                        "intermediate property '" + tokens[i] + "' is null");
            }
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getPropertyValue(Object root, String propertyPath, Class<T> type) {
        Object value = getPropertyValue(root, propertyPath);
        Assert.isAssignable(type, value.getClass());
        return (T) value;
    }
}

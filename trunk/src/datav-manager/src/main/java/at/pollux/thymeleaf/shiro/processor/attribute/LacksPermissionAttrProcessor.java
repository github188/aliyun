/*****************************************************************************
 * Copyright (c) 2013, theborakompanioni (http://www.example.org)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ****************************************************************************/
package at.pollux.thymeleaf.shiro.processor.attribute;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractConditionalVisibilityAttrProcessor;
import org.thymeleaf.util.StringUtils;
import org.thymeleaf.util.Validate;

import at.pollux.thymeleaf.shiro.dialect.ShiroFacade;

public class LacksPermissionAttrProcessor extends AbstractConditionalVisibilityAttrProcessor {

    public static LacksPermissionAttrProcessor create() {
        return new LacksPermissionAttrProcessor();
    }

    private static final String ATTRIBUTE_NAME = "lacksPermission";
    private static final int PRECEDENCE = 300;

    protected LacksPermissionAttrProcessor() {
        super(ATTRIBUTE_NAME);
    }

    @Override
    public int getPrecedence() {
        return PRECEDENCE;
    }

    @Override
    public boolean isVisible(final Arguments arguments, final Element element, final String attributeName) {
        Validate.notNull(element, "element must not be null");
        Validate.notEmpty(attributeName, "attributeName must not be empty");

        final String permission = StringUtils.trim(element.getAttributeValue(attributeName));
        Validate.notEmpty(permission, "value of '" + attributeName + "' must not be empty");

        return ShiroFacade.lacksPermission(permission);
    }
}
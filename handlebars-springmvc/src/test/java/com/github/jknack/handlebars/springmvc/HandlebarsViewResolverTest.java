/**
 * Copyright (c) 2012 Edgar Espina
 * This file is part of Handlebars.java.
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
package com.github.jknack.handlebars.springmvc;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.Test;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;

/**
 * Unit test for {@link HandlebarsViewResolver}.
 *
 * @author edgar.espina
 * @since 0.1.0
 */
public class HandlebarsViewResolverTest {

  @Test
  public void configureNoDash() throws Exception {
    Template template = createMock(Template.class);

    Handlebars handlebars = createMock(Handlebars.class);
    expect(handlebars.compile(URI.create("home"))).andReturn(template);

    HandlebarsView view = createMock(HandlebarsView.class);
    expect(view.getUrl()).andReturn("/home.hbs");
    view.setTemplate(template);
    expectLastCall();
    view.setValueResolver(MapValueResolver.INSTANCE,
        JavaBeanValueResolver.INSTANCE);
    expectLastCall();

    replay(handlebars, view, template);

    HandlebarsViewResolver viewResolver =
        new HandlebarsViewResolver(handlebars);
    viewResolver.configure(view);

    verify(handlebars, view, template);
  }

  @Test
  public void configureWithDash() throws Exception {
    Template template = createMock(Template.class);

    Handlebars handlebars = createMock(Handlebars.class);
    expect(handlebars.compile(URI.create("home"))).andReturn(template);

    HandlebarsView view = createMock(HandlebarsView.class);
    expect(view.getUrl()).andReturn("/home.hbs");
    view.setTemplate(template);
    expectLastCall();
    view.setValueResolver(MapValueResolver.INSTANCE,
        JavaBeanValueResolver.INSTANCE);
    expectLastCall();

    replay(handlebars, view, template);

    HandlebarsViewResolver viewResolver =
        new HandlebarsViewResolver(handlebars);
    assertEquals(view, viewResolver.configure(view));

    verify(handlebars, view, template);
  }

  @Test
  public void valueResolvers() throws Exception {
    Template template = createMock(Template.class);

    Handlebars handlebars = createMock(Handlebars.class);
    expect(handlebars.compile(URI.create("home"))).andReturn(template);

    HandlebarsView view = createMock(HandlebarsView.class);
    expect(view.getUrl()).andReturn("/home.hbs");
    view.setTemplate(template);
    expectLastCall();
    view.setValueResolver(MapValueResolver.INSTANCE);
    expectLastCall();

    replay(handlebars, view, template);

    HandlebarsViewResolver viewResolver =
        new HandlebarsViewResolver(handlebars);
    viewResolver.setValueResolvers(MapValueResolver.INSTANCE);
    assertEquals(view, viewResolver.configure(view));

    verify(handlebars, view, template);
  }
}

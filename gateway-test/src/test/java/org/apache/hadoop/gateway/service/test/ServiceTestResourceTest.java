package org.apache.hadoop.gateway.service.test;

import org.easymock.EasyMock;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class ServiceTestResourceTest {

  @Test
  public void testBuildXForwardBaseURL() throws Exception {
    HttpServletRequest request = EasyMock.createNiceMock( HttpServletRequest.class );
    EasyMock.expect( request.getHeader( "X-Forwarded-Proto" ) ).andReturn( null ).anyTimes();
    EasyMock.expect( request.getHeader( "X-Forwarded-Host" ) ).andReturn( null ).anyTimes();
    EasyMock.expect( request.getHeader( "X-Forwarded-Port" ) ).andReturn( null ).anyTimes();
    EasyMock.expect( request.getHeader( "X-Forwarded-Server" ) ).andReturn( null ).anyTimes();
    EasyMock.expect( request.getHeader( "X-Forwarded-Context" ) ).andReturn( null ).anyTimes();
    EasyMock.expect( request.getScheme() ).andReturn( "test-scheme" ).anyTimes();
    EasyMock.expect( request.getServerName() ).andReturn( "test-server" ).anyTimes();
    EasyMock.expect( request.getLocalPort() ).andReturn( 42 ).anyTimes();
    EasyMock.expect( request.getContextPath() ).andReturn( "/test-path" ).anyTimes();
    EasyMock.replay( request );
    String actual = ServiceTestResource.buildXForwardBaseURL( request );
    assertThat( actual, is( "test-scheme://test-server:42/test-path" ) );
  }

}
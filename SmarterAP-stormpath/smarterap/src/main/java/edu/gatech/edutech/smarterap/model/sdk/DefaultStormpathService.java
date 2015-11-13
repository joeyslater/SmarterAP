/*
 * Copyright 2012 Stormpath, Inc. and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.gatech.edutech.smarterap.model.sdk;

import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.directory.Directory;
import com.stormpath.sdk.ds.DataStore;
import com.stormpath.sdk.tenant.Tenant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Elder Crisostomo
 * @author Scott R. Leitstein - Updated for SmarterAP
 */
@Service
public class DefaultStormpathService implements StormpathService {

    @Resource(name = "stormpathClient")
    private Client client;

    private DataStore dataStore;

    private Directory directory;

    private Application application;

    private Tenant tenant;

    @Value("${stormpath.sdk.smarterap.rest.url}")
    private String smarterApplicationURL;

    @Value("${stormpath.sdk.administrator.rest.url}")
    private String administratorGroupURL;

    @Value("${stormpath.sdk.student.rest.url}")
    private String studentGroupURL;

    @Value("${stormpath.sdk.teacher.rest.url}")
    private String teacherGroupURL;

    @Value("${stormpath.sdk.smarterap.directory.rest.url}")
    private String directoryURL;

    @Override
    public String getDirectoryURL() {
        return directoryURL;
    }

    @Override
    public String getAdministratorGroupURL() {
        return administratorGroupURL;
    }

    @Override
    public Application getApplication() {
        if (application == null) {
            application = getDataStore().getResource(getSmarterAPApplicationURL(), Application.class);
        }
        return application;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public String getSmarterAPApplicationURL() {
        return smarterApplicationURL;
    }

    @Override
    public DataStore getDataStore() {
        if (dataStore == null) {
            dataStore = getClient().getDataStore();
        }
        return dataStore;
    }

    @Override
    public Tenant getTenant() {
        if (tenant == null) {
            tenant = getClient().getCurrentTenant();
        }
        return tenant;
    }

    @Override
    public Directory getDirectory() {
        if (directory == null) {
            directory = getDataStore().getResource(getDirectoryURL(), Directory.class);
        }
        return directory;
    }
}

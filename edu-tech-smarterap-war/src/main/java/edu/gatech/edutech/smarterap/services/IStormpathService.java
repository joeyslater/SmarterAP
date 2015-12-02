package edu.gatech.edutech.smarterap.services;

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

import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.directory.Directory;
import com.stormpath.sdk.ds.DataStore;
import com.stormpath.sdk.tenant.Tenant;

/**
 * The Stormpath SDK can be used to interact with anything in a Stormpath tenant.
 * <p/>
 * This service provides an even simpler abstraction specific to SmarterAP's needs only.
 *
 * @author Elder Crisostomo
 * @author Scott R. Leitstein - Updated for SmarterAP
 */
public interface IStormpathService {

    /**
     * Gets the Client object that interacts with the Stormpath API.
     *
     * @return the Client object that interacts with the Stormpath API.
     */
    Client getClient();

    /**
     * Gets the Rest URL of the SmartAP application.
     *
     * @return the Rest URL of the SmartAP application.
     */
    String getSmarterAPApplicationURL();

    /**
     * Gets the Rest URL of the Administrator Group, which represents
     * the role of Administrator for an Account in the SmartAP application.
     *
     * @return the Rest URL of the Administrator Group
     */
    String getAdministratorGroupURL();

    /**
     * Gets the DataStore from the Client object.
     *
     * @return the DataStore from the Client object.
     */
    DataStore getDataStore();

    /**
     * Returns the Tenant that owns the SmartAP application.
     *
     * @return the Tenant that owns the SmartAP application.
     */
    Tenant getTenant();

    /**
     * Gets the Directory, where the SmartAP application is located, from the DataStore object.
     *
     * @return the Directory from the DataStore object.
     */
    Directory getDirectory();

    /**
     * Gets the Rest URL of the Directory where the SmartAP application is located.
     *
     * @return the Rest URL of the Directory where the SmartAP application is located.
     */
    String getDirectoryURL();

    /**
     * Gets the SmartAP Application from the DataStore object.
     *
     * @return the SmartAP Application from the DataStore object.
     */
    Application getApplication();

}

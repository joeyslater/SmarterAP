package edu.gatech.edutech.smarterap.services;

import static edu.gatech.edutech.smarterap.configs.SecurityConfig.APP_REST_URL;
import static edu.gatech.edutech.smarterap.configs.SecurityConfig.DIRECTORY_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.directory.Directory;
import com.stormpath.sdk.ds.DataStore;
import com.stormpath.sdk.tenant.Tenant;

@Service
public class StormpathService
{
	@Autowired
	private Client		stormpathClient;

	private DataStore	dataStore;

	private Directory	directory;

	private Application	application;

	private Tenant		tenant;

	public Application getApplication()
	{
		if (application == null)
		{
			application = getDataStore().getResource(APP_REST_URL, Application.class);
		}
		return application;
	}

	public Client getClient()
	{
		return stormpathClient;
	}

	public DataStore getDataStore()
	{
		if (dataStore == null)
		{
			dataStore = getClient().getDataStore();
		}
		return dataStore;
	}

	public Tenant getTenant()
	{
		if (tenant == null)
		{
			tenant = getClient().getCurrentTenant();
		}
		return tenant;
	}

	public Directory getDirectory()
	{
		if (directory == null)
		{
			directory = getDataStore().getResource(DIRECTORY_URL, Directory.class);
		}
		return directory;
	}

}

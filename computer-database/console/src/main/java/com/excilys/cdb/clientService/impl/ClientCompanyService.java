package com.excilys.cdb.clientService.impl;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.excilys.cdb.clientService.IClientCompanyService;
import com.excilys.cdb.model.Company;

@Service
public class ClientCompanyService implements IClientCompanyService {

	public static final String SERVER_URI = "http://localhost:8080/web-service/company";
	public static final String MAP_GETCOMPANY = "/find";
	public static final String MAP_GETALL = "/getAll";
	public static final String MAP_DELETE = "/delete";

	public Company getCompany(long id) {
		Client client = ClientBuilder.newClient();
		Company response = client.target(SERVER_URI + MAP_GETCOMPANY)
				.queryParam("id", id).request(MediaType.APPLICATION_JSON_TYPE)
				.get(Company.class);
		return response;
	}

	public List<Company> getAll() {
		Client client = ClientBuilder.newClient();
		Company[] response = client.target(SERVER_URI + MAP_GETALL)
				.request(MediaType.APPLICATION_JSON_TYPE).get(Company[].class);
		return Arrays.asList(response);
	}

	public void delete(long id) {
		Client client = ClientBuilder.newClient();
		client.target(SERVER_URI + MAP_DELETE).queryParam("id", id).request()
				.delete();
	}

}

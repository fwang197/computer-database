package com.excilys.cdb.clientService.impl;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.excilys.cdb.clientService.IClientComputerService;
import com.excilys.cdb.mapper.ComputerDTOMapper;
import com.excilys.cdb.mapper.ComputerDto;
import com.excilys.cdb.model.Computer;

@Service
public class ClientComputerService implements IClientComputerService {

	public static final String SERVER_URI = "http://localhost:8080/web-service/computer";
	public static final String MAP_GETCOMPUTER = "/find";
	public static final String MAP_GETALL = "/getAll";
	public static final String MAP_CREATE = "/create";
	public static final String MAP_UPDATE = "/update";
	public static final String MAP_DELETE = "/delete";

	public List<Computer> getAll() {
		Client client = ClientBuilder.newClient();
		ComputerDto[] response = client.target(SERVER_URI + MAP_GETALL)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.get(ComputerDto[].class);

		return ComputerDTOMapper.toListComputer(Arrays.asList(response));
	}

	public Computer getComputer(long id) {
		Client client = ClientBuilder.newClient();
		ComputerDto response = client.target(SERVER_URI + MAP_GETCOMPUTER)
				.queryParam("id", id).request(MediaType.APPLICATION_JSON_TYPE)
				.get(ComputerDto.class);
		return ComputerDTOMapper.toComputer(response);
	}

	public void create(Computer computer) {
		Client client = ClientBuilder.newClient();
		client.target(SERVER_URI + MAP_CREATE)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(ComputerDTOMapper.toComputerDto(computer),
						MediaType.APPLICATION_JSON));
	}

	public void update(Computer computer) {
		Client client = ClientBuilder.newClient();
		client.target(SERVER_URI + MAP_UPDATE)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(ComputerDTOMapper.toComputerDto(computer),
						MediaType.APPLICATION_JSON));
	}

	public void delete(long id) {
		Client client = ClientBuilder.newClient();
		client.target(SERVER_URI + MAP_DELETE).queryParam("id", id).request()
				.delete();
	}

}

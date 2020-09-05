package com.masterdevskills.cha3.ext4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//TODO
// Hints: use BlockingQueue
// Use delegate pattern, create a new ProxyConnection

public class JdbcConnectionPool {
	private final JdbcConfig config;
	private BlockingQueue<ProxyConnection> connections;

	public JdbcConnectionPool(JdbcConfig config) {
		this.config = config;
		initializeConnectionPool();
	}

	//hints: initialize connection pool with min poolSize
	private void initializeConnectionPool() {
		connections = new LinkedBlockingQueue<>();
		for (int i = 0; i < config.getMinPoolSize(); i++) {
			connections.add(createConnection());
		}
	}

	private ProxyConnection createConnection() {
		try {
			Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
			return new ProxyConnection(connection, this);
		} catch (SQLException throwables) {
			//throw new new Unche
		}

		return null;
	}

	public int getTotalConnection() {
		return connections.size();
	}

	public Connection getConnectionFromPool() {
		throw new UnsupportedOperationException("not yet implemented");
	}

	public void returnConnectionToPool(ProxyConnection connection) {
		connections.add(connection);
	}
}

package edu.uark.registerapp.commands.activeUsers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;

@Service // Define service
public class ActiveUserDeleteCommand implements VoidCommandInterface {
	// Variable and Property
	private String sessionKey;
	@Autowired
	private ActiveUserRepository activeUserRepository;

	// Transaction to delete the activeuser record
	@Transactional
	@Override
	public void execute() {
		final Optional<ActiveUserEntity> activeUserEntity =
			this.activeUserRepository.findBySessionKey(this.sessionKey);
		// Delete active user from repo if it is present
		if (activeUserEntity.isPresent()) {
			this.activeUserRepository.delete(activeUserEntity.get());
		}
	}

	// Session Key getter and setter functions
	public String getSessionKey() {
		return this.sessionKey;
	}
	public ActiveUserDeleteCommand setSessionKey(final String sessionKey) {
		this.sessionKey = sessionKey;
		return this;
	}
}

package edu.uark.registerapp.commands.activeUsers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.UnauthorizedException;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;

@Service // Define service
public class ValidateActiveUserCommand implements ResultCommandInterface<ActiveUserEntity> {
	// Variable and Property
	private String sessionKey;
	@Autowired
	private ActiveUserRepository activeUserRepository;

	@Override
	public ActiveUserEntity execute() {
		final Optional<ActiveUserEntity> activeUserEntity =
			this.activeUserRepository.findBySessionKey(this.sessionKey);
		// Validation of incoming employee request object 
		if (!activeUserEntity.isPresent()) {
			throw new UnauthorizedException();
		}
		return activeUserEntity.get();
	}

	// Session Key getter and setter functions
	public String getSessionKey() {
		return this.sessionKey;
	}
	public ValidateActiveUserCommand setSessionKey(final String sessionKey) {
		this.sessionKey = sessionKey;
		return this;
	}
}

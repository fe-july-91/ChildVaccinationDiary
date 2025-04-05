package team.project.exception;

import jakarta.persistence.EntityExistsException;

public class EntityExistsCustomException extends EntityExistsException {
    public EntityExistsCustomException(String message) {
        super(message);
    }
}

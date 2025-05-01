package com.javaweb.hospital.exception.handler;

import com.hrm.leavemanagement.service.exception.*;
import com.hrm.leavemanagement.utils.naming.NamingConvention;
import org.hibernate.TransactionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterErrors;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Locale;
import java.util.Optional;

@ApplicationScope
@Component
public class AppExceptionHandler implements IAppExceptionHandler {

  private Optional<String> getFieldNameFrom(String constraintName) {
    if (constraintName == null) {
      return Optional.of("");
    }
    if (constraintName.startsWith("pk")) {
      return Optional.of("id");
    }

    String[] keywords = constraintName.split("_");

    if (keywords[0].equals(DBConstraintKeyword.PRIMARY_KEY.toString())) {
      return Optional.of("id");
    }
    if (keywords[0].equals(DBConstraintKeyword.UNIQUE_KEY.toString())) {
      return Optional.of(keywords[keywords.length - 1]);
    }

    return Optional.of("");
  }

  private ApplicationException convertHibernateConstraintViolationException(org.hibernate.exception.ConstraintViolationException ex) {

    String sqlState = ex.getSQLState().toLowerCase(Locale.ROOT);
    String columnName = this.getFieldNameFrom(ex.getConstraintName()).get();
    if (sqlState.equals(SQLServerState.UNIQUE_KEY_VIOLATION)) {
      return DuplicateFieldException.of(columnName);
    }
    return InternalServerException.of(ex.getMessage());
  }


  private ApplicationException convertSpringValidationException(HandlerMethodValidationException ex) {
    ex.getBeanResults().getFirst().getAllErrors().getFirst().getArguments();
    String field = Optional.ofNullable(ex.getBeanResults().getFirst())
      .map(ParameterErrors::getFieldError).map(FieldError::getField)
      .orElse("undefined");
    String message = Optional.ofNullable(ex.getBeanResults().getFirst())
      .map(ParameterErrors::getFieldError).map(FieldError::getDefaultMessage)
      .orElse("undefined");
    return InvalidFieldException.of(message, NamingConvention.toSnakeCase(field));
  }

  private ApplicationException convertHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    String message = ex.getMessage().substring(0, ex.getMessage().indexOf(':'));
    return InvalidRequestException.of(message);
  }

  private ApplicationException convertTransactionException(TransactionException ex) {
    String message = ex.getMessage();
    if (message.contains("Transaction timeout expired")) {
      return TimeoutException.of("Transaction");
    }
    return InternalServerException.of(message);
  }

  @Override
  public ApplicationException convert(Throwable e) {
    return switch (e) {
      case DataIntegrityViolationException ex -> switch (ex.getCause()) {
        case org.hibernate.exception.ConstraintViolationException exc -> this.convertHibernateConstraintViolationException(exc);
        default -> InternalServerException.of(e.getMessage());
      };

      case JpaSystemException ex -> switch (ex.getCause()) {
        case TransactionException exc -> this.convertTransactionException(exc);
        default -> InternalServerException.of(ex.getMessage());
      };

      case org.hibernate.exception.ConstraintViolationException ex ->
        this.convertHibernateConstraintViolationException(ex);

      case org.hibernate.NonUniqueObjectException ex ->
        DuplicateFieldException.of(ex.getEntityName(), "id");

      case HandlerMethodValidationException ex ->
        this.convertSpringValidationException(ex);

      case HttpMessageNotReadableException ex ->
        this.convertHttpMessageNotReadableException(ex);

      case ApplicationException ex -> ex;

      default -> InternalServerException.of(e.getMessage());
    };
  }
}

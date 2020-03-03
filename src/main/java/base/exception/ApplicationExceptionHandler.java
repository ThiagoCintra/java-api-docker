package base.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> defaultException(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, new Erro(String.format("Erro desconhecido: %s", ex.getMessage())),
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		return handleExceptionInternal(ex, new Erro(String.format("Recurso não localizado: %s", ex.getMessage())),
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	public static class Erro {
		private String mensagem;

		public Erro(String mensagem) {
			super();
			this.mensagem = mensagem;
		}

		public String getMensagem() {
			return mensagem;
		}
	}
}

package registrosSalida;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import constantes.ConstantesRutas;

public class LogJuego {
	public static void crearLog() {

		Path rutaDirectorio = Paths.get(ConstantesRutas.DIRECTORIO_SALIDA); // Crea la ruta donde se va a ubicar
																			// dentro del proyecto.

		try {
			if (!Files.exists(rutaDirectorio)) {
				Files.createDirectories(rutaDirectorio); // Crea el directorio si no existe

			}

			Path rutaFicheroLog = Paths.get(ConstantesRutas.ARCHIVO_LOG);
			if (!Files.exists(rutaFicheroLog)) { // Crea el archivo salida.log si se ha creado correctamente el
													// directorio.
				Files.createFile(rutaFicheroLog);

			}

		} catch (Exception errorDirectorio) {
			System.out.println("No se ha podido crear el directorio.");
			System.out.println("Ruta no valida o el directorio ya existe"); // Mensajes de error en el caso de no
																			// haberse podido crear correctamente.
			System.out.println(errorDirectorio);
		}

	}

	public static void salidaAcciones(String accion) {

		try {
			String mensaje = getDiaDeHoy() + ":" + accion;
			Path archivoSalidaLog = Paths.get(ConstantesRutas.ARCHIVO_LOG);

			if (!Files.exists(archivoSalidaLog) || siDiaDistinto(archivoSalidaLog)) {
				cambiarLog(archivoSalidaLog);
				Files.createDirectories(Paths.get(ConstantesRutas.DIRECTORIO_SALIDA));
			}

			Files.writeString(archivoSalidaLog, mensaje + System.lineSeparator(), StandardOpenOption.APPEND,
					StandardOpenOption.CREATE);

		} catch (IOException e) {
			System.out.println("Error al guardar informacion en el archivo salida.log");
		}

	}

	private static boolean siDiaDistinto(Path archivoSalidaLog) {
		try {
			LocalDateTime ultimaModificacion = LocalDateTime
					.ofInstant(Files.getLastModifiedTime(archivoSalidaLog).toInstant(), ZoneId.systemDefault());
			return !LocalDate.now().isEqual(ultimaModificacion.toLocalDate());

		} catch (Exception e) {
			System.out.println("No se ha podido obtener la fecha de la ultima modificion del archibo log");
		}
		return false;
	}

	private static void cambiarLog(Path archivoSalidaLog) {
		try {
			String sufijo = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			Path ficheroLog = Paths.get(ConstantesRutas.ARCHIVO_LOG);

			Files.move(archivoSalidaLog, ficheroLog);
		} catch (IOException e) {
			System.out.println("No se ha podido cambiar el sufijo del archivo");
		}
	}

	private static String getDiaDeHoy() {
		LocalDateTime hoy = LocalDateTime.now();
		DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return "[" + formatoDia.format(hoy) + "]";
	}

}

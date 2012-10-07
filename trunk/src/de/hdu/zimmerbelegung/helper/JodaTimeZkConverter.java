package de.hdu.zimmerbelegung.helper;

import java.util.Date;

import org.joda.time.LocalDate;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;

/**
 * This class converts JodaTime-Date to java-util-date for usage in ZK views
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 * 
 */
@SuppressWarnings("rawtypes")
public class JodaTimeZkConverter implements Converter {
	/**
	 * Convert Date to LocalDate.
	 * 
	 * @param val
	 *            date to be converted
	 * @param comp
	 *            associated component
	 * @param ctx
	 *            bind context for associate Binding and extra parameter (e.g.
	 *            format)
	 * @return the converted LocalDate
	 */
	@Override
	public Object coerceToBean(Object val, Component comp, BindContext ctx) {
		if (val == null)
			return val;
		if (val instanceof Date) {
			return new LocalDate(val);
		}
		throw new UiException("Converter expects a Date object");
	}

	/**
	 * Convert LocalDate to Date.
	 * 
	 * @param val
	 *            LocalDate to be converted
	 * @param comp
	 *            associated component
	 * @param ctx
	 *            bind context for associate Binding and extra parameter (e.g.
	 *            format)
	 * @return the converted Date
	 */
	@Override
	public Object coerceToUi(Object val, Component comp, BindContext ctx) {
		// TODO: Hier muss noch das Datum angemessen formatiert werden
		if (val == null)
			return null;
		if (val instanceof LocalDate) {
			return ((LocalDate) val).toDate();
		}
		throw new UiException("Converter expects a Date/DateTime object");
	}
}
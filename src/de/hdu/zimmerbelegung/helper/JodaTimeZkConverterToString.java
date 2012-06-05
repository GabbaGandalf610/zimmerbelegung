package de.hdu.zimmerbelegung.helper;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.zkoss.bind.BindContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;

/**
 * This class converts JodaTime-Date to string for usage in ZK views
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 * 
 */
public class JodaTimeZkConverterToString extends JodaTimeZkConverter {
	/**
	 * Convert LocalDate to a string representative.
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
		if (val == null)
			return null;
		if (val instanceof LocalDate) {
			return ((LocalDate) val).toString(DateTimeFormat.fullDate());
		}
		throw new UiException("Converter expects a Date/DateTime object");
	}

}

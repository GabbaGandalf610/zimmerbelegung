
package de.hdu.zimmerbelegung.helper;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.zkoss.bind.BindContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;

public class JodaTimeZkConverterToString extends JodaTimeZkConverter {

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

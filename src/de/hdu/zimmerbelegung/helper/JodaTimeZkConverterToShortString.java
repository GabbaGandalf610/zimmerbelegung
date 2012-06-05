package de.hdu.zimmerbelegung.helper;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.zkoss.bind.BindContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;

public class JodaTimeZkConverterToShortString extends
		JodaTimeZkConverterToString {
	@Override
	public Object coerceToUi(Object val, Component comp, BindContext ctx) {
		if (val == null)
			return null;
		if (val instanceof LocalDate) {
			return ((LocalDate) val).toString(DateTimeFormat.forPattern("E., dd.MM."));
		}
		throw new UiException("Converter expects a Date/DateTime object");
	}
}
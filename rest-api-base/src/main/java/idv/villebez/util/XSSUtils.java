package idv.villebez.util;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.safety.Whitelist;
import org.owasp.esapi.ESAPI;

public class XSSUtils {

	/**
	 * Allow Whitelist, and a tag target attribute, All other HTML (tags and
	 * attributes) will be removed.
	 * 
	 * @param value
	 * @return
	 * @see Whitelist#basic()
	 */
	public static String stripSpecificXSS(String value) {
		if (StringUtils.isBlank(value))
			return null;

		// Clean out HTML
		OutputSettings outputSettings = new OutputSettings();
		outputSettings.escapeMode(EscapeMode.xhtml);
		outputSettings.prettyPrint(false);
		value = Jsoup.clean(value, "", Whitelist.basic().addAttributes("a", "target"), outputSettings);

		return value;
	}

	/**
	 * Strips any potential XSS threats out of the value
	 * 
	 * @param value
	 * @return
	 */
	public static String stripXSS(String value) {
		if (value == null)
			return null;

		// Use the ESAPI library to avoid encoded attacks.
		value = ESAPI.encoder().canonicalize(value);

		// Avoid null characters
		value = value.replaceAll("\0", "");

		// Clean out HTML
		value = Jsoup.clean(value, Whitelist.none());

		return value;
	}
}

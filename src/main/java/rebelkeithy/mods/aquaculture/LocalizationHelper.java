/*package rebelkeithy.mods.aquaculture;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Properties;

public enum LocalizationHelper {
	INSTANCE;

	private final String LANG_DIR = "/assets/aquaculture/lang";
	private final HashMap<String, Properties> LANG_MAP = new HashMap<String, Properties>();

	private LocalizationHelper() {
		this.loadLanguage("en_US");
	}

	private void loadLanguage(String lang) {
		InputStream stream = this.getClass().getResourceAsStream(String.format("%s/%s.properties", this.LANG_DIR, lang));

		try {
			if(stream != null) {
				Reader reader = new InputStreamReader(stream);
				Properties props = new Properties();

				props.load(reader);
				this.LANG_MAP.put(lang, props);
				reader.close();
			} else {
				throw new NullPointerException("Cannot build stream for language: " + lang);
			}
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String localize(String tag) {
		return INSTANCE.translate(tag, "en_US");
	}

	public String translate(String tag, String lang) {
		if(this.LANG_MAP.containsKey(lang)) {
			Properties props = this.LANG_MAP.get(lang);

			if(props.containsKey(tag)) {
				return props.getProperty(tag);
			} else {
				throw new NullPointerException("Cannot find tag: " + tag + " in language: " + lang);
			}
		} else {
			throw new NullPointerException("Cannot find language: " + lang);
		}
	}
}*/

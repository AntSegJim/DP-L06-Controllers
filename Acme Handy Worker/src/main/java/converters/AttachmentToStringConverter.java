
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Attachment;

@Component
@Transactional
public class AttachmentToStringConverter implements Converter<Attachment, String> {

	@Override
	public String convert(final Attachment attachment) {
		String result;

		if (attachment == null)
			result = null;
		else
			result = String.valueOf(attachment.getId());
		return result;
	}

}

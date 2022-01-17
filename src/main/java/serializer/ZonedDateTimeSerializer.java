package serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class ZonedDateTimeSerializer extends StdSerializer<ZonedDateTime> {
        private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        protected ZonedDateTimeSerializer() {
            this(null);
        }

        protected ZonedDateTimeSerializer(Class<ZonedDateTime> t) {
            super(t);
        }


        @Override
        public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeString(DateTimeFormatter.ofPattern(SIMPLE_DATE_FORMAT.toPattern()).format(value));

        }
    }


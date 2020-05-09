package me.whiteship.demoinflearnrestapi.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

@JsonComponent // objectmapper는 이제 이 errorSerializer를 사용해서 매핑해줌

public class ErrorsSerializer extends JsonSerializer<Errors> {
    @Override
    public void serialize(Errors errors, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        errors.getFieldErrors().forEach(e->{
            try {
                gen.writeStartObject();
                gen.writeStringField("field", e.getField());
                gen.writeStringField("field", e.getObjectName());
                gen.writeStringField("code", e.getCode());
                gen.writeStringField("defaultMessage", e.getDefaultMessage());
                gen.writeEndObject();
                Object rejectedValue = e.getRejectedValue();
                if (rejectedValue != null){
//                    gen.writeStringField("rejectedValue", rejectedValue.toString());
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        errors.getGlobalErrors().forEach(e->{

            try {
                gen.writeStartObject();
                gen.writeStringField("field", e.getObjectName());
                gen.writeStringField("code", e.getCode());
                gen.writeStringField("defaultMessage", e.getDefaultMessage());
                gen.writeEndObject();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        gen.writeEndArray();
    }
}

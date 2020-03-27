package com.splitwise.modal.validate;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.stereotype.Component;

import com.splitwise.exception.CustomException;
import com.splitwise.modal.common.Persistable;
import com.splitwise.modal.common.ReflectionCommon;

@Component
public class ObjectValidator extends ReflectionCommon {

	// validate primary key object.
	public <T extends Persistable> void validatePkObject(T t, Class<T> entityClazz) throws CustomException {

		if (t == null) {
			throw new CustomException("Object can not be null " + t);
		}
		List<Field> fields = getFields(entityClazz);
		fields.forEach(field -> {
			try {
				Object value = field.get(t);
				if (value == null && field.getName().equals(t.getPrimaryKeyField())) {
					throw new CustomException("Field " + t.getPrimaryKeyField() + " can not be empty.");
				}
			} catch (IllegalArgumentException | IllegalAccessException | CustomException e) {
				e.printStackTrace();
			}
		});
	}
}

package com.splitwise.modal.common;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

public class ReflectionCommon {

	private static final Logger LOG = Logger.getLogger(ReflectionCommon.class);

	protected <T> List<Object> getFieldValues(T t, List<Field> fields) {

		List<Object> listOfValues = new ArrayList<>();
		try {
			for (Field field : fields) {
				Object value = field.get(t);
				if (value != null) {
					listOfValues.add(value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfValues;
	}

	protected <T> String getQuery(T t, String tableName, Class<T> entityClass, List<Field> fields) {

		List<Object> listOfValues = new ArrayList<>();
		StringBuilder sb = new StringBuilder("INSERT INTO " + tableName + " (");
		try {
			for (Field field : fields) {
				Object value = field.get(t);
				if (value != null) {
					sb.append(field.getName() + ",");
					listOfValues.add(value);
				}
			}
			sb = sb.deleteCharAt(sb.length() - 1);
			sb.append(") VALUES (");
			for (int i = 1; i <= listOfValues.size(); i++) {
				sb.append("?,");
			}
			sb = sb.deleteCharAt(sb.length() - 1);
			sb.append(");");
			System.out.println(sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	protected <T extends Persistable> T getRecord(Class<T> entityClass, Map<String, Field> fieldsMap, ResultSet rs) {

		T t = null;
		try {
			t = (T) entityClass.newInstance();
			for (String keySet : fieldsMap.keySet()) {
				Object resultSetValue = rs.getObject(keySet);
				if (resultSetValue != null) {
					Field field = fieldsMap.get(keySet);
					Object convertedValue = null;
					convertedValue = getValueForType(String.valueOf(resultSetValue), field.getType().getSimpleName());
					field.set(t, convertedValue);
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return t;
	}

	protected <T extends Persistable> void getAllRecords(ResultSet rs, Map<String, Field> fieldsMap,
			List<T> queryResults, Class<T> entityClass) {

		LOG.info(":: Entered into private getAllRecords method ::");
		try {
			while (rs.next()) {
				T t = getRecord(entityClass, fieldsMap, rs);
				queryResults.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOG.info("exit from private getAllRecords method.....");
	}

	protected <T extends Persistable> List<Field> getFields(Class<T> entityClass) {

		LOG.info("-----getFields method called");
		Field[] allFields = entityClass.getDeclaredFields();
		List<Field> fields = Arrays.stream(allFields).collect(Collectors.toList());
		fields.forEach(f -> f.setAccessible(true));
		return fields;
	}

	protected <T extends Persistable> Map<String, Field> getFieldsMap(Class<T> entityClass) {

		Map<String, Field> fieldsMap = new HashMap<String, Field>();
		List<Field> fields = getFields(entityClass);
		fields.forEach(field -> {
			fieldsMap.put(field.getName(), field);
		});
		return fieldsMap;
	}

	protected <T extends Persistable> Map<String, Object> getFieldsWithValueMap(T t, Class<T> entityClass) {

		Map<String, Object> fieldsMapWithValues = new HashMap<>();
		List<Field> fields = getFields(entityClass);
		fields.forEach(field -> {
			try {
				Object value = field.get(t);
				if (value != null) {
					fieldsMapWithValues.put(field.getName(), value);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
		return fieldsMapWithValues;
	}

	protected Object getValueForType(String valueToBeSet, String type) throws ParseException {

		switch (type) {

		case "String":
			return valueToBeSet;

		case "short":
		case "Short":
			return Short.valueOf(valueToBeSet);

		case "long":
		case "Long":
			return Long.valueOf(valueToBeSet);

		case "int":
		case "Integer":
			return Integer.valueOf(valueToBeSet);

		case "double":
		case "Double":
			return Double.valueOf(valueToBeSet);

		case "Date":
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			return formatter.parse(valueToBeSet);

		case "boolean":
		case "Boolean":
			return Boolean.parseBoolean(valueToBeSet);
		default:
			throw new IllegalArgumentException("Unsupported Field type: " + type + " value: [ " + valueToBeSet + " ]");
		}
	}
}

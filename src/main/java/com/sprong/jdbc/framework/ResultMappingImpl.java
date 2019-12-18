package com.sprong.jdbc.framework;

import com.sprong.jdbc.framework.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/18
 * @description:
 */
@Slf4j
public class ResultMappingImpl implements ResultMapping {

    @Override
    public <T> T returnOne(ResultSet resultSet, Class<T> clazz) {
        if (Objects.isNull(resultSet) || Objects.isNull(clazz)) {
            return null;
        }
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (columnCount < 1) {
                return null;
            }
            if (!resultSet.next()) {
                return null;
            }
            return mappingResultToDto(resultSet, clazz);
        } catch (SQLException e) {
            log.error("error get result mapping", e);
            return null;
        } catch (IllegalAccessException | InstantiationException e) {
            log.error("", e);
            return null;
        }
    }

    @Override
    public <T> List<T> returnMany(ResultSet resultSet, Class<T> clazz) {
        if (Objects.isNull(resultSet) || Objects.isNull(clazz)) {
            return null;
        }
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (columnCount < 1) {
                return null;
            }
            List<T> results = new ArrayList<>();
            while (resultSet.next()) {
                T object = mappingResultToDto(resultSet, clazz);
                results.add(object);
            }
            return results;
        } catch (SQLException | InstantiationException e) {
            log.error("error get result mapping", e);
            return null;
        } catch (IllegalAccessException e) {
            log.error("", e);
            return null;
        }
    }

    private <T> T mappingResultToDto(ResultSet resultSet, Class<T> clazz) throws IllegalAccessException, InstantiationException, SQLException {
        T object = clazz.newInstance();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        Map<String, Field> map = Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toMap(Field::getName, Function.identity()));
        for (int i = 1; i <= columnCount; i++) {
            String columnName = Optional.ofNullable(metaData.getColumnLabel(i)).orElse(metaData.getColumnName(i));
            String columnToHump = StringUtils.lineToHump(columnName);
            if (map.containsKey(columnToHump)) {
                String type = metaData.getColumnTypeName(i);
                Field field = map.get(columnToHump);
                Object value = null;
                switch (type) {
                    case "INT":
                        value = resultSet.getInt(columnName);
                        break;
                    case "VARCHAR":
                        value = resultSet.getString(columnName);
                        break;
                }
                field.setAccessible(true);
                field.set(object, value);
            }
        }
        return object;
    }
}

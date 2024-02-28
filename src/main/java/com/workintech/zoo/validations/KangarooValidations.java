package com.workintech.zoo.validations;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.KangarooException;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class KangarooValidations {

    private static final String ID_IS_NOT_VALID = "Id can not less than 0";
    private static final String KANGAROO_NOT_EXIST = "Kangaroo with given id is not exist: ";
    private static final String KANGAROO_CREDENTIALS_NOT_VALID = "Kangaroo Credentials are not valid";

    public static void isIdValid(int id) {
        if (id < 0) {
            throw new KangarooException(ID_IS_NOT_VALID, HttpStatus.BAD_REQUEST);

        }
    }

    public static void isKangarooNotExist(Map<Integer, Kangaroo> kangarooMap, Integer id) {
        if (!kangarooMap.containsKey(id)) {
            throw new KangarooException(KANGAROO_NOT_EXIST + id, HttpStatus.NOT_FOUND);
        }
    }

    public static void isKangarooCredentialValid(Kangaroo kangaroo) {
        if (kangaroo == null ||
                kangaroo.getName() == null || kangaroo.getName().isEmpty() ||
                kangaroo.getGender() == null || kangaroo.getGender().isEmpty() ||
                kangaroo.getWeight() < 0 || kangaroo.getHeight() < 0 || kangaroo.getId() < 0) {
            throw new KangarooException(KANGAROO_CREDENTIALS_NOT_VALID, HttpStatus.BAD_REQUEST);
        }
    }
}

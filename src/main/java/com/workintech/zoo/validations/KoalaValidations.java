package com.workintech.zoo.validations;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.KoalaException;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class KoalaValidations {
    private static final String ID_IS_NOT_VALID = "Id can not less than 0";
    private static final String KOALA_NOT_EXIST = "Koala with given id is not exist: ";
    private static final String KOALA_CREDENTIALS_NOT_VALID = "Koala Credentials are not valid";

    public static void isIdValid(int id) {
        if (id < 0) {
            throw new KoalaException(ID_IS_NOT_VALID, HttpStatus.BAD_REQUEST);

        }
    }

    public static void isKoalaNotExist(Map<Integer, Koala> koalaMap, Integer id) {
        if (!koalaMap.containsKey(id)) {
            throw new KoalaException(KOALA_NOT_EXIST + id, HttpStatus.NOT_FOUND);
        }
    }

    public static void isKoalaCredentialValid(Koala koala) {
        if (koala == null ||
                koala.getName() == null || koala.getName().isEmpty() ||
                koala.getGender() == null || koala.getGender().isEmpty() ||
                koala.getWeight() < 0 || koala.getSleepHour() < 0 || koala.getId() < 0) {
            throw new KoalaException(KOALA_CREDENTIALS_NOT_VALID, HttpStatus.BAD_REQUEST);
        }
    }
}

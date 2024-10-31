package team.project.model;

import java.util.Objects;

public enum Gender {
    BOY("Хлопчик"),
    GIRL("Дівчинка");

    private final String genderName;

    Gender(String genderName) {
        this.genderName = genderName;
    }

    public String getGenderName() {
        return genderName;
    }

    public static Gender getByType(String type) {
        for (Gender item : Gender.values()) {
            if (Objects.equals(item.getGenderName().toUpperCase(), type.toUpperCase())) {
                return item;
            }
        }
        return null;
    }
}

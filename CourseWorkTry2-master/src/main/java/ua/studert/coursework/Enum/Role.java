package ua.studert.coursework.Enum;

public enum Role {
    ADMIN, USER;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}

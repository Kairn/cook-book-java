package io.esoma.cbj.crypto.slave;

enum UserRole {
    ADMIN,
    USER
}

/**
 * Class that describes a simple user profile with a very limited amount of information.
 *
 * @author Eddy Soma
 */
public class SimpleUserProfile {

    private final String email;
    private final int id;
    private final UserRole role;

    public SimpleUserProfile(String email) {
        this.email = email;
        this.id = 10;
        this.role = UserRole.USER;
    }

    public SimpleUserProfile(String email, int id, UserRole role) {
        this.email = email;
        this.id = id;
        this.role = role;
    }

    public static SimpleUserProfile parse(String data) {
        try {
            String[] profileParts = data.split("&");
            String email = profileParts[0].split("=")[1];
            int id = Integer.parseInt(profileParts[1].split("=")[1]);
            UserRole role = UserRole.valueOf(profileParts[2].split("=")[1]);

            return new SimpleUserProfile(email, id, role);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load user profile");
        }
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("email=%s&uid=%d&role=%s", email, id, role.toString());
    }

    public boolean isAdmin() {
        return this.role == UserRole.ADMIN;
    }
}

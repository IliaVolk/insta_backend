package inproject.entity;

public class InstagramAuthUser {
    private String username;
    private String bio;
    private String website;
    private String profile_picture;
    private String full_name;
    private long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstagramAuthUser)) return false;

        InstagramAuthUser that = (InstagramAuthUser) o;

        if (id != that.id) return false;
        if (bio != null ? !bio.equals(that.bio) : that.bio != null) return false;
        if (full_name != null ? !full_name.equals(that.full_name) : that.full_name != null) return false;
        if (profile_picture != null ? !profile_picture.equals(that.profile_picture) : that.profile_picture != null)
            return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (website != null ? !website.equals(that.website) : that.website != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (profile_picture != null ? profile_picture.hashCode() : 0);
        result = 31 * result + (full_name != null ? full_name.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

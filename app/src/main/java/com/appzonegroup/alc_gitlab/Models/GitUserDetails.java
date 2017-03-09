package com.appzonegroup.alc_gitlab.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zone2 on 3/9/17.
 */

public class GitUserDetails implements Parcelable {
    String name, company, blog, location, email, bio, html_url;
    boolean hireable;
    int followers, following, public_repos, public_gists;


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(html_url);
        dest.writeString(company);
        dest.writeString(blog);
        dest.writeString(location);
        dest.writeString(email);
        dest.writeString(bio);
        dest.writeByte((byte) (hireable ? 1 : 0));
        dest.writeInt(followers);
        dest.writeInt(following);
        dest.writeInt(public_repos);
        dest.writeInt(public_gists);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GitUserDetails> CREATOR = new Creator<GitUserDetails>() {
        @Override
        public GitUserDetails createFromParcel(Parcel in) {
            return new GitUserDetails(in);
        }

        @Override
        public GitUserDetails[] newArray(int size) {
            return new GitUserDetails[size];
        }
    };

    public String getName() {
        return this.name;
    }

    public GitUserDetails setName(String name) {
        this.name = name;
        return this;
    }

    public String getCompany() {
        return this.company;
    }

    public GitUserDetails setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getBlog() {
        return this.blog;
    }

    public GitUserDetails setBlog(String blog) {
        this.blog = blog;
        return this;
    }

    public String getLocation() {
        return this.location;
    }

    public GitUserDetails setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public GitUserDetails setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getProfileUrl() {
        return this.html_url;
    }


    public GitUserDetails setProfileUrl(String html_url) {
        this.html_url = html_url;
        return this;
    }

    public String getBio() {
        return this.bio;
    }

    public GitUserDetails setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public boolean isHireable() {
        return this.hireable;
    }

    public GitUserDetails setHireable(boolean hireable) {
        this.hireable = hireable;
        return this;
    }

    public int getFollowers() {
        return this.followers;
    }

    public GitUserDetails setFollowers(int followers) {
        this.followers = followers;
        return this;
    }

    public int getFollowing() {
        return this.following;
    }

    public GitUserDetails setFollowing(int following) {
        this.following = following;
        return this;
    }

    public int getPublic_repos() {
        return this.public_repos;
    }

    public GitUserDetails setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
        return this;
    }

    public int getPublic_gists() {
        return this.public_gists;
    }

    public GitUserDetails setPublic_gists(int public_gists) {
        this.public_gists = public_gists;
        return this;
    }

    protected GitUserDetails(Parcel in) {
        name = in.readString();
        html_url = in.readString();
        company = in.readString();
        blog = in.readString();
        location = in.readString();
        email = in.readString();
        bio = in.readString();
        hireable = in.readByte() != 0;
        followers = in.readInt();
        following = in.readInt();
        public_repos = in.readInt();
        public_gists = in.readInt();
    }


}

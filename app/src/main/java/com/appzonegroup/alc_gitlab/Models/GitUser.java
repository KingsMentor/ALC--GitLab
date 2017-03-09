package com.appzonegroup.alc_gitlab.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zone2 on 3/5/17.
 */


public class GitUser implements Parcelable {
    private String login, avatar_url, gravatar_id, url, html_url, followers_url, following_url, gists_url, starred_url, subscriptions_url,
            organizations_url, repos_url, events_url, received_events_url, type;
    private long id;
    private boolean siteAdmin;
    private int score;
    private GitUserDetails gitUserDetails;

    protected GitUser(Parcel in) {
        login = in.readString();
        avatar_url = in.readString();
        gravatar_id = in.readString();
        url = in.readString();
        html_url = in.readString();
        followers_url = in.readString();
        following_url = in.readString();
        gists_url = in.readString();
        starred_url = in.readString();
        subscriptions_url = in.readString();
        organizations_url = in.readString();
        repos_url = in.readString();
        events_url = in.readString();
        received_events_url = in.readString();
        type = in.readString();
        id = in.readLong();
        siteAdmin = in.readByte() != 0;
        score = in.readInt();
        gitUserDetails = in.readParcelable(GitUserDetails.class.getClassLoader());
    }

    public static final Creator<GitUser> CREATOR = new Creator<GitUser>() {
        @Override
        public GitUser createFromParcel(Parcel in) {
            return new GitUser(in);
        }

        @Override
        public GitUser[] newArray(int size) {
            return new GitUser[size];
        }
    };

    public String getLogin() {
        return this.login;
    }

    public GitUser setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getAvatarUrl() {
        return this.avatar_url;
    }

    public GitUser setAvatarUrl(String avatar_url) {
        this.avatar_url = avatar_url;
        return this;
    }

    public String getGravatarId() {
        return this.gravatar_id;
    }

    public GitUser setGravatarId(String gravatar_id) {
        this.gravatar_id = gravatar_id;
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public GitUser setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getHtmlUrl() {
        return this.html_url;
    }

    public GitUser setHtmlUrl(String html_url) {
        this.html_url = html_url;
        return this;
    }

    public String getFollowersUrl() {
        return this.followers_url;
    }

    public GitUser setFollowersUrl(String followers_url) {
        this.followers_url = followers_url;
        return this;
    }

    public String getFollowingUrl() {
        return this.following_url;
    }

    public GitUser setFollowingUrl(String following_url) {
        this.following_url = following_url;
        return this;
    }

    public String getGistsUrl() {
        return this.gists_url;
    }

    public GitUser setGistsUrl(String gists_url) {
        this.gists_url = gists_url;
        return this;
    }

    public String getStarredUrl() {
        return this.starred_url;
    }

    public GitUser setStarredUrl(String starred_url) {
        this.starred_url = starred_url;
        return this;
    }

    public String getSubscriptionsUrl() {
        return this.subscriptions_url;
    }

    public GitUser setSubscriptionsUrl(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
        return this;
    }

    public String getOrganizationsUrl() {
        return this.organizations_url;
    }

    public GitUser setOrganizationsUrl(String organizations_url) {
        this.organizations_url = organizations_url;
        return this;
    }

    public String getReposUrl() {
        return this.repos_url;
    }

    public GitUser setReposUrl(String repos_url) {
        this.repos_url = repos_url;
        return this;
    }

    public String getEventsUrl() {
        return this.events_url;
    }

    public GitUser setEventsUrl(String events_url) {
        this.events_url = events_url;
        return this;
    }

    public String getReceivedEventsUrl() {
        return this.received_events_url;
    }

    public GitUser setReceivedEventsUrl(String received_events_url) {
        this.received_events_url = received_events_url;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public GitUser setType(String type) {
        this.type = type;
        return this;
    }

    public long getId() {
        return this.id;
    }

    public GitUser setId(long id) {
        this.id = id;
        return this;
    }

    public boolean isSiteAdmin() {
        return this.siteAdmin;
    }

    public GitUser setSiteAdmin(boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
        return this;
    }

    public int getScore() {
        return this.score;
    }

    public GitUser setScore(int score) {
        this.score = score;
        return this;
    }

    public GitUserDetails getGitUserDetails() {
        return this.gitUserDetails;
    }

    public GitUser setGitUserDetails(GitUserDetails gitUser) {
        this.gitUserDetails = gitUser;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeString(avatar_url);
        parcel.writeString(gravatar_id);
        parcel.writeString(url);
        parcel.writeString(html_url);
        parcel.writeString(followers_url);
        parcel.writeString(following_url);
        parcel.writeString(gists_url);
        parcel.writeString(starred_url);
        parcel.writeString(subscriptions_url);
        parcel.writeString(organizations_url);
        parcel.writeString(repos_url);
        parcel.writeString(events_url);
        parcel.writeString(received_events_url);
        parcel.writeString(type);
        parcel.writeLong(id);
        parcel.writeByte((byte) (siteAdmin ? 1 : 0));
        parcel.writeInt(score);
        parcel.writeParcelable(gitUserDetails, i);
    }
}

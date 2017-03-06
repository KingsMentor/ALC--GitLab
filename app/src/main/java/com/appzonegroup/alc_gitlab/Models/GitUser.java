package com.appzonegroup.alc_gitlab.Models;

/**
 * Created by zone2 on 3/5/17.
 */


public class GitUser {
    private String login, avatar_url, gravatar_id, url, html_url, followers_url, following_url, gists_url, starred_url, subscriptions_url,
            organizations_url, repos_url, events_url, received_events_url, type;
    private long id;
    private boolean siteAdmin;
    private int score;

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
}

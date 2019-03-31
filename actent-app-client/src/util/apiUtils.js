import { API_BASE_URL, ACCESS_TOKEN, AUTHORIZATION_HEADER, BEARER } from '../constants/apiConstants';
import axios from 'axios';

export function testConfiguration() {
    axios.defaults.baseURL = API_BASE_URL;
    axios.defaults.headers.common['Content-Type'] = 'application/json';

    saveAuthorizationToken(
        'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTUzNjI1NzcxLCJleHAiOjE1NTQyMjY5NzF9._Wx-dWTON7aORB30XfPvUvaNvu9kyUS_zfrp3W9gZvyaD1GFLLkMTBlINnNfB7TIaYum_GHhcarZX-i4D3XfCg',
    );
    setAuthorizationHeader();
}

export function confidureAxios() {
    axios.defaults.baseURL = API_BASE_URL;
    axios.defaults.headers.common['Content-Type'] = 'application/json';
}

export function saveAuthorizationToken(accessToken) {
    localStorage.setItem(ACCESS_TOKEN, accessToken);
}

export function removeAuthorizationToken() {
    localStorage.removeItem(ACCESS_TOKEN);
}

export function setAuthorizationHeader() {
    if (!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject('No access token set.');
    }

    axios.defaults.headers.common[AUTHORIZATION_HEADER] = `${BEARER} ${localStorage.getItem(ACCESS_TOKEN)}`;
}

export function removeAuthorizationHeader() {
    delete axios.defaults.headers.common['Authorization'];
}

export const addReviewToUser = async (review, userId) => {
    if (!axios.defaults.headers.common[AUTHORIZATION_HEADER]) {
        return Promise.reject('No access token set.');
    }

    if (!review.text || !review.score || !userId) {
        return Promise.reject('Bad review parameter and/or userId.');
    }

    return axios.put(`/users/${userId}/reviews`, {
        score: review.score,
        text: review.text,
    });
};

export const getCurrentUser = async _ => {
    if (!axios.defaults.headers.common[AUTHORIZATION_HEADER]) {
        return Promise.reject('No access token set.');
    }

    return axios.get('/users/current');
};

export const getAllUserReviews = async userId => {
    return axios.get(`/users/${userId}/reviews`);
};

export const getUserById = async userId => {
    return axios.get(`/users/${userId}`);
};

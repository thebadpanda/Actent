import {
    API_BASE_URL,
    ACCESS_TOKEN,
    AUTHORIZATION_HEADER,
    BEARER,
    API_USERS_URL,
    API_REVIEWS_URL,
} from '../constants/apiConstants';
import axios from 'axios';

export const testConfiguration = _ => {
    axios.defaults.baseURL = API_BASE_URL;
    axios.defaults.headers.common['Content-Type'] = 'application/json';

    saveAuthorizationToken(
        'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTUzNjI1NzcxLCJleHAiOjE1NTQyMjY5NzF9._Wx-dWTON7aORB30XfPvUvaNvu9kyUS_zfrp3W9gZvyaD1GFLLkMTBlINnNfB7TIaYum_GHhcarZX-i4D3XfCg',
    );
    setAuthorizationHeader();
};

export const confidureAxios = _ => {
    axios.defaults.baseURL = API_BASE_URL;
    axios.defaults.headers.common['Content-Type'] = 'application/json';
};

export const saveAuthorizationToken = accessToken => localStorage.setItem(ACCESS_TOKEN, accessToken);

export const removeAuthorizationToken = _ => localStorage.removeItem(ACCESS_TOKEN);

export const setAuthorizationHeader = _ =>
    localStorage.getItem(ACCESS_TOKEN)
        ? (axios.defaults.headers.common[AUTHORIZATION_HEADER] = `${BEARER} ${localStorage.getItem(ACCESS_TOKEN)}`)
        : Promise.reject('No access token set.');

export const removeAuthorizationHeader = _ => delete axios.defaults.headers.common[AUTHORIZATION_HEADER];

export const addReviewToUser = async (review, userId) => {
    if (!axios.defaults.headers.common[AUTHORIZATION_HEADER]) {
        return Promise.reject('No access token set.');
    }

    if (!review.text || !review.score || !userId) {
        return Promise.reject('Bad review parameter and/or userId.');
    }

    return axios.put(`${API_USERS_URL}/${userId}${API_REVIEWS_URL}`, {
        score: review.score,
        text: review.text,
    });
};

export const getCurrentUser = async _ =>
    checkTokenHeader() ? axios.get(`${API_USERS_URL}/current`) : Promise.reject('No access token set.');

export const getAllUserReviews = async userId =>
    checkTokenHeader()
        ? axios.get(`${API_USERS_URL}/${userId}${API_REVIEWS_URL}`)
        : Promise.reject('No access token set.');

export const getUserById = async userId =>
    checkTokenHeader() ? axios.get(`${API_USERS_URL}/${userId}`) : Promise.reject('No access token set.');

export const checkTokenHeader = _ => (axios.defaults.headers.common[AUTHORIZATION_HEADER] ? true : false);

import { API_BASE_URL } from '../constants/constants.js';

const request = async (options) => {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    const defaults = { headers: headers };
    options = Object.assign({}, defaults, options);


    const response = await fetch(options.url, options);
    const json = await response.json();

    if (!response.ok) {
        return Promise.reject(json);
    }

    return Promise.resolve(json);
}

export const signup = (signupRequest) => {
    return request({
        url: API_BASE_URL + "/auth/signup",
        method: 'POST',
        body: JSON.stringify(signupRequest)
    });
}